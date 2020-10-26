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
        wOfHouse.setName("West of House");
        wOfHouse.setType("Grassland");
        wOfHouse.setDesc("This area looks like a wide open grassy field. " +
                "\nThere is a white house to the east and a forest to the west." +
                "\nThere is a small mailbox in front of me. " +
                "\nA rubber mat saying 'Welcome to Zork!' lies by the door.");
        wOfHouse.setDescVer("This area looks like a wide open field with a white house to the west. " +
                "\nThere is a small mailbox here." +
                "\nA rubber mat saying 'Welcome to Zork!' lies by the door." +
                "\nSeems like every weapon type is effective in this area.");
        wOfHouse.setRoomID(0);
        insideHouse.setName("House Living Room");
        insideHouse.setType("Building");
        insideHouse.setDesc("You entered a white house." +
                "\nIt looks like no one has been living here for years." +
                "\nThere is a room to the east and a room to the north." +
                "\nThere is a table in the middle of the room.");
        insideHouse.setDescVer("You entered a white house." +
                "\nIt looks like no one has been living here for years." +
                "\nThere is a table in the middle of the room." +
                "\nThere is a room to the east and a room to the north." +
                "\nSeems like swords are the most effective in this area.");
        insideHouse.setRoomID(1);
        frontOfHouse.setName("In Front of the White House");
        frontOfHouse.setType("Grassland");
        frontOfHouse.setDesc("You look upon the white house in front of you." +
                "\nThe house looks like it's about to collapse soon." +
                "\nThe front door is boarded up but there are windows that look openable." +
                "\nThere is a field to the west.");
        frontOfHouse.setDescVer("You look upon the white house in front of you." +
                "\nThe house looks like it's about to collapse soon." +
                "\nThe front door is boarded up but there are windows that can be opened." +
                "\nIt looks like you can enter through the windows." +
                "\nThere is a field to the west." +
                "\nSeems like every weapon type is effective in this area.");
        frontOfHouse.setRoomID(2);
        eastForest.setName("East Forest");
        eastForest.setType("Forest");
        eastForest.setDesc("There is nothing but trees surrounding you." +
                "\nYou can feel an ominous presence to the south." +
                "\nThere is heavy fog to the north." +
                "\nThere is a room to the west and the east.");
        eastForest.setDescVer("There is nothing but trees surrounding you." +
                "\nYou can feel an ominous presence coming from a cave to the south." +
                "\nIt would be best to avoid it until you have a weapon." +
                "\nThere is heavy fog to the north. You might get lost in it." +
                "\nThere is a room to the west and the east." +
                "\nSeems like swords and axes are effective in this area.");
        eastForest.setRoomID(3);
        centralForest.setName("Central Forest");
        centralForest.setType("Forest");
        centralForest.setDesc("Even more trees surround you." +
                "\nAn elven guard protects a chest filled with gold." +
                "\nTrees block your path to the north, south, and west." +
                "\nThere is a room surrounded by trees to the east.");
        centralForest.setDescVer("Even more trees surround you." +
                "\nAn elven guard protects a chest filled with gold." +
                "\nTrees block your path to the north, south, and west." +
                "\nThere is a room surrounded by trees to the east." +
                "\nSeems like swords and spears are effective in this area.");
        centralForest.setRoomID(4);
        orcLair.setName("Orc Lair");
        orcLair.setType("Cave");
        orcLair.setDesc("There is an ORC staying in this cave." +
                "\nLooks like the ORC is using the cave as its lair." +
                "\nThere is a room to the north.");
        orcLair.setDescVer("There is an ORC staying in this cave." +
                "\nLooks like the ORC is using the cave as its lair." +
                "\nThere is a room to the north." +
                "\nI should use a sword or an axe to defeat this ORC." +
                "\nSeems like swords and axes are effective in this area.");
        orcLair.setRoomID(5);
        houseStorage.setName("House Storage Room");
        houseStorage.setType("Building");
        houseStorage.setDesc("It's a dusty room used for storage." +
                "\nEmpty shelves line the walls." +
                "\nThere is a cellar in the corner of the room." +
                "\nThere is a room to the south.");
        houseStorage.setDescVer("It's a dusty room used for storage." +
                "\nEmpty shelves line the walls. The shelves look too heavy to carry." +
                "\nThere is a cellar in the corner of the room. It has a key hole near the handle." +
                "\nThere is a room to the south." +
                "\nSeems like swords are effective in this area.");
        houseStorage.setRoomID(6);
        houseStorageDown.setName("House Storage Cellar Room");
        houseStorageDown.setType("Underground");
        houseStorageDown.setDesc("It's a dirty basement." +
                "\nThere is a shield on the other side of the room." +
                "\nThere are stairs that lead back up to the storage room.");
        houseStorageDown.setDescVer("It's a dirty basement." +
                "\nThere is a shield on the other side of the room." +
                "\nThere are stairs that lead back up to the storage room." +
                "\nNothing else is in this room." +
                "\nSeems like axes are effective in this area.");
        houseStorageDown.setRoomID(7);
        houseArmory.setName("House Armory Room");
        houseArmory.setType("Building");
        houseArmory.setDesc("It's an armory room used for forging and storing weapons and armor." +
                "\nA furnace sits in the center of this empty room." +
                "\nThere are stairs going up to another room." +
                "\nThere is a room to the west.");
        houseArmory.setDescVer("It's an armory room used for forging and storing weapons and armor." +
                "\nA furnace sits in the center of the room." +
                "\nIt doesn't look like there's anything left in this room. Someone must have took everything already." +
                "\nThere are stairs going up to another room." +
                "\nThere is a room to the west." +
                "\nSeems like swords are effective in this area.");
        houseArmory.setRoomID(8);
        houseArmoryUp.setName("House Armory Upstairs");
        houseArmoryUp.setType("Building");
        houseArmoryUp.setDesc("It's an attic. Looks like it was used as extra storage space." +
                "\nCobwebs and dust litter the room." +
                "\nLooks like there's a sword in the back of the room." +
                "\nThere are stairs leading back down.");
        houseArmoryUp.setDescVer("It's an attic. Looks like it was used as extra storage space." +
                "\nCobwebs and dust litter the room." +
                "\nLooks like there's a sword in the back of the room." +
                "\nThere are stairs leading back down." +
                "\nSeems like swords are effective in this area.");
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
