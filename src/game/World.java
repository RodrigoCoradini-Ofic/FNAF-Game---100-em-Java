// Importando Bibliotecas
package game;
import animatronics.*;

import map.Door;
import map.Room;
import java.util.ArrayList;

public class World {
    protected ArrayList<Door> doors;
    protected ArrayList<Room> rooms;
    protected ArrayList<Animatronics> animatronics;
    protected double dificuldade;

    // Construtor do World
    public World(double dificuldade) {
        this.dificuldade = dificuldade;
        this.doors = new ArrayList<>();
        iniciarDoors();
        this.rooms = new ArrayList<>();
        iniciarComodos();
        this.animatronics = new ArrayList<>();
        iniciarAnimatronics();
    }

    // Iniciando os Comodos...
    public void iniciarComodos(){
        // Instanciar Cômodos
        Room palcoPrincipal = new Room(Room.TipoComodo.PALCO);
        Room salao = new Room(Room.TipoComodo.SALAO);
        Room cavernaPirata = new Room(Room.TipoComodo.CAVERNA_PIRATA);
        Room corredorEsquerdo = new Room(Room.TipoComodo.CORREDOR_ESQUERDO);
        Room cantoEsquerdo = new Room(Room.TipoComodo.CANTO_ESQUERDO);
        Room depositoDeMateriais = new Room(Room.TipoComodo.DEPOSITO_MATERIAIS);
        Room corredorDireito = new Room(Room.TipoComodo.CORREDOR_DIREITO);
        Room cantoDireito = new Room(Room.TipoComodo.CANTO_DIREITO);
        Room areaDosBastidores = new Room(Room.TipoComodo.AREA_DOS_BASTIDORES);
        Room cozinha = new Room(Room.TipoComodo.COZINHA);
        Room banheiros = new Room(Room.TipoComodo.BANHEIRO);
        Room escritorioPortaEsquerda = new Room(Room.TipoComodo.ESCRITORIO_PORTA_ESQUERDA);
        Room escritorioPortaDireita= new Room(Room.TipoComodo.ESCRITORIO_PORTA_DIREITA);

        // Adidionando Antecedentes e Sucessores
        palcoPrincipal.adicionarComodoDepois(salao);
        palcoPrincipal.adicionarComodoDepois(areaDosBastidores);
        salao.adicionarComodoAntes(palcoPrincipal);
        salao.adicionarComodoDepois(corredorEsquerdo);
        salao.adicionarComodoDepois(corredorDireito);
        cavernaPirata.adicionarComodoDepois(corredorEsquerdo);
        corredorEsquerdo.adicionarComodoAntes(salao);
        corredorEsquerdo.adicionarComodoDepois(cantoEsquerdo);
        cantoEsquerdo.adicionarComodoAntes(corredorEsquerdo);
        cantoEsquerdo.adicionarComodoDepois(escritorioPortaEsquerda);
        depositoDeMateriais.adicionarComodoAntes(areaDosBastidores);
        depositoDeMateriais.adicionarComodoDepois(corredorDireito);
        corredorDireito.adicionarComodoAntes(salao);
        corredorDireito.adicionarComodoDepois(cantoDireito);
        cantoDireito.adicionarComodoAntes(corredorDireito);
        cantoDireito.adicionarComodoDepois(escritorioPortaDireita);
        areaDosBastidores.adicionarComodoAntes(palcoPrincipal);
        areaDosBastidores.adicionarComodoDepois(depositoDeMateriais);
        cozinha.adicionarComodoAntes(banheiros);
        banheiros.adicionarComodoAntes(salao);
        banheiros.adicionarComodoDepois(corredorDireito);
        banheiros.adicionarComodoDepois(cozinha);
        escritorioPortaEsquerda.adicionarComodoAntes(cantoEsquerdo);
        escritorioPortaDireita.adicionarComodoAntes(cantoDireito);

        // Conectar Doors
        escritorioPortaEsquerda.conectarDoor(doors.getFirst());
        escritorioPortaDireita.conectarDoor(doors.get(1));

        // Adicionando a Lista
        rooms.add(palcoPrincipal);
        rooms.add(salao);
        rooms.add(cavernaPirata);
        rooms.add(corredorEsquerdo);
        rooms.add(cantoEsquerdo);
        rooms.add(depositoDeMateriais);
        rooms.add(corredorDireito);
        rooms.add(cantoDireito);
        rooms.add(areaDosBastidores);
        rooms.add(cozinha);
        rooms.add(banheiros);
        rooms.add(escritorioPortaEsquerda);
        rooms.add(escritorioPortaDireita);
    }

    // Iniciando os Animatronics...
    public void iniciarAnimatronics(){
        for(Room room : rooms){
            if(room.getNome().equals(Room.TipoComodo.PALCO.getNome())){
                Bonnie bonnie = new Bonnie(room, this.dificuldade);
                animatronics.add(bonnie);
                Chica chica = new Chica(room, this.dificuldade);
                animatronics.add(chica);
                Freddy freddy = new Freddy(room, this.dificuldade);
                animatronics.add(freddy);
            }
            if(room.getNome().equals(Room.TipoComodo.CAVERNA_PIRATA.getNome())){
                Foxy foxy = new Foxy(room, this.dificuldade);
                animatronics.add(foxy);
            }
        }
    }

    // Instaciando as Portas...
    public void iniciarDoors(){
        Door portaEsquerda = new Door(1);
        doors.add(portaEsquerda);
        Door portaDireita = new Door(2);
        doors.add(portaDireita);
    }

    // GETTERs
    public ArrayList<Door> getDoors() {
        return doors;
    }
}