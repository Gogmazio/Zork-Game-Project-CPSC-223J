import java.util.ArrayList;
import java.util.Scanner;

// Parse inputs from the player
public class Parser {
    private Scanner inputScanner;                                    // Scans input from the player
    private static String playerInput;                               // Stores input from the player
    private static String action;                                    // Stores the action of the input
    private static String object;                                    // Stores the object to do action on of the input
    private final ArrayList<String> DIRECTIONS = new ArrayList<String>(); // Stores all directions that player can go
    private ArrayList<String[]> prevInputs = new ArrayList<>();      // Stores previous inputs from player

    public Parser(){
        inputScanner = new Scanner(System.in);
        DIRECTIONS.add("north");
        DIRECTIONS.add("south");
        DIRECTIONS.add("east");
        DIRECTIONS.add("west");
        DIRECTIONS.add("northeast");
        DIRECTIONS.add("northwest");
        DIRECTIONS.add("southeast");
        DIRECTIONS.add("southwest");
        DIRECTIONS.add("n");
        DIRECTIONS.add("s");
        DIRECTIONS.add("e");
        DIRECTIONS.add("w");
        DIRECTIONS.add("ne");
        DIRECTIONS.add("nw");
        DIRECTIONS.add("se");
        DIRECTIONS.add("sw");
        DIRECTIONS.add("up");
        DIRECTIONS.add("down");
        DIRECTIONS.add("in");
        DIRECTIONS.add("out");
    }

    // Takes input from the player and stores it in the playerInput variable.
    public void getPlayerInput() {
        System.out.println("Enter a Command: ");
        this.playerInput = inputScanner.nextLine();
    }

    // Parses the input from the user
    public void parseInput(Player player) {
        String[] splitInput = this.playerInput.split(" ", 2); // Splits the input into an action and an object.

        prevInputs.add(splitInput);
        action = splitInput[0];                     // Stores the action that the user inputs
        object = splitInput[splitInput.length - 1]; // Stores the object that the user inputs

        // For Testing
//        System.out.println(action);
//        System.out.println(object);

        // Parses single word inputs
        if (splitInput.length == 1) {
            switch (action.toLowerCase()) {
                case "again":
                case "g":
                    parsePrevInput(player, prevInputs.get(prevInputs.size() - 2));
                    return;
                case "i":
                case "inventory":
                case "inv":
                    player.getInventory();
                    return;
                case "diagnose":
                    player.diagnosePlayer();
                    return;
                case "hi":
                case "hello":
                    System.out.println("You said 'Hello'");
                    return;
                case "jump":
                    System.out.println("For whatever reason, you jumped in place.");
                    return;
                case "score":
                    System.out.println("SCORE: " + player.getScore());
                    return;
                case "quit":
                case "q":
                    System.exit(0);
                case "wait":
                    return;
                default:
                    if (DIRECTIONS.contains(object.toLowerCase()))
                        if (player.getLocation().getConnectedRoom(object.toLowerCase()) != null)
                            player.enterDoor(player.getLocation().getConnectedRoom(object.toLowerCase()));
                    return;
            }
        }

        // Parses two word inputs
        if (splitInput.length == 2)
            switch(action.toLowerCase()) {
                case "go":
                    if (DIRECTIONS.contains(object.toLowerCase()))
                        if (player.getLocation().getConnectedRoom(object.toLowerCase()) != null)
                            player.enterDoor(player.getLocation().getConnectedRoom(object.toLowerCase()));
                    return;
                case "get":
                case "take":
                    if (player.getLocation().getObj(object) != null)
                        player.addItemToInventory(player.getLocation().getObj(object));
                    else
                        System.out.println("There is no item named " + object + " in this area.");
                    return;
                default:
                    System.out.println("Invalid Command");
                    return;
            }
    }

    public void parsePrevInput(Player player, String[] input) {
        prevInputs.add(input);
        action = input[0];                     // Stores the action that the user inputs
        object = input[input.length - 1]; // Stores the object that the user inputs

        // Parses single word inputs
        if (input.length == 1) {
            switch (action.toLowerCase()) {
                case "again":
                case "g":
                    parsePrevInput(player, prevInputs.get(prevInputs.size() - 2));
                    return;
                case "i":
                case "inventory":
                case "inv":
                    player.getInventory();
                    return;
                default:
                    if (DIRECTIONS.contains(object.toLowerCase()))
                        if (player.getLocation().getConnectedRoom(object.toLowerCase()) != null)
                            player.enterDoor(player.getLocation().getConnectedRoom(object.toLowerCase()));
            }
            return;
        }

        // Parses two word inputs
        if (input.length == 2)
            switch(action.toLowerCase()) {
                case "go":
                    if (DIRECTIONS.contains(object.toLowerCase()))
                        if (player.getLocation().getConnectedRoom(object.toLowerCase()) != null)
                            player.enterDoor(player.getLocation().getConnectedRoom(object.toLowerCase()));
                    return;
                case "get":
                case "take":
                    if (player.getLocation().getObj(object) != null)
                        player.addItemToInventory(player.getLocation().getObj(object));
                    else
                        System.out.println("There is no item named " + object + " in this area.");
                    return;
                default:
                    System.out.println("Invalid Command");
                    return;
            }
    }

    public String getCurrentInput() {
        return this.playerInput;
    }
}
