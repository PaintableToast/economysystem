package de.paintabletoast.system.command;

import de.paintabletoast.system.boot.Core;
import de.paintabletoast.system.command.economy.EconomyCommand;
import de.paintabletoast.system.manager.PluginHandler;
import de.paintabletoast.system.var.FileReplace;
import de.paintabletoast.system.var.Message;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.TabCompleter;
import org.bukkit.plugin.java.JavaPlugin;

public enum Command implements PluginHandler {
    ECONOMY(new EconomyCommand());
    private final Core core = JavaPlugin.getPlugin(Core.class);
    private CommandExecutor commandExecutor;
    private TabCompleter completer;
    Command(final CommandExecutor commandExecutor) {
        this.commandExecutor = commandExecutor;
        registerCommand();
    }
    Command(final CommandExecutor commandExecutor, final TabCompleter tabcompleter) {
        this.commandExecutor = commandExecutor;
        this.completer = tabcompleter;
        registerCommand();
    }
    private final void registerCommand() {
        try {
            core.getCommand(this.name().toLowerCase()).setExecutor(commandExecutor);
            if(this.getCompleter() != null)
                core.getCommand(this.name().toLowerCase()).setTabCompleter(completer);
            log(Message.COMMAND_SUCCESS.getMessage().replace(FileReplace.COMMAND.getPart(), this.name().toLowerCase()));
        } catch (final Exception error) {
            logError(error, Message.COMMAND_ERROR.getMessage().replace(FileReplace.COMMAND.getPart(), this.name().toLowerCase()));
        }
    }
    private final TabCompleter getCompleter() {
        return completer;
    }
}
