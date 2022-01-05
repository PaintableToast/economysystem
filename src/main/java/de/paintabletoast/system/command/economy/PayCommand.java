package de.paintabletoast.system.command.economy;

import de.paintabletoast.system.api.EconomyHandler;
import de.paintabletoast.system.command.CommandHandler;
import de.paintabletoast.system.var.FileReplace;
import de.paintabletoast.system.var.Message;
import de.paintabletoast.system.var.Sounds;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PayCommand implements CommandHandler {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player) {
            final EconomyHandler economyHandler = new EconomyHandler();
            final Player player = (Player) sender;
            switch (args.length) {
                case 2:
                    try {
                        final Player target = Bukkit.getPlayer(args[0]);
                        if(Bukkit.getOnlinePlayers().contains(target))
                            try {
                                final double number = Double.valueOf(args[1]);
                                if(number > 0) {
                                    if(player == target) sendSoundMessage(player, Message.PAY_NOT_SELF.getMessage(), Sounds.PAY_NOT_SELF.getSound());
                                    else {
                                        economyHandler.addBalance(target, number);
                                        economyHandler.removeBalance(player, number);
                                        sendSoundMessage(player, Message.PAY.getMessage().replace(FileReplace.TARGET.getPart(), target.getName()).replace(FileReplace.MONEY.getPart(), String.valueOf(number)).replace(FileReplace.BALANCE.getPart(), String.valueOf(economyHandler.getBalance(player))), Sounds.PAY.getSound());
                                        sendSoundMessage(player, Message.PAY_GET.getMessage().replace(FileReplace.PLAYER.getPart(), player.getName()).replace(FileReplace.MONEY.getPart(), String.valueOf(number)).replace(FileReplace.BALANCE.getPart(), String.valueOf(economyHandler.getBalance(target))), Sounds.PAY_GET.getSound());
                                    }
                                } else sendSoundMessage(player, Message.WRONG_NUMBER.getMessage(), Sounds.WRONG_NUMBER.getSound());
                            } catch (final Exception error) {
                                sendSoundMessage(player, Message.WRONG_NUMBER.getMessage(), Sounds.WRONG_NUMBER.getSound());
                            }
                    } catch (final Exception error) {
                        sendSoundMessage(player, Message.PLAYER_NOT_ONLINE.getMessage().replace(FileReplace.PLAYER.getPart(), args[0]), Sounds.NOT_ONLINE.getSound());
                    }
                    break;
                default:
                    sendWrongCommandArgumentation(player, Message.PAY_COMMAND.getMessage());
            }
        } else logNoConsole(command.getName());
        return false;
    }
}
