package cn.starry.hub.features.npc;

import cn.starry.core.utils.chat.CC;
import com.bnstra.npclib.api.NPC;
import com.bnstra.npclib.api.skin.Skin;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: EmptyIrony
 * @Date: 2020/12/30 22:35
 */
public abstract class AbstractNPC {
    private NPC npc;

    //NPC 内部名称
    public abstract String getNpcInternalName();

    //NPC 显示名称 可以自行加空格
    public abstract List<String> getNpcDisplayName(Player player);

    public List<String> getNpcTextLine(Player player) {
        List<String> displayName = this.getNpcDisplayName(player);
        List<String> text = new ArrayList<>();
        for (String s : displayName) {
            text.add(CC.translate(s));
        }

        return text;
    }

    //NPC 生成位置
    public abstract Location getNpcSpawnLocation();

    //Player name
    public abstract Skin getNpcSkin(Player player);

    //玩家交互处理
    public abstract void handlePlayerInteract(Player player);

    //npc 手持物品
    public abstract ItemStack getNpcHeldItem();
    //npc 头盔
    public abstract ItemStack getNpcHelmetItem();

    //npc Continuously watching players
    public abstract boolean isContinuouslyWatchingPlayers();

    public NPC getNpc() {
        return this.npc;
    }

    public void setNpc(NPC npc) {
        this.npc = npc;
    }
}

