// Importando Bibliotecas
package systems;

public class Difficulty {
    // Constantes
    private static final double DIFICULDADE_NOITE_1 = 0.8;
    private static final double DIFICULDADE_NOITE_2 = 1.0;
    private static final double DIFICULDADE_NOITE_3 = 1.2;
    private static final double DIFICULDADE_NOITE_4 = 1.5;
    private static final double DIFICULDADE_NOITE_5 = 2.0;

    // MUltiplicador de Dificuldade
    public static double getMultiplicador(int noite) {
        // Caso (Noite1) -> Retorne (Dificuldade)
        return switch (noite) {
            case 1 -> DIFICULDADE_NOITE_1;
            case 2 -> DIFICULDADE_NOITE_2;
            case 3 -> DIFICULDADE_NOITE_3;
            case 4 -> DIFICULDADE_NOITE_4;
            case 5 -> DIFICULDADE_NOITE_5;
            default -> 1.0;
        };
    }
}
