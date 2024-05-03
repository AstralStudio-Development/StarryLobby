package cn.starry.hub.functions.rank;

public enum RankColors {
    RED("红", "RED", "c"),
    GOLD("金", "GOLD", "6"),
    GREEN("绿", "GREEN", "a"),
    YELLOW("黄", "YELLOW", "e"),
    PINK("粉", "PINK", "d"),
    WHITE("白", "WHITE", "f"),
    BLUE("蓝", "BLUE", "b"),
    DARK_GREEN("深绿", "DARK_GREEN", "2"),
    DARK_RED("深红", "DARK_RED", "4"),
    CYAN("青", "CYAN", "3"),
    PURPURE("紫", "PURPURE", "5"),
    BLACK("黑", "BLACK", "0"),
    DARK_BLUE("深蓝", "DARK_BLUE", "1"),
    GRAY("灰", "GRAY", "8");

    private String colorChinese;
    private String colorEnglish;
    private String colorChar;

    public String getColorChinese() {
        return this.colorChinese;
    }

    public String getColorEnglish() {
        return this.colorEnglish;
    }

    public String getColorChar() {
        return this.colorChar;
    }

    private RankColors(String colorChiese, String colorEnglish, String colorChar) {
        this.colorChinese = colorChiese;
        this.colorEnglish = colorEnglish;
        this.colorChar = colorChar;
    }

    public static RankColors getByEnglish(String english) {
        for (RankColors value : RankColors.values()) {
            if (!value.colorEnglish.equalsIgnoreCase(english)) continue;
            return value;
        }
        return null;
    }

    public static RankColors getByChinese(String chinese) {
        for (RankColors value : RankColors.values()) {
            if (!value.colorChinese.equalsIgnoreCase(chinese)) continue;
            return value;
        }
        return null;
    }
}

