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

    // Construtor do World
    public World() {
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
        Room palcoPrincipal = new Room("Palco Principal", 1,     "O palco principal permanece imóvel sob luzes fracas. As cortinas estão abertas, revelando...");
        Room salao = new Room("Salão", 2,     "Mesas vazias ocupam o salão, iluminadas por lâmpadas que piscam ocasionalmente. Balões e desenhos infantis decoram as paredes.");
        Room cavernaPirata = new Room("Caverna Pirata", 3,     "Uma pequena área temática de piratas surge atrás de cortinas vermelhas. A iluminação é quase inexistente, tornando difícil enxergar o que há atrás delas.");
        Room corredorEsquerdo = new Room("Corredor Esquerdo", 4,     "Um corredor estreito se estende até a escuridão. As paredes exibem desenhos infantis antigos, enquanto uma luz fraca ilumina apenas parte do caminho.");
        Room portaEsquerda = new Room("Porta Esquerda", 5,     "O corredor termina em um canto escuro, próximo ao escritório. A câmera mal consegue alcançar o fim do corredor.");
        Room depositoDeMateriais = new Room("Depósito de Materiais", 6,     "Caixas, peças e objetos esquecidos estão empilhados em um pequeno depósito. O espaço parece apertado demais para alguém se esconder ali...");
        Room corredorDireito = new Room("Corredor Direito", 7,     "Outro corredor vazio atravessa a pizzaria. As luzes são fracas e o silêncio contrasta com os desenhos coloridos espalhados pelas paredes.");
        Room portaDireita = new Room("Porta Direita", 8,     "O corredor termina diante da porta do escritório. A iluminação falha por alguns instantes, deixando o canto completamente escuro.");
        Room areaDosBastidores = new Room("Área dos Bastidores", 9,         "Peças de animatrônicos e equipamentos antigos estão espalhados pelo ambiente. Rostos mecânicos observam a câmera de lugares diferentes.");
        Room cozinha = new Room("Cozinha", 10,     "A cozinha permanece mergulhada na escuridão. Panelas e equipamentos podem ser vistos apenas parcialmente, enquanto sons metálicos ecoam pelo ambiente.");
        Room banheiros = new Room("Banheiros", 11,     "Os banheiros estão vazios e silenciosos. Azulejos antigos e luzes fluorescentes criam uma atmosfera fria e desconfortável.");

        // Adidionando Antecedentes e Sucessores
        palcoPrincipal.adicionarComodoDepois(salao);
        palcoPrincipal.adicionarComodoDepois(areaDosBastidores);
        salao.adicionarComodoAntes(palcoPrincipal);
        salao.adicionarComodoDepois(corredorEsquerdo);
        salao.adicionarComodoDepois(corredorDireito);
        cavernaPirata.adicionarComodoDepois(corredorEsquerdo);
        corredorEsquerdo.adicionarComodoAntes(salao);
        corredorEsquerdo.adicionarComodoDepois(portaEsquerda);
        portaEsquerda.adicionarComodoAntes(corredorEsquerdo);
        depositoDeMateriais.adicionarComodoAntes(areaDosBastidores);
        depositoDeMateriais.adicionarComodoDepois(corredorDireito);
        corredorDireito.adicionarComodoAntes(salao);
        corredorDireito.adicionarComodoDepois(portaDireita);
        portaDireita.adicionarComodoAntes(corredorDireito);
        areaDosBastidores.adicionarComodoAntes(palcoPrincipal);
        areaDosBastidores.adicionarComodoDepois(depositoDeMateriais);
        cozinha.adicionarComodoAntes(banheiros);
        banheiros.adicionarComodoAntes(salao);
        banheiros.adicionarComodoDepois(corredorDireito);
        banheiros.adicionarComodoDepois(cozinha);

        // Conectar Doors
        portaEsquerda.conectarDoor(doors.getFirst());
        portaDireita.conectarDoor(doors.get(1));

        // Adicionando a Lista
        rooms.add(palcoPrincipal);
        rooms.add(salao);
        rooms.add(cavernaPirata);
        rooms.add(corredorEsquerdo);
        rooms.add(portaEsquerda);
        rooms.add(depositoDeMateriais);
        rooms.add(corredorDireito);
        rooms.add(portaDireita);
        rooms.add(areaDosBastidores);
        rooms.add(cozinha);
        rooms.add(banheiros);
    }

    // Iniciando os Animatronics...
    public void iniciarAnimatronics(){
        for(Room room : rooms){
            if(room.getNome().equals("Palco Principal")){
                Bonnie bonnie = new Bonnie(room);
                animatronics.add(bonnie);
                Chica chica = new Chica(room);
                animatronics.add(chica);
                Freddy freddy = new Freddy(room);
                animatronics.add(freddy);
            }
            if(room.getNome().equals("Caverna Pirata")){
                Foxy foxy = new Foxy(room);
                animatronics.add(foxy);
            }
        }
    }

    // Instaciando as Portas...
    public void iniciarDoors(){
        Door portaEsquerda = new Door();
        doors.add(portaEsquerda);
        Door portaDireita = new Door();
        doors.add(portaDireita);
    }
}

// Iniciando os Comodos...
//        Room showState = new Room("Show State", 1);
//        Room diningArea = new Room("Dining Area", 2);
//        Room pirateCove = new Room("Pirate Cove", 3);
//        Room westHall = new Room("West Hall", 4);
//        Room westHallCorner = new Room("West Hall Corner", 5);
//        Room supplyCloset = new Room("Supply Closet", 6);
//        Room eastHall = new Room("East Hall", 7);
//        Room eastHallCorner = new Room("East Hall Corner", 8);
//        Room Backstage = new Room("Backstage", 9);
//        Room Kitchen = new Room("Kitchen", 10);
//        Room Restrooms = new Room("Restrooms", 11);