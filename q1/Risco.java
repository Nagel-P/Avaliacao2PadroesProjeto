/**
 * Interface Risco
 *
 * Utilizando Strategy
 * Strategy permite trocar o algoritmo de cálculo de risco em tempo de execução
 * sem o cliente conhecer detalhes da implementação. Atende ao requisito
 * "intercambiável em tempo de execução"
 *
 */
public interface Risco {
    // Calcula o risco usando os parâmetros do contexto fornecido

    String calcularRisco(ContextoRisco contexto);
}
