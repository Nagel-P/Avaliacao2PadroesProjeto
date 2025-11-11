/**
 * - Representa o novo padrão da empresa para autorizar transações
 * - Usa tipos modernos e assinaturas simples
 *
 * Padrão aplicado: Strategy que permite diferentes processadores
 */
public interface Transacao {
    boolean autorizar(String cartao, double valor, String moeda);
}
