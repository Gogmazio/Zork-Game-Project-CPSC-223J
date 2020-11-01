import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

interface DialogState {
    String getStateName();
    void diagnosePlayer(Player player);
    void examineInv(ZorkObj item);
    void examineChara(Character chara);
    void checkArea(Player player);
}

interface State {
    String getStateName();
}

abstract class Character {
    private State state;
    private String name;
    private String dialogue;
    private String desc;
    private String descVerb;
    private String underDesc;
    private String insideDesc;
    private String throughDesc;
    private String behindDesc;
    private int health;
    private int attack;
    private int defense;

    public Character() {
        this.name = "";
        this.dialogue = "";
        this.desc = "";
        this.descVerb = "";
        this.underDesc = "";
        this.insideDesc = "";
        this.throughDesc = "";
        this.behindDesc = "";
        this.health = 0;
        this.attack = 0;
        this.defense = 0;
        this.state = null;
    }

    public State getState() { return this.state; }
    public String getName() { return this.name; }
    public String getDialogue() { return this.dialogue; }
    public String getDescription() { return this.desc; }
    public String getDescriptionVerbose() { return this.descVerb; }
    public String getUnderDesc() { return this.underDesc; }
    public String getInsideDesc() { return this.insideDesc; }
    public String getThroughDesc() { return this.throughDesc;}
    public String getBehindDesc() { return this.behindDesc; }
    public int getHealth() { return this.health; }
    public int getAttack() { return this.attack; }
    public int getDefense() { return this.defense; }

    public void setState(State state) { this.state = state; }
    public void setName(String name) { this.name = name; }
    public void setDialogue(String dial) { this.dialogue = dial; }
    public void setDescription(String desc) { this.desc = desc; }
    public void setDescriptionVerbose(String descVerb) { this.descVerb = descVerb; }
    public void setUnderDesc(String unDesc) { this.underDesc = unDesc;}
    public void setInsideDesc(String inDesc) { this.insideDesc = inDesc;}
    public void setThroughDesc(String thDesc) { this.throughDesc = thDesc;}
    public void setBehindDesc(String beDesc) { this.behindDesc = beDesc;}
    public void setHealth(int health) { this.health = health; }
    public void setAttack(int attack) { this.attack = attack; }
    public void setDefense(int defense) { this.defense = defense; }
    public void isHit(int attackPower) {}
    public void attack(Character enemy) {}
    public void die() {}
}

class Player extends Character {
    private final String ANSI_RESET = "\u001B[0m";    // Default text color
    private final String ANSI_BLUE = "\u001B[34m";    // Blue text color
    private int score;
    private int moves;
    private Room location;
    private DialogState dialogueState;
    private ArrayList<ZorkObj> equipment;
    private ArrayList<ZorkObj> inventory;
    private int hintNum = 0;                          // Current hint that player is on
    private final int MAX_INVENTORY_SIZE = 6;
    private final int MAX_EQUIP_SIZE = 2;
    private final boolean[] HINT_FLAGS = new boolean[] {
            false, false, false, false, false, false, false
    }; // Stores which hints have been passed
    private final String[] HINTS = new String[]
            {"(Use 'take all' in the room that you start in.) [Do not go west yet.]",
            "(Go east and enter the house by using 'enter window'. Go east again and go up stairs to pick up the sword and the diamond.)",
            "(Go back to the living room and go north and open the cellar door by using 'Use key on cellar'. Enter the cellar and take the shield.)",
            "(Equip the sword and shield using 'equip sword' and 'equip shield'.)",
            "(Leave the house by going to the living room and use 'exit house'.)",
            "(Go west into the forest. Go east again and defeat the elven guard to take the golden trinket.)",
            "(Go east back into the forest and go south into the Orc's Lair. Defeat the orc and take the sapphire crystal.)"
            }; // Stores the text of the hints

