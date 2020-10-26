public class WeaponList {
    public WeaponInfo sword = new WeaponInfo();
    public WeaponInfo axe = new WeaponInfo();
    public WeaponInfo spear = new WeaponInfo();
    public WeaponInfo shield = new WeaponInfo();

    public WeaponList() {
        sword.setName("SWORD");
        sword.setWeaponType("sword");
        sword.setdesc("A common sword.");
        sword.setdescVer("A common sword. Looks durable enough to swing 10 times.");
        sword.setAttackPower(1);
        sword.setID(0);
        axe.setName("AXE");
        axe.setWeaponType("axe");
        axe.setdesc("A common one-handed axe.");
        axe.setdescVer("A common one-handed axe. Looks strong enough to cut down an orc in one swing.");
        axe.setAttackPower(3);
        axe.setID(1);
        spear.setName("SPEAR");
        spear.setWeaponType("spear");
        spear.setdesc("A common two-handed spear.");
        spear.setdescVer("A common two-handed spear. Looks sharp enough to stab through diamonds.");
        spear.setAttackPower(1);
        spear.setID(2);
        shield.setName("SHIELD");
        shield.setWeaponType("shield");
        shield.setdesc("A common shield.");
        shield.setdescVer("A common shield. Looks sturdy enough to take hits from dragons.");
        shield.setAttackPower(0);
        shield.setID(3);
    }
}

class WeaponInfo {
    String name;
    String weaponType;
    String desc;
    String descVer;
    int attackPower;
    int ID;

    WeaponInfo() {
        this.name = "";
        this.weaponType = "";
        this.desc = "";
        this.descVer = "";
        this.attackPower = -1;
        this.ID = -1;
    }

    public void setName(String name) { this.name = name; }
    public void setWeaponType(String type) { this.weaponType = type; }
    public void setdesc(String desc) { this.desc = desc; }
    public void setdescVer (String desc) { this.descVer = desc; }
    public void setAttackPower (int attackPower) { this.attackPower = attackPower; }
    public void setID(int ID) { this.ID = ID; }
}
