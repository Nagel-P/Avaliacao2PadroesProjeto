/**
 * Classe de apoio para encadear validadores.
 * - next: próximo se OK
 * - nextOnFailure: próximo se falhar (permite pular)
 */
public abstract class Base implements Validador {
    protected Base proximo;
    protected Base proximoSeFalhar;

    public Base comProximo(Base p) {
        this.proximo = p;
        return this;
    }

    public Base comProximoSeFalhar(Base p) {
        this.proximoSeFalhar = p;
        return this;
    }

    public Base getProximo() {
        return proximo;
    }

    public Base getProximoSeFalhar() {
        return proximoSeFalhar;
    }
}