    public Player() {
        super();
        this.score = 0;
        this.moves = 0;
        this.location = null;
        this.equipment = null;
        this.inventory = null;
        this.dialogueState = null;
    }

    public Player(String name, int health, Room start) {
        super();
        setName(name);
        setHealth(health);
        setAttack(1);
        setDefense(1);
        setDialogue("It's you!");
        setUnderDesc("The ground is underneath you.");
        setInsideDesc("You look into yourself and see a hero!");
        setBehindDesc("You feel a presence behind you...just kidding!");
        setThroughDesc("You try your hardest to see through yourself but nothing happens.");
        this.score = 0;
        this.moves = 0;
        this.location = start;
        this.equipment = new ArrayList<>();
        this.inventory = new ArrayList<>();
        this.dialogueState = new BriefState();
    }

    // get methods
    public int getScore() { return this.score; }
    public int getMoves() { return this.moves; }
    public Room getLocation() { return this.location; }
    public String getDialogState() { return this.dialogueState.getStateName(); }
    public String getHint() { return this.HINTS[hintNum]; }
    public boolean getHintFlag(int pos) { return !this.HINT_FLAGS[pos]; }
    public void getInventory() {
        assert inventory != null;

        if (inventory.size() == 0) {
            System.out.println("Your inventory is empty.");
            return;
        }

        for (ZorkObj zorkObj : inventory) {
            System.out.println(zorkObj.getName());
        }
    }
    public void getEquipment() {
        assert equipment != null;

        if (equipment.size() == 0) {
            System.out.println("Your inventory is empty.");
            return;
        }

        for (ZorkObj zorkObj : equipment) {
            System.out.println(zorkObj.getName());
        }
    }
    public ZorkObj getEquip(String name) {
        for (ZorkObj zorkObj : equipment) {
            if (zorkObj.getName().equals(name))
                return zorkObj;
        }
        return null;
    }
    public ZorkObj getItem(String name) {
        for (ZorkObj zorkObj : inventory) {
            if (zorkObj.getName().equals(name))
                return zorkObj;
        }
        return null;
    }
    public void getStatus() {
        String ANSI_YELLOW = "\u001B[33m";
        System.out.println("Name: " + ANSI_YELLOW + getName() + ANSI_RESET +
                "\nHealth: " + ANSI_YELLOW + getHealth() + ANSI_RESET +
                "\nAttack: " + ANSI_YELLOW + getAttack() + ANSI_RESET +
                "\nDefense: " + ANSI_YELLOW + getDefense() + ANSI_RESET);
    }

    // set methods
    public void setScore(int score){ this.score = score; }
    public void setMoves(int moves){ this.moves = moves; }
    public void setLocation(Room location) { this.location = location; }

    // Returns true if the string 'equip' is found within the player's equipment
    public boolean inEquip(String equip) {
        for (ZorkObj zorkObj : equipment) {
            if (zorkObj.getName().equals(equip))
                return true;
        }
        return false;
    }

    // Returns true if the string 'item' is found within the player's inventory
    public boolean inInventory(String item) {
        for (ZorkObj zorkObj : inventory) {
            if (zorkObj.getName().equals(item))
                return true;
        }
        return false;
    }

    // Subtracts the player's health according to the attack power of the enemy subtracted by the player's defense
    public void isHit(int attackPower) { setHealth(getHealth() - (attackPower - getDefense())); }

