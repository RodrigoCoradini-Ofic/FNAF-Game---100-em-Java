// Importando Bibliotecas
package game;
import java.util.Scanner;

// Iniciando a Classe
public class Menu {
    // Constantes
    public static final String MENU_CORPO = """
                =======================================
                          Five Night At Freddy's
                =======================================
                
                   - INICIAR Novo Game  (Digite 1)
                   - CONTINUAR De Onde Parou  (Digite 2)
                   - SAIR  (Digite 3)
                   
                ---------------------------------------
                """;

    // Iniciando o Menu
    public void menu(){
        // Iniciando o Scanner
        Scanner entrada = new Scanner(System.in);

        // Iniciando o Loop do Menu
        while(true){
            System.out.println(MENU_CORPO);
            String resultado = entrada.nextLine();

            if(resultado.equals("1")){
                System.out.println("Iniciando o Game...");
                Game game = new Game(1);
            }if(resultado.equals("2")){
                System.out.println("Iniciando o Game...");
                Game game = new Game(5);
            }if(resultado.equals("3")){
                System.out.println("Desligando o Game...");
                break;
            }
        }
    }
}

