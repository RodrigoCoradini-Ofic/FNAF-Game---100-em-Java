// Importando Bibliotecas
package animatronics;
import map.Door;
import map.Room;

public class Foxy extends Animatronics {
    protected EstadoFoxy estado;
    protected String descricaoEstado;
    protected boolean correndo;
    protected Room roomInicial;

    // Enum dos Estados do FOXY
    public enum EstadoFoxy {
        FECHADO(1, "As cortinas permanecem fechadas. Apenas a placa desgastada da Pirate Cove pode ser vista."),
        ENTREABERTO(2, "As cortinas estão entreabertas. Dois olhos parecem observar através da escuridão."),
        MEIO_ABERTO(3, "As cortinas estão quase abertas. Foxy permanece imóvel, parcialmente revelado pela escuridão."),
        DIANTE_DAS_CORTINAS(4, "Foxy está diante das cortinas, imóvel. Seu olhar permanece fixo na câmera"),
        PALCO_VAZIO(5, "As cortinas estão completamente abertas. O palco está vazio.");

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
    public Foxy(Room room, double dificuldade) {
        super(
                "Foxy",
                2.0,
                "Corra... se conseguir!",
                dificuldade
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
                this.estado = EstadoFoxy.MEIO_ABERTO;
                this.descricaoEstado = estado.getDescricao();
                break;
            // Caso estiver Meio Aberto, fica Diante das Cortinas
            case MEIO_ABERTO:
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

    // Lógica Desatualizar Estado
    public void desatualizarEstados(){
        switch (estado) {
            // Caso estiver Diante das Cortinas, fica meio Fechado
            case DIANTE_DAS_CORTINAS:
                this.estado = EstadoFoxy.MEIO_ABERTO;
                this.descricaoEstado = estado.getDescricao();
                break;
            // Caso estiver Meio aberto, fica Entreaberto
            case MEIO_ABERTO:
                this.estado = EstadoFoxy.ENTREABERTO;
                this.descricaoEstado = estado.getDescricao();
                break;
            // Caso estiver Entreaberto, fica Fechado
            case ENTREABERTO:
                this.estado = EstadoFoxy.FECHADO;
                this.descricaoEstado = estado.getDescricao();
                break;
            case FECHADO:
                break;
        }
    }

    // Reescrevendo o TentaMovimentar
    @Override
    public boolean tentaMovimentar(Door door) {
        SituacaoMovimento situacao = getSituacaoMovimento();

        return switch (situacao) {
            case PORTA_ESQUERDA, PORTA_DIREITA -> lidarComPorta(door);
            case NO_CAMINHO -> decisaoMovimentacao();
            case CORRENDO -> {
                movimentarFrente();
                yield false;
            }
            case SEM_CAMINHO -> {
                System.out.println(nome + " não possui caminho para frente.");
                yield false;
            }
        };
    }

    // Reescrevendo a Lógica de Movimentação do Foxy
    @Override
    protected boolean decisaoMovimentacao(){
        double chanceMovimento = calcularChanceMovimento();
        double chanceRestante = 100 - chanceMovimento;
        double chanceParado = chanceRestante * PORCENTAGEM_CONVERTIDA_EM_CHANCE_PARADO;

        int porcentagem = random.nextInt(100) + 1;

        // Vai pra Frente
        if (porcentagem <= chanceMovimento) {
            atualizarEstados();
            return false;}
        // Fica Parado
        if (porcentagem <= chanceMovimento + chanceParado) {
            return false;}
        // Volta
        desatualizarEstados();
        return false;
    }

    // Metodo para correr
    public void correr(){
        this.correndo = true;
    }

    // Reescrevendo o Movimentar para Tras do Foxy
    @Override
    public void movimentarTras() {
        // Volta para tras das Cortinas
        this.correndo = false;
        this.localizacao = this.roomInicial;
        this.estado = EstadoFoxy.FECHADO;
        this.descricaoEstado = estado.getDescricao();
    }

    protected boolean lidarComPorta(Door door) {
        // Porta fechada: Foxy não consegue atacar
        if (door.estaFechado()) {
            System.out.println("Foxy bateu na porta!");
            movimentarTras();
            return false;}
        // Porta aberta: tenta atacar
        return atacar(door);
    }

    // Sobreescrevendo o GetSituaçãoMovimento
    @Override
    public Animatronics.SituacaoMovimento getSituacaoMovimento() {
        String nomeComodo = localizacao.getNome();

        if (nomeComodo.equals(Room.TipoComodo.ESCRITORIO_PORTA_ESQUERDA.getNome())) {
            return Animatronics.SituacaoMovimento.PORTA_ESQUERDA;}
        if (nomeComodo.equals(Room.TipoComodo.ESCRITORIO_PORTA_DIREITA.getNome())) {
            return Animatronics.SituacaoMovimento.PORTA_DIREITA;}
        if (this.correndo) {
            return Animatronics.SituacaoMovimento.CORRENDO;}
        if (localizacao.getComodoDepois().isEmpty()) {
            return Animatronics.SituacaoMovimento.SEM_CAMINHO;}
        return Animatronics.SituacaoMovimento.NO_CAMINHO;
    }

    // GETTERs
    public String getDescricaoEstado() {
        return descricaoEstado;
    }
}