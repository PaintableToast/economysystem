package de.paintabletoast.system.mysql;

import de.paintabletoast.system.var.ConfigFile;
import de.paintabletoast.system.var.PathFile;

public enum MySQLData {
    HOST,
    PORT,
    DATABASE,
    USER,
    PASSWORD,
    TABLENAME,
    ;
    public final String getData() {
        return ConfigFile.MYSQL.getConfig().getString(PathFile.MYSQL.getPath()+ this.name());
    }
}