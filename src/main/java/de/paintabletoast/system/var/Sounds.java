package de.paintabletoast.system.var;

import org.bukkit.Sound;

public enum Sounds {
    NO_PERMISSION,
    WRONG_COMMAND,
    NOT_ONLINE,
    WRONG_NUMBER,
    PAY_NOT_SELF,
    PAY,
    PAY_GET,
    BANK_CHECK,
    BANK_CHECK_ASK,
    BANK_CHECK_GET,
    BANK_CHECK_AGREE_SEND,
    BANK_CHECK_DISAGREE_SEND,
    BANK_CHECK_AGREE_GET,
    BANK_CHECK_DISAGREE_GET,
    BANK_CHECK_TIMEOUT_SEND,
    BANK_CHECK_VIEW,
    ECONOMY_CLEAR,
    ECONOMY_CLEAR_SELF,
    ECONOMY_CLEAR_GET,
    ECONOMY_RESET,
    ECONOMY_RESET_SELF,
    ECONOMY_RESET_GET,
    ECONOMY_REMOVE_GET,
    ECONOMY_SET_SELF,
    ECONOMY_SET_OTHER,
    ECONOMY_SET_GET,
    ECONOMY_ADD_SELF,
    ECONOMY_ADD_OTHER,
    ECONOMY_ADD_GET,
    ECONOMY_REMOVE_SELF,
    ECONOMY_REMOVE_OTHER,
    ECONOmY_REMOVE_GET,
    ;
    public final Sound getSound() {
        return Sound.valueOf(ConfigFile.SOUNDS.getConfig().getString(PathFile.SOUNDS.getPath() + this.name()));
    }
}
