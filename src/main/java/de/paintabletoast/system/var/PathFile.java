package de.paintabletoast.system.var;

public enum PathFile {
    MESSAGE,
    CONFIG,
    PERMISSION,
    MYSQL,
    SOUNDS,
    SETTINGS,

    ARGUMENTS,
    GENERAL,
    CONSOLE,
    USER;
    public final String getPath() {
        return this.toString() + ".";
    }
    public final String getBeforePath() {
        return "." + this.toString();
    }
    public final String getBothPath() {
        return getBeforePath() + ".";
    }
}
