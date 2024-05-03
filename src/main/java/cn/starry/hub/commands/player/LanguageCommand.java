package cn.starry.hub.commands.player;

import cn.starry.hub.Main;
import cn.starry.hub.api.enums.LanguageType;
import cn.starry.hub.functions.menu.profile.LanguageMenu;
import cn.starry.hub.listener.handler.LobbyHandler;
import cn.starry.hub.utils.ColorUtil;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.UUID;

public class LanguageCommand extends Command {

    public LanguageCommand() {
        super("language");
        setAliases(Arrays.asList("lang","语言"));
    }

    public boolean execute(CommandSender sender, String s, String[] strings) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ColorUtil.color("&c你必须是一名玩家才能执行这个指令!"));
            return true;
        }
        Player player = (Player)sender;
        UUID uuid = player.getUniqueId();
        LanguageType languageType = LanguageType.valueOf(Main.getInstance().getData().getPlayerData(uuid,"language"));
        if (strings.length != 1) {
            if (Main.getInstance().getConfig().getBoolean("apiMode")) {
                LanguageType[] languages = LanguageType.values();
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < languages.length; i++) {
                    sb.append(languages[i]);

                    if (i != languages.length - 1) {
                        sb.append(", ");
                    }
                }
                if (languageType.equals(LanguageType.CHINESE)) {
                    player.sendMessage(ColorUtil.color("&a未指定语言！有效语言: &e" + Arrays.toString(languages)).replace("[", "").replace("]", ""));
                } /*else if (languageType.equals(LanguageType.ENGLISH)) {
                    player.sendMessage(ColorUtil.color("&aMissing language! Vaild languages: &e" + Arrays.toString(languages)).replace("[", "").replace("]", ""));
                } else if (languageType.equals(LanguageType.JAPANESE)) {
                    player.sendMessage(ColorUtil.color("&a不明な言語です！有効な言語: &e" + Arrays.toString(languages)).replace("[", "").replace("]", ""));
                }
                */
            } else {
                new LanguageMenu().openMenu(player,true);
            }
            return true;
        }
        if (LanguageType.containsLanguage(strings[0])) {
            if (LanguageType.CHINESE.getSubName().contains(strings[0])) {
                player.sendMessage(ColorUtil.color("&a已选择语言: &e简体中文"));
                Main.getInstance().getData().updatePlayerData(uuid,"language",LanguageType.CHINESE.toString());
            } /*else if (LanguageType.ENGLISH.getSubName().contains(strings[0])) {
                player.sendMessage(ColorUtil.color("&aSelected language: &eEnglish"));
                Main.getInstance().getData().updatePlayerData(uuid,"language",LanguageType.ENGLISH.toString());
            } else if (LanguageType.JAPANESE.getSubName().contains(strings[0])) {
                player.sendMessage(ColorUtil.color("&a選択した言語: &e日本語"));
                Main.getInstance().getData().updatePlayerData(uuid,"language",LanguageType.JAPANESE.toString());
            }*/
            player.playSound(player.getLocation(),Sound.BLOCK_NOTE_PLING,1,1);
            new LobbyHandler().loadItem(player);
            return true;
        } else {
            LanguageType[] languages = LanguageType.values();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < languages.length; i++) {
                sb.append(languages[i]);

                if (i != languages.length - 1) {
                    sb.append(", ");
                }
            }
            if (languageType.equals(LanguageType.CHINESE)) {
                player.sendMessage(ColorUtil.color("&a无效语言“" + strings[0] + "”！有效语言: &e" + Arrays.toString(languages)).replace("[","").replace("]",""));
            } /*else if (languageType.equals(LanguageType.ENGLISH)) {
                player.sendMessage(ColorUtil.color("&aInvaild language '" + strings[0] + "'! Vaild languages: &e" + Arrays.toString(languages)).replace("[","").replace("]",""));
            } else if (languageType.equals(LanguageType.JAPANESE)) {
                player.sendMessage(ColorUtil.color("&a言語 「" + strings[0] + "」は無効です！ 有効な言語: &e" + Arrays.toString(languages)).replace("[","").replace("]",""));
            }*/
            return true;
        }
    }

}