    // Attacks the enemy given in the parameter. Player's attacks have an effectiveness bonus of 2 attack power if
    // the player is fighting in a room type that is favorable to the player's equiped weapon(s)
    public void attack(Character enemy) {
        String ANSI_RED = "\u001B[31m"; // Red text color
        if (enemy.getHealth() < 1) {
            System.out.println("The " + ANSI_RED + enemy.getName() + ANSI_RESET + " is already dead.");
            return;
        }

        int EFF_BONUS = 2;
        switch(getLocation().getRoomType()) {
            case "Grassland":
                System.out.println("You hit the " + ANSI_RED + enemy.getName() + ANSI_RESET);
                if (inEquip("SWORD")) {
                    enemy.isHit(this.getAttack() + EFF_BONUS);
                }
                else {
                    enemy.isHit(this.getAttack());
                }
                break;
            case "Forest":
                System.out.println("You hit the " + ANSI_RED + enemy.getName() + ANSI_RESET);
                if (inEquip("SWORD") || inEquip("SPEAR")) {
                    enemy.isHit(this.getAttack() + EFF_BONUS);
                }
                else {
                    enemy.isHit(this.getAttack());
                }
                break;
            case "Cave":
                System.out.println("You hit the " + ANSI_RED + enemy.getName() + ANSI_RESET);
                if (inEquip("AXE")) {
                    enemy.isHit(this.getAttack() + EFF_BONUS);
                }
                else {
                    enemy.isHit(this.getAttack());
                }
                break;
            default:
                System.out.println("You hit the " + ANSI_RED + enemy.getName() + ANSI_RESET);
                enemy.isHit(this.getAttack());
                break;
        }
    }

    public void incrementTurn() {
        int MAX_HEALTH = 5;
        if (getMoves() % 3 == 0 && getHealth() < MAX_HEALTH) {
            setHealth(getHealth() + 1);
            System.out.println("You feel as if some of your wounds are healing.");
        }
        this.moves += 1;
    }

    public void incrementHintNum() {
        this.HINT_FLAGS[this.hintNum] = true;
        this.hintNum++;
    }

    public void enterDoor(Door door) {
        this.location = door.connectedTo;
        checkArea();
    }

    public void addItemToInventory(ZorkObj item) {
        assert this.inventory != null;

        if (this.inventory.size() > this.MAX_INVENTORY_SIZE) {
            System.out.println("You are currently have too many items. You need to drop some off.");
            return;
        }

        this.inventory.add(item);
        this.score += item.getPoints();
        System.out.println("You have picked up: " + ANSI_BLUE + item.getName() + ANSI_RESET);
    }

    public boolean checkWeight(ZorkObj item) { return ((item.getWeightLevel() < 2)); }
    public boolean checkInvSize() { return (this.inventory.size() < this.MAX_INVENTORY_SIZE); }

    public void removeItemFromChara(String item) {
        assert this.inventory != null;
        for (int i = 0; i < inventory.size(); i++) {
            if (inventory.get(i).getName().equals(item)) {
                this.inventory.remove(inventory.get(i));
                System.out.println("You have thrown away: " + ANSI_BLUE + item + ANSI_RESET);
                return;
            }
        }
        for (int i = 0; i < equipment.size(); i++) {
            if (equipment.get(i).getName().equals(item)) {
                this.equipment.remove(equipment.get(i));
                System.out.println("You have thrown away: " + ANSI_BLUE + item + ANSI_RESET);
                return;
            }
        }
        System.out.println("You do not currently have: " + ANSI_BLUE + item + ANSI_RESET);
    }

    public void addEquipment(ZorkObj equip) {
        assert this.inventory != null;

        if (this.equipment.size() >= this.MAX_EQUIP_SIZE) {
            System.out.println("You are currently equipping too many items. You need to take one off.");
            return;
        }

        this.equipment.add(equip);
        setAttack(equip.getAttackPower() + getAttack());
        setDefense(equip.getDefense() + getDefense());
        System.out.println("You have equipped: " + ANSI_BLUE + equip.getName() + ANSI_RESET);
    }

    public void equipFromInv(String equName) {
        if (this.equipment.size() >= this.MAX_EQUIP_SIZE) {
            System.out.println("You are currently equipping too many items. You need to take one off.");
            return;
        }

        for (int i = 0; i < inventory.size(); i++) {
            if (inventory.get(i).getName().equals(equName)) {
                addEquipment(inventory.get(i));
                this.inventory.remove(inventory.get(i));
                break;
            }
        }
    }

