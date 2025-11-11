/**
 * Banco: checa duplicidade e insere (simulado)
 * - Deve suportar rollback caso próximas validações falhem
 * - Timeout padrão de 5s
 */
public class Banco extends Base {

    // simula "banco" por memória
    private static final java.util.Set<String> tabelaNumeros = new java.util.HashSet<>();

    @Override
    public Resultado validar(Documento doc) {
        String chave = gerarChave(doc);
        if (tabelaNumeros.contains(chave)) {
            return Resultado.falha("Documento duplicado");
        }
        // insufla inserção (modificação do estado do documento)
        tabelaNumeros.add(chave);
        doc.marcarInseridoNoBanco(true);
        return Resultado.ok("Inserido no banco (simulado)");
    }

    @Override
    public void rollback(Documento doc) {
        // desfaz inserção
        String chave = gerarChave(doc);
        tabelaNumeros.remove(chave);
        doc.marcarInseridoNoBanco(false);
        System.out.println("Rollback: remoção da entrada no banco simulada");
    }

    private String gerarChave(Documento doc) {
        // dummy: usa hash do xml
        return Integer.toString(doc.getXml().hashCode());
    }

    @Override
    public int getTimeoutSegundos() {
        return 5;
    }
}
