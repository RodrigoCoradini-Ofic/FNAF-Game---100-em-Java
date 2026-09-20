// Importando Bibliotecas
package game;
import animatronics.Animatronics;
import map.Camera;
import map.Door;
import systems.Difficulty;
import systems.Energy;
import systems.Relogio;
import java.util.ArrayList;
import java.util.Scanner;

// iniciando o game
public class Game {
    protected int idNoite;
    protected World world;
    protected double dificuldade;
    protected Relogio relogio;
    protected GameScheduler gameScheduler;
    protected boolean jogadorMorto;
    protected Camera camera;
    protected Energy energy;
    protected String menu;
    protected boolean jogadorSobreviveu;

    // Constantes
    protected static final int TEMPO_CADA_EXECUCAO = 5;
    protected static final int SEGUNDOS_POR_HORA = 60;
    public static final String TEXTO_NEGRITO = "\u001B[1m";
    public static final String TEXTO_RESET = "\u001B[0m";

    // Construtor do game
    public Game(int idNoite) {
        this.idNoite = idNoite;
        this.jogadorMorto = false;
        this.jogadorSobreviveu = false;
        this.world = new World();
        this.dificuldade = Difficulty.getMultiplicador(idNoite);
        this.relogio = new Relogio();
        this.energy = new Energy(SEGUNDOS_POR_HORA);
        this.gameScheduler = new GameScheduler(relogio, world.animatronics, idNoite, energy, TEMPO_CADA_EXECUCAO, world.doors);
        this.gameScheduler.iniciar();
        this.iniciarInterfaceJogador(world.doors);
    }

    // Iniciando a Interface do Jogador...
    public void iniciarInterfaceJogador(ArrayList<Door> doors) {
        Scanner entrada = new Scanner(System.in);

        // Iniciando o Loop
        while (!relogio.isNoiteTerminou() && !jogadorMorto) {
            // Verificando se o Jogador Morreu
            this.jogadorMorto = gameScheduler.matouJogador;
            if (jogadorMorto) {
                this.gameScheduler.parar();
                this.matarJogador(gameScheduler.animatronicQueMatou);
                break;
            }
            // Iniciando a Interface
            this.gerarInterfaceGame(gameScheduler.getDescricaoEventoAleatorio());
            System.out.println(menu);
            String resposta = entrada.nextLine();
            // Tratando Saídas
            if (resposta.equals("1")) {
                world.doors.get(1).abrirPorta();
            }
            if (resposta.equals("2")) {
                world.doors.get(1).fecharPorta();
            }
            if (resposta.equals("3")) {
                world.doors.getFirst().abrirPorta();
            }
            if (resposta.equals("4")) {
                world.doors.getFirst().fecharPorta();
            }
            if (resposta.equals("5")) {
                this.camera = new Camera(world.rooms, world.animatronics, this);
                this.camera.menuCameras();
                return;
            }
            if (resposta.equals("6")) {
                this.camera = new Camera(world.rooms, world.animatronics, this);
                this.camera.ligarLuz(world.rooms.get(7));
            }
            if (resposta.equals("7")) {
                this.camera = new Camera(world.rooms, world.animatronics, this);
                this.camera.ligarLuz(world.rooms.get(4));
            }
            // Se escrever errado, reinicia interface
            // Verificando se o Jogador Morreu
            this.jogadorMorto = gameScheduler.matouJogador;
            if (jogadorMorto) {
                this.gameScheduler.parar();
                this.matarJogador(gameScheduler.animatronicQueMatou);
                break;
            }
        }this.jogadorGanhou();// Se o tempo acabar e Jogador não morrer, Printa sobreviveu
    }

    // Gerar a Interface
    public void gerarInterfaceGame(String eventoAleatorio) {
        // Verificar se a porta está fechada para Printar na tela
        boolean verificaPortaEsquerda = false;
        boolean verificaPortaDireita = false;
        for (Door door : this.world.doors) {
            if (door.getIdDoor() == 1) {
                if (!door.estaFechado()){
                    verificaPortaEsquerda = true;
                }
            }
            if (door.getIdDoor() == 2) {
                if (!door.estaFechado()){
                    verificaPortaDireita = true;
                }
            }
        }
        this.menu = """
                    ============================================================================
                    |                            %s Sala Segurança %s                          |
                    ============================================================================
                    |     Noite %d                                       Energia: %d %%        |
                    |     Horas 0%d:00
                    |
                    | - [1] Abrir Porta Direita     (%s)              [6] Ligar Luz Direita
                    | - [2] Fechar Porta Direita
                    |
                    | - [3] Abrir Porta Esquerda     (%s)             [7] Ligar Luz Esquerda
                    | - [4] Fechar Porta Esquerda
                    |
                    | - [5] Ver Câmeras
                    ----------------------------------------------------------------------------
                    %s
                    """.formatted(TEXTO_NEGRITO, TEXTO_RESET, idNoite, (int) energy.getEnergia(), relogio.getHora(),
                verificaPortaDireita ? "Aberta" : "Fechada",
                verificaPortaEsquerda ? "Aberta" : "Fechada",
                eventoAleatorio);// Gerar Evento Aleatorio
    }

    // Interface de Vitória
    public void jogadorGanhou(){
        this.jogadorSobreviveu = true;
        System.out.printf("""
                ============================================================================
                |                         %s Você Sobreviveu %s                            |
                ============================================================================
                |       06:00 AM — O turno terminou. Por enquanto, você está Seguro.
                |
                ----------------------------------------------------------------------------
                """, TEXTO_NEGRITO, TEXTO_RESET);
    }

    // Matar o Jogador
    public void matarJogador(Animatronics animatronic) {
        this.jogadorMorto = true;

        System.out.printf("""
                ============================================================================
                |                         %s  Você Morreu %s                               |
                ============================================================================
                %s
                ----------------------------------------------------------------------------
                %n""", TEXTO_NEGRITO, TEXTO_RESET,animatronic);
    }

    // GETTERs
    public boolean isJogadorMorto() {
        return jogadorMorto;
    }

    public World getWorld() {
        return world;
    }

    public boolean isJogadorSobreviveu() {
        return jogadorSobreviveu;
    }
}