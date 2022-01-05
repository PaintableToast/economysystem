package de.paintabletoast.system.boot;

import de.paintabletoast.system.command.Command;
import de.paintabletoast.system.event.Event;
import de.paintabletoast.system.manager.PluginHandler;
import de.paintabletoast.system.mysql.MySQL;
import de.paintabletoast.system.var.ConfigFile;
import de.paintabletoast.system.var.Message;

public class Base implements PluginHandler {
    private static Base instance;
    private MySQL mysql;
    public Base() {
        instance = this;
    }
    protected final void initPlugin() {
        try {
            mysql = new MySQL();
            loadConfigurationFiles();
            log(Message.PLUGIN_LOAD.getMessage());
            if(mysql.isConnectable()) {
                mysql.connect();
                Thread.sleep(1000);
            }
            registerCommands();
            registerEvents();
            log(Message.PLUGIN_SUCCESS_LOAD.getMessage());
        } catch (final Exception error) {
            logError(error, Message.PLUGIN_ERROR_LOAD.getMessage());
        }
    }
    public static Base getInstance() {
        return instance;
    }
    private final void registerCommands() {
        for(final Command command : Command.values());
    }
    private final void registerEvents() {
        for (final Event event : Event.values());
    }
    private final void loadConfigurationFiles() {
        for(final ConfigFile configfile: ConfigFile.values()) configfile.saveDefaultConfig();
    }
}
