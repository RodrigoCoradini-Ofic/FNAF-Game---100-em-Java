// Importando Bibliotecas
package game;
import save.SaveData;
import save.SaveManager;

import java.io.IOException;
import java.util.Scanner;

// Iniciando a Classe
public class Menu {
    protected SaveManager saveManager = new SaveManager();
    protected SaveData saveData;
    protected Scanner entrada = new Scanner(System.in);
    protected String menuCorpo;
    // Dados Jogador
    protected String nomeJogador;

    // Constantes
    public static final int ID_NOITE_1 = 1;
    public static final String TEXTO_NEGRITO = "\u001B[1m";
    public static final String TEXTO_RESET = "\u001B[0m";
    public static final String INTRODUCAO = """
            A Freddy Fazbear's Pizza esconde uma história que ninguém quer contar.
            Desaparecimentos, um assassino misterioso e animatrônicos que parecem \s
            carregar algo além de peças e fios.
            Agora, depois de anos, você está sozinho no escritório.
            As câmeras estão ligadas.
            As portas funcionam.
           \s
            %sMas alguma coisa já sabe que você está aqui...%s
           """.formatted(TEXTO_NEGRITO, TEXTO_RESET);

    // Iniciando o Menu
    public void menu(){
        this.iniciarInterfaceJogo();
        // Iniciando o Loop do Menu
        while(true){
            System.out.println(menuCorpo);
            String resultado = entrada.nextLine();

            if(resultado.equals("1")){
                System.out.println("Iniciando o Game...");
                Game game = new Game(ID_NOITE_1);
            }if(resultado.equals("2")){
                System.out.println("Iniciando o Game...");
                Game game = new Game(saveData.getUltimaNoiteCompletada());
            }if(resultado.equals("3")){
                saveManager.salvar(saveData);
                System.out.println("Salvando o Game...");
                System.out.println("Desligando o Game...");
                break;
            }
        }
    }

    // Iniciando Inteface Jogo
    public void iniciarInterfaceJogo(){
        this.saveData = saveManager.carregar();
           this.menuCorpo = """
            ============================================================================
            |                       %s  Five Night At Freddy's %s                      |
            ============================================================================
            |
            |     Nome: %s
            |                                               Noite Atual: %d
            |   - INICIAR Novo Game  (Digite 1)             Última Noite Completada: %d
            |   - CONTINUAR De Onde Parou  (Digite 2)
            |   - SAIR e se Arrepender (Digite 3)           Score Total: %d
            |
            ----------------------------------------------------------------------------
            %s
            """.formatted(TEXTO_NEGRITO,
                   TEXTO_RESET,
                   saveData.getNomeJogador(),
                   saveData.getNoiteAtual(),
                   saveData.getUltimaNoiteCompletada(),
                   saveData.getScore(),
                   INTRODUCAO);
    }
}

