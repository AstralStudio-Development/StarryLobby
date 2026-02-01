package cn.starry.hub.functions.scoreboard;

import cn.starry.core.Core;
import cn.starry.core.utils.RankUtil;
import cn.starry.core.utils.scoreboard.AssembleAdapter;
import cn.starry.core.utils.time.TimeUtil;
import cn.starry.hub.StarryLobby;
import cn.starry.skywars.proxy.enums.Data;
import cn.starry.skywars.proxy.util.DataUtils;
import cn.starry.skywars.proxy.util.MathUtils;
import dev.jnic.annotations.Include;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.entity.Player;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Include
public class Scoreboard implements AssembleAdapter {
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("MM/dd/yy");
    private final DecimalFormat numformatInt = new DecimalFormat("0");
    private final DecimalFormat numFormat = new DecimalFormat("0.0");
    private final DecimalFormat numFormatTwo = new DecimalFormat("0.00");
    private final DecimalFormat df = new DecimalFormat(",###,###,###,###");
    private final String RadonGameName = "%network_id%";
    private final String type;

    private long lastAnimationTime = 0;
    private int animationTick = 0;
    
    public Scoreboard() {
        this.type = StarryLobby.getInstance().getType();
    }

    @Override
    public String getTitle(Player player) {

        List<String> animationTitle;
        //UUID uuid = player.getUniqueId();

        //LanguageType languageType = LanguageType.valueOf(Main.getInstance().getData().getPlayerData(uuid,"language"));

        if (type.equalsIgnoreCase("Lobby")) {
            animationTitle = Arrays.asList("&b❏  &b&l幻梦茶会  &b❒");
        } else if (type.equalsIgnoreCase("BedWars")) {
            animationTitle = Arrays.asList("&b&l起床战争");
        } else if (type.equalsIgnoreCase("Login")) {
            animationTitle = Arrays.asList("&b&l登录大厅");
        } else if (type.equalsIgnoreCase("Arcade")) {
            animationTitle = Arrays.asList("&6&l街&e&l机游戏","&f&l街&6&l机&e&l游戏","&f&l街机&6&l游&e&l戏","&f&l街机游&6&l戏","&f&l街机游戏","&e&l街机游戏","&f&l街机游戏","&e&l街机游戏","&e&l街机游戏","&e&l街机游戏","&e&l街机游戏","&e&l街机游戏","&e&l街机游戏","&e&l街机游戏","&e&l街机游戏","&e&l街机游戏","&e&l街机游戏","&e&l街机游戏","&e&l街机游戏","&e&l街机游戏","&e&l街机游戏");
        } else if (type.equalsIgnoreCase("MurderMystery")) {
            animationTitle = Arrays.asList("&6&l密&e&l室杀手","&f&l密&6&l室&e&l杀手","&f&l密室&6&l杀&e&l手","&f&l密室杀&6&l手","&f&l密室杀手","&e&l密室杀手","&f&l密室杀手","&e&l密室杀手","&e&l密室杀手","&e&l密室杀手","&e&l密室杀手","&e&l密室杀手","&e&l密室杀手","&e&l密室杀手","&e&l密室杀手","&e&l密室杀手","&e&l密室杀手","&e&l密室杀手","&e&l密室杀手","&e&l密室杀手","&e&l密室杀手");
        } else if (type.equalsIgnoreCase("SkyWars")) {
                animationTitle = Arrays.asList("&b&l空岛战争");
        } else if (type.equalsIgnoreCase("MegaWalls")) {
                animationTitle = Arrays.asList("&b&l超级战墙");
        } else if (type.equalsIgnoreCase("Prototype")) {
            animationTitle = Arrays.asList("&b&l游戏实验室");
        } else {
            animationTitle = Arrays.asList("&f...");
        }

        String text = animationTitle.get(animationTick);
        if (System.currentTimeMillis() - lastAnimationTime >= 125) {
            animationTick++;
            if (animationTick + 1 >= animationTitle.size()) {
                animationTick = 0;
            }
            lastAnimationTime = System.currentTimeMillis();
        }

        return text;

    }

