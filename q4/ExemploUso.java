/**
 * Montagem da cadeia e regras:
 *
 * Ordem: Schema -> Certificado -> Banco -> Regras -> Sefaz
 *
 * Regras: 3 e 5 só executam se anteriores passarem -> isso já ocorre pois next
 * só segue com sucesso.
 * ValidadorBanco faz rollback se posteriores falharem -> Executor gerencia
 * rollback.
 * Exemplo de nextOnFailure: se Schema falhar, podemos pular diretamente para
 * fim (null).
 */
public class ExemploUso {

    public static void main(String[] args) {
        // constrói validadores
        Schema v1 = new Schema();
        Certificado v2 = new Certificado();
        Banco v4 = new Banco();
        Regras v3 = new Regras();
        Sefaz v5 = new Sefaz();

        // encadeia: v1 -> v2 -> v4 -> v3 -> v5
        v1.comProximo(v2);
        v2.comProximo(v4);
        v4.comProximo(v3);
        v3.comProximo(v5);

        // exemplo: se v1 falhar, termina (poderia pular para outro fluxo)
        v1.comProximoSeFalhar(null);

        CircuitBreaker cb = new CircuitBreaker(3);
        ExecutorValidador exec = new ExecutorValidador(cb);

        // Cenário 1: documento válido
        Documento docOk = new Documento("<xml>nf-e válida</xml>");
        System.out.println("Resultado (esperado OK): " + exec.executar(v1, docOk));
        System.out.println("Inserido no banco? " + docOk.isInseridoNoBanco());

        // Cenário 2: certificado expirado (v2 falha) -> cadeia pára, v4 não será
        // executado
        Documento docCertExp = new Documento("<xml>cert-exp</xml>");
        System.out.println("Resultado (cert expirado): " + exec.executar(v1, docCertExp));
        System.out.println("Inserido no banco? " + docCertExp.isInseridoNoBanco());

        // Cenário 3: banco insere, mas regra fiscal falha; banco deve ser rollbackado
        Documento docRegraErr = new Documento("imposto-err");
        System.out.println("Resultado (regra fiscal inválida): " + exec.executar(v1, docRegraErr));
        System.out.println("Inserido no banco? " + docRegraErr.isInseridoNoBanco());

        exec.shutdown();
    }
}
