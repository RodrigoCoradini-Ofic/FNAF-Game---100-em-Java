// Importando Bibliotecas
package systems;

import map.Camera;

public class Energy {
    protected double energia;
    protected double duracaoNoite;
    protected double qtdPerdidaPorSegundo;
    protected double consumoPortas;
    protected double consumoCamera;

    // Constantes
    private static final int HORAS_GAME = 6;
    private static final double PERCENTUAL_REAL_ENERGIA_GASTO = 0.06;
    private static final double PERCENTUAL_REAL_ENERGIA_GASTO_PORTA = 0.06;
    private static final double PERCENTUAL_REAL_ENERGIA_GASTO_CAMERA = 0.04;

    // Construtor da Energia
    public Energy(int segundosPorHora) {
        this.duracaoNoite = segundosPorHora * HORAS_GAME;
        this.energia = 100.00;
        this.qtdPerdidaPorSegundo = (duracaoNoite / energia) * PERCENTUAL_REAL_ENERGIA_GASTO;
        this.consumoPortas = qtdPerdidaPorSegundo * PERCENTUAL_REAL_ENERGIA_GASTO_PORTA;
        this.consumoCamera = qtdPerdidaPorSegundo * PERCENTUAL_REAL_ENERGIA_GASTO_CAMERA;
    }

    // Metodo para perder energia
    public void perderEnergia(int qtdPortasFechadas, Camera camera) {
        if (this.getEnergia() <= 0) {
            this.energia = 0;
            return;}
        if (camera.isCameraAtiva()){
            this.energia -= (Math.min(1.0,this.qtdPerdidaPorSegundo + (qtdPortasFechadas * this.consumoPortas) + this.consumoCamera));
        }
        this.energia -= (Math.min(1.0,this.qtdPerdidaPorSegundo + (qtdPortasFechadas * this.consumoPortas)));
    }

    // GETTERs
    public double getEnergia() {
        return energia;
    }
}