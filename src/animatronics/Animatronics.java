// Importando Bibliotecas
package animatronics;
import map.Door;
import map.Room;
import java.util.ArrayList;
import java.util.Random;

// Iniciando a Classe-Mâe
public abstract class Animatronics {
    protected String nome;
    protected Room localizacao;
    protected double agressividadeBase;
    protected String fraseMorte;
    protected String descricaoEstado;
    protected Random random;
    protected double dificuldade;
    protected boolean atacando;
    protected long inicioAtaque;
    protected double agressividadeEfetiva;
    protected double chanceMovimento;

    // Constantes
    public static final double PORCENTAGEM_CONVERTIDA_EM_CHANCE_PARADO = 0.2;
    protected static final double CHANCE_MOVIMENTACAO = 0.30;
    protected static final int PORCENTAGEM_CHANCE_IR_PERTO_JOGADOR = 60;
    protected static final long TEMPO_ATAQUE = 3000;
    protected static final int KEEP_CHANCE_MOVIMENTO = 70;

    // Enum da Situação do Movimento -- Onde o Animatronic Está
    public enum SituacaoMovimento {
        PORTA_ESQUERDA,
        PORTA_DIREITA,
        NO_CAMINHO,
        SEM_CAMINHO,
        CORRENDO // Exclusivo do Foxy
    }

    // Construtor dos Animatronics
    public Animatronics(String nome, double agressividadeBase, String fraseMorte, double dificuldade) {
        this.nome = nome;
        this.agressividadeBase = agressividadeBase;
        this.fraseMorte = fraseMorte;
        this.dificuldade = dificuldade;
        this.atacando = false;
        this.inicioAtaque = 0;
        this.random = new Random();
    }

    // Lógica de Movimentação pra frente
    protected void movimentarFrente() {
        // Se Movimenta: Com N% de chance de ir mais perto do jogador
        ArrayList<Room> destinos = localizacao.getComodoDepois();
        Room origem = this.localizacao;
        if (destinos.isEmpty()) {
            return;}
        // Se só existe um destino
        if (destinos.size() == 1) {
            this.localizacao = destinos.getFirst();
            return;}
        int porcentagem = random.nextInt(100) + 1;
        // N% → primeiro caminho
        if (porcentagem <= PORCENTAGEM_CHANCE_IR_PERTO_JOGADOR) {
            this.localizacao = destinos.getFirst();
        } else {
            // Total-N% → qualquer um dos outros caminhos
            int indice = 1 + random.nextInt(destinos.size() - 1);
            this.localizacao = destinos.get(indice);
//            System.out.printf(
//                    "%s | Local: %s | Agressividade: %.2f | Chance: %.2f%n",
//                    nome, localizacao.getNome(), getAgressividadeEfetiva(), getChanceMovimento());
//            System.out.println(
//                    nome +
//                            " | De: " + origem.getNome() +
//                            " | Para: " + localizacao.getNome());
        }
    }

    // Lógica de Movimentação pra Trás
    protected void movimentarTras() {
        // Se Movimenta pra Trás
        if (localizacao.getComodoAntes().isEmpty()) {
            return;}
        int indice = random.nextInt(localizacao.getComodoAntes().size());

        this.localizacao = localizacao.getComodoAntes().get(indice);
    }

    // Lógica de Tentar movimentar
    public boolean tentaMovimentar(Door door) {
        SituacaoMovimento situacao = getSituacaoMovimento();

        return switch (situacao) {
            case PORTA_ESQUERDA, PORTA_DIREITA -> lidarComPorta(door);
            case NO_CAMINHO -> decisaoMovimentacao();
            case CORRENDO -> false; // Tratamento necessario -- Foxy
            case SEM_CAMINHO -> {
                System.out.println(nome + " não possui caminho para frente.");
                yield false;
            }
        };
    }

