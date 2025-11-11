/**
 * Estado ALERTA_VERMELHO
 *
 * - Vai para EMERGENCIA se falha no resfriamento
 * - Pode voltar para AMARELO se temperatura diminuir
 */
public class AlertaVermelho implements EstadoUsina {

    @Override
    public void verificarTransicao(Usina u) {
        if (u.isFalhaResfriamento()) {
            u.mudarEstado(new Emergencia());
        } else if (u.getTemperatura() < 400) {
            u.mudarEstado(new AlertaAmarelo());
        }
    }

    @Override
    public String getNome() {
        return "ALERTA_VERMELHO";
    }
}
