package cn.starry.hub.database;

import cn.starry.hub.Main;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.*;

import static com.mongodb.client.model.Filters.eq;

public class  MongoDB {

    private MongoClient mongoClient;
    private MongoDatabase database;
    private MongoCollection<Document> playerCollection;
    private MongoCollection<Document> slotCollection;
    private MongoCollection<Document> boosterCollection;
    private MongoCollection<Document> achievementCollection;
    private MongoCollection<Document> deliveryCollection;
    private MongoCollection<Document> presentsCollection;

    public MongoDB() {
        mongoClient = new MongoClient(Main.getPlugin(Main.class).getConfig().getString("database.host"), Main.getPlugin(Main.class).getConfig().getInt("database.port"));
        database = mongoClient.getDatabase(Main.getPlugin(Main.class).getConfig().getString("database.database"));
        playerCollection = database.getCollection("profile");
        slotCollection = database.getCollection("slot");
        boosterCollection = database.getCollection("booster");
        achievementCollection = database.getCollection("achievement");
        deliveryCollection = database.getCollection("delivery");
        presentsCollection = database.getCollection("presents");
    }

    public void savePlayerData(UUID playerUUID, String player, String isLoginBefore, String show,String receive,String time,String joinmessage,String settings_filterate_public,String level,String screen,String speed,String vanish,String isWipe,String wipeReason,String cooler,String language,String isNicked,String nickName,String rank,String rankColor, String prefixColor, String karma) {
        //ProfileData 
        Document playerData = new Document("uuid", playerUUID.toString())
                .append("name", player)
                .append("isLoginBefore", isLoginBefore)
                .append("settings_lobby_show", show)
                .append("settings_chat_receive", receive)
                .append("settings_lobby_time", time)
                .append("settings_chat_joinmessage", joinmessage)
                .append("settings_chat_filterate_public", settings_filterate_public)
                .append("level", level)
                .append("screen", screen)
                .append("speed", speed)
                .append("vanish", vanish)
                .append("wipe", isWipe)
                .append("wipeReason", wipeReason)
                .append("cooler", cooler)
                .append("language", language)
                .append("isNicked", isNicked)
                .append("nickName", nickName)
                .append("rank", rank)
                .append("rankColor", rankColor)
                .append("prefixColor", prefixColor)
                .append("karma", karma);
        if (playerCollection.find(new Document("uuid", playerUUID.toString())).first() != null) {
            return;
        }
        playerCollection.insertOne(playerData);
    }

    public void saveSlotData(UUID playerUUID, String player, String Slot1, String Slot2, String Slot3, String Slot4, String Slot5, String Slot6, String Slot7, String Slot8, String Slot9) {
        //SlotData
        Document playerData = new Document("uuid", playerUUID.toString())
                .append("name", player)
                .append("SLOT1", Slot1)
                .append("SLOT2", Slot2)
                .append("SLOT3", Slot3)
                .append("SLOT4", Slot4)
                .append("SLOT5", Slot5)
                .append("SLOT6", Slot6)
                .append("SLOT7", Slot7)
                .append("SLOT8", Slot8)
                .append("SLOT9", Slot9);
        if (slotCollection.find(new Document("uuid", playerUUID.toString())).first() != null) {
            return;
        }
        slotCollection.insertOne(playerData);
    }

    public void saveBoosterData(String player, String boosterType, double booster) {
        //Network BoosterData
        Document playerData = new Document("name", player)
                .append("game", boosterType)
                .append("booster", booster);
        if (boosterCollection.find(new Document("game", boosterType)).first() != null) {
            return;
        }
        boosterCollection.insertOne(playerData);
    }

    public void saveAchievementData(UUID playerUUID, String player, String achievement, int points) {
        //AchievementData
        Document playerData = new Document("uuid", playerUUID.toString())
                .append("name", player)
                .append("achievements", achievement)
                .append("points", points);
        if (achievementCollection.find(new Document("uuid", playerUUID.toString())).first() != null) {
            return;
        }
        achievementCollection.insertOne(playerData);
    }

    public void savePresentsData(UUID playerUUID, String player, String achievement, int points) {
        //AchievementData
        Document playerData = new Document("uuid", playerUUID.toString())
                .append("name", player)
                .append("presents", achievement)
                .append("points", points);
        if (presentsCollection.find(new Document("uuid", playerUUID.toString())).first() != null) {
            return;
        }
        presentsCollection.insertOne(playerData);
    }

