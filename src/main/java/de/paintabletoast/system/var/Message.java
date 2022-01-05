package de.paintabletoast.system.var;

public enum Message {
    PREFIX(PathFile.GENERAL),
    CONSOLE_PREFIX(PathFile.GENERAL),
    INFORMATION_TAG(PathFile.GENERAL),
    WALLET(PathFile.GENERAL),

    NO_PERMISSION(PathFile.USER),
    WRONG_COMMAND_ARGUMENTATION(PathFile.USER),
    PLAYER_NOT_ONLINE(PathFile.USER),
    WRONG_NUMBER(PathFile.USER),
    PAY_NOT_SELF(PathFile.USER),
    PAY(PathFile.USER),
    PAY_GET(PathFile.USER),
    BANK_CHECK(PathFile.USER),
    BANK_CHECK_ASK(PathFile.USER),
    BANK_CHECK_GET(PathFile.USER),
    BANK_CHECK_AGREE_SEND(PathFile.USER),
    BANK_CHECK_DISAGREE_SEND(PathFile.USER),
    BANK_CHECK_AGREE_GET(PathFile.USER),
    BANK_CHECK_DISAGREE_GET(PathFile.USER),
    BANK_CHECK_TIMEOUT_SEND(PathFile.USER),
    BANK_CHECK_VIEW(PathFile.USER),
    ECONOMY_CLEAR_SELF(PathFile.USER),
    ECONOMY_CLEAR(PathFile.USER),
    ECONOMY_CLEAR_GET(PathFile.USER),
    ECONOMY_RESET_SELF(PathFile.USER),
    ECONOMY_RESET(PathFile.USER),
    EOCNOMY_RESET_GET(PathFile.USER),
    ECONOMY_SET_SELF(PathFile.USER),
    ECONOMY_SET_OTHER(PathFile.USER),
    ECONOMY_SET_GET(PathFile.USER),
    ECONOMY_ADD_SELF(PathFile.USER),
    ECONOMY_ADD_OTHER(PathFile.USER),
    ECONOMY_ADD_GET(PathFile.USER),
    ECONOMY_REMOVE_SELF(PathFile.USER),
    ECONOMY_REMOVE_OTHER(PathFile.USER),
    ECONOMY_REMOVE_GET(PathFile.USER),


    NO_CONSOLE(PathFile.CONSOLE),
    ERROR(PathFile.CONSOLE),
    PLUGIN_LOAD(PathFile.CONSOLE),
    PLUGIN_SUCCESS_LOAD(PathFile.CONSOLE),
    PLUGIN_ERROR_LOAD(PathFile.CONSOLE),
    PLUGIN_STOP(PathFile.CONSOLE),
    FILE_LOAD_CONFIG(PathFile.CONSOLE),
    COMMAND_SUCCESS(PathFile.CONSOLE),
    COMMAND_ERROR(PathFile.CONSOLE),
    LISTENER_SUCCESS(PathFile.CONSOLE),
    LISTENER_ERROR(PathFile.CONSOLE),

    MYSQL_CONNECT(PathFile.MYSQL),
    MYSQL_DISCONNECT(PathFile.MYSQL),
    MYSQL_CONNECT_ERROR(PathFile.MYSQL),
    MYSQL_ERROR(PathFile.MYSQL),

    ECONOMY_COMMAND(PathFile.ARGUMENTS),
    PAY_COMMAND(PathFile.ARGUMENTS),
    BANK_COMMAND(PathFile.ARGUMENTS)
    ;
    private PathFile pathFile;
    Message(final PathFile pathFile) {
       this.pathFile = pathFile;
    }
    public final String getMessage() {
        if(pathFile == PathFile.MYSQL) return ConfigFile.MESSAGE.getConfig().getMySQLString(PathFile.MESSAGE.getPath() + pathFile.getPath() +  this.name());
        if(pathFile == PathFile.GENERAL) return ConfigFile.MESSAGE.getConfig().getPreString(PathFile.MESSAGE.getPath() + pathFile.getPath() +  this.name());
        return ConfigFile.MESSAGE.getConfig().getString(PathFile.MESSAGE.getPath() + pathFile.getPath() +  this.name());
    }

}

