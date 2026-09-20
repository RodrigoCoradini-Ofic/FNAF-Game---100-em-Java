// Importando Bibliotecas
package systems;

public class Score {
    // Constantes
    private static final int SCORE_NOITE_1 = 100;
    private static final int SCORE_NOITE_2 = 150;
    private static final int SCORE_NOITE_3 = 200;
    private static final int SCORE_NOITE_4 = 300;
    private static final int SCORE_NOITE_5 = 500;

    // MUltiplicador de Dificuldade
    public static int getScore(int noite) {
        // Caso (Noite1) -> Retorne (Dificuldade)
        return switch (noite) {
            case 1 -> SCORE_NOITE_1;
            case 2 -> SCORE_NOITE_2;
            case 3 -> SCORE_NOITE_3;
            case 4 -> SCORE_NOITE_4;
            case 5 -> SCORE_NOITE_5;
            default -> 100;
        };
    }
}
