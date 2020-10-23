import javax.swing.*;
import java.util.ArrayList;

public class Game {
    private static Player player;
    private final static Map gameMap = new Map();
    private final static Parser inputParser = new Parser();

    static void Start() {
        gameMap.addRoom(new Room("roomName", "description", "roomType", 0));
        gameMap.addRoom(new Room("roomName2", "description2", "roomType2", 1));
        gameMap.getRoom(0).addDoor(new Door("DoorName", "North", gameMap.getRoom(1), false, false), 0, 1);
        gameMap.getRoom(0).addWalls();
        gameMap.getRoom(0).addObject(new Key("Key", "This is a key to a door."));
        gameMap.getRoom(1).addDoor(new Door("DoorName2", "South", gameMap.getRoom(0), false, false), 2, 1);
        gameMap.getRoom(1).addWalls();

        player = new Player("Player", 5, gameMap.getRoom(0));
    }

    public static void main(String[] args) {
        Start();
        boolean running = true;
        while(running) {
            System.out.println("MOVES: " + player.getMoves());
            inputParser.getPlayerInput();
            inputParser.parseInput(player);
            player.incrementTurn();
        }
    }
}

// Handles entire map
class Map {
    private final ArrayList<Room> Map = new ArrayList<>();
    public Map(){ }
    public void addRoom(Room room){
        Map.add(room);
    }

    public Room getRoom(int roomID){
        return this.Map.get(roomID);
    }
}

// Handles wall objects
class Wall {
    String direction;
    Wall(){ }
    Wall(String direction){ this.direction = direction; }
}

interface TravelObj {
    String direction = "";
    String name = "";
    Room connectedTo = null;
    boolean isLocked = false;
    boolean isHidden = false;

    void unlock();
    void uncover();
}

// Handles door objects
class Door implements TravelObj {
    String direction; // Direction door is located at in the perspective of the center of a room.
    String doorName;  // Name of the door.
    Room connectedTo; // Room the door is connected to.
    boolean isLocked; // True if door is currently locked.
    boolean isHidden; // True if door is currently hidden.

    Door(){ }
    Door(String doorName, String direction, Room connectedTo, boolean isLocked, boolean isHidden){
        this.doorName = doorName;
        this.direction = direction;
        this.connectedTo = connectedTo;
        this.isLocked = isLocked;
        this.isHidden = isHidden;
    }

    public void unlock() { this.isLocked = false; }
    public void uncover() { this.isHidden = false; }
}

// Contains room object
class Room {
    String roomName;                                          // Name of the room
    String description;                                       // Description of the room
    String roomType;                                          // Type of the room
    int roomID;                                               // ID of the room
    ArrayList<ZorkObj> objects = new ArrayList<>();           // Objects in the room
    final int ROOM_SIZE = 3;                           // Constant for size of room
    Door[][] doors = new Door[ROOM_SIZE][ROOM_SIZE];   // Doors that this room is connected to
    Wall[][] walls = new Wall[ROOM_SIZE][ROOM_SIZE];   // Walls in this room

    Room(){
        this.roomName = "";
        this.description = "";
        this.roomType = "";
        this.objects = null;
        this.doors = null;
    }
    Room(String roomName){
        this.roomName = roomName;
        this.description = "This is an empty room";
        this.roomType = "Empty";
        this.objects = null;
    }
    Room(String roomName, String description, String roomType, int roomID){
        this.roomName = roomName;
        this.description = description;
        this.roomType = roomType;
        this.roomID = roomID;
    }

//    public String getRoomName(){ return this.roomName; }
//    public String getRoomType(){ return this.roomType; }
//    public String getDescription(){ return this.description; }
//    public ArrayList<String> getObjects(){ return this.objects; }
//    public Door[][] getDoors(){ return this.doors; }
//    public Wall[][] getWalls(){ return this.walls; }

    public void addWalls(){
        for(int i = 0; i < ROOM_SIZE; i++) {
            for(int j = 0; j < ROOM_SIZE; j++) {
                if (walls[i][j] == null && doors[i][j] == null) {
                    walls[i][j] = new Wall();
                }
            }
        }
    }

    public void addDoor(Door door, int x, int y){
        if (walls[x][y] == null && doors[x][y] == null) {
            doors[x][y] = door;
        }
    }

    public void addObject(ZorkObj objectName) {
        this.objects.add(objectName);
    }

    public Door getConnectedRoom(String direction){
        switch (direction){
            case "north":
            case "n":
                if (walls[0][1] == null)
                    return doors[0][1];
                else if (doors[0][1] == null)
                    System.out.println("There is a wall in this direction.");
                    return null;
            case "south":
            case "s":
                if (walls[2][1] == null)
                    return doors[2][1];
                else if (doors[2][1] == null)
                    System.out.println("There is a wall in this direction.");
                    return null;
            case "east":
            case "e":
                if (walls[1][2] == null)
                    return doors[1][2];
                else if (doors[1][2] == null)
                    System.out.println("There is a wall in this direction.");
                    return null;
            case "west":
            case "w":
                if (walls[1][0] == null)
                    return doors[1][0];
                else if (doors[1][0] == null)
                    System.out.println("There is a wall in this direction.");
                    return null;
            case "northeast":
            case "ne":
                if (walls[0][2] == null)
                    return doors[0][2];
                else if (doors[0][2] == null)
                    System.out.println("There is a wall in this direction.");
                    return null;
            case "northwest":
            case "nw":
                if (walls[0][0] == null)
                    return doors[0][0];
                else if (doors[0][0] == null)
                    System.out.println("There is a wall in this direction.");
                    return null;
            case "southeast":
            case "se":
                if (walls[2][2] == null)
                    return doors[2][2];
                else if (doors[2][2] == null)
                    System.out.println("There is a wall in this direction.");
                    return null;
            case "southwest":
            case "sw":
                if (walls[2][0] == null)
                    return doors[2][0];
                else if (doors[2][0] == null)
                    System.out.println("There is a wall in this direction.");
                    return null;
            case "up":
            case "down":
            case "in":
            case "out":
                if (walls[1][1] == null)
                    return doors[1][1];
                else if (doors[1][1] == null)
                    System.out.println("There is nothing in this direction.");
                    return null;
        }
        return null;
    }

    public ZorkObj getObj(String name) {
        for(int i = 0; i < this.objects.size(); i++) {
            if (this.objects.get(i).getObjName().equals(name)) {
                return this.objects.get(i);
            }
        }
        return null;
    }
}