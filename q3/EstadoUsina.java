/**
 * Interface EstadoUsina
 *
 * - Define o comportamento comum de todos os estados
 * - Cada estado implementa sua própria lógica de transição
 *
 */
public interface EstadoUsina {
    void verificarTransicao(Usina u);

    String getNome();
}
