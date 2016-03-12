package pcsladders.constant;


public enum Tier {

    UNRANKED("UNRANKED", 0),
    BRONZE("BRONZE", 1000),
    SILVER("SILVER", 2000),
    GOLD("GOLD", 3000),
    PLATINUM("PLATINUM", 4000),
    DIAMOND("DIAMOND", 5000),
    MASTER("MASTER", 6000),
    CHALLENGER("CHALLENGER", 7000);

    private String name;
    private int value;

    Tier(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}
