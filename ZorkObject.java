import java.io.Serializable;

interface ZorkObj {
    String getName();

    String getDescription();
    String getDescriptionVerbose();
    int getPoints();
    int getWeightLevel();
    int getAttackPower();
    int getDefense();

    String getUnderDesc();
    String getInsideDesc();
    String getThroughDesc();
    String getBehindDesc();
    String getDialogue();
}

class Object implements ZorkObj, Serializable {
    String name;
    String weaponType;
    String description;
    String descriptionVerbose;
    String underDesc;
    String insideDesc;
    String throughDesc;
    String behindDesc;
    String dialogue;
    int points;
    int weightLevel;
    int attackPower;
    int defense;
    int itemID;

    public Object(){
        this.name = "";
        this.weaponType = "";
        this.description = "";
        this.descriptionVerbose = "";
        this.underDesc = "There doesn't seem to be anything under this.";
        this.insideDesc = "No matter how hard you try, you can not look inside this object.";
        this.throughDesc = "Without X-Ray vision, you can not look through this object.";
        this.behindDesc = "There doesn't seem to be anything behind this.";
        this.dialogue = "You are trying to talk to a " + getName() + ". What did you expect?";
        this.points = 0;
        this.weightLevel = 0;
        this.attackPower = 0;
        this.itemID = -1;
    }
    public String getName() { return this.name; }
    public String getDescription() { return this.description; }
    public String getDescriptionVerbose() { return this.descriptionVerbose; }
    public int getPoints() { return this.points; }
    public int getWeightLevel() { return this.weightLevel; }
    public int getAttackPower() { return this.attackPower; }
    public int getDefense() { return this.defense; }
    public String getUnderDesc() { return this.underDesc; }
    public String getInsideDesc() { return this.insideDesc; }
    public String getThroughDesc() { return this.throughDesc;}
    public String getBehindDesc() { return this.behindDesc; }
    public String getDialogue() { return this.dialogue; }

    public void setName(String name) { this.name = name; }
    public void setWeaponType(String type) { this.weaponType = type;}
    public void setDescription(String des) { this.description = des;}
    public void setDescriptionVerbose(String desVer) { this.descriptionVerbose = desVer;}
    public void setPoints(int points) { this.points = points; }
    public void setWeightLevel(int weight) { this.weightLevel = weight;}
    public void setAttackPower(int pow) { this.attackPower = pow;}
    public void setDefense(int def) { this.defense = def;}
    public void setItemID(int id) { this.itemID = id;}
    public void setInsideDesc(String inDesc) { this.insideDesc = inDesc;}
    public void setDialogue(String dial) { this.dialogue = dial; }
}

class Key extends Object {
    public Key(String name, String description, String descriptionVerbose) {
        super();
        setName(name);
        setWeaponType("misc");
        setDescription(description);
        setDescriptionVerbose(descriptionVerbose);
        setWeightLevel(0);
        setAttackPower(0);
        setDefense(0);
        setItemID(0);
        setDialogue("The " + getName() + " is telling you to not steal anything.");
    }
}

class Sword extends Object {
    public Sword(String name, String wepType, String des, String desVer, int atkPow, int ID) {
        super();
        setName(name);
        setWeaponType(wepType);
        setDescription(des);
        setDescriptionVerbose(desVer);
        setWeightLevel(0);
        setAttackPower(atkPow);
        setDefense(0);
        setItemID(ID);
        setDialogue("Talking to a " + getName() + " are you? What did you expect would happen?");
    }
}

class Axe extends Object {
    public Axe(String name, String wepType, String des, String desVer, int atkPow, int ID) {
        super();
        setName(name);
        setWeaponType(wepType);
        setDescription(des);
        setDescriptionVerbose(desVer);
        setWeightLevel(0);
        setAttackPower(atkPow);
        setDefense(0);
        setItemID(ID);
        setDialogue("You hear a faint voice. 'You have my sword. And my bow. And my " + getName() + ".'");
    }
}

class Spear extends Object {
    public Spear(String name, String wepType, String des, String desVer, int atkPow, int ID) {
        super();
        setName(name);
        setWeaponType(wepType);
        setDescription(des);
        setDescriptionVerbose(desVer);
        setWeightLevel(0);
        setAttackPower(atkPow);
        setDefense(0);
        setItemID(ID);
        setDialogue("You are trying to talk to a " + getName() + ". What did you expect?");
    }
}

class Shield extends Object {
    public Shield(String name, String wepType, String des, String desVer, int atkPow, int ID) {
        super();
        setName(name);
        setWeaponType(wepType);
        setDescription(des);
        setDescriptionVerbose(desVer);
        setWeightLevel(0);
        setAttackPower(atkPow);
        setDefense(3);
        setItemID(ID);
        setDialogue("The " + getName() + " will protect you but it won't talk to you.");
    }
}

class Table extends Object {
    public Table(String name, String description, String descriptionVerbose, int itemID) {
        super();
        setName(name);
        setWeaponType("misc");
        setDescription(description);
        setDescriptionVerbose(descriptionVerbose);
        setWeightLevel(3);
        setAttackPower(0);
        setDefense(0);
        setItemID(itemID);
        setDialogue("Why are you trying to talk to a " + getName() + "?");
    }
}

class MailBox extends Object {
    public MailBox(String name, String description, String descriptionVerbose, int itemID) {
        super();
        setName(name);
        setWeaponType("misc");
        setDescription(description);
        setDescriptionVerbose(descriptionVerbose);
        setInsideDesc("Looks like there is a key and a letter inside the mail box.");
        setWeightLevel(3);
        setAttackPower(0);
        setDefense(0);
        setItemID(itemID);
        setDialogue("Nope. Doesn't look like anything will talk back to you.");
    }
}

class Letter extends Object {
    public Letter(String name, String description, String descriptionVerbose, int itemID) {
        super();
        setName(name);
        setWeaponType("misc");
        setDescription(description);
        setDescriptionVerbose(descriptionVerbose);
        setWeightLevel(0);
        setAttackPower(0);
        setDefense(0);
        setItemID(itemID);
        setDialogue("If you count reading the " + getName() + " as speaking, then go read it for its dialogue.");
    }
}

class Diamond extends Object {
    public Diamond(String name, String description, String descriptionVerbose, int itemID) {
        super();
        setName(name);
        setDescription(description);
        setDescriptionVerbose(descriptionVerbose);
        setPoints(5);
        setItemID(itemID);
        setDialogue("Did you expect the " + getName() + " to talk back?");
    }
}

class SapphireCrystal extends Object {
    public SapphireCrystal(String name, String description, String descriptionVerbose, int itemID) {
        super();
        setName(name);
        setDescription(description);
        setDescriptionVerbose(descriptionVerbose);
        setPoints(7);
        setItemID(itemID);
        setDialogue("Did you expect the " + getName() + " to talk back?");
    }
}

class GoldTrinket extends Object {
    public GoldTrinket(String name, String description, String descriptionVerbose, int itemID) {
        super();
        setName(name);
        setDescription(description);
        setDescriptionVerbose(descriptionVerbose);
        setPoints(6);
        setItemID(itemID);
        setDialogue("Did you expect the " + getName() + " to talk back?");
    }
}