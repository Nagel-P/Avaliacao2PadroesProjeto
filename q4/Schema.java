/**
 * Schema: valida o XML contra XSD (dummy)
 * - Timeout de 8s como exemplo
 */
public class Schema extends Base {

    @Override
    public Resultado validar(Documento doc) {
        // Dummy: considera schema inválido se xml contém "<schema-err>"
        if (doc.getXml().contains("<schema-err>")) {
            return Resultado.falha("Schema inválido");
        }
        return Resultado.ok("Schema ok");
    }

    @Override
    public int getTimeoutSegundos() {
        return 8;
    }
}