    public void equipToInv(String equName) {
        if (this.inventory.size() >= this.MAX_INVENTORY_SIZE) {
            System.out.println("You are currently equipping too many items. You need to take one off.");
            return;
        }

        for (int i = 0; i < equipment.size(); i++) {
            if (equipment.get(i).getName().equals(equName)) {
                System.out.println("You took off " + ANSI_BLUE + equipment.get(i).getName() + ANSI_RESET + " and put it in your bag.");
                this.inventory.add(equipment.get(i));
                this.equipment.remove(equipment.get(i));
                break;
            }
        }
    }

    public void changeDialogueState(String state) {
        switch(state) {
            case "superbrief":
                this.dialogueState = new SuperBriefState();
                return;
            case "brief":
                this.dialogueState = new BriefState();
                return;
            case "verbose":
                this.dialogueState = new VerboseState();
                return;
            case "autoplay":
                this.dialogueState = new AutoPlay();
        }
    }

    public void diagnose() { dialogueState.diagnosePlayer(this); }
    public void checkArea() { dialogueState.checkArea(this); }
    public void examineInv(ZorkObj item) { dialogueState.examineInv(item); }
    public void examineChara(Character chara) { dialogueState.examineChara(chara); }

    // Savefile is located in the 'Save' folder where the project is located in
    // Saves the player's name, health, attack power, defense, score, move count, dialogue state, location,
    // inventory, and equipment.
    public void save() {
        try {
            File saveFile = new File("Save\\SaveFile");
            FileWriter saveFileWriter = new FileWriter(saveFile);
            FileOutputStream itemSave = new FileOutputStream("Save\\ItemSaveFile");
            ObjectOutputStream itemSaveWriter = new ObjectOutputStream(itemSave);
            saveFileWriter.write(getName() + "\n");
            saveFileWriter.write(getHealth() + "\n");
            saveFileWriter.write(getAttack() + "\n");
            saveFileWriter.write(getDefense() + "\n");
            saveFileWriter.write(getScore() + "\n");
            saveFileWriter.write(getMoves() + "\n");
            saveFileWriter.write(getDialogState() + "\n");
            saveFileWriter.write(getLocation().getRoomID() + "\n");
            itemSaveWriter.writeObject(this.inventory);
            itemSaveWriter.writeObject(this.equipment);
            saveFileWriter.close();
            System.out.println("Game Saved.");
        }
        catch(Exception e) {
            System.out.println("Failed to Save.\n");
            e.printStackTrace();
        }
    }

    // Savefile is located in the 'Save' folder where the project is located in
    // Loads the player's name, health, attack power, defense, score, move count, dialogue state, location,
    // inventory, and equipment.
    public void load(Map gameMap) {
        try {
            File saveFile = new File("Save\\saveFile");
            Scanner readFile = new Scanner(saveFile);
            FileInputStream itemSave = new FileInputStream("Save\\itemSaveFile");
            ObjectInputStream readItemSave = new ObjectInputStream(itemSave);
            String data = readFile.nextLine();
            setName(data);
            data = readFile.nextLine();
            setHealth(Integer.parseInt(data));
            data = readFile.nextLine();
            setAttack(Integer.parseInt(data));
            data = readFile.nextLine();
            setDefense(Integer.parseInt(data));
            data = readFile.nextLine();
            setScore(Integer.parseInt(data));
            data = readFile.nextLine();
            setMoves(Integer.parseInt(data));
            data = readFile.nextLine();
            changeDialogueState(data);
            data = readFile.nextLine();
            setLocation(gameMap.getRoom(Integer.parseInt(data)));
            @SuppressWarnings("unchecked")
            ArrayList<ZorkObj> zorkItemData = (ArrayList<ZorkObj>)readItemSave.readObject();
            this.inventory = zorkItemData;
            @SuppressWarnings("unchecked")
            ArrayList<ZorkObj> zorkEquipData = (ArrayList<ZorkObj>)readItemSave.readObject();
            this.equipment = zorkEquipData;
            System.out.println("Game Loaded.");
        }
        catch (Exception e) {
            System.out.println("Failed to Load.\n");
            e.printStackTrace();
        }
    }

