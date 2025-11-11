/**
 * CircuitBreaker simples:
 * - conta falhas consecutivas e tranca após limite (3)
 * - pode ser resetado externamente
 */
public class CircuitBreaker {
    private final int limite;
    private int contador = 0;
    private boolean aberto = false;

    public CircuitBreaker(int limite) {
        this.limite = limite;
    }

    public synchronized void registrarFalha() {
        if (aberto)
            return;
        contador++;
        if (contador >= limite) {
            aberto = true;
            System.out.println("Circuit breaker ABERTO");
        }
    }

    public synchronized void registrarSucesso() {
        contador = 0;
    }

    public synchronized boolean estaAberto() {
        return aberto;
    }

    public synchronized void reset() {
        aberto = false;
        contador = 0;
    }
}
