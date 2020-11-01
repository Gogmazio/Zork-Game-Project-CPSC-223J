import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

// Parse inputs from the player
public class Parser {
    private final String VERSION;
    private final String ANSI_RESET = "\u001B[0m";
    private final Scanner inputScanner;                              // Scans input from the player
    private static String playerInput;                               // Stores input from the player
    private final ArrayList<String[]> prevInputs = new ArrayList<>();// Stores previous inputs from player
    private final String[] autoInputs = new String [] {
            "take all", "e", "enter window", "e", "go up stairs", "take all", "go down stairs",
            "w", "n", "use key on cellar", "enter cellar", "take all", "go up stairs", "s",
            "exit window", "w", "w", "equip sword", "equip shield", "s", "attack orc", "g",
            "take all", "n", "w", "attack elven guard", "g", "take all"
    };

    public Parser(){
        inputScanner = new Scanner(System.in);
        this.VERSION = "1.0";
    }

    // Takes input from the player and stores it in the playerInput variable.
    public void getPlayerInput() {
        System.out.println("Enter a Command: ");
        playerInput = inputScanner.nextLine();
    }

    // Parses the input from the user
    public void parseInput(Map gameMap, Player player) {
        String[] splitInput = playerInput.split(" ", 0); // Splits the input into an action and an object.
        String action;                                              // Stores the action that the user input
        String predic;                                              // Stores the predicate of the input
        String object;                                              // Stores the object to do action on of the input
        String object2;                                             // Stores extra words from the input
        String ANSI_RED = "\u001B[31m";                             // Red text color
        String ANSI_BLUE = "\u001B[34m";                            // Blue text color
        prevInputs.add(splitInput);

        // Parses single word inputs
        // Stores the action of the input
        if (splitInput.length == 1) {
            action = splitInput[0];
            switch (action.toLowerCase()) {
                case "cheat":
                    System.out.println(player.getHint());
                    break;
                case "superbrief":
                    System.out.println("Dialogue length set to superbrief.");
                    player.changeDialogueState("superbrief");
                    break;
                case "brief":
                    System.out.println("Dialogue length set to brief.");
                    player.changeDialogueState("brief");
                    break;
                case "verbose":
                    System.out.println("Dialogue length set to verbose.");
                    player.changeDialogueState("verbose");
                    break;
                case "autoplay":
                case "auto":
                    System.out.println("Turning on autoplay.");
                    player.changeDialogueState("autoplay");
                    break;
                case "again":
                case "g":
                    prevInputs.remove(splitInput);
                    playerInput = "";
                    for (int i = 0; i < prevInputs.get(prevInputs.size() - 1).length; i++) {
                        playerInput = playerInput.concat(prevInputs.get(prevInputs.size() - 1)[i]);
                        playerInput = playerInput.concat(" ");
                    }
                    parseInput(gameMap, player);
                    return;
                case "i":
                case "inventory":
                case "inv":
                    player.getInventory();
                    break;
                case "look":
                case "l":
                    player.checkArea();
                    player.incrementTurn();
                    break;
                case "equipment":
                    player.getEquipment();
                    break;
                case "diagnose":
                    player.diagnose();
                    player.incrementTurn();
                    break;
                case "hi":
                case "hello":
                    System.out.println("You said 'Hello'");
                    player.incrementTurn();
                    break;
                case "jump":
                    System.out.println("For whatever reason, you jumped in place.");
                    player.incrementTurn();
                    break;
                case "walk":
                    System.out.println("You walked around the area but you've already seen everything.");
                    player.incrementTurn();
                    break;
                case "save":
                    System.out.println("Saving Game...");
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    }
                    catch (Exception e) {
                        System.out.println("Error: " + e);
                    }
                    player.save();
                    break;
                case "restore":
                    System.out.println("Loading Game...");
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    }
                    catch (Exception e) {
                        System.out.println("Error: " + e);
                    }
                    player.load(gameMap);
                    player.checkArea();
                    break;
                case "restart":
                    player.setHealth(0);
                    break;
                case "status":
                    player.getStatus();
                    break;
                case "score":
                    System.out.println("SCORE: " + player.getScore());
                    break;
                case "quit":
                case "q":
                    System.out.print("Thank you for playing!");
                    System.exit(0);
                case "wait":
                case "z":
                case "stay":
                    System.out.println("You stood in place.");
                    player.incrementTurn();
                    break;
                case "north":
                case "south":
                case "east":
                case "west":
                case "northeast":
                case "northwest":
                case "southeast":
                case "southwest":
                case "n":
                case "s":
                case "e":
                case "w":
                case "ne":
                case "nw":
                case "se":
                case "sw":
                    if (player.getLocation().getConnectedRoom(action.toLowerCase()) != null)
                        player.enterDoor(player.getLocation().getConnectedRoom(action.toLowerCase()));
                    player.incrementTurn();
                    break;
                case "version":
                    System.out.println("Version: " + VERSION);
                    return;
                default:
                    System.out.println("Invalid Command");
                    return;
            }
        }

        // Parses two word inputs
        if (splitInput.length == 2) {
            action = splitInput[0];                     // Stores the action that the user inputs
            object = splitInput[1];                     // Stores the object that the user inputs
            switch (action.toLowerCase()) {
                case "attack":
                    if (player.getLocation().isCharaInRoom(object.toUpperCase())) {
                        player.attack(player.getLocation().getChara(object.toUpperCase()));
                    }
                    else {
                        System.out.println("There is no " + ANSI_RED + object.toLowerCase() + ANSI_RESET + " here.");
                    }
                    player.incrementTurn();
                    break;
                case "go":
                    if (player.getLocation().getConnectedRoom(object.toLowerCase()) != null)
                        player.enterDoor(player.getLocation().getConnectedRoom(object.toLowerCase()));
                    else
                        System.out.println("There is nothing in that direction.");
                    player.incrementTurn();
                    break;
                case "enter":
                case "exit":
                    if (player.getLocation().getConnectedRoom(action.toLowerCase(), object.toLowerCase()) != null)
                        player.enterDoor(player.getLocation().getConnectedRoom(action.toLowerCase(), object.toLowerCase()));
                    player.incrementTurn();
                    break;
                case "break":
                    if (player.getLocation().getConnectedRoom(object.toLowerCase()) != null) {
                        if (player.getLocation().getConnectedRoom(object.toLowerCase()).getLocked()) {
                            player.getLocation().getConnectedRoom(object.toLowerCase()).unlock();
                            System.out.println("You broke down the door.");
                        } else
                            System.out.println("There is nothing to break down.");
                    }
                    else
                        System.out.println("There is nothing to break down.");
                    player.incrementTurn();
                    break;
                case "equip":
                    player.equipFromInv(object.toUpperCase());
                    player.incrementTurn();
                    break;
                case "unequip":
                    player.equipToInv(object.toUpperCase());
                    player.incrementTurn();
                    break;
                case "examine":
                    if (player.inInventory(object.toUpperCase()))
                        player.examineInv(player.getItem(object.toUpperCase()));
                    else if (player.inEquip(object.toUpperCase()))
                        player.examineInv(player.getEquip(object.toUpperCase()));
                    else
                        System.out.println("You do not have: " + ANSI_BLUE + object.toUpperCase() + ANSI_RESET);
                    break;
                case "read":
                    if (player.inInventory(object.toUpperCase()))
                        player.examineInv(player.getItem(object.toUpperCase()));
                    else
                        System.out.println("You do not have: " + ANSI_BLUE + object.toUpperCase() + ANSI_RESET);
                    break;
                case "get":
                case "take":
                    if (player.getLocation().isObjInRoom(object.toUpperCase())) {
                        ZorkObj zorkObj = player.getLocation().getObj(object.toUpperCase());
                        if (player.checkWeight(zorkObj))
                            player.addItemToInventory(zorkObj);
                        else {
                            System.out.println(ANSI_BLUE + zorkObj.getName() + ANSI_RESET + " is too heavy to pick up.");
                            player.getLocation().addObject(zorkObj);
                        }
                    }
                    else if (object.toLowerCase().equals("all")) {
                        int objectsInRoom = player.getLocation().getObjLength();

                        if (objectsInRoom == 0)
                            System.out.println("There are no items in this room.");

                        for (int i = 0; i < objectsInRoom; i++) {
                            ZorkObj zorkObj = player.getLocation().getObj();
                            if (player.checkWeight(zorkObj) && player.checkInvSize()){
                                player.addItemToInventory(zorkObj);
                            }
                            else if (!player.checkInvSize()) {
                                System.out.println("Your inventory is too full.");
                                player.getLocation().addObject(zorkObj);
                            }
                            else {
                                System.out.println(ANSI_BLUE + zorkObj.getName() + ANSI_RESET + " is too heavy to pick up.");
                                player.getLocation().addObject(zorkObj);
                            }
                        }
                    }
                    else
                        System.out.println("There is no item named " + ANSI_BLUE + object + ANSI_RESET + " in this area.");
                    player.incrementTurn();
                    break;
                case "drop":
                    player.removeItemFromChara(object.toUpperCase());
                    player.incrementTurn();
                    break;
                default:
                    System.out.println("Invalid Command");
                    return;
            }
        }

        else if (splitInput.length == 3) {
            action = splitInput[0];                     // Stores the action that the user inputs
            predic = splitInput[1];                     // Stores the predicate of the input
            object = splitInput[2];                     // Stores the object that the user inputs
            switch (action.toLowerCase()) {
                case "attack":
                    if (player.getLocation().isCharaInRoom((predic + " " + object).toUpperCase())) {
                        player.attack(player.getLocation().getChara((predic + " " + object).toUpperCase()));
                    }
                    else {
                        System.out.println("There is no " + ANSI_RED + (predic + " " + object).toLowerCase() + ANSI_RESET + " here.");
                    }
                    player.incrementTurn();
                    break;
                case "go":
                    switch (predic.toLowerCase()) {
                        case "down":
                        case "up":
                        case "in":
                        case "out":
                            if (player.getLocation().getConnectedRoom(predic.toLowerCase(), object.toLowerCase()) != null)
                                player.enterDoor(player.getLocation().getConnectedRoom(predic.toLowerCase(), object.toLowerCase()));
                            player.incrementTurn();
                            break;
                        default:
                            System.out.println("Invalid Command.");
                            break;
                    }
                    break;
                case "look":
                    switch (predic.toLowerCase()) {
                        case "inside":
                            if (player.getLocation().isObjInRoom(object.toUpperCase()))
                                System.out.println(player.getLocation().getObjNoDel(object.toUpperCase()).getInsideDesc());
                            else if(player.getLocation().isCharaInRoom((object).toUpperCase()))
                                System.out.println(player.getLocation().getChara((object).toUpperCase()).getInsideDesc());
                            else
                                System.out.println("Object is not found in this area.");
                            player.incrementTurn();
                            break;
                        case "under":
                        case "below":
                            if (player.getLocation().isObjInRoom(object.toUpperCase()))
                                System.out.println(player.getLocation().getObjNoDel(object.toUpperCase()).getUnderDesc());
                            else if(player.getLocation().isCharaInRoom((object).toUpperCase()))
                                System.out.println(player.getLocation().getChara((object).toUpperCase()).getUnderDesc());
                            else
                                System.out.println("Object is not found in this area.");
                            player.incrementTurn();
                            break;
                        case "behind":
                            if (player.getLocation().isObjInRoom(object.toUpperCase()))
                                System.out.println(player.getLocation().getObjNoDel(object.toUpperCase()).getBehindDesc());
                            else if(player.getLocation().isCharaInRoom((object).toUpperCase()))
                                System.out.println(player.getLocation().getChara((object).toUpperCase()).getBehindDesc());
                            else
                                System.out.println("Object is not found in this area.");
                            player.incrementTurn();
                            break;
                        case "through":
                            if (player.getLocation().isObjInRoom(object.toUpperCase()))
                                System.out.println(player.getLocation().getObjNoDel(object.toUpperCase()).getThroughDesc());
                            else if(player.getLocation().isCharaInRoom((object).toUpperCase()))
                                System.out.println(player.getLocation().getChara((object).toUpperCase()).getThroughDesc());
                            else
                                System.out.println("Object is not found in this area.");
                            player.incrementTurn();
                            break;
                        case "at":
                            if (player.getLocation().isObjInRoom(object.toUpperCase()))
                                System.out.println(player.getLocation().getObjNoDel(object.toUpperCase()).getDescription());
                            else if (player.getLocation().isCharaInRoom(object.toUpperCase()))
                                player.examineChara(player.getLocation().getChara(object.toUpperCase()));
                            else
                                System.out.println("Object is not found in this area.");
                            player.incrementTurn();
                            break;
                        default:
                            System.out.println("Invalid Command.");
                            return;
                    }
                    break;
                case "talk":
                case "speak":
                    if ("to".equals(predic)) {
                        if (player.getLocation().isObjInRoom(object.toUpperCase()))
                            System.out.println(player.getLocation().getObjNoDel(object.toUpperCase()).getDialogue());
                        else if (player.getLocation().isCharaInRoom(object.toUpperCase()))
                            System.out.println(player.getLocation().getChara(object.toUpperCase()).getDialogue());
                        else
                            System.out.println("There is nothing to talk to.");
                        player.incrementTurn();
                    } else {
                        System.out.println("Invalid Command.");
                    }
                    break;
                case "in":
                case "enter":
                    if (player.getLocation().getConnectedRoom(action.toLowerCase(), object.toLowerCase()) != null)
                        player.enterDoor(player.getLocation().getConnectedRoom(action.toLowerCase(), object.toLowerCase()));
                    else
                        System.out.println("There is nothing to enter.");
                    player.incrementTurn();
                    break;
                case "out":
                case "exit":
                    if (player.getLocation().getConnectedRoom(action.toLowerCase(), object.toLowerCase()) != null)
                        player.enterDoor(player.getLocation().getConnectedRoom(action.toLowerCase(), object.toLowerCase()));
                    else
                        System.out.println("There is nothing to exit.");
                    player.incrementTurn();
                    break;
                case "get":
                case "take":
                    if (predic.toLowerCase().equals("off")) {
                            player.equipToInv(object.toUpperCase());
                            player.incrementTurn();
                            break;
                    }
                    else if (player.getLocation().getObj((predic + object).toUpperCase()) != null) {
                        player.addItemToInventory(player.getLocation().getObj((predic + object).toUpperCase()));
                        System.out.println("You have picked up: " + predic + " " + object);
                    }
                    else
                        System.out.println("There is no item named " + predic + " " + object + " in this area.");
                    player.incrementTurn();
                    break;
                default:
                    System.out.println("Invalid Command");
                    return;
            }
        }

        else if (splitInput.length == 4) {
            action = splitInput[0];                     // Stores the action that the user inputs
            predic = splitInput[1];                     // Stores the predicate of the input
            object = splitInput[2];                     // Stores the object that the user inputs
            object2 = splitInput[3];                    // Stores extra words that the user inputs
            switch (action.toLowerCase()) {
                case "attack":
                    if (player.getLocation().isCharaInRoom((predic + " " + object + " " + object2).toUpperCase())) {
                        player.attack(player.getLocation().getChara((predic + " " + object + " " + object2).toUpperCase()));
                    }
                    else {
                        System.out.println("There is no " + ANSI_RED + (predic + " " + object + " " + object2).toLowerCase() + ANSI_RESET + " here.");
                    }
                    player.incrementTurn();
                    break;
                case "look":
                    switch (predic.toLowerCase()) {
                        case "inside":
                            if (player.getLocation().isObjInRoom((object + " " + object2).toUpperCase()))
                                System.out.println(player.getLocation().getObjNoDel((object + " " + object2).toUpperCase()).getInsideDesc());
                            else if(player.getLocation().isCharaInRoom(((object + " " + object2)).toUpperCase()))
                                System.out.println(player.getLocation().getChara(((object + " " + object2)).toUpperCase()).getInsideDesc());
                            else
                                System.out.println("Object is not found in this area.");
                            player.incrementTurn();
                            break;
                        case "under":
                        case "below":
                            if (player.getLocation().isObjInRoom((object + " " + object2).toUpperCase()))
                                System.out.println(player.getLocation().getObjNoDel((object + " " + object2).toUpperCase()).getUnderDesc());
                            else if(player.getLocation().isCharaInRoom(((object + " " + object2)).toUpperCase()))
                                System.out.println(player.getLocation().getChara(((object + " " + object2)).toUpperCase()).getUnderDesc());
                            else
                                System.out.println("Object is not found in this area.");
                            player.incrementTurn();
                            break;
                        case "behind":
                            if (player.getLocation().isObjInRoom((object + " " + object2).toUpperCase()))
                                System.out.println(player.getLocation().getObjNoDel((object + " " + object2).toUpperCase()).getBehindDesc());
                            else if(player.getLocation().isCharaInRoom(((object + " " + object2)).toUpperCase()))
                                System.out.println(player.getLocation().getChara(((object + " " + object2)).toUpperCase()).getBehindDesc());
                            else
                                System.out.println("Object is not found in this area.");
                            player.incrementTurn();
                            break;
                        case "through":
                            if (player.getLocation().isObjInRoom((object + " " + object2).toUpperCase()))
                                System.out.println(player.getLocation().getObjNoDel((object + " " + object2).toUpperCase()).getThroughDesc());
                            else if(player.getLocation().isCharaInRoom(((object + " " + object2)).toUpperCase()))
                                System.out.println(player.getLocation().getChara(((object + " " + object2)).toUpperCase()).getThroughDesc());
                            else
                                System.out.println("Object is not found in this area.");
                            player.incrementTurn();
                            break;
                        case "at":
                            if (player.getLocation().isObjInRoom((object + " " + object2).toUpperCase()))
                                System.out.println(player.getLocation().getObjNoDel((object + " " + object2).toUpperCase()).getDescription());
                            else if (player.getLocation().isCharaInRoom((object + " " + object2).toUpperCase()))
                                player.examineChara(player.getLocation().getChara((object + " " + object2).toUpperCase()));
                            else
                                System.out.println("Object is not found in this area.");
                            player.incrementTurn();
                            break;
                        default:
                            System.out.println("Invalid Command.");
                            break;
                    }
                    break;
                case "talk":
                case "speak":
                    if ("to".equals(predic)) {
                        if (player.getLocation().isObjInRoom((object + " " + object2).toUpperCase()))
                            System.out.println(player.getLocation().getObjNoDel((object + " " + object2).toUpperCase()).getDialogue());
                        else if (player.getLocation().isCharaInRoom((object + " " + object2).toUpperCase()))
                            System.out.println(player.getLocation().getChara((object + " " + object2).toUpperCase()).getDialogue());
                        else
                            System.out.println("There is nothing to talk to.");
                        player.incrementTurn();
                    } else {
                        System.out.println("Invalid Command.");
                    }
                    break;
                case "use":
                    if (predic.toUpperCase().equals("KEY")) {
                        if (object.toUpperCase().equals("ON")) {
                            if (player.getLocation().getConnectedRoom(action.toLowerCase(), object2.toLowerCase()) != null) {
                                if (player.getLocation().getConnectedRoom(action.toLowerCase(), object2.toLowerCase()).getLocked()) {
                                    player.getLocation().getConnectedRoom(action.toLowerCase(), object2.toLowerCase()).unlock();
                                    System.out.println("You unlocked the door.");
                                } else
                                    System.out.println("You do not have a key.");
                            } else {
                                if (player.inInventory("KEY"))
                                    System.out.println("There is nothing to use a key on.");
                                else
                                    System.out.println("You do not have a key.");
                            }
                        }
                    }
                    player.incrementTurn();
                    break;
                default:
                    System.out.println("Invalid Command");
                    return;
            }
        }
        else if (splitInput.length > 4) {
            System.out.println("Invalid Command");
            return;
        }

        // Makes the enemies that are in the 'aggressive' state in the same room as the player attack the player.
        ArrayList<Character> NPCsInRoom = player.getLocation().getCharacters();
        if (NPCsInRoom.size() > 0) {
            for (Character character : NPCsInRoom) {
                if (character.getHealth() > 0 && character.getState().getStateName().equals("agg")) {
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (Exception e) {
                        System.out.println("Error: " + e);
                    }
                    System.out.println(ANSI_RED + character.getName() + ANSI_RESET + " is attacking!");
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (Exception e) {
                        System.out.println("Error: " + e);
                    }
                    character.attack(player);
                }
            }
        }
    }

    public void auto(Map map, Player player) {
        String ANSI_YELLOW = "\u001B[33m";
        for (String autoInput : autoInputs) {
            playerInput = autoInput;
            System.out.println("Moves: " + player.getMoves());
            System.out.println(ANSI_YELLOW + playerInput + ANSI_RESET);
            parseInput(map, player);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (Exception e) {
                System.out.println("Error: " + e);
            }
        }
    }
}