    // Faz a decisão da Movimentação
    protected boolean decisaoMovimentacao() {
        double chanceMovimento = calcularChanceMovimento();
        double chanceRestante = 100 - chanceMovimento;
        double chanceParado = chanceRestante * PORCENTAGEM_CONVERTIDA_EM_CHANCE_PARADO;

        int porcentagem = random.nextInt(100) + 1;

        // Vai pra Frente
        if (porcentagem <= chanceMovimento) {
            movimentarFrente();
            return false;}
        // Fica Parado
        if (porcentagem <= chanceMovimento + chanceParado) {
            return false;}
        // Volta
        movimentarTras();
        return false;
    }

    // Metodo para Lidar com a Porta
    protected boolean lidarComPorta(Door door) {
        // Se está na Porta, tenta Atacar
        if (atacar(door)) {
            return true;}

        boolean podeVoltar = !localizacao.getComodoAntes().isEmpty();
        boolean deveVoltar = random.nextInt(2) == 0; //50%

        // Se tiver pra onde voltar e der True, Volta
        if (podeVoltar && deveVoltar) {
            this.movimentarTras();}
        return false;
    }

    // Metodo para Calcular a Chance de Movimento
    protected double calcularChanceMovimento() {
        this.agressividadeEfetiva = this.getAgressividadeBase() * this.dificuldade;
        this.chanceMovimento = CHANCE_MOVIMENTACAO * 100 + this.agressividadeEfetiva * 3;
        return Math.min(this.chanceMovimento, KEEP_CHANCE_MOVIMENTO);
    }

    // Lógica Ataque
    protected boolean atacar(Door door) {
        if (!estaNaPorta()) {
            return false;}
        if (door.estaFechado()) {
            cancelarAtaque();
            return false;}
        if (!atacando) {
            iniciarAtaque();
            return false;}
        return verificarAtaque();
    }

    // Verifica se o Animatronic está na porta
    protected boolean estaNaPorta() {
        return localizacao.getNome().equals(Room.TipoComodo.ESCRITORIO_PORTA_ESQUERDA.getNome()) ||
                localizacao.getNome().equals(Room.TipoComodo.ESCRITORIO_PORTA_DIREITA.getNome());
    }

    // Inicia o Ataque
    protected void iniciarAtaque() {
        this.atacando = true;
        this.inicioAtaque = System.currentTimeMillis();
    }

    // Verifica se o tempo de Ataque acabou
    protected boolean verificarAtaque() {
        long tempoPassado = System.currentTimeMillis() - inicioAtaque;

        if (tempoPassado >= TEMPO_ATAQUE) {
            atacando = false;
            return true;}
        return false;
    }

    // Cancela o Ataque
    protected void cancelarAtaque() {
        atacando = false;
        inicioAtaque = 0;
    }

    // Metodo para pegar a Situação do Movimento
    public SituacaoMovimento getSituacaoMovimento() {
        String nomeComodo = localizacao.getNome();

        if (nomeComodo.equals(Room.TipoComodo.ESCRITORIO_PORTA_ESQUERDA.getNome())) {
            return SituacaoMovimento.PORTA_ESQUERDA;}
        if (nomeComodo.equals(Room.TipoComodo.ESCRITORIO_PORTA_DIREITA.getNome())) {
            return SituacaoMovimento.PORTA_DIREITA;}
        if (localizacao.getComodoDepois().isEmpty()) {
            return SituacaoMovimento.SEM_CAMINHO;}
        return SituacaoMovimento.NO_CAMINHO;
    }

    // Reencrevendo o ToString
    @Override
    public String toString() {
        return " %s \n Morreu para: %s".formatted(this.getFraseMorte(), this.getNome());
    }

    // GETs
    public Room getLocalizacao() {
        return localizacao;
    }

    public String getFraseMorte() {
        return fraseMorte;
    }

    public String getNome() {
        return nome;
    }

    public double getAgressividadeBase() {
        return agressividadeBase;
    }

    public String getDescricaoEstado() {
        return descricaoEstado;
    }

    public double getAgressividadeEfetiva() {
        return agressividadeEfetiva;
    }

    public double getChanceMovimento() {
        return chanceMovimento;
    }
}
