package de.paintabletoast.system.event;

import de.paintabletoast.system.boot.Core;
import de.paintabletoast.system.manager.PluginHandler;
import de.paintabletoast.system.var.FileReplace;
import de.paintabletoast.system.var.Message;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public enum Event implements PluginHandler {
    PLAYERCONNECTIONEVENT(new PlayerConnectionEvent());
    private Listener listener;
    private final Core core = JavaPlugin.getPlugin(Core.class);
    Event(final Listener listener) {
        this.listener = listener;
        registerEvent();
    }
    private final void registerEvent() {
        final String name = listener.getClass().getSimpleName();
        try {
            Bukkit.getPluginManager().registerEvents(listener, core);
            log(Message.LISTENER_SUCCESS.getMessage().replace(FileReplace.LISTENER.getPart(), name));
        } catch (final Exception error) {
            logError(error, Message.LISTENER_ERROR.getMessage().replace(FileReplace.LISTENER.getPart(), name));
        }
    }
}
