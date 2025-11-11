/**
 *
 * - Gerencia a estratégia atual de cálculo de risco
 * - Oferecer método para trocar a estratégia durante a execução
 * - Método executarCalculo define o trabalho pra estratégia atual
 */
public class CalculadorRisco {
    private Risco estrategiaAtual;

    public CalculadorRisco(Risco inicial) {
        this.estrategiaAtual = inicial;
    }

    public void trocarRisco(Risco novaEstrategia) {
        this.estrategiaAtual = novaEstrategia;
    }

    public String executarCalculo(ContextoRisco contexto) {
        if (estrategiaAtual == null) {
            throw new IllegalStateException("Nenhuma estratégia de risco definida.");
        }
        // Delegate para a strategy
        return estrategiaAtual.calcularRisco(contexto);
    }
}
