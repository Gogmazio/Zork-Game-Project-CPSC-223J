interface ZorkObj {
    String name = "";
    String description = "";

    String getObjName();
    String getDetails();
}

class Key implements ZorkObj{
    String name;
    String description;

    public Key(){
        this.name = "";
        this.description = "";
    }
    public Key(String name) {
        this.name = name;
        this.description = "";
    }
    public Key(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getObjName() { return this.name; }
    public String getDetails() { return this.description; }

}