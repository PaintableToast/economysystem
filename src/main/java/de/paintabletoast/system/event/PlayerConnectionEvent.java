package de.paintabletoast.system.event;

import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerConnectionEvent implements ListenerHandler{
    @EventHandler
    public final void onConnect(PlayerJoinEvent event) {

    }
    @EventHandler
    public final void onDisconnect(PlayerQuitEvent event) {

    }
}