    public void die() {
        System.out.println("You died.");
        System.out.println("Restart? (Y/N)");
    }
}

class Orc extends Character {
    String ANSI_RESET = "\u001B[0m";// Default text color
    String ANSI_RED = "\u001B[31m"; // Red text color
    int hitRate;
    Room location;

    public Orc() {
        setName("ORC");
        setDialogue("GAWR! KILL!");
        setDescription("A hulking brute with green skin. Very aggressive and hits hard.");
        setDescriptionVerbose("A hulking brute with green skin. Very aggressive and hits hard but its accuracy must not be good with all those muscles.");
        setHealth(3);
        setAttack(3);
        setDefense(1);
        setUnderDesc("The ground is underneath the " + ANSI_RED + getName() + ANSI_RESET + ".");
        setInsideDesc("How are you going to look inside this guy?");
        setBehindDesc("His body is too big to see what's behind him.");
        setThroughDesc("You try your hardest to see through him but nothing happens.");
        setState(new Aggressive());
        this.hitRate = 50;
        this.location = null;
    }

    public void attack(Character player) {
        Random rand = new Random();
        int accuracy = rand.nextInt(100);
        if (accuracy < hitRate) {
            player.isHit(getAttack());
            System.out.println("The " + ANSI_RED + getName() + ANSI_RESET + " hits you!");
        }
        else {
            System.out.println("The " + ANSI_RED + getName() + ANSI_RESET + " misses his attack!");
        }
    }

    public void isHit(int attackPower) {
        setHealth(getHealth() + getDefense() - attackPower);
        if (getHealth() < 1) {
            die();
        }
    }

    public void die() {
        System.out.println("You have defeated the " + ANSI_RED + getName() + ANSI_RESET + "!");
        setDescription("A body of a brute lies before you.");
        setDescriptionVerbose("A body of a brute lies before you. There seems to be nothing usable on his body.");
    }
}

class ElfGuard extends Character {
    String ANSI_RESET = "\u001B[0m";// Default text color
    String ANSI_RED = "\u001B[31m"; // Red text color
    int hitRate;
    Room location;

    public ElfGuard() {
        setName("ELVEN GUARD");
        setDialogue("You shall NOT pass!");
        setDescription("An elven guardian. Moves quick and strikes with high accuracy.");
        setDescriptionVerbose("An elven guardian. Moves quick and strikes with high accuracy. Best to have high defense to take him on.");
        setHealth(4);
        setAttack(2);
        setDefense(2);
        setState(new Passive());
        setUnderDesc("The ground is underneath the " + ANSI_RED + getName() + ANSI_RESET + ".");
        setInsideDesc("How are you going to look inside this guy?");
        setBehindDesc("His shield blocks the things behind him.");
        setThroughDesc("You try your hardest to see through him but nothing happens.");
        this.hitRate = 90;
        this.location = null;
    }

    public void attack(Character player) {
        Random rand = new Random();
        int accuracy = rand.nextInt(100);
        if (accuracy < hitRate) {
            player.isHit(getAttack());
            System.out.println("The " + ANSI_RED + getName() + ANSI_RESET + " hits you!");
        }
        else {
            System.out.println("The " + ANSI_RED + getName() + ANSI_RESET + " misses his attack!");
        }
    }

    public void isHit(int attackPower) {
        setState(new Aggressive());
        setHealth(getHealth() + getDefense() - attackPower);
        if (getHealth() < 1) {
            die();
        }
    }

    public void die() {
        System.out.println("You have defeated the " + ANSI_RED + getName() + ANSI_RESET + "!");
        setDescription("A body lies before you.");
        setDescriptionVerbose("A body lies before you. Best to leave him alone so he can rest in peace.");
    }
}

