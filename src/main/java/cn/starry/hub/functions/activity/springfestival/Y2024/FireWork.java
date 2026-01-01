package cn.starry.hub.functions.activity.springfestival.Y2024;

import org.bukkit.*;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.inventory.meta.FireworkMeta;

import java.util.Random;

public class FireWork {

    public void shootFirework() {
        Firework firework_1 = (Firework)(Bukkit.getWorld("world").spawnEntity(new Location(Bukkit.getWorld("world"), -35.5, 50, -0.5), EntityType.FIREWORK_ROCKET));
        Firework firework_2 = (Firework)(Bukkit.getWorld("world").spawnEntity(new Location(Bukkit.getWorld("world"), -16.5, 49.0, 26.5), EntityType.FIREWORK_ROCKET));
        Firework firework_3 = (Firework)(Bukkit.getWorld("world").spawnEntity(new Location(Bukkit.getWorld("world"), -13.5, 45.0, -34.5), EntityType.FIREWORK_ROCKET));
        Firework firework_4 = (Firework)(Bukkit.getWorld("world").spawnEntity(new Location(Bukkit.getWorld("world"), 26.5, 66.0, 13.5), EntityType.FIREWORK_ROCKET));
        Firework firework_5 = (Firework)(Bukkit.getWorld("world").spawnEntity(new Location(Bukkit.getWorld("world"), -40.5, 46.0, -19.5), EntityType.FIREWORK_ROCKET));
        Firework firework_6 = (Firework)(Bukkit.getWorld("world").spawnEntity(new Location(Bukkit.getWorld("world"), -48.5, 48.0, 14.5), EntityType.FIREWORK_ROCKET));
        Firework firework_7 = (Firework)(Bukkit.getWorld("world").spawnEntity(new Location(Bukkit.getWorld("world"), -6.5, 53.0, 59.5), EntityType.FIREWORK_ROCKET));
        Firework firework_8 = (Firework)(Bukkit.getWorld("world").spawnEntity(new Location(Bukkit.getWorld("world"), 22.5, 57.0, -77.5), EntityType.FIREWORK_ROCKET));
        FireworkMeta fm_1 = firework_1.getFireworkMeta();
        FireworkMeta fm_2 = firework_2.getFireworkMeta();
        FireworkMeta fm_3 = firework_3.getFireworkMeta();
        FireworkMeta fm_4 = firework_4.getFireworkMeta();
        FireworkMeta fm_5 = firework_5.getFireworkMeta();
        FireworkMeta fm_6 = firework_6.getFireworkMeta();
        FireworkMeta fm_7 = firework_7.getFireworkMeta();
        FireworkMeta fm_8 = firework_8.getFireworkMeta();
        Random r = new Random();
        FireworkEffect.Type type = null;
        int fType = r.nextInt(5) + 1;
        switch (fType) {
            default: {
                type = FireworkEffect.Type.BALL;
                break;
            }
            case 2: {
                type = FireworkEffect.Type.BALL_LARGE;
                break;
            }
            case 3: {
                type = FireworkEffect.Type.BURST;
                break;
            }
            case 4: {
                type = FireworkEffect.Type.CREEPER;
                break;
            }
            case 5: {
                type = FireworkEffect.Type.STAR;
            }
        }
        int c1i = r.nextInt(16) + 1;
        int c2i = r.nextInt(16) + 1;
        Color c1 = this.getColor(c1i);
        Color c2 = this.getColor(c2i);
        FireworkEffect effect = FireworkEffect.builder().flicker(r.nextBoolean()).withColor(c1).withFade(c2).with(type).trail(r.nextBoolean()).build();
        fm_1.addEffect(effect);
        fm_2.addEffect(effect);
        fm_3.addEffect(effect);
        fm_4.addEffect(effect);
        fm_5.addEffect(effect);
        fm_6.addEffect(effect);
        fm_7.addEffect(effect);
        fm_8.addEffect(effect);
        int power = r.nextInt(2) + 1;
        fm_1.setPower(power);
        fm_2.setPower(power);
        fm_3.setPower(power);
        fm_4.setPower(power);
        fm_5.setPower(power);
        fm_6.setPower(power);
        fm_7.setPower(power);
        fm_8.setPower(power);
        firework_1.setFireworkMeta(fm_1);
        firework_2.setFireworkMeta(fm_2);
        firework_3.setFireworkMeta(fm_3);
        firework_4.setFireworkMeta(fm_4);
        firework_5.setFireworkMeta(fm_5);
        firework_6.setFireworkMeta(fm_6);
        firework_7.setFireworkMeta(fm_7);
        firework_8.setFireworkMeta(fm_8);
    }

    public Color getColor(int c) {
        switch (c) {
            default: {
                return Color.AQUA;
            }
            case 2: {
                return Color.BLACK;
            }
            case 3: {
                return Color.BLUE;
            }
            case 4: {
                return Color.FUCHSIA;
            }
            case 5: {
                return Color.GRAY;
            }
            case 6: {
                return Color.GREEN;
            }
            case 7: {
                return Color.LIME;
            }
            case 8: {
                return Color.MAROON;
            }
            case 9: {
                return Color.NAVY;
            }
            case 10: {
                return Color.OLIVE;
            }
            case 11: {
                return Color.ORANGE;
            }
            case 12: {
                return Color.PURPLE;
            }
            case 13: {
                return Color.RED;
            }
            case 14: {
                return Color.SILVER;
            }
            case 15: {
                return Color.TEAL;
            }
            case 16: {
                return Color.WHITE;
            }
            case 17:
        }
        return Color.YELLOW;
    }

}
