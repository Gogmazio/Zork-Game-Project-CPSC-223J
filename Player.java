import java.util.ArrayList;

public class Player {
    private String name;
    private int health;
    private int score;
    private int moves;
    private Room location;
    private final ArrayList<ZorkObj> inventory;

    public Player() {
        this.name = "";
        this.health = 0;
        this.score = 0;
        this.moves = 0;
        this.location = null;
        this.inventory = null;
    }

    public Player(String name, int health, Room start) {
        this.name = name;
        this.health = health;
        this.score = 0;
        this.moves = 0;
        this.location = start;
        this.inventory = new ArrayList<>();
    }

    public String getName() { return this.name; }
    public int getHealth() { return this.health; }
    public int getScore() { return this.score; }
    public int getMoves() { return this.moves; }
    public Room getLocation() { return this.location; }

    public void incrementTurn() {
        if (getMoves() % 3 == 0) {
            this.health += 1;
        }
        this.moves += 1;
    }

    public void getInventory() {
        assert inventory != null;

        if (inventory.size() == 0) {
            System.out.println("Your inventory is empty.");
            return;
        }

        for (ZorkObj zorkObj : inventory) {
            System.out.println(zorkObj.getObjName());
        }
    }

    public void enterDoor(Door door) {
        System.out.println("You have entered: " + door.doorName);
        this.location = door.connectedTo;
    }

    public void addItemToInventory(ZorkObj item) {
        assert this.inventory != null;
        this.inventory.add(item);
        System.out.println("You have picked up: " + item.getObjName());
    }

    public void diagnosePlayer() {
        if (getHealth() == 5){
            System.out.println("You are healthy.");
        }
        else if (getHealth() == 4){
            System.out.println("You feel a little tired.");
        }
        else if (getHealth() == 3){
            System.out.println("You feel tired.");
        }
        else if (getHealth() == 2){
            System.out.println("You have visible injuries.");
        }
        else if (getHealth() == 1){
            System.out.println("You are extremely injured.");
        }
    }
}