    @Override
    public List<String> getLines(Player player) {
        UUID uuid = player.getUniqueId();
        List<String> lines = new ArrayList<>();
        String dateStr = DATE_FORMATTER.format(LocalDateTime.now());
        
            if (type.equalsIgnoreCase("Lobby")) {
                lines.add("  &7" + dateStr + " &8" + PlaceholderAPI.setPlaceholders(player, RadonGameName));
                lines.add(PlaceholderAPI.setPlaceholders(player, ""));
                lines.add(PlaceholderAPI.setPlaceholders(player, "  &f会员等级  "));
                lines.add(PlaceholderAPI.setPlaceholders(player, "  %core_rank_display%"));
                lines.add(PlaceholderAPI.setPlaceholders(player, ""));
                lines.add(PlaceholderAPI.setPlaceholders(player, "  &f成就点数 &d%core_points%"));
                lines.add(PlaceholderAPI.setPlaceholders(player, ""));
                lines.add(PlaceholderAPI.setPlaceholders(player, "&f  大厅 &a#1 "));
                lines.add(PlaceholderAPI.setPlaceholders(player, "&f  总在线 &a%bungee_online%&a «  "));
                lines.add(PlaceholderAPI.setPlaceholders(player, ""));
                lines.add(PlaceholderAPI.setPlaceholders(player, "  &fYume.games  "));
                lines.add(PlaceholderAPI.setPlaceholders(player, "  "));
                if (StarryLobby.getInstance().getRebootRunnable().getCurrentTask() != null) {
                    lines.add("  &c即将重启 &7(" + TimeUtil.millisToRoundedTime(StarryLobby.getInstance().getRebootRunnable().getCurrentTask().getEndTime() - System.currentTimeMillis()).replace(" ", "") + "后) ");
                }
                return lines;
            } else if (type.equalsIgnoreCase("BedWars")) {
                //switch (languageType) {
                    //default:
                lines.add("  &7" + dateStr + " &8" + PlaceholderAPI.setPlaceholders(player, RadonGameName));
                lines.add(PlaceholderAPI.setPlaceholders(player, ""));
                lines.add(PlaceholderAPI.setPlaceholders(player, "  &f等级 %bedwars_level%").replace("[","").replace("]",""));
                lines.add(PlaceholderAPI.setPlaceholders(player, ""));
                lines.add(PlaceholderAPI.setPlaceholders(player, "  &f进度 &b%bedwars_progress% "));
                lines.add(PlaceholderAPI.setPlaceholders(player, "  &f➥ &8[ %bedwars_progress_bar% &8] "));
                lines.add(PlaceholderAPI.setPlaceholders(player, ""));
                lines.add(PlaceholderAPI.setPlaceholders(player, "  &f代币 &2%bedwars_coins%"));
                lines.add(PlaceholderAPI.setPlaceholders(player, ""));
                lines.add(PlaceholderAPI.setPlaceholders(player, "  &f总击杀数 &a%bedwars_total_kills%"));
                lines.add(PlaceholderAPI.setPlaceholders(player, "  &f总胜利数 &a%bedwars_wins%"));
                lines.add(PlaceholderAPI.setPlaceholders(player, ""));
                if (StarryLobby.getInstance().getRebootRunnable().getCurrentTask() != null) {
                    lines.add("  &c即将重启 &7(" + TimeUtil.millisToRoundedTime(StarryLobby.getInstance().getRebootRunnable().getCurrentTask().getEndTime() - System.currentTimeMillis()).replace(" ", "") + "后) ");
                }
                        //break;
                //}
                return lines;
            } else if (type.equalsIgnoreCase("Login")) {
                lines.add(PlaceholderAPI.setPlaceholders(player,""));
                lines.add(PlaceholderAPI.setPlaceholders(player,"  &7输入以下命令注册"));
                lines.add(PlaceholderAPI.setPlaceholders(player,"  &f /reg <密码> <密码> "));
                lines.add(PlaceholderAPI.setPlaceholders(player,""));
                lines.add(PlaceholderAPI.setPlaceholders(player,"  &7输入以下命令登录"));
                lines.add(PlaceholderAPI.setPlaceholders(player,"  &f /login <密码> "));
                lines.add(PlaceholderAPI.setPlaceholders(player,""));
                lines.add(PlaceholderAPI.setPlaceholders(player,"  &f请牢记你的密码"));
                lines.add(PlaceholderAPI.setPlaceholders(player,"  &f切勿与他人分享 "));
                lines.add(PlaceholderAPI.setPlaceholders(player,""));
                return lines;
            } else if (type.equalsIgnoreCase("Arcade")) {
                lines.add("  &7" + dateStr + " &8" + PlaceholderAPI.setPlaceholders(player, RadonGameName));
                lines.add(PlaceholderAPI.setPlaceholders(player,""));
                lines.add(PlaceholderAPI.setPlaceholders(player,"&f在数据查询处NPC"));
                lines.add(PlaceholderAPI.setPlaceholders(player,"&f处查询你的街机"));
                lines.add(PlaceholderAPI.setPlaceholders(player,"&f游戏数据统计"));
                lines.add(PlaceholderAPI.setPlaceholders(player,""));
                lines.add(PlaceholderAPI.setPlaceholders(player,"&f硬币: &60"));
                lines.add(PlaceholderAPI.setPlaceholders(player,""));
                if (StarryLobby.getInstance().getRebootRunnable().getCurrentTask() != null) {
                    lines.add("&c即将重启 &7(" + TimeUtil.millisToRoundedTime(StarryLobby.getInstance().getRebootRunnable().getCurrentTask().getEndTime() - System.currentTimeMillis()).replace(" ", "") + "后) ");
                } else {
                    lines.add(PlaceholderAPI.setPlaceholders(player, "&ewww.YumeGames.net"));
                }
                return lines;
            } else if (type.equalsIgnoreCase("MurderMystery")) {
                lines.add("  &7" + dateStr + " &8" + PlaceholderAPI.setPlaceholders(player, RadonGameName));
                /*
                lines.add(PlaceholderAPI.setPlaceholders(player,""));
                lines.add(PlaceholderAPI.setPlaceholders(player,"&f总击杀数: &a%murdermystery_kills%"));
                lines.add(PlaceholderAPI.setPlaceholders(player,"&f总胜利数: &a%murdermystery_wins%"));
                lines.add(PlaceholderAPI.setPlaceholders(player,""));
                lines.add(PlaceholderAPI.setPlaceholders(player,"&f侦探胜场: &c%murdermystery_deaths%"));
                lines.add(PlaceholderAPI.setPlaceholders(player,"&f杀手胜场: &a%murdermystery_loses% "));
                lines.add(PlaceholderAPI.setPlaceholders(player,""));
                lines.add(PlaceholderAPI.setPlaceholders(player,"&f代币: &2%vault_eco_balance_commas%"));
                lines.add(PlaceholderAPI.setPlaceholders(player,""));
                 */
                lines.add(PlaceholderAPI.setPlaceholders(player,""));
                lines.add(PlaceholderAPI.setPlaceholders(player,"&f总击杀数: &a0"));
                lines.add(PlaceholderAPI.setPlaceholders(player,"&f总胜利数: &a0"));
                lines.add(PlaceholderAPI.setPlaceholders(player,""));
                lines.add(PlaceholderAPI.setPlaceholders(player,"&f侦探胜场: &a0"));
                lines.add(PlaceholderAPI.setPlaceholders(player,"&f杀手胜场: &a0"));
                lines.add(PlaceholderAPI.setPlaceholders(player,""));
                lines.add(PlaceholderAPI.setPlaceholders(player,"&f代币: &20"));
                lines.add(PlaceholderAPI.setPlaceholders(player,""));
                if (StarryLobby.getInstance().getRebootRunnable().getCurrentTask() != null) {
                    lines.add("&c即将重启 &7(" + TimeUtil.millisToRoundedTime(StarryLobby.getInstance().getRebootRunnable().getCurrentTask().getEndTime() - System.currentTimeMillis()).replace(" ", "") + "后) ");
                } else {
                    lines.add(PlaceholderAPI.setPlaceholders(player, "&ewww.YumeGames.net"));
                }
                return lines;
            } else if (type.equalsIgnoreCase("SkyWars")) {
                lines.add(PlaceholderAPI.setPlaceholders(player,""));
                lines.add(PlaceholderAPI.setPlaceholders(player," &f 等级 " +  DataUtils.getIntFormated(player, Data.PLAYERINFO, Data.field.LEVEL) + " "));
                lines.add(PlaceholderAPI.setPlaceholders(player,""));
                lines.add(PlaceholderAPI.setPlaceholders(player," &f 单挑模式击杀 &a" + MathUtils.Format(DataUtils.getTotalStat(player, Data.field.KILL, "SOLO"))));
                lines.add(PlaceholderAPI.setPlaceholders(player," &f 单挑模式胜场 &a" + MathUtils.Format(DataUtils.getTotalStat(player, Data.field.WIN, "SOLO"))));
                lines.add(PlaceholderAPI.setPlaceholders(player," &f 双人模式击杀 &a" + MathUtils.Format(DataUtils.getTotalStat(player, Data.field.KILL, "TEAM"))));
                lines.add(PlaceholderAPI.setPlaceholders(player," &f 双人模式胜场 &a" + MathUtils.Format(DataUtils.getTotalStat(player, Data.field.WIN, "TEAM"))));
                lines.add(PlaceholderAPI.setPlaceholders(player,""));
                lines.add(PlaceholderAPI.setPlaceholders(player," &f 硬币 &6" + DataUtils.getIntFormated(player,Data.PLAYERINFO, Data.field.COIN)));
                lines.add(PlaceholderAPI.setPlaceholders(player," &f 灵魂 &b" + DataUtils.getIntFormated(player,Data.PLAYERINFO, Data.field.SOUL) + "&7/" +(100+DataUtils.getInt(player,Data.PLAYERINFO, Data.field.SOUL_ADDED))));
                lines.add(PlaceholderAPI.setPlaceholders(player," &f 代币 &2" + DataUtils.getIntFormated(player, Data.PLAYERINFO,Data.field.TOKEN)));
                lines.add(PlaceholderAPI.setPlaceholders(player,""));
                if (StarryLobby.getInstance().getRebootRunnable().getCurrentTask() != null) {
                    lines.add("  &c即将重启 &7(" + TimeUtil.millisToRoundedTime(StarryLobby.getInstance().getRebootRunnable().getCurrentTask().getEndTime() - System.currentTimeMillis()).replace(" ", "") + "后)");
                    lines.add(" ");
                }
                return lines;
                /*
            } else if (Main.getPlugin(Main.class).getConfig().getString("type").equalsIgnoreCase("MegaWalls")) {
                PlayerStats stats = GamePlayer.get(player.getUniqueId()).getPlayerStats();
                lines.add(PlaceholderAPI.setPlaceholders(player,""));
                lines.add(PlaceholderAPI.setPlaceholders(player,"  &f已选职业"));
                lines.add(PlaceholderAPI.setPlaceholders(player,"  &f➥ &b" + stats.getSelected().getDisplayName()));
                lines.add(PlaceholderAPI.setPlaceholders(player,""));
                lines.add(PlaceholderAPI.setPlaceholders(player,"  &f击杀数 &a" + StringUtils.formattedCoins(stats.getKills())));
                lines.add(PlaceholderAPI.setPlaceholders(player,"  &f最终击杀数 &a" + StringUtils.formattedCoins(stats.getFinalKills())));
                lines.add(PlaceholderAPI.setPlaceholders(player,"  &f胜利次数 &a" + StringUtils.formattedCoins(stats.getWins())));
                lines.add(PlaceholderAPI.setPlaceholders(player,"  &fMVP场数 &a" + StringUtils.formattedCoins(stats.getMVP())));
                lines.add(PlaceholderAPI.setPlaceholders(player,""));
                lines.add(PlaceholderAPI.setPlaceholders(player,"  &f硬币 &6" + StringUtils.formattedCoins(stats.getCoins())));
                lines.add(PlaceholderAPI.setPlaceholders(player,""));
                if (MegaWallsLobby.isActiveMode()) {
                    lines.add(PlaceholderAPI.setPlaceholders(player,"  &e&l神话游戏"));
                    lines.add(PlaceholderAPI.setPlaceholders(player,"  &62倍硬币,持续&a&l" + getFormattedTime(MegaWallsLobby.getActiveMode() - System.currentTimeMillis())));
                    lines.add(PlaceholderAPI.setPlaceholders(player,""));
                }
                if (Main.getInstance().getRebootRunnable().getCurrentTask() != null) {
                    lines.add("  &c即将重启 &7(" + TimeUtil.millisToRoundedTime(Main.getInstance().getRebootRunnable().getCurrentTask().getEndTime() - System.currentTimeMillis()).replace(" ", "") + "后) ");
                    lines.add(" ");
                } else {
                    lines.add(PlaceholderAPI.setPlaceholders(player, "  &bFairyMc.cn "));
                    lines.add(PlaceholderAPI.setPlaceholders(player, ""));
                }
                return lines;

                 */
            } else if (type.equalsIgnoreCase("Prototype")) {
                lines.add("  &7" + dateStr + " &8" + PlaceholderAPI.setPlaceholders(player, RadonGameName));
                lines.add(PlaceholderAPI.setPlaceholders(player,""));
                lines.add(PlaceholderAPI.setPlaceholders(player,"&f  这个大厅内的  "));
                lines.add(PlaceholderAPI.setPlaceholders(player,"&f  所有游戏均处于  "));
                lines.add(PlaceholderAPI.setPlaceholders(player,"&f  开发测试阶段！  "));
                lines.add(PlaceholderAPI.setPlaceholders(player,""));
                lines.add(PlaceholderAPI.setPlaceholders(player,"&f  上报游戏漏洞或  "));
                lines.add(PlaceholderAPI.setPlaceholders(player,"&f  反馈游戏意见请发送  "));
                lines.add(PlaceholderAPI.setPlaceholders(player,"&f  电子邮件至  "));
                lines.add(PlaceholderAPI.setPlaceholders(player,"&b  support@yume.games  "));
                lines.add(PlaceholderAPI.setPlaceholders(player,""));
                return lines;
            } else {
                lines.add("");
                lines.add("&c嗯,你貌似进入了一个");
                lines.add("&c不应该存在的地方...");
                lines.add("");
                lines.add("&c尝试返回大厅,并将此情况反馈至");
                lines.add("&c服务器工作人员");
                lines.add("");
                return lines;
            }
        }

    private String getFormattedTime(long l) {
        int time = (int)l / 1000;
        int min = (int)Math.floor(time / 60);
        int sec = time % 60;
        String minStr = min < 10 ? "0" + min : String.valueOf(min);
        String secStr = sec < 10 ? "0" + sec : String.valueOf(sec);
        return minStr + ":" + secStr;
    }

}
