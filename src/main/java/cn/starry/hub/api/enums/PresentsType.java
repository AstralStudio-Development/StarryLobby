package cn.starry.hub.api.enums;

public enum PresentsType {

    MAINLOBBY(0, "");

    private final int id;
    private final String displayName;

    private PresentsType(int id, String displayName) {
        this.id = id;
        this.displayName = displayName;
    }

    public int getID() {
        return this.id;
    }

    public String getDisplayName() {
        return this.displayName;
    }

    public static PresentsType getById(int id) {
        for (PresentsType achievementType : PresentsType.values()) {
            if (achievementType.getID() == id) {
                return achievementType;
            }
        }
        return null;
    }

}
