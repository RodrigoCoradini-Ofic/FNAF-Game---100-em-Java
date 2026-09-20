// Importando Bibliotecas
package game;
import animatronics.Animatronics;
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

    // Constantes:
    protected static final int SEGUNDOS_POR_HORA = 10;
    protected static final int SEGUNDOS_CALMARIA = 3;

    // Construtor do GameScheduler
    public GameScheduler(Relogio relogio, ArrayList<Animatronics> animatronics, int idNoite) {
        this.scheduler = Executors.newScheduledThreadPool(5);
        this.relogio = relogio;
        this.animatronics = animatronics;
        this.idNoite = idNoite;
        this.matouJogador = false;
        this.animatronicQueMatou = null;
    }

    // Iniciar as Atividades em Segundo Plano...
    public void iniciar() {

        // Relógio -- Passar Hora
        scheduler.scheduleAtFixedRate(() -> {
            relogio.passarHora();
        }, SEGUNDOS_POR_HORA, SEGUNDOS_POR_HORA, TimeUnit.SECONDS);

        // Animatronics -- Se Movimentar
        for (Animatronics animatronic : animatronics) {
            // Lógica de Movimentação Animatronic
            double dificuldadeAnimatronic = animatronic.getAgressividadeBase();

            scheduler.scheduleAtFixedRate(() -> {
                try {
                    boolean matouJogador = animatronic.tentaMovimentar(idNoite, animatronic.getLocalizacao().getDoor());
                    if (matouJogador) {
                        this.matouJogador = true;
                        this.parar();
                        this.animatronicQueMatou = animatronic;
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }, SEGUNDOS_CALMARIA, 5, TimeUnit.SECONDS);
        }
        // Energia
        // Outros Eventos
    }

    public void parar() {
        scheduler.shutdownNow();
        Thread.currentThread().interrupt();

    }
}