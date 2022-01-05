package de.paintabletoast.system.var;

public enum Config {
    BANK_CHECK_DELAY,
    DEFAULT,
    ;
    public final Double getValueDouble() {
        return (Double) ConfigFile.CONFIG.getConfig().get(PathFile.CONFIG.getPath() + this.name());
    }
    public final Double getValueInteger() {
        return (Double) ConfigFile.CONFIG.getConfig().get(PathFile.CONFIG.getPath() + this.name());
    }
}
