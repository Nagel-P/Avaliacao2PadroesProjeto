// Arquivo: Desligada.java
/**
 * Estado DESLIGADA
 *
 * - Estado inicial da Usina
 * - Pode ir apenas para OPERACAO_NORMAL
 */
public class Desligada implements EstadoUsina {

    @Override
    public void verificarTransicao(Usina u) {
        if (u.getTemperatura() > 100) {
            u.mudarEstado(new OperacaoNormal());
        }
    }

    @Override
    public String getNome() {
        return "DESLIGADA";
    }
}
