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

    // Constantes
    protected static final double CHANCE_MOVIMENTACAO = 0.30;

    // Construtor dos Animatronics
    public Animatronics(String nome, double agressividadeBase, String fraseMorte) {
        this.nome = nome;
        this.agressividadeBase = agressividadeBase;
        this.fraseMorte = fraseMorte;
    }

    // Lógica de Movimentação pra frente
    public void movimentarFrente() {
        try {
            // Se Movimenta: Com 70% de chance de ir mais perto do jogador
            ArrayList<Room> destinos = localizacao.getComodoDepois();
            if (destinos.isEmpty()) {
                return;
            }
            Random random = new Random();
            // Se só existe um destino
            if (destinos.size() == 1) {
                this.localizacao = destinos.getFirst();
                return;
            }
            int porcentagem = random.nextInt(100) + 1;
            // 70% → primeiro caminho
            if (porcentagem <= 70) {
                this.localizacao = destinos.getFirst();
            } else {
                // 30% → qualquer um dos outros caminhos
                int indice = 1 + random.nextInt(destinos.size() - 1);
                this.localizacao = destinos.get(indice);
            }
        } catch (Exception e) {
            System.out.println("Erro ao movimentar Frente");
            e.printStackTrace();
        }
    }

    // Lógica de Movimentação pra Trás
    public void movimentarTras() {
        try {
            // Se Movimenta pra Trás
            Random random = new Random();
            if (localizacao.getComodoAntes().isEmpty()) {
                return;
            }

            int indice = random.nextInt(localizacao.getComodoAntes().size());

            this.localizacao = localizacao.getComodoAntes().get(indice);
        } catch (Exception e) {
            System.out.println("Erro ao movimentar Tras");
            e.printStackTrace();
        }
    }

    // Lógica de Tentar movimentar
    public boolean tentaMovimentar(int idNoite, Door door) throws InterruptedException {
        // Tenta atacar
        boolean resultadoAtacar = this.atacar(door);
        // Se Matou o Jogador Encerra
        if (resultadoAtacar) {
            return true;
        } else if (localizacao.getNome().equals("Porta Esquerda") || localizacao.getNome().equals("Porta Direita")){
                // Se não matou e está na porta, escolhe entre voltar ou esperar
                Random random = new Random();
                if(random.nextInt(2) == 0 && !localizacao.getComodoAntes().isEmpty()){
                    this.movimentarTras();// Volta
                    return false;
                }else return false; // Fica Parado
        } else{ // Se não matou, e não está na porta, tenta se mover
                // Chance de Movimentação
                    ArrayList<Room> destinos = localizacao.getComodoDepois();
                    if (destinos.isEmpty()) {
                        System.out.println(nome + " não possui caminho para frente.");
                        return false;
                    }
                    double agressividadeEfetiva = getAgressividadeBase() * idNoite;
                    double chanceMovimento = CHANCE_MOVIMENTACAO * 100 + agressividadeEfetiva * 3;
                    chanceMovimento = Math.min(chanceMovimento, 70);
                    Random random = new Random();
                    double chanceQueSobra = 100 - chanceMovimento;
                    double chanceDeVoltar = chanceQueSobra * 0.3;
                    // Verificar Porcentagem...
                    int porcentagem = random.nextInt(100) + 1;
                    if (porcentagem <= (int) chanceMovimento) {
                        // Se as probabilidades forem à favor, se movimenta pra Frente
                        this.movimentarFrente();
//                        System.out.println(
//                                nome + " | Local: " + localizacao.getNome()
//                                        + " | Agressividade: " + agressividadeEfetiva
//                                        + " | Chance: " + chanceMovimento);
                        return false;
                    }if (porcentagem <= ((int) chanceMovimento + (int) chanceDeVoltar)) {
                        // Se as probabilidades não forem à favor, fica Parado
                        return false;
                    }else {
                        // Volta um comodo
                        this.movimentarTras();
                        return false;
                    }
            }
    }

    // Lógica Ataque
    public boolean atacar(Door  door) throws InterruptedException {
        // Verifica Porta e Comodo
        if(localizacao.getNome().equals("Porta Esquerda") && !door.estaFechado() || localizacao.getNome().equals("Porta Direita") && !door.estaFechado()){
            // Cowndown
            Thread.sleep(3000);
            //Matar
            return true;
        } else{
            // Não matou
            return false;
        }
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
}
