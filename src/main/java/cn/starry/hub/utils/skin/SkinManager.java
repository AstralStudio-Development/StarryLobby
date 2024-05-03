package cn.starry.hub.utils.skin;

import com.mojang.authlib.properties.Property;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class SkinManager {
    public static String[] getSkinFormApi(String skinName) {
        String value = null;
        String signature = null;
        try {
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(SkinManager.getResponse("https://api.mojang.com/users/profiles/minecraft/" + skinName));
            JSONObject json = (JSONObject)obj;
            String uuid = (String)json.get("id");
            Object obj2 = parser.parse(SkinManager.getResponse("https://sessionserver.mojang.com/session/minecraft/profile/" + uuid + "?unsigned=false"));
            JSONObject json2 = (JSONObject)obj2;
            Object props = ((JSONArray)json2.get("properties")).get(0);
            JSONObject propsObj = (JSONObject)props;
            value = (String)propsObj.get("value");
            signature = (String)propsObj.get("signature");
        } catch (ParseException ignored) {
        }
        return new String[]{value, signature};
    }

    public static String[] getSkin(Player player) {
        Property property = ((CraftPlayer) player).getProfile().getProperties().get("textures").iterator().next();
        return new String[]{property.getValue(), property.getSignature()};
    }

    private static String getResponse(String url) {
        StringBuffer receive = new StringBuffer();
        try {
            URL url1 = new URL(url);
            HttpURLConnection http = (HttpURLConnection)url1.openConnection();
            http.setRequestMethod("GET");
            http.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:95.0) Gecko/20100101 Firefox/95.0");
            InputStream im = http.getInputStream();
            InputStreamReader imr = new InputStreamReader(im, StandardCharsets.UTF_8);
            BufferedReader br = new BufferedReader(imr);
            String theline = null;
            while ((theline = br.readLine()) != null) {
                receive.append(theline).append("\r\n");
            }
            br.close();
            imr.close();
            im.close();
        }
        catch (IOException e) {
            return null;
        }
        return receive.toString();
    }
}
