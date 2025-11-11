
/**
 * Implementação de Factory como Fabrica Risco
 *
 * - Isola criação das estratégias em um ponto único
 * - Separa as responsabilidade de "construção" do resto do sistema
 * - Facilita a troca de implementações sem impactar clientes
 */
public class FabricaRisco {

    public enum Tipo {
        VALOR_RISCO,
        PERDA_ESPERADA,
        TESTE_ESTRESSE
    }

    public static Risco criar(Tipo tipo) {
        switch (tipo) {
            case VALOR_RISCO:
                return new ValorRisco();
            case PERDA_ESPERADA:
                return new PerdaEsperada();
            case TESTE_ESTRESSE:
                return new TesteEstresse();
            default:
                throw new IllegalArgumentException("Tipo de risco não suportado: " + tipo);
        }
    }
}
