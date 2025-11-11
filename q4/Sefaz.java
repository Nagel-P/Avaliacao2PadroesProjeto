/**
 * Sefaz: consulta online simulada
 * - Só deve executar se anteriores passarem
 * - Timeout maior pois envolve rede: 10s
 */
public class Sefaz extends Base {

    @Override
    public Resultado validar(Documento doc) {
        // Dummy: se xml contém "sefaz-off" => falha (serviço fora)
        if (doc.getXml().contains("sefaz-off")) {
            return Resultado.falha("SEFAZ indisponível");
        }
        return Resultado.ok("SEFAZ ok");
    }

    @Override
    public int getTimeoutSegundos() {
        return 10;
    }
}
