package cn.starry.hub.api.enums;

import java.util.Arrays;
import java.util.List;

public enum LanguageType {

    CHINESE(0, Arrays.asList("cn","CN","简体中文","CHINESE","chinese"));
    //ENGLISH(1, Arrays.asList("en","EN","英文","ENGLISH","english")),
    //JAPANESE(2, Arrays.asList("ja","JA","JAPANESE","JAP","jap","japanese"));

    private final int id;
    private final List<String> subName;

    private LanguageType(int id, List<String> subName) {
        this.id = id;
        this.subName = subName;
    }

    public int getID() {
        return this.id;
    }

    public List<String> getSubName() {
        return this.subName;
    }

    public static boolean containsLanguage(String input) {
        for (LanguageType language : LanguageType.values()) {
            if (language.name().equals(input) || language.subName.contains(input)) {
                return true;
            }
        }
        return false;
    }

}
