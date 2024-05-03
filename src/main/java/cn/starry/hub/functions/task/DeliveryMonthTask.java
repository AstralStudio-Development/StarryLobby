package cn.starry.hub.functions.task;

import cn.starry.hub.Main;

import java.util.TimerTask;

public class DeliveryMonthTask extends TimerTask {

    public void run() {
        Main.getInstance().getData().updateAllDeliveryData("normal",false);
        Main.getInstance().getData().updateAllDeliveryData("vip",false);
        Main.getInstance().getData().updateAllDeliveryData("vipPlus",false);
        Main.getInstance().getData().updateAllDeliveryData("mvp",false);
        Main.getInstance().getData().updateAllDeliveryData("mvpPlus",false);
    }

}
