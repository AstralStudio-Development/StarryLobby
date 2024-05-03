package cn.starry.hub.api.enums;

public enum GameType {

    SPORTS(0, "竞技"),
    LEISURE(1, "休闲"),
    SURVIVAL(2, "生存"),
    RPG(3, "长期游戏"),
    PRACTICE(4, "练习"),
    TEAM(5, "团队");

    private final int id;
    private final String displayName;

    private GameType(int id, String displayName) {
        this.id = id;
        this.displayName = displayName;
    }

    public int getID() {
        return this.id;
    }

    public String getDisplayName() {
        return this.displayName;
    }

    public String getFormattedDisplayName() {
        if (id != 3) {
            return "&8" + this.displayName + "&8类";
        } else {
            return "&8" + this.displayName;
        }
    }
}
