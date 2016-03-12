package pcsladders.constant;


public enum Division {

    V("V", 0),
    IV("IV", 200),
    III("III", 400),
    II("II", 600),
    I("I", 800);

    private String name;
    private int value;

    Division(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return name;
    }
}
