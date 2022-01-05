package de.paintabletoast.system.var;

public enum FileReplace {
    PREFIX,
    CONSOLE_PREFIX,
    PLUGIN_NAME,
    COMMAND,
    COMMAND_ARGUMENT,
    ENTER,
    LISTENER,
    INFORMATION,
    PLAYER,
    TARGET,
    DEFAULT,
    MONEY,
    BALANCE,
    WALLET,
    FILE,
    HOST,
    PORT,
    DATABASE,
    USER,
    PASSWORD;
    FileReplace() {

    }
    public String getPart() {
        return "%" + this.toString() + "%";
    }
}
