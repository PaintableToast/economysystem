package de.paintabletoast.system.api;

import org.bukkit.entity.Player;

public class EconomyHandler implements Economy{
    @Override
    public void setBankAccount(Player player, double money) {
        if(isEnough(player, money));
    }

    @Override
    public void clearBankAccount(Player player) {
        setBankAccount(player, 0);
    }

    @Override
    public int getBalance(Player player) {
        return 0;
    }

    @Override
    public void addBalance(Player player, double money) {
        setBankAccount(player, getBalance(player) + money);
    }

    @Override
    public void removeBalance(Player player, double money) {
        setBankAccount(player, getBalance(player) - money);
    }

    @Override
    public void resetBankAccount(Player player) {

    }

    @Override
    public boolean isEnough(Player player, double money) {
        if(money > 0) return true;
        else return false;
    }
}
