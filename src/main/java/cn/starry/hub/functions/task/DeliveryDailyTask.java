package cn.starry.hub.functions.task;

import cn.starry.hub.Main;

import java.util.TimerTask;

public class DeliveryDailyTask extends TimerTask {

    public void run() {
        Main.getInstance().getData().updateAllDeliveryData("daily",false);
    }

}
