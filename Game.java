// Jeffrey Chen
// CPSC 223J
// November 1, 2020

import java.util.Scanner;

public class Game {
    private static Player player = new Player();
    private final static Parser inputParser = new Parser();
    private final static LocationList maps = new LocationList();
    private final static ItemList items = new ItemList();
    private final static WeaponList weps = new WeaponList();
    private final static Map gameMap = new Map();

    // Creates game map
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
        gameMap.getRoom(maps.wOfHouse.roomID).addObject(new Key(items.key.name, items.key.desc, items.key.descVer));
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
        gameMap.getRoom(maps.insideHouse.roomID).addObject(new Table(items.carpet.name, items.carpet.desc, items.carpet.descVer, items.carpet.ID));
        gameMap.getRoom(maps.eastForest.roomID).addDoor(new Door("toWofHouse", "East", gameMap.getRoom(maps.wOfHouse.roomID), false, false));
        gameMap.getRoom(maps.eastForest.roomID).addDoor(new Door("toOrcLair", "South", gameMap.getRoom(maps.orcLair.roomID), false, false));
        gameMap.getRoom(maps.eastForest.roomID).addDoor(new Door("toCentralForest", "West", gameMap.getRoom(maps.centralForest.roomID), false, false));
        gameMap.getRoom(maps.eastForest.roomID).addDoor(new Door("toEastForest", "North", gameMap.getRoom(maps.eastForest.roomID), false, false));
        gameMap.getRoom(maps.eastForest.roomID).addObject(new Axe(weps.axe.name, weps.axe.weaponType, weps.axe.desc, weps.axe.descVer, weps.axe.attackPower, weps.axe.ID));
        gameMap.getRoom(maps.centralForest.roomID).addDoor(new Door("toEastForest", "East", gameMap.getRoom(maps.eastForest.roomID), false, false));
        gameMap.getRoom(maps.centralForest.roomID).addObject(new GoldTrinket(items.goldTrinket.name, items.goldTrinket.desc, items.goldTrinket.descVer, items.goldTrinket.ID));
        gameMap.getRoom(maps.centralForest.roomID).addCharacter(new ElfGuard());
        gameMap.getRoom(maps.orcLair.roomID).addDoor(new Door("toEastForest", "North", gameMap.getRoom(maps.eastForest.roomID), false, false));
        gameMap.getRoom(maps.orcLair.roomID).addObject(new SapphireCrystal(items.sapphire.name, items.sapphire.desc, items.sapphire.descVer, items.sapphire.ID));
        gameMap.getRoom(maps.orcLair.roomID).addCharacter(new Orc());
        gameMap.getRoom(maps.houseArmory.roomID).addDoor(new Door("stairs", "Up", gameMap.getRoom(maps.houseArmoryUp.roomID), false, false));
        gameMap.getRoom(maps.houseArmory.roomID).addDoor(new Door("toLiving", "West", gameMap.getRoom(maps.insideHouse.roomID), false, false));
        gameMap.getRoom(maps.houseArmory.roomID).addObject(new Spear(weps.spear.name, weps.spear.weaponType, weps.spear.desc, weps.spear.descVer, weps.spear.attackPower, weps.spear.ID));
        gameMap.getRoom(maps.houseArmoryUp.roomID).addDoor(new Door("stairs", "Down", gameMap.getRoom(maps.houseArmory.roomID), false, false));
        gameMap.getRoom(maps.houseArmoryUp.roomID).addObject(new Sword(weps.sword.name, weps.sword.weaponType, weps.sword.desc, weps.sword.descVer, weps.sword.attackPower, weps.sword.ID));
        gameMap.getRoom(maps.houseArmoryUp.roomID).addObject(new Diamond(items.diamond.name, items.diamond.desc, items.diamond.descVer, items.diamond.ID));
        gameMap.getRoom(maps.houseStorage.roomID).addDoor(new Door("cellar", "Enter", gameMap.getRoom(maps.houseStorageDown.roomID), true, false));
        gameMap.getRoom(maps.houseStorage.roomID).addDoor(new Door("toLiving", "South", gameMap.getRoom(maps.insideHouse.roomID), false, false));
        gameMap.getRoom(maps.houseStorageDown.roomID).addDoor(new Door("stairs", "Up", gameMap.getRoom(maps.houseStorage.roomID), false, false));
        gameMap.getRoom(maps.houseStorageDown.roomID).addObject(new Shield(weps.shield.name, weps.shield.weaponType, weps.shield.desc, weps.shield.descVer, weps.shield.attackPower, weps.shield.ID));
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

    // Sets what hint should show up according to the player's progression
    private static void hint() {
        // Flags for the cheat mode.
        if (player.inInventory("KEY") && player.getHintFlag(0))
            player.incrementHintNum();
        else if ((player.inInventory("SWORD") && player.inInventory("DIAMOND") && player.getHintFlag(1)))
            player.incrementHintNum();
        else if ((player.inInventory("SHIELD") && player.getHintFlag(2)))
            player.incrementHintNum();
        else if (player.inEquip("SWORD") && player.inEquip("SHIELD") && player.getHintFlag(3))
            player.incrementHintNum();
        else if (player.getLocation().getRoomName().equals("frontOfHouse") && player.getHintFlag(4))
            player.incrementHintNum();
        else if (player.inInventory("GOLDEN TRINKET") && player.getHintFlag(5))
            player.incrementHintNum();
        else if (player.inInventory("SAPPHIRE CRYSTAL") && player.getHintFlag(6))
            player.incrementHintNum();
    }

    public static void main(String[] args) {
        Start();
        boolean running = true;
        while (running) {
            hint();
            if (!player.getDialogState().equals("autoplay")) {
                System.out.println("MOVES: " + player.getMoves());
                inputParser.getPlayerInput();
                inputParser.parseInput(gameMap, player);
            }
            else if (player.getDialogState().equals("autoplay")) {
                inputParser.auto(gameMap, player);
            }

            if (player.getScore() > 17 || player.getHealth() < 1) {
                running = false;
            }
        }
        if (player.getScore() > 17) {
            System.out.println("You won!\n" + "SCORE: " + player.getScore() + "\nMOVES: " + player.getMoves());
            System.exit(0);
        }

        player.die();
        Scanner inputScanner = new Scanner(System.in);
        String playerInput = inputScanner.nextLine();
        if (playerInput.toUpperCase().equals("Y")) {
            main(args);
        }
        else
            System.out.println("Thank you for playing!");
        System.exit(0);
    }
}