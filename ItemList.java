public class ItemList {
    public ItemInfo key = new ItemInfo();
    public ItemInfo table = new ItemInfo();
    public ItemInfo carpet = new ItemInfo();
    public ItemInfo mailBox = new ItemInfo();
    public ItemInfo letter = new ItemInfo();
    public ItemInfo diamond = new ItemInfo();
    public ItemInfo sapphire = new ItemInfo();
    public ItemInfo goldTrinket = new ItemInfo();

    public ItemList() {
        key.setName("KEY");
        key.setDesc("It's a small key.");
        key.setDescVer("It's a small key. Seems like it can be used on common locks.");
        key.setID(0);
        table.setName("TABLE");
        table.setDesc("It's a wooden table.");
        table.setDescVer("It's a wooden table. Looks like it can break apart fairly easily.");
        table.setID(1);
        carpet.setName("CARPET");
        carpet.setDesc("It's a red carpet.");
        carpet.setDescVer("It's a red carpet. It doesn't have any particular design on it.");
        carpet.setID(2);
        diamond.setName("DIAMOND");
        diamond.setDesc("It's a shiny gemstone with a cool sky blue color to it.");
        diamond.setDescVer("It's a shiny gemstone with a cool sky blue color to it. Looks like it's worth a lot.");
        diamond.setID(3);
        mailBox.setName("MAILBOX");
        mailBox.setDesc("It's a red mailbox.");
        mailBox.setDescVer("It's a red mailbox. Seems like there's something inside.");
        mailBox.setID(4);
        letter.setName("LETTER");
        letter.setDesc("The letter reads: 'WELCOME TO ZORK!" +
                "\nAttached to this letter is a key. Use it to open any locked doors you may find on your way!" +
                "\nWe hope you have fun!'");
        letter.setDescVer("The letter reads: 'WELCOME TO ZORK!" +
                "\nAttached to this letter is a key. Use it to open any locked doors you may find on your way!" +
                "\nWe hope you have fun!'");
        letter.setID(5);
        sapphire.setName("SAPPHIRE CRYSTAL");
        sapphire.setDesc("It's a crystal that shines a bright blue color.");
        sapphire.setDescVer("It's a crystal that shines a bright blue color. Looks like it's worth a lot.");
        sapphire.setID(6);
        goldTrinket.setName("GOLD TRINKET");
        goldTrinket.setDesc("It's a necklace made out of gold.");
        goldTrinket.setDescVer("It's a made out of gold. Might sell for a lot of money.");
        goldTrinket.setID(7);
    }
}

class ItemInfo {
    String name;
    String desc;
    String descVer;
    int ID;

    ItemInfo() {
        this.name = "";
        this.desc = "";
        this.descVer = "";
        this.ID = -1;
    }

    public void setName(String name) { this.name = name; }
    public void setDesc(String desc) { this.desc = desc; }
    public void setDescVer (String desc) { this.descVer = desc; }
    public void setID(int ID) { this.ID = ID; }
}
