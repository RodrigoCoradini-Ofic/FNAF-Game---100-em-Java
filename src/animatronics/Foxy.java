// Importando Bibliotecas
package animatronics;
import map.Door;
import map.Room;
import java.util.ArrayList;
import java.util.Random;

public class Foxy extends Animatronics {
    protected EstadoFoxy estado;
    protected String descricaoEstado;
    protected boolean correndo;
    protected Room roomInicial;

    // Enum dos Estados do FOXY
    public enum EstadoFoxy {
        FECHADO(1, "As cortinas permanecem fechadas. Apenas a placa desgastada da Pirate Cove pode ser vista."),
        ENTREABERTO(2, "As cortinas estão entreabertas. Dois olhos parecem observar através da escuridão."),
        DIANTE_DAS_CORTINAS(3, "Foxy está diante das cortinas, imóvel. Seu olhar permanece fixo na câmera"),
        PALCO_VAZIO(4, "As cortinas estão completamente abertas. O palco está vazio.");

        private final int id;
        private final String descricao;

        // Construtor do enum
        EstadoFoxy(int id, String descricao) {
            this.id = id;
            this.descricao = descricao;

        }

        // GETTERs
        public int getId() {
            return id;
        }

        public String getDescricao() {
            return descricao;
        }
    }

    // Construtor do Foxy
    public Foxy(Room room) {
        super(
                "Foxy",
                2.0,
                "Corra... se conseguir!"
        );
        this.localizacao = room;
        this.roomInicial = room;
        this.estado = EstadoFoxy.FECHADO;
        this.descricaoEstado = estado.getDescricao();
        this.correndo = false;
    }

    // Lógica de Atualização de Estados
    public void atualizarEstados(){
        // Verificar os Estado do Foxy
        switch (estado) {
            // Caso estiver Fechado, fica Entreaberto
            case FECHADO:
                this.estado = EstadoFoxy.ENTREABERTO;
                this.descricaoEstado = estado.getDescricao();
                break;
            // Caso estiver Entreaberto, fica Diante das Cortinas
            case ENTREABERTO:
                this.estado = EstadoFoxy.DIANTE_DAS_CORTINAS;
                this.descricaoEstado = estado.getDescricao();
                break;
            // Caso estiver Diante das Cortinas, fica Vazio
            case DIANTE_DAS_CORTINAS:
                this.estado = EstadoFoxy.PALCO_VAZIO;
                this.descricaoEstado = estado.getDescricao();
                this.correr();
                break;
            case PALCO_VAZIO:
                break;
        }
    }

    // Reescrevendo a Lógica de Movimentação do Foxy
    @Override
    public boolean tentaMovimentar(int idNoite, Door door) throws InterruptedException {
        // Tenta atacar
        boolean resultadoAtacar = super.atacar(door);

//        // Nova Lógica movimentação
//        switch (resultadoAtacar) {
//            case true:// Se está na porta e está aberta, mata
//                return true;
//            case false:// Senão...
//                switch (localizacao.getNome().equals("Porta Esquerda") || localizacao.getNome().equals("Porta Direita")){
//                    case true:// Se está na porta e está fechada, volta
//                        System.out.println("Porta Esquerda");
//                        return false;
//                }
//                return false;
//        }

        // Se Matou o Jogador Encerra
        if (resultadoAtacar) {
            return true;
        } else if (localizacao.getNome().equals("Porta Esquerda") || localizacao.getNome().equals("Porta Direita")){
            // Se não matou e está na porta, volta
            System.out.println("Foxy Bateu na Porta Esquerda");
            this.movimentarTras();
            return false;
        } else if(correndo) { // Se não matou, não está na porta e está correndo, move pra frente
                    this.movimentarFrente();
                    return false;
                } else{ // Se não matou, e não está na porta, e não está correndo, tenta se mover
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
                    this.atualizarEstados();
//                    System.out.println(
//                            nome + " | Local: " + localizacao.getNome()
//                                    + " | Agressividade: " + agressividadeEfetiva
//                                    + " | Chance: " + chanceMovimento);
//                    System.out.println("Foxy camera: " + this.descricaoEstado);
                    return false;
                }else {
                // Fica Parado
                return false;
            }
        }
    }

    // Metodo para correr
    public void correr(){
        this.correndo = true;

//        System.out.println("Foxy saiu Correndo");
    }

    // Reescrevendo o Movimentar para Tras do Foxy
    @Override
    public void movimentarTras() {
        // Volta para tras das Cortinas
        this.correndo = false;
        this.localizacao = this.roomInicial;
        this.estado = EstadoFoxy.FECHADO;
        this.descricaoEstado = estado.getDescricao();
//        System.out.println("Foxy voltou para Trá das Cortinas");
//        System.out.println(this.getDescricaoEstado());
    }

    // GETTERs
    public String getDescricaoEstado() {
        return descricaoEstado;
    }

    public EstadoFoxy getEstado() {
        return estado;
    }
}
