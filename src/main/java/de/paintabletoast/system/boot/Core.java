package de.paintabletoast.system.boot;

import de.paintabletoast.system.manager.PluginHandler;
import de.paintabletoast.system.mysql.MySQL;
import de.paintabletoast.system.var.Message;
import org.bukkit.plugin.java.JavaPlugin;

public class Core extends JavaPlugin implements PluginHandler {
    @Override
    public final void onEnable() {
        logLogo();
        final Base base = new Base();
        base.initPlugin();
    }
    @Override
    public final void onDisable() {
        logLogo();
        final MySQL mySQL = MySQL.getInstance();
        if(mySQL.isConnectable())
            MySQL.getInstance().disconnect();
        log(Message.PLUGIN_STOP.getMessage());
    }
    private final void logLogo() {
        log("\n" +
                "\n" +
                " ________       ___    ___ ________  _________  _______   _____ ______      \n" +
                "|\\   ____\\     |\\  \\  /  /|\\   ____\\|\\___   ___\\\\  ___ \\ |\\   _ \\  _   \\    \n" +
                "\\ \\  \\___|_    \\ \\  \\/  / | \\  \\___|\\|___ \\  \\_\\ \\   __/|\\ \\  \\\\\\__\\ \\  \\   \n" +
                " \\ \\_____  \\    \\ \\    / / \\ \\_____  \\   \\ \\  \\ \\ \\  \\_|/_\\ \\  \\\\|__| \\  \\  \n" +
                "  \\|____|\\  \\    \\/  /  /   \\|____|\\  \\   \\ \\  \\ \\ \\  \\_|\\ \\ \\  \\    \\ \\  \\ \n" +
                "    ____\\_\\  \\ __/  / /       ____\\_\\  \\   \\ \\__\\ \\ \\_______\\ \\__\\    \\ \\__\\\n" +
                "   |\\_________\\\\___/ /       |\\_________\\   \\|__|  \\|_______|\\|__|     \\|__|\n" +
                "   \\|_________\\|___|/        \\|_________|                                   \n" +
                "                                                                            \n" +
                "                                                                            \n" +
                "\n");
    }
}
