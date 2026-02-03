package cn.starry.hub.features.npc.type.login;

import cn.starry.core.utils.ItemBuilder;
import cn.starry.hub.menus.login.StaffMenu;
import cn.starry.hub.features.npc.AbstractNPC;
import cn.starry.hub.features.npc.Skin;
import dev.jnic.annotations.Include;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

@Include
public class StaffNPC extends AbstractNPC {

    @Override
    public String getNpcInternalName() {
        return "staff";
    }

    @Override
    public List<String> getNpcDisplayName(Player player) {
        ArrayList<String> lines = new ArrayList<String>();
        lines.add("&7最后更新于2025/8/6");
        lines.add("&a在职工作人员列表");
        lines.add("&f右键点击");
        return lines;
    }

    @Override
    public Location getNpcSpawnLocation() {
        return new Location(Bukkit.getWorld("world"), 14.5, 90.0, 3.5, 65, 0.0f);
    }

    @Override
    public Skin getNpcSkin(Player player) {
        String value = "ewogICJ0aW1lc3RhbXAiIDogMTY2NjU4MjkzMjc4MiwKICAicHJvZmlsZUlkIiA6ICJkY2NkYmM0MTE5NjE0Njc1OTQxYmI5MmNkMmZiOGVjMyIsCiAgInByb2ZpbGVOYW1lIiA6ICJEWmVycjAiLAogICJzaWduYXR1cmVSZXF1aXJlZCIgOiB0cnVlLAogICJ0ZXh0dXJlcyIgOiB7CiAgICAiU0tJTiIgOiB7CiAgICAgICJ1cmwiIDogImh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTliNTc3NDExMzA5ZTVlMDgzMGE0NzcwMzgzZGY3NjJlNTRjYWIwMGY3Mjk1MDNkYmUzNDkwNmQ4N2IzZWNkZSIKICAgIH0KICB9Cn0=";
        String signature = "LvSHaG8+q2G38HpTXDqf0D8h1r4mnq/HXizRcXiJnDmbF4V1FUuN5HmBWXS5RdvohEGkNkkBdr0ignYEBEdGozIUx2hV8pkRLWrbS6fRY8+NSqz65VbMs+x6rr/t+/tQjsffuTGj2G5p9ARX7lH97J2EHrVmrGjihbKuxxahJfdUvyj554YDGO0J4AQO1cxiEdbOOJiUsKc47S7pSPo6tiHb2VFrmURLgJXWsxrFitYZfyB/AVXT6fLK6EmFF8eGQUuxO3Z69KblMgMxcGecTvz1Stz836uOfBpKl2HYaKlfiMvMrwedVlLyHOwfqt5y05iqomnsK7c4iB02hx/6VVlsQ6+24iDWXX1kqEPC+Ebm80DTYOboRmarMg74BbD/UUvKHIkQT8mKmzIoyG9eIStTubtqczfPFR36hEh0igtnla35gdsrf1BOftthznRnWlCmVO6W49TJxMIMxFxMXXHnuH5/akA7XH5zn0fzVG0Qu64p9npcuSjB8S11yCnfYfkuOZXSqKm+m9CwB1HLWxGIN7VTRuG86dp4QIZ2x/QYswFZA4PA20tYR9e9e/8E04fyX++S3WRUfj9lxnqir4kXCK3ay/jU0tXgiWowvsD+KEkltoDfX0rPa+Hf8jg+A95btZ6jl4PK8sA1cjIRYYRQqRqmZXC63Tyso8RJw2s=";
        return new Skin(value, signature);
    }

    @Override
    public void handlePlayerInteract(Player player) {
        new StaffMenu().openMenu(player);
    }

    @Override
    public ItemStack getNpcHeldItem() {
        return new ItemBuilder(Material.DIAMOND_SWORD).shiny().build();
    }

    @Override
    public ItemStack getNpcHelmetItem() {
        return null;
    }

    @Override
    public boolean isContinuouslyWatchingPlayers() {
        return false;
    }
}


