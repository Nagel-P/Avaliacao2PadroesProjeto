/**
 * Estado ALERTA_AMARELO
 *
 * - Vai para ALERTA_VERMELHO se temperatura > 400°C por mais de 30 segundos
 * - Pode voltar para OPERACAO_NORMAL se a temperatura diinuir
 */
public class AlertaAmarelo implements EstadoUsina {

    @Override
    public void verificarTransicao(Usina u) {
        if (u.getTemperatura() <= 300) {
            u.mudarEstado(new OperacaoNormal());
        } else if (u.getTemperatura() > 400 && u.getSobreaqucimento() > 30) {
            u.mudarEstado(new AlertaVermelho());
        }
    }

    @Override
    public String getNome() {
        return "ALERTA_AMARELO";
    }
}
