class LocationList {
    public roomInfo wOfHouse = new roomInfo();
    public roomInfo insideHouse = new roomInfo();
    public roomInfo frontOfHouse = new roomInfo();
    public roomInfo eastForest = new roomInfo();
    public roomInfo centralForest = new roomInfo();
    public roomInfo orcLair = new roomInfo();
    public roomInfo houseStorage = new roomInfo();
    public roomInfo houseStorageDown = new roomInfo();
    public roomInfo houseArmory = new roomInfo();
    public roomInfo houseArmoryUp = new roomInfo();

    public LocationList() {
        String ANSI_RESET = "\u001B[0m";
        String ANSI_WHITE = "\u001B[37m";
        String ANSI_RED = "\u001B[31m";
        String ANSI_BLUE = "\u001B[34m";

        wOfHouse.setName("West of House");
        wOfHouse.setType("Grassland");
        wOfHouse.setDesc("This area looks like a wide open grassy field. " +
                "\nThere is a white house to the east and a forest to the west." +
                "\nThere is a small " + ANSI_BLUE + "mailbox" + ANSI_RESET + " in front of me. " +
                "\nA rubber mat saying 'Welcome to Zork!' lies by the door.");
        wOfHouse.setDescVer("This area looks like a wide open field with a white house to the west. " +
                "\nThere is a small " + ANSI_BLUE + "mailbox" + ANSI_RESET + " here." +
                "\nA rubber mat saying 'Welcome to Zork!' lies by the door." +
                "\nSeems like every " + ANSI_BLUE + "weapon" + ANSI_RESET + " type is effective in this area.");
        wOfHouse.setRoomID(0);
        insideHouse.setName("House Living Room");
        insideHouse.setType("Building");
        insideHouse.setDesc("You entered the house's living room." +
                "\nIt looks like no one has been living here for years." +
                "\nThere is a room to the east and a room to the north." +
                "\nThere is a " + ANSI_BLUE + "table" + ANSI_RESET + " in the middle of the room.");
        insideHouse.setDescVer("You entered the house's living room." +
                "\nIt looks like no one has been living here for years." +
                "\nThere is a " + ANSI_BLUE + "table" + ANSI_RESET + " in the middle of the room." +
                "\nThere is a room to the east and a room to the north." +
                "\nSeems like " + ANSI_BLUE + "swords" + ANSI_RESET + " are the most effective in this area.");
        insideHouse.setRoomID(1);
        frontOfHouse.setName("In Front of the White House");
        frontOfHouse.setType("Grassland");
        frontOfHouse.setDesc("You look upon the white house in front of you." +
                "\nThe house looks like it's about to collapse soon." +
                "\nThe front door is boarded up but there are " + ANSI_WHITE + "windows" + ANSI_RESET + " that look openable." +
                "\nThere is a field to the west.");
        frontOfHouse.setDescVer("You look upon the white house in front of you." +
                "\nThe house looks like it's about to collapse soon." +
                "\nThe front door is boarded up but there are " + ANSI_WHITE + "windows" + ANSI_RESET + " that can be opened." +
                "\nIt looks like you can enter through the " + ANSI_WHITE + "windows" + ANSI_RESET + "." +
                "\nThere is a field to the west." +
                "\nSeems like every " + ANSI_BLUE + "weapon" + ANSI_RESET + " type is effective in this area.");
        frontOfHouse.setRoomID(2);
        eastForest.setName("East Forest");
        eastForest.setType("Forest");
        eastForest.setDesc("There is nothing but trees surrounding you." +
                "\nThere is an axe lodged into the side of a tree." +
                "\nYou can feel an ominous presence to the south." +
                "\nThere is heavy fog to the north." +
                "\nThere is a room to the west and the east.");
        eastForest.setDescVer("There is nothing but trees surrounding you." +
                "\nThere is an axe lodged into the side of a tree." +
                "\nYou can feel an ominous presence coming from a cave to the south." +
                "\nIt would be best to avoid it until you have a " + ANSI_BLUE + "weapon" + ANSI_RESET + "." +
                "\nThere is heavy fog to the north. You might get lost in it." +
                "\nThere is a room to the west and the east." +
                "\nSeems like " + ANSI_BLUE + "swords and axes" + ANSI_RESET + " are effective in this area.");
        eastForest.setRoomID(3);
        centralForest.setName("Central Forest");
        centralForest.setType("Forest");
        centralForest.setDesc("Even more trees surround you." +
                "\nAn elven guard protects a " + ANSI_BLUE + "golden trinket" + ANSI_RESET + "." +
                "\nTrees block your path to the north, south, and west." +
                "\nThere is a room surrounded by trees to the east.");
        centralForest.setDescVer("Even more trees surround you." +
                "\nAn elven guard protects a " + ANSI_BLUE + "golden trinket" + ANSI_RESET + "." +
                "\nTrees block your path to the north, south, and west." +
                "\nThere is a room surrounded by trees to the east." +
                "\nSeems like " + ANSI_BLUE + "swords and spears" + ANSI_RESET + " are effective in this area.");
        centralForest.setRoomID(4);
        orcLair.setName("Orc Lair");
        orcLair.setType("Cave");
        orcLair.setDesc("There is an " + ANSI_RED + "ORC" + ANSI_RESET + " staying in this cave." +
                "\nLooks like the " + ANSI_RED + "ORC" + ANSI_RESET + " is using the cave as its lair." +
                "\nThere is a room to the north.");
        orcLair.setDescVer("There is an " + ANSI_RED + "ORC" + ANSI_RESET + " staying in this cave." +
                "\nLooks like the " + ANSI_RED + "ORC" + ANSI_RESET + " is using the cave as its lair." +
                "\nThere is a room to the north." +
                "\nI should use a " + ANSI_BLUE + "weapon" + ANSI_RESET + " to defeat this " + ANSI_RED + "ORC" + ANSI_RESET + "." +
                "\nSeems like " + ANSI_BLUE + "axes" + ANSI_RESET + " are effective in this area.");
        orcLair.setRoomID(5);
        houseStorage.setName("House Storage Room");
        houseStorage.setType("Building");
        houseStorage.setDesc("It's a dusty room used for storage." +
                "\nEmpty shelves line the walls." +
                "\nThere is a " + ANSI_WHITE + "cellar" + ANSI_RESET + " in the corner of the room." +
                "\nThere is a room to the south.");
        houseStorage.setDescVer("It's a dusty room used for storage." +
                "\nEmpty shelves line the walls. The shelves look too heavy to carry." +
                "\nThere is a " + ANSI_WHITE + "cellar" + ANSI_RESET + " in the corner of the room. It has a key hole near the handle." +
                "\nThere is a room to the south." +
                "\nSeems like " + ANSI_BLUE + "swords" + ANSI_RESET + " are effective in this area.");
        houseStorage.setRoomID(6);
        houseStorageDown.setName("House Storage Cellar Room");
        houseStorageDown.setType("Cave");
        houseStorageDown.setDesc("It's a dirty basement." +
                "\nThere is a " + ANSI_BLUE + "shield" + ANSI_RESET + " on the other side of the room." +
                "\nThere are " + ANSI_WHITE + "stairs" + ANSI_RESET + " that lead back up to the storage room.");
        houseStorageDown.setDescVer("It's a dirty basement." +
                "\nThere is a " + ANSI_BLUE + "shield" + ANSI_RESET + " on the other side of the room." +
                "\nThere are " + ANSI_WHITE + "stairs" + ANSI_RESET + " that lead back up to the storage room." +
                "\nNothing else is in this room." +
                "\nSeems like " + ANSI_BLUE + "axes" + ANSI_RESET + " are effective in this area.");
        houseStorageDown.setRoomID(7);
        houseArmory.setName("House Armory Room");
        houseArmory.setType("Building");
        houseArmory.setDesc("It's an armory room used for storing weapons and armor." +
                "\nA " + ANSI_BLUE + "spear" + ANSI_RESET + " sits in a corner of this empty room." +
                "\nThere are " + ANSI_WHITE + "stairs" + ANSI_RESET + " going up to another room." +
                "\nThere is a room to the west.");
        houseArmory.setDescVer("It's an armory room used for storing weapons and armor." +
                "\nA " + ANSI_BLUE + "spear" + ANSI_RESET + " sits in a corner of this empty room." +
                "\nIt doesn't look like there's anything else in this room. Someone must have took everything already." +
                "\nThere are " + ANSI_WHITE + "stairs" + ANSI_RESET + " going up to another room." +
                "\nThere is a room to the west." +
                "\nSeems like " + ANSI_BLUE + "swords" + ANSI_RESET + " are effective in this area.");
        houseArmory.setRoomID(8);
        houseArmoryUp.setName("House Armory Upstairs");
        houseArmoryUp.setType("Building");
        houseArmoryUp.setDesc("It's an attic. Looks like it was used as extra storage space." +
                "\nCobwebs and dust litter the room." +
                "\nLooks like there's a " + ANSI_BLUE + "sword" + ANSI_RESET + " in the back of the room along with a diamond shining in the corner." +
                "\nThere are " + ANSI_WHITE + "stairs" + ANSI_RESET + " leading back down.");
        houseArmoryUp.setDescVer("It's an attic. Looks like it was used as extra storage space." +
                "\nCobwebs and dust litter the room." +
                "\nLooks like there's a " + ANSI_BLUE + "sword" + ANSI_RESET + " in the back of the room along with a diamond shining in the corner." +
                "\nThere are " + ANSI_WHITE + "stairs" + ANSI_RESET + " leading back down." +
                "\nSeems like " + ANSI_BLUE + "swords" + ANSI_RESET + " are effective in this area.");
        houseArmoryUp.setRoomID(9);
    }
}

class roomInfo {
    String name;
    String desc;
    String descVer;
    String type;
    int roomID;

    roomInfo() {
        this.name = "";
        this.desc = "";
        this.descVer = "";
        this.type = "";
        this.roomID = -1;
    }

    public void setName(String name) { this.name = name; }
    public void setDesc(String desc) { this.desc = desc; }
    public void setDescVer (String desc) { this.descVer = desc; }
    public void setType(String type) { this.type = type; }
    public void setRoomID(int roomID) { this.roomID = roomID; }
}
