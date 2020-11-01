import java.util.ArrayList;

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
    Wall(){ }
}

interface TravelObj {
    void unlock();
}

// Handles door objects
class Door implements TravelObj {
    String name;      // Name of door
    String direction; // Direction door is located at in the perspective of the center of a room.
    Room connectedTo; // Room the door is connected to.
    boolean isLocked; // True if door is currently locked.
    boolean isHidden; // True if door is currently hidden.

    Door(String name, String direction, Room connectedTo, boolean isLocked, boolean isHidden){
        this.name = name;
        this.direction = direction;
        this.connectedTo = connectedTo;
        this.isLocked = isLocked;
        this.isHidden = isHidden;
    }

    public boolean getLocked() { return this.isLocked; }

    public void unlock() { this.isLocked = false; }
}

// Contains room object
class Room {
    private final String roomName;                                    // Name of the room
    private final String description;                                 // Description of the room
    private final String descriptionVerbose;                          // Detailed description of the room
    private final String roomType;                                    // Type of the room
    private final int roomID;                                         // ID of the room
    private final ArrayList<ZorkObj> objects = new ArrayList<>();     // Objects in this room
    private final ArrayList<Character> characters = new ArrayList<>();// Characters in this room
    private final int ROOM_SIZE_W = 4;                                // Constant for width of room
    private final int ROOM_SIZE_H = 4;                                // Constant for height of room
    private final Door[][] doors = new Door[ROOM_SIZE_W][ROOM_SIZE_H];// Doors that this room is connected to
    private final Wall[][] walls = new Wall[ROOM_SIZE_W][ROOM_SIZE_H];// Walls in this room

    // Constructors
    Room(String roomName, String description, String descriptionVerbose, String roomType, int roomID){
        this.roomName = roomName;
        this.description = description;
        this.descriptionVerbose = descriptionVerbose;
        this.roomType = roomType;
        this.roomID = roomID;
    }

    // Get methods
    public int getRoomID() { return this.roomID; }
    public String getRoomName(){ return this.roomName; }
    public String getRoomType(){ return this.roomType; }
    public String getDescription(){ return this.description; }
    public String getDescriptionVerbose() { return this.descriptionVerbose; }
    public int getObjLength() { return this.objects.size(); }
    public ZorkObj getObjNoDel(String name) {
        for (ZorkObj object : this.objects) {
            if (object.getName().equals(name)) {
                return object;
            }
        }
        return null;
    }
    public ZorkObj getObj(String name) {
        for(int i = 0; i < this.objects.size(); i++) {
            if (this.objects.get(i).getName().equals(name)) {
                ZorkObj object = this.objects.get(i);
                System.out.println(objects);
                this.objects.remove(i);
                System.out.println(objects);
                return object;
            }
        }
        return null;
    }
    public ZorkObj getObj() {
        ZorkObj object = this.objects.get(0);
        this.objects.remove(0);
        return object;
    }
    public Character getChara(String name) {
        for (Character value : this.characters) {
            if (value.getName().equals(name)) {
                return value;
            }
        }
        return null;
    }
    public ArrayList<Character> getCharacters() { return this.characters; }

