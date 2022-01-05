package de.paintabletoast.system.var;

public enum Settings {
    MYSQL;
    public final boolean getSetting() {
        return (boolean) ConfigFile.SETTINGS.getConfig().get(PathFile.SETTINGS.getPath() + this.name());
    }

}
