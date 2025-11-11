/**
 * Implementação de Expected Shortfall como Perda Esperada
 * 
 * - Dummy: mensuração em texto que varia da mensagem do VaR
 * - esse eExemplo mostra intercambialidade com Var (Strategy)
 */
public class PerdaEsperada implements Risco {

    @Override
    public String calcularRisco(ContextoRisco contexto) {
        int tamanho = contexto.getSerieRetornos().size();
        double nivel = contexto.getNivelConfianca();

        return String.format("Perda Esperada (simulada) -> usa amostra=%d, quantil-aproximado=%.2f", tamanho, nivel);
    }
}
