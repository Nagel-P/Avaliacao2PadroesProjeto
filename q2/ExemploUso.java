// Arquivo: ExemploUso.java
/**
 * Classe de demonstração do adaptar implementado
 *
 * Mostra como o sistema moderno pode chamar o legado sem saber detalhes dele
 * Mostra como a resposta do legado é convertida de volta
 */
public class ExemploUso {

    public static void main(String[] args) {
        SistemaLegado sistemaAntigo = new SistemaLegado();
        Transacao processador = new AdaptarLegado(sistemaAntigo);

        System.out.println("== Enviando transação moderna para o legado ==");
        processador.autorizar("1234-5678-9999-0000", 250.75, "BRL");
    }
}
