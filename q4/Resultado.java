//Resultado simples do validador

public class Resultado {
    private final boolean sucesso;
    private final String mensagem;

    public Resultado(boolean sucesso, String mensagem) {
        this.sucesso = sucesso;
        this.mensagem = mensagem;
    }

    public boolean isSucesso() {
        return sucesso;
    }

    public String getMensagem() {
        return mensagem;
    }

    public static Resultado ok(String msg) {
        return new Resultado(true, msg);
    }

    public static Resultado falha(String msg) {
        return new Resultado(false, msg);
    }
}
