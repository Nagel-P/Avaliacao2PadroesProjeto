/**
 * ValidadorCertificado: verifica expiração/revogação (dummy)
 * - Timeout padrão de 4s
 */
public class Certificado extends Base {

    @Override
    public Resultado validar(Documento doc) {
        // Dummy: se xml contém "cert-exp" => expirado; "cert-rev" => revogado
        if (doc.getXml().contains("cert-exp")) {
            return Resultado.falha("Certificado expirado");
        }
        if (doc.getXml().contains("cert-rev")) {
            return Resultado.falha("Certificado revogado");
        }
        return Resultado.ok("Certificado ok");
    }

    @Override
    public int getTimeoutSegundos() {
        return 4;
    }
}
