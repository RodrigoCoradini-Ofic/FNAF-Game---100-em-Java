// Importando Bibliotecas
package systems;

public class Energy {
    protected double energia;
    protected double duracaoNoite;
    protected double qtdPerdidaPorSegundo;
    protected double consumoPortas;

    // Constantes
    private static final int HORAS_GAME = 6;
    private static final double PERCENTUAL_REAL_ENERGIA_GASTO = 0.6;
    private static final double PERCENTUAL_REAL_ENERGIA_GASTO_PORTA = 0.4;

    // Construtor da Energia
    public Energy(int segundosPorHora) {
        this.duracaoNoite = segundosPorHora * HORAS_GAME;
        this.energia = 100.00;
        this.qtdPerdidaPorSegundo = (duracaoNoite / energia) * PERCENTUAL_REAL_ENERGIA_GASTO;
        this.consumoPortas = qtdPerdidaPorSegundo * PERCENTUAL_REAL_ENERGIA_GASTO_PORTA;
    }

    // Metodo para perder energia
    public void perderEnergia(int qtdPortasFechadas) {
        this.energia -= (Math.min(1.0,this.qtdPerdidaPorSegundo + (qtdPortasFechadas * this.consumoPortas)));
    }

    // GETTERs
    public double getEnergia() {
        return energia;
    }
}