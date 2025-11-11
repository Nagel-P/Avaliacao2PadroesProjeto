import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

// Classe de demonstração mostrando troca dinâmica de algoritmos.
public class ExemploUso {

    public static void main(String[] args) {
        // Monta contexto com alguns dados fictícios
        ContextoRisco contexto = new ContextoRisco.Builder()
                .comSerieRetornos(Arrays.asList(-0.02, 0.01, -0.05, 0.03))
                .comHorizonteDias(10)
                .comNivelConfianca(0.99)
                .comParametrosOpcionais(criarParametros())
                .build();

        // Inicializa calculador com VaR
        CalculadorRisco calculador = new CalculadorRisco(FabricaRisco.criar(FabricaRisco.Tipo.VALOR_RISCO));
        System.out.println("Usando VaR: " + calculador.executarCalculo(contexto));

        // Troca para Perda Esperada em tempo de execução
        calculador.trocarRisco(FabricaRisco.criar(FabricaRisco.Tipo.PERDA_ESPERADA));
        System.out.println("Usando Perda Esperada: " + calculador.executarCalculo(contexto));

        // Troca para Teste de Estresse em tempo de execução
        calculador.trocarRisco(FabricaRisco.criar(FabricaRisco.Tipo.TESTE_ESTRESSE));
        System.out.println("Usando Teste de Estresse: " + calculador.executarCalculo(contexto));
    }

    private static Map<String, Object> criarParametros() {
        Map<String, Object> params = new HashMap<>();
        params.put("choque", "queda-30%-setor-bancario");
        return params;
    }
}