class VerboseState implements DialogState {
    public String getStateName() { return "verbose"; }

    public void diagnosePlayer(Player player) {
        if (player.getHealth() == 5){
            System.out.println("You are healthy. Your health is at its maximum level, 5.");
        }
        else if (player.getHealth() == 4){
            System.out.println("You feel a little tired. Your health is at 4.");
        }
        else if (player.getHealth() == 3){
            System.out.println("You feel tired. Your health is at 3.");
        }
        else if (player.getHealth() == 2){
            System.out.println("You have visible injuries. Your health is at 2.");
        }
        else if (player.getHealth() == 1){
            System.out.println("You are extremely injured. Your health is at 1.");
        }
    }

    public void checkArea(Player player) {
        String description = player.getLocation().getDescriptionVerbose();
        System.out.println(description);
    }

    public void examineInv(ZorkObj item) {
        String description = item.getDescriptionVerbose();
        System.out.println(description);
    }

    public void examineChara(Character chara) {
        String description = chara.getDescriptionVerbose();
        System.out.println(description);
    }
}

class BriefState implements DialogState {
    public String getStateName() { return "brief"; }

    public void diagnosePlayer(Player player) {
        if (player.getHealth() == 5){
            System.out.println("You are healthy.");
        }
        else if (player.getHealth() == 4){
            System.out.println("You feel a little tired.");
        }
        else if (player.getHealth() == 3){
            System.out.println("You feel tired.");
        }
        else if (player.getHealth() == 2){
            System.out.println("You have visible injuries.");
        }
        else if (player.getHealth() == 1){
            System.out.println("You are extremely injured.");
        }
    }

    public void checkArea(Player player) {
        String description = player.getLocation().getDescription();
        System.out.println(description);
    }

    public void examineInv(ZorkObj item) {
        String description = item.getDescription();
        System.out.println(description);
    }

    public void examineChara(Character chara) {
        String description = chara.getDescription();
        System.out.println(description);
    }
}

class SuperBriefState implements DialogState {
    public String getStateName() { return "superbrief"; }

    public void diagnosePlayer(Player player) {
        if (player.getHealth() == 5){
            System.out.println("Healthy.");
        }
        else if (player.getHealth() == 4){
            System.out.println("A little tired.");
        }
        else if (player.getHealth() == 3){
            System.out.println("Tired.");
        }
        else if (player.getHealth() == 2){
            System.out.println("Injured.");
        }
        else if (player.getHealth() == 1){
            System.out.println("Nearly dead.");
        }
    }
    public void checkArea(Player player) {
    }

    public void examineInv(ZorkObj item) {
        String description = item.getDescription();
        System.out.println(description);
    }

    public void examineChara(Character chara) {
        String description = chara.getDescription();
        System.out.println(description);
    }
}

class AutoPlay implements DialogState {
    public String getStateName() { return "autoplay"; }

    public void diagnosePlayer(Player player) {
        if (player.getHealth() == 5){
            System.out.println("Your health is at its maximum level, 5.");
        }
        else if (player.getHealth() == 4){
            System.out.println("Your health is at 4.");
        }
        else if (player.getHealth() == 3){
            System.out.println("Your health is at 3.");
        }
        else if (player.getHealth() == 2){
            System.out.println("Your health is at 2.");
        }
        else if (player.getHealth() == 1){
            System.out.println("Your health is at 1.");
        }
    }

    public void checkArea(Player player) {
        String description = player.getLocation().getDescriptionVerbose();
        System.out.println(description);
    }

    public void examineInv(ZorkObj item) {
        String description = item.getDescriptionVerbose();
        System.out.println(description);
    }

    public void examineChara(Character chara) {
        String description = chara.getDescriptionVerbose();
        System.out.println(description);
    }
}

class Aggressive implements State {
    public String getStateName() { return "agg";}
}

class Passive implements State {
    public String getStateName() { return "pas";}
}