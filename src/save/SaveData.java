// Importando Bibliotecas
package save;

public class SaveData {
    protected int noiteAtual;
    protected int ultimaNoiteCompletada;
    protected String nomeJogador;
    protected int score;

    // Construtor
    public SaveData() {
    }

    public SaveData(String nomeJogador,int noiteAtual, int ultimaNoiteCompletada, int score) {
        this.nomeJogador = nomeJogador;
        this.noiteAtual = noiteAtual;
        this.ultimaNoiteCompletada = ultimaNoiteCompletada;
        this.score = score;
    }

    // GETTERs e SETTERs
    public int getNoiteAtual() {
        return noiteAtual;
    }

    public void setNoiteAtual(int noiteAtual) {
        this.noiteAtual = noiteAtual;
    }

    public int getUltimaNoiteCompletada() {
        return ultimaNoiteCompletada;
    }

    public void setUltimaNoiteCompletada(int ultimaNoiteCompletada) {
        this.ultimaNoiteCompletada = ultimaNoiteCompletada;
    }

    public String getNomeJogador() {
        return nomeJogador;
    }

    public void setNomeJogador(String nomeJogador) {
        this.nomeJogador = nomeJogador;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}