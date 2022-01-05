package de.paintabletoast.system.var;

public enum Permission {
    ECONOMY_COMMAND_ADMIN,
    ECONOMY_COMMAND_VIEW_BALANCE;
    public final String getPermission() {
        return ConfigFile.PERMISSION.getConfig().getString(PathFile.PERMISSION.getPath() + this.name());
    }
}
