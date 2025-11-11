/**
 * Implementação de Value at Risk como Valor Risco
 * 
 * - Cálculo: retorna uma mensagem apenas simulando o cálculo
 * - Single Responsibility: apenas implementa a lógica (dummy) do VaR
 */
public class ValorRisco implements Risco {

    @Override
    public String calcularRisco(ContextoRisco contexto) {
        // Dummy: usamos estatísticas básicas da série se disponível para montar uma
        // mensagem
        int tamanho = contexto.getSerieRetornos().size();
        double nivel = contexto.getNivelConfianca();
        int horizonte = contexto.getHorizonteDias();

        return String.format("VaR calculado (simulado) -> amostras=%d, nivel=%.2f, horizonte=%d dias", tamanho, nivel,
                horizonte);
    }
}
