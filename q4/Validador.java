// Arquivo: Validador.java
/**
 * Interface geral dos validadores
 * - validar: executa a validação e pode modificar documento
 * - rollback: desfaz modificações se aplicável
 * - getTimeoutSegundos: timeout da validação
 */
public interface Validador {
    Resultado validar(Documento doc) throws Exception;

    default void rollback(Documento doc) {
    }

    default int getTimeoutSegundos() {
        return 5;
    } // timeout padrão
}