    // Returns true if object with 'name' is found in the current room
    public boolean isObjInRoom(String name) {
        for (ZorkObj object : this.objects) {
            if (object.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    // Returns true if character with 'name' is found in the current room
    public boolean isCharaInRoom(String name) {
        for (Character character : this.characters) {
            if (character.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    // Adds walls to spaces in this room that do not have a door.
    // @PARAM: none
    // @RETURN: none
    public void addWalls(){
        for(int i = 0; i < ROOM_SIZE_W; i++) {
            for(int j = 0; j < ROOM_SIZE_H; j++) {
                if (walls[i][j] == null && doors[i][j] == null) {
                    walls[i][j] = new Wall();
                }
            }
        }
    }

    // Adds a door object to a direction of this room.
    // @PARAM: door: Door to be added.
    // @RETURN: none
    public void addDoor(Door door){
        int x;
        int y;

        switch(door.direction) {
            case "North":
                x = 1;
                y = 0;
                break;
            case "South":
                x = 1;
                y = 2;
                break;
            case "West":
                x = 0;
                y = 1;
                break;
            case "East":
                x = 2;
                y = 1;
                break;
            case "North East":
                x = 2;
                y = 0;
                break;
            case "North West":
                x = 0;
                y = 0;
                break;
            case "South East":
                x = 2;
                y = 2;
                break;
            case "South West":
                x = 0;
                y = 2;
                break;
            case "Up":
                x = 1;
                y = 1;
                break;
            case "Down":
                x = 3;
                y = 0;
                break;
            case "In":
            case "Enter":
                x = 3;
                y = 1;
                if (doors[x][y] != null) { y = 3; }
                break;
            case "Out":
            case "Exit":
                x = 3;
                y = 2;
                if (doors[x][y] != null) { x = 2; y = 3; }
                break;
            default:
                x = -1;
                y = -1;
                break;
        }
        if (walls[x][y] == null && doors[x][y] == null) {
            doors[x][y] = door;
        }
    }

    // Adds an object to this room.
    // @PARAM: objectName: Object to be added.
    // @RETURN: none
    public void addObject(ZorkObj objectName) { this.objects.add(objectName); }

    // Adds a character to this room.
    // @PARAM: characterName: Character to be added.
    // @RETURN: none
    public void addCharacter(Character characterName) { this.characters.add(characterName); }

    // Returns the connected room of a door object.
    // @PARAM: direction: Direction the player has inputted.
    // @RETURN: door[][]: Door that is located on this (x,y) space.
    //          null: Returned if there is no door in the specified (x,y) space.
    public Door getConnectedRoom(String direction){
        switch (direction){
            case "north":
            case "n":
                if (walls[1][0] == null)
                    return doors[1][0];
                else if (doors[1][0] == null)
                    System.out.println("There is a wall in this direction.");
                return null;
            case "south":
            case "s":
                if (walls[1][2] == null)
                    return doors[1][2];
                else if (doors[1][2] == null)
                    System.out.println("There is a wall in this direction.");
                return null;
            case "west":
            case "w":
                if (walls[0][1] == null)
                    return doors[0][1];
                else if (doors[0][1] == null)
                    System.out.println("There is a wall in this direction.");
                return null;
            case "east":
            case "e":
                if (walls[2][1] == null)
                    return doors[2][1];
                else if (doors[2][1] == null)
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
        }
        return null;
    }

    // Returns the connected room of a door object when given a direction that is up, down, enter, exit, or use.
    public Door getConnectedRoom(String dir, String doorName) {
        switch(dir) {
            case "up":
                if (walls[1][1] == null)
                    if (doors[1][1].name.equals(doorName) && !doors[1][1].getLocked())
                        return doors[1][1];
                    else
                        System.out.println("The door is locked.");
                else if (doors[1][1] == null)
                    System.out.println("There is nothing in this direction.");
                return null;
            case "down":
                if (walls[3][0] == null)
                    if (doors[3][0].name.equals(doorName) && !doors[3][0].getLocked())
                        return doors[3][0];
                    else
                        System.out.println("The door is locked.");
                else if (doors[3][0] == null)
                    System.out.println("The floor is the only thing in this direction.");
                return null;
            case "in":
            case "enter":
                if (walls[3][1] == null) {
                    if (doors[3][1].name.equals(doorName) && !doors[3][1].getLocked()) {
                        return doors[3][1];
                    }
                    else if (walls[3][3] == null) {
                        if (doors[3][3].name.equals(doorName) && !doors[3][3].getLocked())
                            return doors[3][3];
                    }
                    else
                        System.out.println("The door is locked.");
                }
                else if (doors[3][1] == null && doors[3][3] == null)
                    System.out.println("There is nothing to get inside of.");
                return null;
            case "out":
            case "exit":
                if (walls[3][2] == null) {
                    if (doors[3][2].name.equals(doorName) && !doors[3][2].getLocked()) {
                        return doors[3][2];
                    }
                    else if (walls[2][3] == null) {
                        if (doors[2][3].name.equals(doorName) && !doors[2][3].getLocked())
                            return doors[2][3];
                    }
                    else
                        System.out.println("The door is locked.");
                }
                else if (doors[3][2] == null && doors[2][3] == null)
                    System.out.println("There is nothing to get out of.");
                return null;
            case "use":
            case "break":
                if (walls[3][1] == null) {
                    if (doors[3][1].name.equals(doorName) && doors[3][1].getLocked()) {
                        return doors[3][1];
                    }
                    else if (walls[3][3] == null) {
                        if (doors[3][3].name.equals(doorName) && doors[3][3].getLocked())
                            return doors[3][3];
                    }
                }
                else if (doors[3][1] == null && doors[3][3] == null)
                    System.out.println("There is nothing to get inside of.");
                return null;
        }
        return null;
    }
}