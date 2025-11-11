/**
 * Implementação de Stress Testing como Teste Estresse
 *
 * - Dummy: resulta em uma mensagem demonstrando uma aplicação vinda de
 * parametrosOpcionais
 * - Demonstra como estratégias diferentes podem precisar de parâmetros
 * opcionais no Contexto
 */
public class TesteEstresse implements Risco {

    @Override
    public String calcularRisco(ContextoRisco contexto) {
        Object choque = contexto.getParametrosOpcionais().getOrDefault("choque", "queda-10%");
        int horizonte = contexto.getHorizonteDias();

        return String.format("Teste de Estresse (simulado) -> choque=%s aplicado por %d dias", choque, horizonte);
    }
}
