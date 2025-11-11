/**
 * valida regras fiscais e só executa se as anteriores estiverem OK
 * - Não modifica documento
 * - Timeout padrão de 6s
 */
public class Regras extends Base {

    @Override
    public Resultado validar(Documento doc) {
        // Dummy: se xml contem "imposto-err" => falha
        if (doc.getXml().contains("imposto-err")) {
            return Resultado.falha("Cálculo de imposto incorreto");
        }
        return Resultado.ok("Regras fiscais ok");
    }

    @Override
    public int getTimeoutSegundos() {
        return 6;
    }
}
