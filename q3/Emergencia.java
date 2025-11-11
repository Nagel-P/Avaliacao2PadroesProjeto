/**
 * Estado EMERGENCIA
 *
 * - Só pode ser ativado após ALERTA_VERMELHO já garantido pela regra de
 * transição
 * - Estado final que não permite mais transições
 */
public class Emergencia implements EstadoUsina {

    @Override
    public void verificarTransicao(Usina u) {
        System.out.println("EMERGÊNCIA ATIVA! Nenhuma transição permitida.");
    }

    @Override
    public String getNome() {
        return "EMERGENCIA";
    }
}
