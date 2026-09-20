// Importando Bibliotecas
package map;
import animatronics.Animatronics;
import game.Game;
import java.util.ArrayList;
import java.util.Scanner;

public class Camera {
    protected ArrayList<Room> rooms;
    protected ArrayList<Animatronics> animatronics;
    StringBuilder construtor;
    protected Game game;

    // Constantes
    public static final String TEXTO_NEGRITO = "\u001B[1m";
    public static final String TEXTO_RESET = "\u001B[0m";
    String MENU_CAMERAS_CORPO = """
            ============================================================================
            |                              %s Câmeras %s                               |
            ============================================================================
            |
            """.formatted(TEXTO_NEGRITO, TEXTO_RESET);

    // Construtor da Classe
    public Camera(ArrayList<Room> rooms, ArrayList<Animatronics> animatronics, Game game) {
        this.rooms = rooms;
        this.animatronics = animatronics;
        this.construtor = new StringBuilder();
        this.game = game;
        this.construindoMenuCameras();

    }
    // Criando a String das Cameras Disponíveis
    public void construindoMenuCameras(){
        this.construtor.append(MENU_CAMERAS_CORPO);
        for(Room room : rooms) {
            this.construtor.append("""
                     | - [%d] %s
                     """.formatted(room.getIdRoom(), room.getNome()));
        }
        this.construtor.append("""
                | \n|                         [0] Sair
                ----------------------------------------------------------------------------""");
    }

    // Menu das Câmeras
    public void menuCameras(){
        // Criando o Loop
        Scanner entrada = new Scanner(System.in);
        while (!game.isJogadorMorto()){// Verificando se o Jogador Morreu
            System.out.println(construtor.toString());
            int resultado = Integer.parseInt(entrada.nextLine());
            if(resultado == 0){
                game.iniciarInterfaceJogador(game.getWorld().getDoors());
            }
            // Criando um for para todos os Roons
            for (Room room : this.rooms) {
                boolean encontrouAnimatronic = false;
                if (resultado == room.getIdRoom()) {// Se o N° Digitado for = idRoom
                    if (resultado == rooms.get(2).getIdRoom()) {
                        Animatronics foxy = animatronics.get(3);
                        System.out.println(foxy.getDescricaoEstado()); // Se for o Foxy, manda seu Estado
                        break;
                    }
                    // Criando um for para todos os Animatronics
                    for (Animatronics animatronic : animatronics) {
                        if (animatronic.getLocalizacao().getNome().equals(room.getNome())){// Se um Animatronic estiver na mesma Room, me mostre
                            System.out.println(TEXTO_NEGRITO + animatronic.getNome() + TEXTO_RESET + " está olhando para Câmera");
                            encontrouAnimatronic = true;
                            }
                        }if (!encontrouAnimatronic){// Se nenhum Animatronic estiver na Câmera mostre sua descrição
                        System.out.println(room.getDescricao());
                    }
                }
            }
            // Se eu Escrever errado não faz nada
        }
    }

    // Metodo de Ligar Luz, verifica Animatronic na Porta
    public void ligarLuz(Room room) {
        boolean encontrouAnimatronic = false;
        for (Animatronics animatronic : animatronics) {// Verifica todos os Animatronics
            if (animatronic.getLocalizacao().getNome().equals(room.getNome())) {
                // Retorna apenas quem está na porta
                System.out.println("Flash revela ... " + animatronic.getNome());
                encontrouAnimatronic = true;
            }
        }if(!encontrouAnimatronic){
            System.out.println("O Flash mostra um Corredor Escuro e Vazio...");
        }
    }
}