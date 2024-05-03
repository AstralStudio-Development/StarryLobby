package cn.starry.hub.utils;

import cn.starry.hub.functions.rank.nopurchase.*;
import cn.starry.hub.functions.rank.purchase.*;

import java.util.UUID;

public class RankUtil {
    public static String getFormatRankById(String id, UUID uuid) {
        switch (id) {
            case "P_VIP": {
                return new Vip().getFormatName();
            }
            case "P_VIP_PLUS": {
                return new VipPlus().getFormatName();
            }
            case "P_MVP": {
                return new Mvp().getFormatName();
            }
            case "P_MVP_PLUS": {
                return new MvpPlus(uuid).getFormatName();
            }
            case "P_MVP_PLUS_PLUS": {
                return new MvpPlusPlus(uuid).getFormatName();
            }
            case "NOP_OWNER": {
                return new Owner().getFormatName();
            }
            case "NOP_ADMIN": {
                return new Admin().getFormatName();
            }
            case "NOP_GM": {
                return new GameMaster().getFormatName();
            }
            case "NOP_MEDIA": {
                return new Media().getFormatName();
            }
            case "NOP_SLOTH": {
                return new Sloth().getFormatName();
            }
            case "NOP_STUDIO": {
                return new Studio().getFormatName();
            }
        }
        return new Default().getFormatName();
    }

    public static String getDisplayRankById(String id, UUID uuid) {
        switch (id) {
            case "P_VIP": {
                return new Vip().getDisplayName();
            }
            case "P_VIP_PLUS": {
                return new VipPlus().getDisplayName();
            }
            case "P_MVP": {
                return new Mvp().getDisplayName();
            }
            case "P_MVP_PLUS": {
                return new MvpPlus(uuid).getDisplayName();
            }
            case "P_MVP_PLUS_PLUS": {
                return new MvpPlusPlus(uuid).getDisplayName();
            }
            case "NOP_OWNER": {
                return new Owner().getDisplayName();
            }
            case "NOP_ADMIN": {
                return new Admin().getDisplayName();
            }
            case "NOP_GM": {
                return new GameMaster().getDisplayName();
            }
            case "NOP_MEDIA": {
                return new Media().getDisplayName();
            }
            case "NOP_SLOTH": {
                return new Sloth().getDisplayName();
            }
            case "NOP_STUDIO": {
                return new Studio().getDisplayName();
            }
        }
        return new Default().getDisplayName();
    }

    public static boolean isRankAvailable(String id) {
        switch (id) {
            case "P_VIP": {
                return true;
            }
            case "P_VIP_PLUS": {
                return true;
            }
            case "P_MVP": {
                return true;
            }
            case "P_MVP_PLUS": {
                return true;
            }
            case "P_MVP_PLUS_PLUS": {
                return true;
            }
            case "NOP_OWNER": {
                return true;
            }
            case "NOP_ADMIN": {
                return true;
            }
            case "NOP_GM": {
                return true;
            }
            case "NOP_MEDIA": {
                return true;
            }
            case "NOP_SLOTH": {
                return true;
            }
            case "NOP_STUDIO": {
                return true;
            }
            case "NOP_DEFAULT": {
                return true;
            }
        }
        return false;
    }
}
