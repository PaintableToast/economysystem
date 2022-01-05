package de.paintabletoast.system.command.economy;

import de.paintabletoast.system.api.EconomyHandler;
import de.paintabletoast.system.command.CommandHandler;
import de.paintabletoast.system.manager.PluginHandler;
import de.paintabletoast.system.var.FileReplace;
import de.paintabletoast.system.var.Message;
import de.paintabletoast.system.var.Permission;
import de.paintabletoast.system.var.Sounds;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class EconomyCommand implements CommandHandler, PluginHandler, TabCompleter {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player target;
        double number;
        if (sender instanceof Player) {
            final Player player = (Player) sender;
            if(player.hasPermission(Permission.ECONOMY_COMMAND_ADMIN.getPermission()))
                switch (args.length) {
                    case 1:
                        switch (args[0].toLowerCase()) {
                            case "clear":
                                economyHandler.clearBankAccount(player);
                                sendSoundMessage(player, Message.ECONOMY_CLEAR_SELF.getMessage(), Sounds.ECONOMY_CLEAR_SELF.getSound());
                                break;
                            case "reset":
                                economyHandler.resetBankAccount(player);
                                sendSoundMessage(player, Message.ECONOMY_RESET_SELF.getMessage(), Sounds.ECONOMY_RESET_SELF.getSound());
                                break;
                            default:
                                sendWrongCommandArgumentation(player, Message.ECONOMY_COMMAND.getMessage());
                        }
                        break;
                    case 2:
                        try {
                            number = Double.valueOf(args[1]);
                            if (number > 0)
                                switch (args[0].toLowerCase()) {
                                    case "set":
                                        economyHandler.setBankAccount(player, number);
                                        sendSoundMessage(player,
                                                Message.ECONOMY_SET_SELF.getMessage()
                                                        .replace(FileReplace.BALANCE.getPart(), String.valueOf(economyHandler.getBalance(player))),
                                                Sounds.ECONOMY_SET_SELF.getSound());
                                        break;
                                    case "add":
                                        economyHandler.addBalance(player, number);
                                        sendSoundMessage(player,
                                                Message.ECONOMY_ADD_SELF.getMessage()
                                                        .replace(FileReplace.MONEY.getPart(), args[1])
                                                        .replace(FileReplace.BALANCE.getPart(), String.valueOf(economyHandler.getBalance(player))),
                                                Sounds.ECONOMY_ADD_SELF.getSound());
                                        break;
                                    case "remove":
                                        economyHandler.removeBalance(player, number);
                                        sendSoundMessage(player,
                                                Message.ECONOMY_REMOVE_SELF.getMessage()
                                                        .replace(FileReplace.MONEY.getPart(), args[1])
                                                        .replace(FileReplace.BALANCE.getPart(), String.valueOf(economyHandler.getBalance(player))),
                                                Sounds.ECONOMY_REMOVE_SELF.getSound());
                                        break;
                                    default:
                                        sendWrongCommandArgumentation(player, Message.ECONOMY_COMMAND.getMessage());
                                }
                            else
                                sendSoundMessage(player, Message.WRONG_NUMBER.getMessage(), Sounds.WRONG_NUMBER.getSound());
                        } catch (final Exception error) {
                            sendSoundMessage(player, Message.WRONG_NUMBER.getMessage(), Sounds.WRONG_NUMBER.getSound());
                            return true;
                        }
                        try {
                            target = Bukkit.getPlayer(args[1]);
                            switch (args[0].toLowerCase()) {
                                case "clear":
                                    economyHandler.clearBankAccount(target);
                                    break;
                                case "reset":
                                    economyHandler.resetBankAccount(target);
                                    break;
                                default:
                                    sendWrongCommandArgumentation(player, Message.ECONOMY_COMMAND.getMessage());
                            }
                        } catch (final Exception error) {
                            sendSoundMessage(player, Message.PLAYER_NOT_ONLINE.getMessage().replace(FileReplace.PLAYER.getPart(), args[1]), Sounds.NOT_ONLINE.getSound());
                            return true;
                        }
                        break;
                    case 3:
                        try {
                            number = Double.valueOf(args[2]);
                            target = Bukkit.getPlayer(args[1]);
                            if (number > 0)
                                if (Bukkit.getOnlinePlayers().contains(target))
                                    switch (args[0].toLowerCase()) {
                                        case "add":
                                            economyHandler.addBalance(target, number);
                                            break;
                                        case "remove":
                                            economyHandler.removeBalance(target, number);
                                            break;
                                        case "set":
                                            economyHandler.removeBalance(target, number);
                                            break;
                                        default:
                                            sendWrongCommandArgumentation(player, Message.ECONOMY_COMMAND.getMessage());
                                            break;
                                    }
                                else
                                    sendSoundMessage(player, Message.PLAYER_NOT_ONLINE.getMessage().replace(FileReplace.PLAYER.getPart(), args[1]), Sounds.NOT_ONLINE.getSound());
                            else
                                sendSoundMessage(player, Message.WRONG_NUMBER.getMessage(), Sounds.WRONG_NUMBER.getSound());
                        } catch (final NullPointerException error) {
                            sendSoundMessage(player, Message.PLAYER_NOT_ONLINE.getMessage().replace(FileReplace.PLAYER.getPart(), args[1]), Sounds.NOT_ONLINE.getSound());
                            sendSoundMessage(player, Message.WRONG_NUMBER.getMessage(), Sounds.WRONG_NUMBER.getSound());
                        }
                        break;
                    default:
                        sendWrongCommandArgumentation(player, Message.ECONOMY_COMMAND.getMessage());
                }
            else sendNoPermissionSound(player);
        } else logNoConsole(command.getName());
        return false;
    }
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        final ArrayList<String> args0 = new ArrayList<>();
        final ArrayList<String> args1 = new ArrayList<>();
        ArrayList<String> completion = new ArrayList<>();
        args0.add("add");
        args0.add("remove");
        args0.add("clear");
        args0.add("reset");
        args0.add("set");
        for (final Player player : Bukkit.getOnlinePlayers())
            args1.add(player.getName());
        switch (args.length) {
            case 1:
                for(final String default_arguments : args0)
                    if(default_arguments.startsWith(args[0].toLowerCase()))
                        completion.add(default_arguments);
                return completion;
            case 2:
                for(final String player_names : args1)
                    if(player_names.startsWith(args[1].toLowerCase()))
                        completion.add(player_names);
                return completion;
        }
        return null;
    }
}
    /*

    ECONOMY [ADD/REMOVE/CLEAR/RESET/SET] [PLAYER] {MONEY}

    ECONOMY ADD [PLAYER] [MONEY] - OTHER ADD
    ECONOMY ADD [MONEY] - SELF ADD

    ECONOMY REMOVE [PLAYER] [MONEY] - OTHER REMOVE
    ECONOMY REMOVE [MONEY] - SELF REMOVE

    ECONOMY CLEAR [PLAYER] - CLEAR TO 0 OTHER
    ECONOMY CLEAR - CLEAR TO 0 OWN

    ECONOMY RESET [PLAYER] - RESET TO DEFAULT OTHER
    ECONOMY RESET - RESET OWN ACCOUNT

    ECONOMY SET [PLAYER] [MONEY] - OTHER SET MONEY
    ECONOMY SET [MONEY] - SELF SET MONEY
     */