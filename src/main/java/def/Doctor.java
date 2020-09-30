package def;

public enum Doctor {
    avery("Jack Begood"),
    johnson("Adam Johnson"),
    smith("Carol Smith");

    String name;
     Doctor (String name) {
        this.name = name;
    }
    public String getName() {
         return name;
    }
}