    public void saveDeliveryData(UUID playerUUID, String player, boolean daily, boolean Default, boolean vip, boolean vipPlus, boolean mvp, boolean mvpPlus, boolean newPlayer) {
        //DeliveryData
        Document playerData = new Document("uuid", playerUUID.toString())
                .append("name", player)
                .append("daily", daily)
                .append("default", Default)
                .append("vip", vip)
                .append("vipPlus", vipPlus)
                .append("mvp", mvp)
                .append("mvpPlus", mvpPlus)
                .append("newPlayer", newPlayer);
        if (deliveryCollection.find(new Document("uuid", playerUUID.toString())).first() != null) {
            return;
        }
        deliveryCollection.insertOne(playerData);
    }

    public void updatePlayerData(UUID playerUUID, String type, String data) {
        if (playerCollection.find(new Document("uuid", playerUUID.toString())).first() == null) {
            return;
        }
        Bson filter = new Document("uuid", playerUUID.toString());
        Bson updateStatus = new Document("$set", new Document(type, data));
        playerCollection.updateOne(filter, updateStatus);
    }

    public void updatePlayerData(String name, String type, String data) {
        if (playerCollection.find(new Document("name", name)).first() == null) {
            return;
        }
        Bson filter = new Document("name", name);
        Bson updateStatus = new Document("$set", new Document(type, data));
        playerCollection.updateOne(filter, updateStatus);
    }

    public void updatePlayerData(UUID playerUUID, String type, boolean data) {
        if (playerCollection.find(new Document("uuid", playerUUID.toString())).first() == null) {
            return;
        }
        Bson filter = new Document("uuid", playerUUID.toString());
        Bson updateStatus = new Document("$set", new Document(type, data));
        playerCollection.updateOne(filter, updateStatus);
    }

    public void updateSlotData(UUID playerUUID, String type, String data) {
        if (slotCollection.find(new Document("uuid", playerUUID.toString())).first() == null) {
            return;
        }
        Bson filter = new Document("uuid", playerUUID.toString());
        Bson updateStatus = new Document("$set", new Document(type, data));
        slotCollection.updateOne(filter, updateStatus);
    }

    public void updateBoosterData(String game, String type, String data) {
        if (boosterCollection.find(new Document("game", game)).first() == null) {
            return;
        }
        Bson filter = new Document("game", game);
        Bson updateStatus = new Document("$set", new Document(type, data));
        boosterCollection.updateOne(filter, updateStatus);
    }

    public void updateBoosterMultiple(String game, String type, double data) {
        if (boosterCollection.find(new Document("game", game)).first() == null) {
            return;
        }
        Bson filter = new Document("game", game);
        Bson updateStatus = new Document("$set", new Document(type, data));
        boosterCollection.updateOne(filter, updateStatus);
    }

    public void updateAchievementData(UUID playerUUID, String type, String data) {
        if (achievementCollection.find(new Document("uuid", playerUUID.toString())).first() == null) {
            return;
        }
        if (getAchievementData(playerUUID,"achievements").equalsIgnoreCase("")) {
            Bson filter = new Document("uuid", playerUUID.toString());
            Bson updateStatus = new Document("$set", new Document(type, data));
            achievementCollection.updateOne(filter, updateStatus);
        } else {
            Bson filter = new Document("uuid", playerUUID.toString());
            Bson updateStatus = new Document("$set", new Document(type, getAchievementData(playerUUID,"achievements") + "," + data));
            achievementCollection.updateOne(filter, updateStatus);
        }
    }

    public void updateAchievementPoints(UUID playerUUID, String type, int data) {
        if (achievementCollection.find(new Document("uuid", playerUUID.toString())).first() == null) {
            return;
        }
        Bson filter = new Document("uuid", playerUUID.toString());
        Bson updateStatus = new Document("$set", new Document(type, getAchievementPoints(playerUUID,"points") + data));
        achievementCollection.updateOne(filter, updateStatus);
    }

    public void updatePresentsData(UUID playerUUID, String type, String data) {
        if (presentsCollection.find(new Document("uuid", playerUUID.toString())).first() == null) {
            return;
        }
        if (getPresentsData(playerUUID,"presents").equalsIgnoreCase("")) {
            Bson filter = new Document("uuid", playerUUID.toString());
            Bson updateStatus = new Document("$set", new Document(type, data));
            presentsCollection.updateOne(filter, updateStatus);
        } else {
            Bson filter = new Document("uuid", playerUUID.toString());
            Bson updateStatus = new Document("$set", new Document(type, getPresentsData(playerUUID,"presents") + "," + data));
            presentsCollection.updateOne(filter, updateStatus);
        }
    }

