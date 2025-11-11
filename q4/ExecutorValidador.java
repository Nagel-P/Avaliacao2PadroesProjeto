import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.*;

/**
 * ExecutorValidador:
 * - Executa a cadeia com timeout por validador
 * - Respeita next / nextOnFailure
 * - Mantém lista de validadores que modificaram doc para rollback em caso de
 * falha final
 * - Usa CircuitBreaker para interromper após N falhas
 */
public class ExecutorValidador {

    private final CircuitBreaker cb;
    private final ExecutorService executor = Executors.newCachedThreadPool();

    public ExecutorValidador(CircuitBreaker cb) {
        this.cb = cb;
    }

    public boolean executar(Base inicio, Documento doc) {
        if (cb.estaAberto()) {
            System.out.println("Execução abortada: circuit breaker aberto");
            return false;
        }

        Base atual = inicio;
        Deque<Validador> modificadoresAplicados = new ArrayDeque<>();
        boolean cadeiaSucesso = true;

        while (atual != null) {
            final Validador val = atual;
            Callable<Resultado> tarefa = () -> val.validar(doc);

            Future<Resultado> fut = executor.submit(tarefa);
            Resultado res;
            try {
                res = fut.get(val.getTimeoutSegundos(), TimeUnit.SECONDS);
            } catch (TimeoutException te) {
                fut.cancel(true);
                res = Resultado.falha("Timeout no validador: " + val.getClass().getSimpleName());
            } catch (Exception e) {
                res = Resultado.falha("Erro no validador: " + e.getMessage());
            }

            if (!res.isSucesso()) {
                System.out.println("Falha em " + val.getClass().getSimpleName() + " -> " + res.getMensagem());
                cb.registrarFalha();
                cadeiaSucesso = false;

                // se este validador modificou o documento, adiciona para rollback
                if (modificadorAplicou(val, doc)) {
                    modificadoresAplicados.push(val);
                }

                // decide próximo (pular ou terminar)
                Base proximo = atual.getProximoSeFalhar() != null ? atual.getProximoSeFalhar() : null;
                atual = proximo;
                // se não existe nextOnFailure e falha, encerrar cadeia
                if (proximo == null) {
                    break;
                } else {
                    continue;
                }
            } else {
                System.out.println(val.getClass().getSimpleName() + " -> OK");
                cb.registrarSucesso();

                // se validador modificou o documento, registrar para possível rollback futuro
                if (modificadorAplicou(val, doc)) {
                    modificadoresAplicados.push(val);
                }

                // segue normalmente apenas se houver próximo
                atual = atual.getProximo();
            }
        }

        if (!cadeiaSucesso) {
            // realiza rollback em ordem reversa dos aplicados
            while (!modificadoresAplicados.isEmpty()) {
                Validador v = modificadoresAplicados.pop();
                try {
                    v.rollback(doc);
                } catch (Exception e) {
                    System.out.println("Erro no rollback de " + v.getClass().getSimpleName() + ": " + e.getMessage());
                }
            }
            return false;
        }
        return true;
    }

    private boolean modificadorAplicou(Validador v, Documento doc) {
        if (v instanceof Banco && doc.isInseridoNoBanco())
            return true;
        return false;
    }

    public void shutdown() {
        executor.shutdownNow();
    }
}
