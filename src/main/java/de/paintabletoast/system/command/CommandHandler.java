package de.paintabletoast.system.command;

import de.paintabletoast.system.api.EconomyHandler;
import de.paintabletoast.system.var.FileReplace;
import de.paintabletoast.system.var.Message;
import de.paintabletoast.system.var.Sounds;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.Player;

public interface CommandHandler extends CommandExecutor {
    EconomyHandler economyHandler = new EconomyHandler();
    default void sendSoundMessage(final Player player, final String message, final Sound sound) {
        sendSoundMessage(player, message, sound, false);
    }
    default void sendModifiedSoundMessage(final Player player, final String message, final Sound sound) {
        sendSoundMessage(player,
                message
                , sound);
    }
    default void sendSoundMessage(final Player player, final String message, final Sound sound, final boolean open) {
        if(open)
            playOpenWorldSound(player.getLocation(), sound);
        else
            playSound(player, sound);
        player.sendMessage(message);
    }
    default void broadcastSoundMessage(final Location location, final String message, final Sound sound) {
        playOpenWorldSound(location, sound);
        for(final Player player : Bukkit.getOnlinePlayers())
            player.sendMessage(message);
    }
    default void broadcastSoundMessage(final String message, final Sound sound) {
        for(final Player player : Bukkit.getOnlinePlayers()) {
            player.sendMessage(message);
            playSound(player, sound);
        }
    }
    default void playSound(final Player player, final Sound sound) {
        player.getWorld().playSound(player.getLocation(), sound, 1, 1);
    }
    default void playOpenWorldSound(final Location location, final Sound sound) {
        location.getWorld().playSound(location, sound, 1, 1);
    }
    default void sendNoPermissionSound(final Player player) {
        sendSoundMessage(player, Message.NO_PERMISSION.getMessage(), Sounds.NO_PERMISSION.getSound());
    }
    default void sendWrongCommandArgumentation(final Player player, final String command) {
        sendSoundMessage(player, Message.WRONG_COMMAND_ARGUMENTATION.getMessage().replace(FileReplace.COMMAND_ARGUMENT.getPart(), command), Sounds.WRONG_COMMAND.getSound());
    }
    default void logNoConsole(final String command) {
        Bukkit.getConsoleSender().sendMessage(Message.NO_CONSOLE.getMessage().replace(FileReplace.COMMAND.getPart(), command));
    }
}