    public void updatePresentsPoints(UUID playerUUID, String type, int data) {
        if (presentsCollection.find(new Document("uuid", playerUUID.toString())).first() == null) {
            return;
        }
        Bson filter = new Document("uuid", playerUUID.toString());
        Bson updateStatus = new Document("$set", new Document(type, getPresentsPoints(playerUUID,"points") + data));
        presentsCollection.updateOne(filter, updateStatus);
    }

    public void updateDeliveryData(UUID playerUUID, String type, boolean data) {
        if (deliveryCollection.find(new Document("uuid", playerUUID.toString())).first() == null) {
            return;
        }
        Bson filter = new Document("uuid", playerUUID.toString());
        Bson updateStatus = new Document("$set", new Document(type, data));
        deliveryCollection.updateOne(filter, updateStatus);
    }

    public void updateAllDeliveryData(String type, boolean data) {
        Bson updateStatus = new Document("$set", new Document(type, data));
        deliveryCollection.updateMany(new Document(), updateStatus);
    }

    public String getPlayerData(UUID playerUUID, String type) {
        Document playerData = playerCollection.find(eq("uuid", playerUUID.toString())).first();
        if (playerData != null) {
            return playerData.getString(type);
        }
        return null;
    }

    public String getPlayerData(String name, String type) {
        Document playerData = playerCollection.find(eq("name", name)).first();
        if (playerData != null) {
            return playerData.getString(type);
        }
        return null;
    }

    public String getSlotData(UUID playerUUID, String type) {
        Document playerData = slotCollection.find(eq("uuid", playerUUID.toString())).first();
        if (playerData != null) {
            return playerData.getString(type);
        }
        return null;
    }

    public String getBoosterData(String game, String type) {
        Document playerData = boosterCollection.find(eq("game", game)).first();
        if (playerData != null) {
            return playerData.getString(type);
        }
        return null;
    }

    public Double getBoosterMultiple(String game, String type) {
        Document playerData = boosterCollection.find(eq("game", game)).first();
        if (playerData != null) {
            return playerData.getDouble(type);
        }
        return null;
    }

    public String getAchievementData(UUID playerUUID, String type) {
        Document playerData = achievementCollection.find(eq("uuid", playerUUID.toString())).first();
        if (playerData != null) {
            return playerData.getString(type);
        }
        return null;
    }

    public Integer getAchievementPoints(UUID playerUUID, String type) {
        Document playerData = achievementCollection.find(eq("uuid", playerUUID.toString())).first();
        if (playerData != null) {
            return playerData.getInteger(type);
        }
        return null;
    }

    public String getPresentsData(UUID playerUUID, String type) {
        Document playerData = presentsCollection.find(eq("uuid", playerUUID.toString())).first();
        if (playerData != null) {
            return playerData.getString(type);
        }
        return null;
    }

    public Integer getPresentsPoints(UUID playerUUID, String type) {
        Document playerData = presentsCollection.find(eq("uuid", playerUUID.toString())).first();
        if (playerData != null) {
            return playerData.getInteger(type);
        }
        return null;
    }

    public double getPercentageOfAchievement(String achievementInternalName) {
        long totalUsers = achievementCollection.countDocuments();
        if (totalUsers == 0) {
            return 0.0;
        }
        List<String> allAchievements = new ArrayList<>();
        FindIterable<Document> documents = achievementCollection.find();
        for (Document document : documents) {
            String achievement = document.getString("achievements");
            if (achievement != null) {
                allAchievements.add(achievement);
            }
        }
        int count = 0;
        for (String singleAchievement : allAchievements) {
            if (singleAchievement.contains(achievementInternalName)) {
                count++;
            }
        }
        double percentage = (double) count / totalUsers * 100;
        return Math.round(percentage * 10.0) / 10.0; // 保留一位小数
    }


    public boolean getDeliveryData(UUID playerUUID, String type) {
        Document playerData = deliveryCollection.find(eq("uuid", playerUUID.toString())).first();
        if (playerData != null) {
            return playerData.getBoolean(type);
        }
        return false;
    }

}
