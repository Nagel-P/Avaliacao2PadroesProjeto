import java.util.HashMap;

/**
 * Simula o sistema bancário legado
 *
 * - Possui interface antiga e incompatível com o novo padrão
 * - Utiliza HashMap como entrada e codificação numérica de moeda
 */
public class SistemaLegado {

    /**
     * Processa transação no formato legado
     * Campos esperados:
     * - numeroCartao (String)
     * - valor (Double)
     * - codigoMoeda (Integer)
     * - canalOrigem (String) → campo obrigatório inexistente no sistema novo
     */
    public boolean processarTransacao(HashMap<String, Object> parametros) {
        // Simula uma validação básica
        if (!parametros.containsKey("numeroCartao") ||
                !parametros.containsKey("valor") ||
                !parametros.containsKey("codigoMoeda") ||
                !parametros.containsKey("canalOrigem")) {
            System.out.println("Erro: faltam campos obrigatórios no formato legado!");
            return false;
        }

        System.out.println("Transação processada no legado: " + parametros);
        return true; // Dummy (aceita tudo)
    }

    /**
     * Simula resposta do legado (formato antigo)
     */
    public HashMap<String, Object> obterRespostaLegado(boolean sucesso) {
        HashMap<String, Object> resposta = new HashMap<>();
        resposta.put("status", sucesso ? "APROVADA" : "NEGADA");
        resposta.put("codigo", sucesso ? 200 : 403);
        return resposta;
    }
}
