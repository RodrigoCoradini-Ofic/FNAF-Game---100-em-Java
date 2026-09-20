// Importando Bibliotecas
package game;
import animatronics.Animatronics;
import map.Camera;
import systems.Difficulty;
import systems.Relogio;
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

    // Construtor do game
    public Game(int idNoite) {
        this.idNoite = idNoite;
        this.jogadorMorto = false;
        this.world = new World();
        this.dificuldade = Difficulty.getMultiplicador(idNoite);
        this.relogio = new Relogio();
        this.gameScheduler = new GameScheduler(relogio, world.animatronics, idNoite);
        this.gameScheduler.iniciar();
        this.iniciarInterfaceJogador();
    }

    // Iniciando a Interface do Jogador...
    public void iniciarInterfaceJogador() {
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
            String menu = """
                    ================================
                    |        Sala Segurança        |
                    ================================
                    | Noite %d        Energia: 78  |
                    | Horas 0%d:00
                    |
                    | - [1] Abrir Porta Direita
                    | - [2] Fechar Porta Direita
                    |
                    | - [3] Abrir Porta Esquerda
                    | - [4] Fechar Porta Esquerda
                    |
                    | - [5] Ver Câmeras
                    """.formatted(idNoite, relogio.getHora());
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
                world.doors.get(0).abrirPorta();
            }
            if (resposta.equals("4")) {
                world.doors.get(0).fecharPorta();
            }
            if (resposta.equals("5")) {
                this.camera = new Camera(world.rooms, world.animatronics, this);
                return;
            }
            // Se escrever errado, reinicia interface
            // Verificando se o Jogador Morreu
            this.jogadorMorto = gameScheduler.matouJogador;
            if (jogadorMorto) {
                this.gameScheduler.parar();
                this.matarJogador(gameScheduler.animatronicQueMatou);
                break;
            }
        }
    }

    // Matar o Jogador
    public void matarJogador(Animatronics animatronic) {
        this.jogadorMorto = true;

        System.out.println("""
                ================================
                          Você Morreu
                ================================
                %s
                """.formatted(animatronic));
    }

    // GETTERs
    public boolean isJogadorMorto() {
        return jogadorMorto;
    }
}