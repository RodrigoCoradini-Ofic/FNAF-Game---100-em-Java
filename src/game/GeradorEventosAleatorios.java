// Importando Bibliotecas
package game;
import java.util.Random;

public class GeradorEventosAleatorios {
    // Constantes
    public static final int CAMPO_AMOSTRAL = 150;

    // Enum dos Evendos
    public enum Eventos {
        EVENTO_1(1, "Você ouviu isso?"),
        EVENTO_2(2, "A música parou."),
        EVENTO_3(3, "Não olhe para trás."),
        EVENTO_4(4, "Algo está muito perto."),
        EVENTO_5(5, "O corredor estava vazio há um segundo."),
        EVENTO_6(6, "Por que eles estão olhando para a câmera?"),
        EVENTO_7(7, "Ele sabe que você está olhando."),
        EVENTO_8(8, "Você ouve passos ao fundo..."),
        EVENTO_9(9, "Não há ninguém no corredor."),
        EVENTO_10(10, "06:00 parece muito distante."),
        EVENTO_GOLDEN_FREDDY_1(11, "It's me"),
        EVENTO_GOLDEN_FREDDY_2(12, "Há muito mais fantasia e diversão de onde eu vim"),
        EVENTO_GOLDEN_FREDDY_3(13, "Um Freddy's Amarelo apareceu no seu Escritóio, aparentemente sem vida...");

        private final int id;
        private final String descricao;

        Eventos (int id, String descricao){
            this.id = id;
            this.descricao = descricao;
        }

        //GETTERs
        public int getId() {
            return id;
        }

        public String getDescricao() {
            return descricao;
        }
    }

    // Evendos Aleatorios
    public String gerarEventoAleatorio(){
        Random random = new Random();
        int possibilidades = random.nextInt(CAMPO_AMOSTRAL);
        return switch (possibilidades) {
            case 0 -> Eventos.EVENTO_1.getDescricao();
            case 1 -> Eventos.EVENTO_2.getDescricao();
            case 2 -> Eventos.EVENTO_3.getDescricao();
            case 3 -> Eventos.EVENTO_4.getDescricao();
            case 4 -> Eventos.EVENTO_5.getDescricao();
            case 5 -> Eventos.EVENTO_6.getDescricao();
            case 6 -> Eventos.EVENTO_7.getDescricao();
            case 7 -> Eventos.EVENTO_8.getDescricao();
            case 8 -> Eventos.EVENTO_9.getDescricao();
            case 9 -> Eventos.EVENTO_10.getDescricao();
            case 10 -> Eventos.EVENTO_GOLDEN_FREDDY_1.getDescricao();
            case 11 -> Eventos.EVENTO_GOLDEN_FREDDY_2.getDescricao();
            case 12 -> Eventos.EVENTO_GOLDEN_FREDDY_3.getDescricao();
            default -> "";
        };
    }
}
