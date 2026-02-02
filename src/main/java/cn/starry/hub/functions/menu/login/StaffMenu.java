package cn.starry.hub.functions.menu.login;

import cn.starry.core.utils.chat.CC;
import cn.starry.hub.functions.menu.login.button.MoreStaffButton;
import cn.starry.hub.functions.menu.login.button.StaffHeadButton;
import cn.starry.hub.functions.menu.login.button.StaffTypeButton;
import cn.starry.hub.utils.menu.Button;
import cn.starry.hub.utils.menu.Menu;
import cn.starry.hub.utils.menu.buttons.BackButton;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class StaffMenu extends Menu {

    private final Menu parent;

    public StaffMenu(Menu parent) {
        this.parent = parent;
    }

    public StaffMenu() {
        this(null);
    }

    @Override
    public String getTitle(Player player) {
        return CC.translate("           &0在职工作人员列表");
    }

    @Override
    public Map<Integer, Button> getButtons(Player player) {
        Map<Integer, Button> buttons = new HashMap<>();

        buttons.put(10, new StaffTypeButton("&c运营组", "&f负责服务器主要策划,开发,运营的技术人员。", "RED"));

        buttons.put(11, new StaffHeadButton("&cStalyer", true, "首席睡觉官", "看我干什么，问下一个。", "5b4ff4465cc6bcc6ed2a20c39014e9e4d5af9fb639fac1c05d50f47d14325f16"));
        buttons.put(12, new StaffHeadButton("&cStarry_Killer", true, "陪上一个睡的", "看我干什么，问上一个。", "ec4e780fdefd63d140d0597307a5236d331ffc02ed16615d15f56930a5ffcc9c"));
        buttons.put(13, new StaffHeadButton("&cQlickly_", true, "睡觉", "工作日白天不在 有事找我多发几次", "58432ab55415c7842434f6534008e65b8293ac3b0720b95b6af5e2a0a6633b9e"));
        buttons.put(14, new StaffHeadButton("&cCloudForeal", true, "开发、策划", "我真的怀疑有些人闲的程度啊", "bbf137bddedf4b2737b150cde4cc19283fb8be72af29fa5c86e33166cb2f421e"));
        buttons.put(15, new StaffHeadButton("&cpi_ka", true, "开发、策划", "", "5c6fe12e5cce23843167c9ced85c3b5ae17b8cde569d31c8f9bce3121d392d86"));
        buttons.put(16, new StaffHeadButton("&czhuiqiuat", true, "开发", "", "c89c5f915240c551539945e8fd8b3d33f6a0995c5fab0aec5d07a158253be723"));

        buttons.put(19, new StaffTypeButton("&2客服组", "&f负责服务器内管理玩家群体。", "GREEN"));

        buttons.put(20, new StaffHeadButton("&2SakiMukiYou", true, "客服", "", "482c998edb03d6f19539a61e9d8e428404ccaf67ebd55589f2fae6169278c0e0"));

        buttons.put(40, new BackButton(parent));
        buttons.put(44, new MoreStaffButton(this)); // Pass this as parent to ThanksMenu

        return buttons;
    }

    @Override
    public int getSize() {
        return 45;
    }
}
