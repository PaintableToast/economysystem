package de.paintabletoast.system.mysql;

import de.paintabletoast.system.manager.PluginHandler;
import de.paintabletoast.system.var.Message;
import de.paintabletoast.system.var.Settings;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQL implements PluginHandler {
    private Connection connection;
    private static MySQL instance;
    public MySQL() {
        instance = this;
    }
    public final static MySQL getInstance() {
        return instance;
    }
    public final void connect() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://" + MySQLData.HOST.getData() + ":" + MySQLData.PORT.getData() + "/" + MySQLData.DATABASE.getData() + "?useSSL=false", MySQLData.USER.getData(), MySQLData.PASSWORD.getData());
            log(Message.MYSQL_CONNECT.getMessage());
        } catch (final SQLException error) {
            logError(Message.MYSQL_CONNECT_ERROR.getMessage());
        }
    }
    public final boolean isConnected() {
        return(connection == null ? false : true);
    }
    public final void disconnect() {
        try {
            connection.close();
            log(Message.MYSQL_DISCONNECT.getMessage());
        } catch (final SQLException error) {
            logError(Message.MYSQL_ERROR.getMessage());
        }
    }
    public final Connection getConnection() {
        return connection;
    }
    public final boolean isConnectable() {
        return Settings.MYSQL.getSetting();
    }
}
