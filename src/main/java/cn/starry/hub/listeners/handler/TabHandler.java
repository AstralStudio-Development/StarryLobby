package cn.starry.hub.listeners.handler;

import cn.starry.core.utils.chat.CC;
import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.wrappers.WrappedChatComponent;
import dev.jnic.annotations.Include;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.lang.reflect.InvocationTargetException;
import java.time.Year;

@Include
public class TabHandler implements Listener {

    private static final WrappedChatComponent FOOTER = WrappedChatComponent.fromText(CC.translate(
            "&7\n&8       » &7交流群 &f477859489 &8«       \n&8» &7游戏地址 &fYume.games &8«\n" + "       \n&7"));
    
    // Cache the header template, date will be appended
    private static final String HEADER_TEMPLATE = "&7\n&f  幻梦茶会 &8@ &b%s \n&7";

    @EventHandler
    public void onJoin(PlayerJoinEvent event) throws InvocationTargetException {
        PacketContainer tabListPc = ProtocolLibrary.getProtocolManager().createPacket(PacketType.Play.Server.PLAYER_LIST_HEADER_FOOTER);
        
        String year = String.valueOf(Year.now().getValue());
        String headerText = String.format(HEADER_TEMPLATE, year);
        
        tabListPc.getChatComponents().write(0, WrappedChatComponent.fromText(CC.translate(headerText)));
        tabListPc.getChatComponents().write(1, FOOTER);
        
        ProtocolLibrary.getProtocolManager().sendServerPacket(event.getPlayer(), tabListPc);
    }

}
