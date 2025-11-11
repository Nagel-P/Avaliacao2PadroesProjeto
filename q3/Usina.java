/**
 * Classe principal Usina
 *
 * - Representa o contexto do padrão State
 * - Armazena o estado atual e dados de operação
 * - Permite alternar dinamicamente entre estados conforme condições
 */
public class Usina {

    private EstadoUsina estadoAtual;
    private boolean modoManutencao;

    // Parâmetros simulados de operação
    private double temperatura;
    private double pressao;
    private double radiacao;
    private boolean falhaResfriamento;
    private long sobreaqucimento;

    public Usina() {
        this.estadoAtual = new Desligada(); // inicia desligada
        this.modoManutencao = false;
    }

    public void setParametros(double temperatura, double pressao, double radiacao, boolean falhaResfriamento,
            long tempoAlta) {
        this.temperatura = temperatura;
        this.pressao = pressao;
        this.radiacao = radiacao;
        this.falhaResfriamento = falhaResfriamento;
        this.sobreaqucimento = tempoAlta;
    }

    public void verificar() {
        if (!modoManutencao) {
            estadoAtual.verificarTransicao(this);
        } else {
            System.out.println("[MODO MANUTENÇÃO] Nenhuma transição aplicada.");
        }
    }

    public void mudarEstado(EstadoUsina novo) {
        System.out.println("Transição: " + estadoAtual.getNome() + " → " + novo.getNome());
        this.estadoAtual = novo;
    }

    public void ativarManutencao(boolean ativar) {
        this.modoManutencao = ativar;
        System.out.println("Modo manutenção " + (ativar ? "ATIVADO" : "DESATIVADO"));
    }

    // Getters
    public double getTemperatura() {
        return temperatura;
    }

    public double getPressao() {
        return pressao;
    }

    public double getRadiacao() {
        return radiacao;
    }

    public boolean isFalhaResfriamento() {
        return falhaResfriamento;
    }

    public long getSobreaqucimento() {
        return sobreaqucimento;
    }

    public EstadoUsina getEstadoAtual() {
        return estadoAtual;
    }
}
