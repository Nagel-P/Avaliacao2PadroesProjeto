import java.util.HashMap;

/**
 * Implementação de Adapter como AdaptarLegado
 *
 * - Conecta a interface moderna com o sistema legado
 * - Traduz chamadas modernas para o formato antigo e vice versa
 *
 * - Resolve incompatibilidade entre interfaces
 * - Permite reutilizar código legado sem alterá-lo
 */
public class AdaptarLegado implements Transacao {

    private final SistemaLegado legado;

    public AdaptarLegado(SistemaLegado legado) {
        this.legado = legado;
    }

    @Override
    public boolean autorizar(String cartao, double valor, String moeda) {
        HashMap<String, Object> parametros = new HashMap<>();

        parametros.put("numeroCartao", cartao);
        parametros.put("valor", valor);
        parametros.put("codigoMoeda", converterMoeda(moeda));

        // Campo obrigatório que não existe no novo sistema
        parametros.put("canalOrigem", "APP_MOBILE");

        // Chamada ao legado
        boolean resultado = legado.processarTransacao(parametros);

        // Converte a resposta do legado de volta (bidirecionalidade)
        HashMap<String, Object> resposta = legado.obterRespostaLegado(resultado);
        exibirRespostaConvertida(resposta);

        return resultado;
    }

    /**
     * Converte código de moeda moderno para o código legado
     */
    private int converterMoeda(String moeda) {
        switch (moeda.toUpperCase()) {
            case "USD":
                return 1;
            case "EUR":
                return 2;
            case "BRL":
                return 3;
            default:
                return 0; // moeda não reconhecida
        }
    }

    /**
     * Exibe resposta do legado convertida para formato moderno
     */
    private void exibirRespostaConvertida(HashMap<String, Object> resposta) {
        String status = (String) resposta.get("status");
        int codigo = (int) resposta.get("codigo");

        System.out.println("Resposta convertida para formato moderno:");
        System.out.println(" - Status: " + status);
        System.out.println(" - Código: " + codigo);
    }
}
