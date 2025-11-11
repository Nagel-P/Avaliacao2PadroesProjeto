/**
 * Estado OPERACAO_NORMAL
 *
 * - Vai para ALERTA_AMARELO se temperatura > 300°C
 */
public class OperacaoNormal implements EstadoUsina {

    @Override
    public void verificarTransicao(Usina u) {
        if (u.getTemperatura() > 300) {
            u.mudarEstado(new AlertaAmarelo());
        }
    }

    @Override
    public String getNome() {
        return "OPERACAO_NORMAL";
    }
}
