
/**
 * Representa a NFe  que será validada
 * - Campos mínimos para a avaliação que podem ser estendidos
 */
import java.util.HashMap;
import java.util.Map;

public class Documento {
    private final String xml;
    private final Map<String, Object> atributos = new HashMap<>();
    // sinaliza se foi inserido no "banco" pela validação de duplicidade
    private boolean inseridoNoBanco = false;

    public Documento(String xml) {
        this.xml = xml;
    }

    public String getXml() {
        return xml;
    }

    public Map<String, Object> getAtributos() {
        return atributos;
    }

    public boolean isInseridoNoBanco() {
        return inseridoNoBanco;
    }

    public void marcarInseridoNoBanco(boolean v) {
        this.inseridoNoBanco = v;
    }
}
