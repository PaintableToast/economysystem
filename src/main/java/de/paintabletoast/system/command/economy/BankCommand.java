package de.paintabletoast.system.command.economy;

import de.paintabletoast.system.api.EconomyHandler;
import de.paintabletoast.system.command.CommandHandler;
import de.paintabletoast.system.var.FileReplace;
import de.paintabletoast.system.var.Message;
import de.paintabletoast.system.var.Sounds;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BankCommand implements CommandHandler {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player) {
            final Player player = (Player) sender;
            final EconomyHandler economyHandler = new EconomyHandler();
            switch (args.length) {
                case 0:
                    sendSoundMessage(player, Message.BANK_CHECK.getMessage().replace(FileReplace.BALANCE.getPart(), String.valueOf(economyHandler.getBalance(player))), Sounds.BANK_CHECK.getSound());
                    break;
                case 1:

                default:
                    sendWrongCommandArgumentation(player, Message.BANK_COMMAND.getMessage());
            }
        } else logNoConsole(command.getName());
        return false;
    }
}
