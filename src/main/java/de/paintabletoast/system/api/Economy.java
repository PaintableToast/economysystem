package de.paintabletoast.system.api;

import org.bukkit.entity.Player;

public interface Economy {
    /*
        SETS THE BANKACCOUNT TO A SIGNIFICANT VALUE
     */
    public void setBankAccount(final Player player, final double money);

    /*
        SETS THE BANKACCOUNT TO 0
     */
    public void clearBankAccount(final Player player);

    /*
        RETURNS BANK ACCOUNT BALANCE VALUE
     */
    public int getBalance(final Player player);

    /*
        ADD SOMETHING TO THE BANKACCOUNT
     */
    public void addBalance(final Player player, final double money);

    /*
        REMOVE SOMETHING FROM BANKACCOUNT
     */
    public void removeBalance(final Player player, final double money);

    /*
        RESETS THE BANKACCOUNT TO THE DEFAULT VALUE
     */
    public void resetBankAccount(final Player player);

    /*
        RETURN IF THERE IS ENOUGH MONEY ON THE BANKACCOUNT [TRUE/FALSE]
     */
    public boolean isEnough(final Player player, final double money);
}
