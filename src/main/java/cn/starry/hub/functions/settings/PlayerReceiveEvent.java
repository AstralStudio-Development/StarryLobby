package cn.starry.hub.functions.settings;

import cn.starry.hub.parm.AutoRegister;
import cn.starry.hub.utils.ColorUtil;
import cn.starry.hub.Main;
import cn.starry.hub.utils.RankUtil;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@AutoRegister
public class PlayerReceiveEvent implements Listener {

    private static List<String> DirtyList = new ArrayList<>();
    private static List<String> MessageList = new ArrayList<>();

    public static void loadSensitiveWords() {
        DirtyList.add("DoMCer");
        DirtyList.add("www.YumeGames.net");
        DirtyList.add("花雨庭");
        DirtyList.add("www.xxx.com");
        DirtyList.add("mc.163.com");
        DirtyList.add("163.com");
        DirtyList.add(".com");
        DirtyList.add(".cn");
        DirtyList.add("zqat.top");
        DirtyList.add("pornhub.com");
        DirtyList.add("挂");
        DirtyList.add("王航");
        DirtyList.add("妈死");
        DirtyList.add("操你妈");
        DirtyList.add("草泥马");
        DirtyList.add("cnmd");
        DirtyList.add("ctmd");
        DirtyList.add("MS");
        DirtyList.add("ms");
        DirtyList.add("sm");
        DirtyList.add("SM");
        DirtyList.add("fuck");
        DirtyList.add("sb");
        DirtyList.add("傻逼");
        DirtyList.add("fw");
        DirtyList.add("FW");
        DirtyList.add("废物");
        DirtyList.add("lj");
        DirtyList.add("LJ");
        DirtyList.add("垃圾");
        DirtyList.add("菜鸡");
        DirtyList.add("蔡徐坤");
        DirtyList.add("cxk");
        DirtyList.add("ikun");
        DirtyList.add("鸡你太美");
        DirtyList.add("妈的");
        DirtyList.add("玛德");
        DirtyList.add("傻叉");
        DirtyList.add("脑残");
        DirtyList.add("NT");
        DirtyList.add("nt");
        DirtyList.add("xxs");
        DirtyList.add("XXS");
        DirtyList.add("我是你爸");
        DirtyList.add("叫爸爸");
        DirtyList.add("脑瘫");
        DirtyList.add("贱种");
        DirtyList.add("杂种");
        DirtyList.add("服务器");
        DirtyList.add("手淫");
        DirtyList.add("做爱");
        DirtyList.add("做鸡");
        DirtyList.add("手冲");
        DirtyList.add("操你");
        DirtyList.add("强奸");
        DirtyList.add("强暴");
        DirtyList.add("好爽~");
        DirtyList.add("啊~");
        DirtyList.add("c你");
        DirtyList.add("插你");
        DirtyList.add("处女膜");
        DirtyList.add("舔你");
        DirtyList.add("前后运动");
        DirtyList.add("一个人寂寞");
        DirtyList.add("加群");
        DirtyList.add("加Q群");
        DirtyList.add("购买配置");
        DirtyList.add("殴打");
        DirtyList.add("做坤");
        DirtyList.add("大鸡巴");
        DirtyList.add("大坤巴");
        DirtyList.add("${jndi:ldap:");
        DirtyList.add("张元朗");
        DirtyList.add("张宸宁");
        DirtyList.add("张辰宁");
        DirtyList.add("张震");
        DirtyList.add("习近平");
        DirtyList.add("毛泽东");
        DirtyList.add("邓小平");
        DirtyList.add("王雨涵");
        DirtyList.add("郑梓文");
    }

    public static void loadPrefabricationMessages() {
        MessageList.add("尽管我很喜欢pvp，但是我认为你打的比我更好 :3");
        MessageList.add("我喜欢在我的披萨上撒上菠萝!");
        MessageList.add("如果这个世界是无限的...那太阳是怎么围绕着它旋转的呢?");
        MessageList.add("不要尝试欺骗你的朋友们:(");
        MessageList.add("大家好!我是一个普通的玩家,我爱这个服务器=w=");
        MessageList.add("在我无聊的时候,我喜欢前往b站看视频");
        MessageList.add("我爱你<3");
        MessageList.add("等下...这不是我说的!");
        MessageList.add("我的错...抱歉QAQ");
        MessageList.add("你说得对,但是原神是一款...");
        MessageList.add("你觉得这是我的锅,那就是我的锅");
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();

        String rank = Main.getInstance().getData().getPlayerData(player.getUniqueId(),"rank").equalsIgnoreCase("NOP_DEFAULT") ? RankUtil.getFormatRankById(Main.getInstance().getData().getPlayerData(player.getUniqueId(),"rank"), player.getUniqueId()) : RankUtil.getFormatRankById(Main.getInstance().getData().getPlayerData(player.getUniqueId(),"rank"), player.getUniqueId()) + " ";

        if (Main.getPlugin(Main.class).getConfig().getString("type").equals("Login")) {
            event.setCancelled(true);
            player.sendMessage(ColorUtil.color("&c该服务器限制了聊天!"));
            return;
        }

        if (event.getMessage().contains("${jndi:ldap:")) {
            player.kickPlayer(ColorUtil.color("&c您因疑似尝试使用Log4j漏洞被踢出服务器"));
            event.setCancelled(true);
        }

        for (Player p : Bukkit.getOnlinePlayers()) {
            if (!Main.getPlugin(Main.class).getData().getPlayerData(p.getUniqueId(), "settings_chat_receive").equals("ENABLE")) {
                event.getRecipients().remove(p);
                if (p.getName().equals(player.getName())) {
                    event.setCancelled(true);
                    player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO,1,1);
                    player.sendMessage(ColorUtil.color("&c在你开启屏蔽发言时,你的发言也将受到限制!"));
                }
            }
        }

        if (!player.hasPermission("lobby.bypass")) {
            for (String words : DirtyList) {
                if (event.getMessage().contains(words)) {
                    String message = getRandomMessage();
                    event.setMessage(message);
                }
            }
        }

        event.setFormat(ColorUtil.color(rank + "%s&f: " + (player.hasPermission("lobby.admin") ? ColorUtil.color("%s") : "%s")));

        String msg;
        if (player.hasPermission("lobby.emoji")) {
            msg = event.getMessage()
                    .replaceAll("<3", "§c❤§f")
                    .replaceAll(":star:", "§6✬§f")
                    .replaceAll(":yes:", "§a✔§f")
                    .replaceAll(":no:", "§c✖§f")
                    .replaceAll(":java:", "§b☕")
                    .replaceAll(":123:", "§a1§e2§c3")
                    .replaceAll(":oof:", "§c§lOOF")
                    .replaceAll(":cat:", "§d(\\\u25cfω●~)§f")
                    .replaceAll(":qwq:", "§d§lQwQ§f")
                    .replaceAll(":awa:", "§a§lAwA§f")
                    .replaceAll(":owo:", "§e§lOwO~§f")
                    .replaceAll(":skeleton:", "§4☠§f")
                    .replaceAll(":snow:", "§b☃§f");
        } else {
            msg = event.getMessage();
        }
        event.setMessage(msg);
    }

    private static String getRandomMessage() {
        Random random = new Random();
        int index = random.nextInt(MessageList.size());

        return MessageList.get(index);
    }

}
