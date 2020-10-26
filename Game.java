// To DO:
// relocate the flooring system for up down, in out movement.
// create map of entire game
// add enemies, equipment, treasure, and puzzles to map
// add more parser terms
// add cheat mode/hint mode
// add autoplay mode

public class Game {
    private static Player player = new Player();
    private final static Parser inputParser = new Parser();
    private final static LocationList maps = new LocationList();
    private final static ItemList items = new ItemList();
    private final static WeaponList weps = new WeaponList();
    public final static Map gameMap = new Map();

    static void Start() {
        gameMap.addRoom(new Room(maps.wOfHouse.name, maps.wOfHouse.desc, maps.wOfHouse.descVer, maps.wOfHouse.type, maps.wOfHouse.roomID));
        gameMap.addRoom(new Room(maps.insideHouse.name, maps.insideHouse.desc, maps.insideHouse.descVer, maps.insideHouse.type, maps.insideHouse.roomID));
        gameMap.addRoom(new Room(maps.frontOfHouse.name, maps.frontOfHouse.desc, maps.frontOfHouse.descVer, maps.frontOfHouse.type, maps.frontOfHouse.roomID));
        gameMap.addRoom(new Room(maps.eastForest.name, maps.eastForest.desc, maps.eastForest.descVer, maps.eastForest.type, maps.eastForest.roomID));
        gameMap.addRoom(new Room(maps.centralForest.name, maps.centralForest.desc, maps.centralForest.descVer, maps.centralForest.type, maps.centralForest.roomID));
        gameMap.addRoom(new Room(maps.orcLair.name, maps.orcLair.desc, maps.orcLair.descVer, maps.orcLair.type, maps.orcLair.roomID));
        gameMap.addRoom(new Room(maps.houseStorage.name, maps.houseStorage.desc, maps.houseStorage.descVer, maps.houseStorage.type, maps.houseStorage.roomID));
        gameMap.addRoom(new Room(maps.houseStorageDown.name, maps.houseStorageDown.desc, maps.houseStorageDown.descVer, maps.houseStorageDown.type, maps.houseStorageDown.roomID));
        gameMap.addRoom(new Room(maps.houseArmory.name, maps.houseArmory.desc, maps.houseArmory.descVer, maps.houseArmory.type, maps.houseArmory.roomID));
        gameMap.addRoom(new Room(maps.houseArmoryUp.name, maps.houseArmoryUp.desc, maps.houseArmoryUp.descVer, maps.houseArmoryUp.type, maps.houseArmoryUp.roomID));
        gameMap.getRoom(maps.wOfHouse.roomID).addDoor(new Door("toFrontHouse", "East", gameMap.getRoom(maps.frontOfHouse.roomID), false, false));
        gameMap.getRoom(maps.wOfHouse.roomID).addDoor(new Door("toEastForest", "West", gameMap.getRoom(maps.eastForest.roomID), false, false));
        gameMap.getRoom(maps.wOfHouse.roomID).addObject(new Key(items.key.name, items.key.desc, items.key.descVer, items.key.ID));
        gameMap.getRoom(maps.wOfHouse.roomID).addObject(new MailBox(items.mailBox.name, items.mailBox.desc, items.mailBox.descVer, items.mailBox.ID));
        gameMap.getRoom(maps.wOfHouse.roomID).addObject(new Letter(items.letter.name, items.letter.desc, items.letter.descVer, items.letter.ID));
        gameMap.getRoom(maps.frontOfHouse.roomID).addDoor(new Door("toWofHouse", "West", gameMap.getRoom(maps.wOfHouse.roomID), false, false));
        gameMap.getRoom(maps.frontOfHouse.roomID).addDoor(new Door("house", "Enter", gameMap.getRoom(maps.insideHouse.roomID), true, false));
        gameMap.getRoom(maps.frontOfHouse.roomID).addDoor(new Door("window", "Enter", gameMap.getRoom(maps.insideHouse.roomID), false, false));
        gameMap.getRoom(maps.insideHouse.roomID).addDoor(new Door("house","Exit", gameMap.getRoom(maps.frontOfHouse.roomID), true, false));
        gameMap.getRoom(maps.insideHouse.roomID).addDoor(new Door("window","Exit", gameMap.getRoom(maps.frontOfHouse.roomID), false, false));
        gameMap.getRoom(maps.insideHouse.roomID).addDoor(new Door("toArmory", "East", gameMap.getRoom(maps.houseArmory.roomID), false, false));
        gameMap.getRoom(maps.insideHouse.roomID).addDoor(new Door("toStorage", "North", gameMap.getRoom(maps.houseStorage.roomID), false, false));
        gameMap.getRoom(maps.insideHouse.roomID).addObject(new Table(items.table.name, items.table.desc, items.table.descVer, items.table.ID));
        gameMap.getRoom(maps.eastForest.roomID).addDoor(new Door("toWofHouse", "East", gameMap.getRoom(maps.wOfHouse.roomID), false, false));
        gameMap.getRoom(maps.eastForest.roomID).addDoor(new Door("toOrcLair", "South", gameMap.getRoom(maps.orcLair.roomID), false, false));
        gameMap.getRoom(maps.eastForest.roomID).addDoor(new Door("toCentralForest", "West", gameMap.getRoom(maps.centralForest.roomID), false, false));
        gameMap.getRoom(maps.eastForest.roomID).addDoor(new Door("toEastForest", "North", gameMap.getRoom(maps.eastForest.roomID), false, false));
        gameMap.getRoom(maps.centralForest.roomID).addDoor(new Door("toEastForest", "East", gameMap.getRoom(maps.eastForest.roomID), false, false));
        gameMap.getRoom(maps.centralForest.roomID).addCharacter(new ElfGuard());
        gameMap.getRoom(maps.orcLair.roomID).addDoor(new Door("toEastForest", "North", gameMap.getRoom(maps.eastForest.roomID), false, false));
        gameMap.getRoom(maps.orcLair.roomID).addCharacter(new Orc());
        gameMap.getRoom(maps.houseArmory.roomID).addDoor(new Door("stairs", "Up", gameMap.getRoom(maps.houseArmoryUp.roomID), false, false));
        gameMap.getRoom(maps.houseArmory.roomID).addDoor(new Door("toLiving", "West", gameMap.getRoom(maps.insideHouse.roomID), false, false));
        gameMap.getRoom(maps.houseArmoryUp.roomID).addDoor(new Door("stairs", "Down", gameMap.getRoom(maps.houseArmory.roomID), false, false));
        gameMap.getRoom(maps.houseStorage.roomID).addDoor(new Door("cellar", "Enter", gameMap.getRoom(maps.houseStorageDown.roomID), true, false));
        gameMap.getRoom(maps.houseStorage.roomID).addDoor(new Door("toLiving", "South", gameMap.getRoom(maps.insideHouse.roomID), false, false));
        gameMap.getRoom(maps.houseStorageDown.roomID).addDoor(new Door("stairs", "Down", gameMap.getRoom(maps.houseStorage.roomID), false, false));
        gameMap.getRoom(maps.wOfHouse.roomID).addWalls();
        gameMap.getRoom(maps.insideHouse.roomID).addWalls();
        gameMap.getRoom(maps.frontOfHouse.roomID).addWalls();
        gameMap.getRoom(maps.eastForest.roomID).addWalls();
        gameMap.getRoom(maps.centralForest.roomID).addWalls();
        gameMap.getRoom(maps.orcLair.roomID).addWalls();
        gameMap.getRoom(maps.houseArmory.roomID).addWalls();
        gameMap.getRoom(maps.houseArmoryUp.roomID).addWalls();
        gameMap.getRoom(maps.houseStorage.roomID).addWalls();
        gameMap.getRoom(maps.houseStorageDown.roomID).addWalls(); // Remember to add doors

        player = new Player("Player", 5, gameMap.getRoom(0));

        player.checkArea();
    }

    public static void main(String[] args) {
        Start();
        boolean running = true;
        while(running) {
            System.out.println("MOVES: " + player.getMoves());
            inputParser.getPlayerInput();
            inputParser.parseInput(gameMap, player);

            if (player.getHealth() == 0) {
                running = false;
            }
        }
        player.die();
    }
}