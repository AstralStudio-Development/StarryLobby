package cn.starry.hub.api;

import cn.starry.hub.Main;
import cn.starry.hub.database.MongoDB;
import cn.starry.hub.utils.RankUtil;

import java.util.UUID;

public class RankApiProvider {
    private UUID uuid;
    private String rankFormatPrefix;
    private String rankPrefix;

    public RankApiProvider(UUID uuid) {
        this.uuid = uuid;
        this.rankFormatPrefix = RankUtil.getFormatRankById(Main.getInstance().getData().getPlayerData(uuid,"rank"), this.uuid);
        this.rankPrefix = RankUtil.getDisplayRankById(Main.getInstance().getData().getPlayerData(uuid,"rank"), this.uuid);
    }

    public UUID getUuid() {
        return this.uuid;
    }

    public String getRankFormatPrefix() {
        return this.rankFormatPrefix;
    }

    public String getRankPrefix() {
        return this.rankPrefix;
    }

}
