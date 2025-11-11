import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Classe ContextoRisco imutável
 *
 * - Imutabilidade: torna seguro o compartilhamento de informações
 * - Builder: facilita a criação com muitos parâmetros "opcionais"
 *
 */
public final class ContextoRisco {
    private final List<Double> serieRetornos;
    private final int horizonteDias;
    private final double nivelConfianca;
    private final Map<String, Object> parametrosOpcionais;

    private ContextoRisco(Builder b) {
        this.serieRetornos = b.serieRetornos == null ? Collections.emptyList()
                : Collections.unmodifiableList(b.serieRetornos);
        this.horizonteDias = b.horizonteDias;
        this.nivelConfianca = b.nivelConfianca;
        this.parametrosOpcionais = b.parametrosOpcionais == null ? Collections.emptyMap()
                : Collections.unmodifiableMap(b.parametrosOpcionais);
    }

    // Getters
    public List<Double> getSerieRetornos() {
        return serieRetornos;
    }

    public int getHorizonteDias() {
        return horizonteDias;
    }

    public double getNivelConfianca() {
        return nivelConfianca;
    }

    public Map<String, Object> getParametrosOpcionais() {
        return parametrosOpcionais;
    }

    // Builder para construção fluente
    public static class Builder {
        private List<Double> serieRetornos;
        private int horizonteDias = 1;
        private double nivelConfianca = 0.95;
        private Map<String, Object> parametrosOpcionais;

        public Builder comSerieRetornos(List<Double> serieRetornos) {
            this.serieRetornos = serieRetornos;
            return this;
        }

        public Builder comHorizonteDias(int dias) {
            this.horizonteDias = dias;
            return this;
        }

        public Builder comNivelConfianca(double nivel) {
            this.nivelConfianca = nivel;
            return this;
        }

        public Builder comParametrosOpcionais(Map<String, Object> params) {
            this.parametrosOpcionais = params;
            return this;
        }

        public ContextoRisco build() {
            return new ContextoRisco(this);
        }
    }
}
