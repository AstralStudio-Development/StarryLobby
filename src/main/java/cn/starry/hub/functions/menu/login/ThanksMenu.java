package cn.starry.hub.functions.menu.login;

import cn.starry.core.utils.chat.CC;
import cn.starry.hub.functions.menu.login.button.SpecialThanksButton;
import cn.starry.hub.utils.menu.Button;
import cn.starry.hub.utils.menu.Menu;
import cn.starry.hub.utils.menu.buttons.BackButton;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class ThanksMenu extends Menu {

    private final Menu parent;

    public ThanksMenu(Menu parent) {
        this.parent = parent;
    }

    public ThanksMenu() {
        this(null);
    }

    @Override
    public String getTitle(Player player) {
        return CC.translate("           &0特别感谢列表");
    }

    @Override
    public Map<Integer, Button> getButtons(Player player) {
        Map<Integer, Button> buttons = new HashMap<>();

        buttons.put(10, new SpecialThanksButton("&bBlue_King2China", "2020", "1c3e4c161ca5a412c95998ef4a1a0f75823f7b7e34930cd025af24cebfcab365"));
        buttons.put(11, new SpecialThanksButton("&bShuiLongtou", "2021", "bd6a3079bc697d6da9a4dae00cd99af46bc4481217dad70e5f59475ef0d9f288"));
        buttons.put(12, new SpecialThanksButton("&bMoJiya_", "2021-2024", "90fd2ccfb617f5c7c09310a4fa1e3c17719c7d5943e74394c004e21d0ea21d3"));
        buttons.put(13, new SpecialThanksButton("&bLeruitou", "2021-2024", "de6260ab7af6d7958427ae5a178ce5f129c5cbfec509b056fbbe4267e604bbe2"));
        buttons.put(14, new SpecialThanksButton("&bBedrock_ShaDog", "2023-至今", "abbe222efc6fafc192af856416c91d3316a4fd589d50e51de7a5702484829a7f"));
        buttons.put(15, new SpecialThanksButton("&bTheGoodBoys", "2023-至今", "80db68ee5c317625537feb67c9d395edd5d3ae2e3f4372bcfcb47e19fcc7a99a"));
        buttons.put(16, new SpecialThanksButton("&bShizoukia", "2024-至今", "8e6d097d57e0d059cf3b01d859b72b6ff15bd17d1e09a41e9b5ca39ab424b7fc"));

        buttons.put(19, new SpecialThanksButton("&bCan_Leng_", "2022-2024", "64b22fdccf923bb566fa35934b2d2a9cdd1e347da603912e272024a4fe4cb783"));
        buttons.put(20, new SpecialThanksButton("&bLove_Taffy", "2021-至今", "8e37d0c63ae415f189dcdbc9d179f541f9af5bce33a91d1d46d3665252092613"));
        buttons.put(21, new SpecialThanksButton("&bKaBuSaMa", "2025-至今", "57ac0469b906b9039b69a56fa07a76a6a1920e098cab4ee7a863a818a21f2e3a"));
        buttons.put(22, new SpecialThanksButton("&bdyslmj_boy", "2022-至今", "1256629e143e341ed26701b785ec4d041ba36d5ceae3b5dd0a0b3df9271f45a1"));

        buttons.put(40, new BackButton(parent));

        return buttons;
    }

    @Override
    public int getSize() {
        return 45;
    }
}
