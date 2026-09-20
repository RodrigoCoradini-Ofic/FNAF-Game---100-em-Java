// Importando Bibliotecas
package game;
import animatronics.Animatronics;
import map.Door;
import systems.Energy;
import systems.Relogio;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class GameScheduler {
    protected ScheduledExecutorService scheduler;
    protected Relogio relogio;
    protected ArrayList<Animatronics> animatronics;
    protected int idNoite;
    protected boolean matouJogador;
    protected Animatronics animatronicQueMatou;
    protected Energy energy;
    protected int TEMPO_CADA_EXECUCAO;
    protected ArrayList<Door> doors;
    protected GeradorEventosAleatorios eventoAleatorio;
    protected String descricaoEventoAleatorio;

    // Constantes:
    protected static final int SEGUNDOS_POR_HORA = 60;
    protected static final int SEGUNDOS_CALMARIA = 20;
    protected static final int QTD_OPERACOES_SEGUNDO_PLANO = 7;
    protected static final int SEGUNDOS_PERDER_ENERGIA = 1;
    protected static final int SEGUNDOS_CADA_EVENTO_ALEATORIO = 5;

    // Construtor do GameScheduler
    public GameScheduler(Relogio relogio, ArrayList<Animatronics> animatronics, int idNoite, Energy energy, int tempoCadaExecucao, ArrayList<Door> doors) {
        this.scheduler = Executors.newScheduledThreadPool(QTD_OPERACOES_SEGUNDO_PLANO);
        this.eventoAleatorio = new GeradorEventosAleatorios();
        this.relogio = relogio;
        this.animatronics = animatronics;
        this.idNoite = idNoite;
        this.matouJogador = false;
        this.animatronicQueMatou = null;
        this.energy = energy;
        this.TEMPO_CADA_EXECUCAO = tempoCadaExecucao;
        this.doors = doors;
        this.descricaoEventoAleatorio = "";
    }

    // Iniciar as Atividades em Segundo Plano...
    public void iniciar() {

        // Relógio -- Passar Hora
        scheduler.scheduleAtFixedRate(() -> {
            relogio.passarHora();
        }, SEGUNDOS_POR_HORA, SEGUNDOS_POR_HORA, TimeUnit.SECONDS);

        // Animatronics -- Se Movimentar
        for (Animatronics animatronic : animatronics) {
            scheduler.scheduleAtFixedRate(() -> {
                try {
                    boolean matouJogador = animatronic.tentaMovimentar(idNoite, animatronic.getLocalizacao().getDoor());
                    if (matouJogador) {
                        this.matouJogador = true;
                        this.parar();
                        this.animatronicQueMatou = animatronic;
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }, SEGUNDOS_CALMARIA, TEMPO_CADA_EXECUCAO, TimeUnit.SECONDS);
        }
        // Energia -- Perder Energia
        scheduler.scheduleAtFixedRate(() -> {
            int multiplicador = 0;
            for (Door door : doors) {
                if (door.estaFechado()) {
                    multiplicador++;
                }
            }
            energy.perderEnergia(multiplicador);
        }, SEGUNDOS_PERDER_ENERGIA, SEGUNDOS_PERDER_ENERGIA, TimeUnit.SECONDS);

        // Outros Eventos -- Eventos Aleatorios
        scheduler.scheduleAtFixedRate(() -> {
            this.descricaoEventoAleatorio = eventoAleatorio.gerarEventoAleatorio();
        }, SEGUNDOS_CADA_EVENTO_ALEATORIO, SEGUNDOS_CADA_EVENTO_ALEATORIO, TimeUnit.SECONDS);
    }

    public void parar() {
        scheduler.shutdownNow();
        Thread.currentThread().interrupt();
    }

    // GETTERs
    public String getDescricaoEventoAleatorio() {
        return descricaoEventoAleatorio;
    }
}