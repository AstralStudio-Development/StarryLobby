package cn.starry.hub.functions.scoreboard;

import cn.starry.hub.api.data.PlayerData;
import cn.starry.hub.functions.activity.springfestival.Y2024.presents.PresentsManager;
import cn.starry.hub.utils.scoreboard.AssembleAdapter;
import cn.starry.hub.utils.time.TimeUtil;
import cn.starry.hub.Main;
import cn.starry.hub.api.enums.ProfileState;
import cn.starry.hub.utils.RankUtil;
import cn.starry.skywars.proxy.enums.Data;
import cn.starry.skywars.proxy.util.DataUtils;
import cn.starry.skywars.proxy.util.MathUtils;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.entity.Player;
import xyz.yuzegod.megawallslobby.MegaWallsLobby;
import xyz.yuzegod.megawallslobby.game.GamePlayer;
import xyz.yuzegod.megawallslobby.stats.PlayerStats;
import xyz.yuzegod.megawallslobby.util.StringUtils;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class Scoreboard implements AssembleAdapter {
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yy");
    private final DecimalFormat numformatInt = new DecimalFormat("0");
    private final DecimalFormat numFormat = new DecimalFormat("0.0");
    private final DecimalFormat numFormatTwo = new DecimalFormat("0.00");
    private final DecimalFormat df = new DecimalFormat(",###,###,###,###");
    private final String RadonGameName = "L" + generateRandomString();

    private long lastAnimationTime = 0;
    private int animationTick = 0;

    @Override
    public String getTitle(Player player) {

        List<String> animationTitle;
        //UUID uuid = player.getUniqueId();

        //LanguageType languageType = LanguageType.valueOf(Main.getInstance().getData().getPlayerData(uuid,"language"));

        if (Main.getPlugin(Main.class).getConfig().getString("type").equalsIgnoreCase("Lobby")) {
            //if (languageType.equals(LanguageType.CHINESE)) {
            animationTitle = Arrays.asList("&b&l主大厅");
            //} else {
             //   animationTitle = Arrays.asList("&b&lSTARDUST");
            //}
        } else if (Main.getPlugin(Main.class).getConfig().getString("type").equalsIgnoreCase("BedWars")) {
            animationTitle = Arrays.asList("&6&l起&e&l床战争","&f&l起&6&l床&e&l战争","&f&l起床&6&l战&e&l争","&f&l起床战&6&l争","&f&l起床战争","&e&l起床战争","&f&l起床战争","&e&l起床战争","&e&l起床战争","&e&l起床战争","&e&l起床战争","&e&l起床战争","&e&l起床战争","&e&l起床战争","&e&l起床战争","&e&l起床战争","&e&l起床战争","&e&l起床战争","&e&l起床战争","&e&l起床战争","&e&l起床战争");
        } else if (Main.getPlugin(Main.class).getConfig().getString("type").equalsIgnoreCase("Login")) {
            animationTitle = Arrays.asList("&b&l登录大厅");
        } else if (Main.getPlugin(Main.class).getConfig().getString("type").equalsIgnoreCase("Arcade")) {
            animationTitle = Arrays.asList("&6&l街&e&l机游戏","&f&l街&6&l机&e&l游戏","&f&l街机&6&l游&e&l戏","&f&l街机游&6&l戏","&f&l街机游戏","&e&l街机游戏","&f&l街机游戏","&e&l街机游戏","&e&l街机游戏","&e&l街机游戏","&e&l街机游戏","&e&l街机游戏","&e&l街机游戏","&e&l街机游戏","&e&l街机游戏","&e&l街机游戏","&e&l街机游戏","&e&l街机游戏","&e&l街机游戏","&e&l街机游戏","&e&l街机游戏");
        } else if (Main.getPlugin(Main.class).getConfig().getString("type").equalsIgnoreCase("MurderMystery")) {
            animationTitle = Arrays.asList("&6&l密&e&l室杀手","&f&l密&6&l室&e&l杀手","&f&l密室&6&l杀&e&l手","&f&l密室杀&6&l手","&f&l密室杀手","&e&l密室杀手","&f&l密室杀手","&e&l密室杀手","&e&l密室杀手","&e&l密室杀手","&e&l密室杀手","&e&l密室杀手","&e&l密室杀手","&e&l密室杀手","&e&l密室杀手","&e&l密室杀手","&e&l密室杀手","&e&l密室杀手","&e&l密室杀手","&e&l密室杀手","&e&l密室杀手");
        } else if (Main.getPlugin(Main.class).getConfig().getString("type").equalsIgnoreCase("SkyWars")) {
                animationTitle = Arrays.asList("&b&l空岛战争");
        } else if (Main.getPlugin(Main.class).getConfig().getString("type").equalsIgnoreCase("MegaWalls")) {
                animationTitle = Arrays.asList("&b&l超级战墙");
        } else if (Main.getPlugin(Main.class).getConfig().getString("type").equalsIgnoreCase("Prototype")) {
            animationTitle = Arrays.asList("&e&l游戏实验室");
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
        boolean profile = PlayerData.PROFILE.get(player) == ProfileState.LOADED;
        //LanguageType languageType = LanguageType.valueOf(Main.getInstance().getData().getPlayerData(uuid,"language"));
        if (!profile) {
            lines.add("");
            lines.add("&c我们正在加载您的档案...");
            lines.add("&c请稍等片刻...");
            lines.add("");
            lines.add("&c如等待长时间仍在加载,");
            lines.add("&c请尝试重新进入服务器.");
            lines.add("");
            return lines;
        } else {
            if (Main.getPlugin(Main.class).getConfig().getString("type").equalsIgnoreCase("Lobby")) {
               // switch (languageType) {
                    //default:
                lines.add("  &7" + dateFormat.format(System.currentTimeMillis()) + " &8" + RadonGameName);
                lines.add(PlaceholderAPI.setPlaceholders(player, ""));
                lines.add(PlaceholderAPI.setPlaceholders(player, "  &f会员等级  "));
                lines.add(PlaceholderAPI.setPlaceholders(player, "  " + RankUtil.getDisplayRankById(Main.getInstance().getData().getPlayerData(uuid,"rank"),uuid)));
                lines.add(PlaceholderAPI.setPlaceholders(player, ""));
                lines.add(PlaceholderAPI.setPlaceholders(player, "  &f成就点数 &e%starrylobby_points%"));
                lines.add(PlaceholderAPI.setPlaceholders(player, "  &f服务器等级 &31"));
                lines.add(PlaceholderAPI.setPlaceholders(player, ""));
                lines.add(PlaceholderAPI.setPlaceholders(player, "  &f大厅 &a#1 "));
                lines.add(PlaceholderAPI.setPlaceholders(player, "  &f在线数 &a%bungee_total% "));
                lines.add(PlaceholderAPI.setPlaceholders(player, ""));
                lines.add(PlaceholderAPI.setPlaceholders(player, "  &f好友在线人数 &a0 "));
                lines.add(PlaceholderAPI.setPlaceholders(player, "  &f公会在线人数 &20 "));
                lines.add(PlaceholderAPI.setPlaceholders(player, "  "));
                if (Main.getInstance().getRebootRunnable().getCurrentTask() != null) {
                    lines.add("  &c即将重启 &7(" + TimeUtil.millisToRoundedTime(Main.getInstance().getRebootRunnable().getCurrentTask().getEndTime() - System.currentTimeMillis()).replace(" ", "") + "后) ");
                } else {
                    lines.add(PlaceholderAPI.setPlaceholders(player, "  &fYumeGames"));
                }
                        //break;
                //}
                return lines;
            } else if (Main.getPlugin(Main.class).getConfig().getString("type").equalsIgnoreCase("BedWars")) {
                //switch (languageType) {
                    //default:
                        lines.add("&7" + dateFormat.format(System.currentTimeMillis()) + " &8" + RadonGameName);
                        lines.add(PlaceholderAPI.setPlaceholders(player, ""));
                        lines.add(PlaceholderAPI.setPlaceholders(player, "&f等级: %bw1058_player_level%").replace("[","").replace("]",""));
                        //lines.add(PlaceholderAPI.setPlaceholders(player, ""));
                        //lines.add(PlaceholderAPI.setPlaceholders(player, "&fELO: " + RatingProvider.getRatingDisplayName(uuid)));
                        lines.add(PlaceholderAPI.setPlaceholders(player, ""));
                        lines.add(PlaceholderAPI.setPlaceholders(player, "&f进度: &b%bw1058_player_xp_formatted%&7/&a%bw1058_player_rerq_xp_formatted% "));
                        lines.add(PlaceholderAPI.setPlaceholders(player, " %bw1058_player_progress% "));
                        lines.add(PlaceholderAPI.setPlaceholders(player, ""));
                        lines.add(PlaceholderAPI.setPlaceholders(player, "&f代币: &20"));
                        lines.add(PlaceholderAPI.setPlaceholders(player, ""));
                        lines.add(PlaceholderAPI.setPlaceholders(player, "&f总击杀数: &a%bw1058_stats_kills%"));
                        lines.add(PlaceholderAPI.setPlaceholders(player, "&f总胜利数: &a%bw1058_stats_wins%"));
                        lines.add(PlaceholderAPI.setPlaceholders(player, ""));
                        if (Main.getInstance().getRebootRunnable().getCurrentTask() != null) {
                            lines.add("&c即将重启 &7(" + TimeUtil.millisToRoundedTime(Main.getInstance().getRebootRunnable().getCurrentTask().getEndTime() - System.currentTimeMillis()).replace(" ", "") + "后) ");
                        } else {
                            lines.add(PlaceholderAPI.setPlaceholders(player, "&ewww.YumeGames.net"));
                        }
                        //break;
                //}
                return lines;
            } else if (Main.getPlugin(Main.class).getConfig().getString("type").equalsIgnoreCase("Login")) {
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
            } else if (Main.getPlugin(Main.class).getConfig().getString("type").equalsIgnoreCase("Arcade")) {
                lines.add("&7" + dateFormat.format(System.currentTimeMillis()) + " &8" + RadonGameName);
                lines.add(PlaceholderAPI.setPlaceholders(player,""));
                lines.add(PlaceholderAPI.setPlaceholders(player,"&f在数据查询处NPC"));
                lines.add(PlaceholderAPI.setPlaceholders(player,"&f处查询你的街机"));
                lines.add(PlaceholderAPI.setPlaceholders(player,"&f游戏数据统计"));
                lines.add(PlaceholderAPI.setPlaceholders(player,""));
                lines.add(PlaceholderAPI.setPlaceholders(player,"&f硬币: &60"));
                lines.add(PlaceholderAPI.setPlaceholders(player,""));
                if (Main.getInstance().getRebootRunnable().getCurrentTask() != null) {
                    lines.add("&c即将重启 &7(" + TimeUtil.millisToRoundedTime(Main.getInstance().getRebootRunnable().getCurrentTask().getEndTime() - System.currentTimeMillis()).replace(" ", "") + "后) ");
                } else {
                    lines.add(PlaceholderAPI.setPlaceholders(player, "&ewww.YumeGames.net"));
                }
                return lines;
            } else if (Main.getPlugin(Main.class).getConfig().getString("type").equalsIgnoreCase("MurderMystery")) {
                lines.add("&7" + dateFormat.format(System.currentTimeMillis()) + " &8" + RadonGameName);
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
                if (Main.getInstance().getRebootRunnable().getCurrentTask() != null) {
                    lines.add("&c即将重启 &7(" + TimeUtil.millisToRoundedTime(Main.getInstance().getRebootRunnable().getCurrentTask().getEndTime() - System.currentTimeMillis()).replace(" ", "") + "后) ");
                } else {
                    lines.add(PlaceholderAPI.setPlaceholders(player, "&ewww.YumeGames.net"));
                }
                return lines;
            } else if (Main.getPlugin(Main.class).getConfig().getString("type").equalsIgnoreCase("SkyWars")) {
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
                if (Main.getInstance().getRebootRunnable().getCurrentTask() != null) {
                    lines.add("  &c即将重启 &7(" + TimeUtil.millisToRoundedTime(Main.getInstance().getRebootRunnable().getCurrentTask().getEndTime() - System.currentTimeMillis()).replace(" ", "") + "后)");
                    lines.add(" ");
                }
                return lines;
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
            } else if (Main.getPlugin(Main.class).getConfig().getString("type").equalsIgnoreCase("Prototype")) {
                lines.add("&7" + dateFormat.format(System.currentTimeMillis()) + " &8" + RadonGameName);
                lines.add(PlaceholderAPI.setPlaceholders(player,""));
                lines.add(PlaceholderAPI.setPlaceholders(player,"&f这个大厅内的"));
                lines.add(PlaceholderAPI.setPlaceholders(player,"&f所有游戏均处于"));
                lines.add(PlaceholderAPI.setPlaceholders(player,"&f开发测试阶段！"));
                lines.add(PlaceholderAPI.setPlaceholders(player,""));
                lines.add(PlaceholderAPI.setPlaceholders(player,"&f上报游戏漏洞或"));
                lines.add(PlaceholderAPI.setPlaceholders(player,"&f反馈游戏意见请发送"));
                lines.add(PlaceholderAPI.setPlaceholders(player,"&f电子邮件至"));
                lines.add(PlaceholderAPI.setPlaceholders(player,"&esupport@YumeGames.net"));
                lines.add(PlaceholderAPI.setPlaceholders(player,""));
                if (Main.getInstance().getRebootRunnable().getCurrentTask() != null) {
                    lines.add("&c即将重启 &7(" + TimeUtil.millisToRoundedTime(Main.getInstance().getRebootRunnable().getCurrentTask().getEndTime() - System.currentTimeMillis()).replace(" ", "") + "后) ");
                } else {
                    lines.add(PlaceholderAPI.setPlaceholders(player, "&ewww.YumeGames.net"));
                }
                return lines;
            } else {
                lines.add("");
                lines.add("&c呃,你貌似进入了一个");
                lines.add("&c不应该存在的地方...");
                lines.add("");
                lines.add("&c尝试返回大厅,并将此情况反馈至");
                lines.add("&c服务器工作人员");
                lines.add("");
                return lines;
            }
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

    private String generateRandomString() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        String NUMBERS = "0123456789";
        String LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        for (int i = 0; i < 1; i++) {
            int index = random.nextInt(NUMBERS.length());
            sb.append(NUMBERS.charAt(index));
        }
        // 生成后两位大写字母
        for (int i = 0; i < 1; i++) {
            int index = random.nextInt(LETTERS.length());
            sb.append(LETTERS.charAt(index));
        }
        return sb.toString();
    }

}
