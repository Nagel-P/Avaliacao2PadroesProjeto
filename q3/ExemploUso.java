
// Classe de teste que mostra o funcionamento do padrão State
public class ExemploUso {

    public static void main(String[] args) throws InterruptedException {
        Usina u = new Usina();

        u.setParametros(120, 10, 1, false, 0);
        u.verificar();

        u.setParametros(350, 15, 2, false, 10);
        u.verificar();

        u.setParametros(420, 15, 2, false, 40);
        u.verificar();

        u.setParametros(420, 15, 2, true, 45);
        u.verificar();

        // Ativando manutenção
        u.ativarManutencao(true);
        u.setParametros(200, 10, 1, false, 0);
        u.verificar(); // nada acontece por estar em manutenção
    }
}
