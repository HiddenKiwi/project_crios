package pcsladders.constant;


public enum QueueType {

    RANKED_TEAM_3x3("RANKED_TEAM_3x3"),
    RANKED_TEAM_5x5("RANKED_TEAM_5x5");

    private String name;

    QueueType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return getName();
    }
}
