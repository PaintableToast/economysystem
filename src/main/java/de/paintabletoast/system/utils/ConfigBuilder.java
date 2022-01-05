package de.paintabletoast.system.utils;

import de.paintabletoast.system.boot.Core;
import de.paintabletoast.system.mysql.MySQLData;
import de.paintabletoast.system.var.Config;
import de.paintabletoast.system.var.FileReplace;
import de.paintabletoast.system.var.Message;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import de.paintabletoast.system.manager.PluginHandler;

import java.io.*;

public class ConfigBuilder implements PluginHandler {
    private final Core core = JavaPlugin.getPlugin(Core.class);
    private String name;
    private File file;
    private YamlConfiguration yamlconfiguration;
    public ConfigBuilder(final String name) {
        this.name = name;
    }
    private final ConfigBuilder save() {
        if (yamlconfiguration != null && this.file != null)
            try {
                if (this.yamlconfiguration.getConfigurationSection("").getKeys(true).size() != 0) this.yamlconfiguration.save(file);
            } catch (final IOException error) {
                logError(error);
            }
        return null;
    }
    private final YamlConfiguration get() {
        if (this.yamlconfiguration == null) this.reload();
        return this.yamlconfiguration;
    }
    public final ConfigBuilder saveDefaultConfig() {
        file = new File(core.getDataFolder(), name);
        core.saveResource(name, false);
        return this;
    }
    public final ConfigBuilder reload() {
        if (file == null) file = new File(core.getDataFolder(), name);
        yamlconfiguration = YamlConfiguration.loadConfiguration(file);
        try {
            final Reader def_config_stream = new InputStreamReader(core.getResource(name), "UTF8");
            if (def_config_stream != null) {
                final YamlConfiguration def_config = YamlConfiguration.loadConfiguration(def_config_stream);
                yamlconfiguration.setDefaults(def_config);
            }
        } catch (final NullPointerException | UnsupportedEncodingException error) {
            logError(error);
        }
        return this;
    }
    public final ConfigBuilder set(final String key, final Object value) {
        get().set(key, value);
        save();
        return this;
    }
    public final Object get(final String key) {
        return this.get().get(key);
    }

    public final String getString(final String path) {
        return this.getPreString(path)
                .replace(FileReplace.PREFIX.getPart(), Message.PREFIX.getMessage())
                .replace(FileReplace.CONSOLE_PREFIX.getPart(), Message.CONSOLE_PREFIX.getMessage())
                .replace(FileReplace.INFORMATION.getPart(), Message.INFORMATION_TAG.getMessage())
                .replace(FileReplace.PLUGIN_NAME.getPart(), core.getName())
                .replace(FileReplace.WALLET.getPart(), Message.WALLET.getMessage())
                .replace(FileReplace.DEFAULT.getPart(), String.valueOf(Config.DEFAULT.getValueDouble()));

    }

    public final String getPreString(final String path) {
        return ((String) get(path))
                .replace("&", "§")
                .replace(FileReplace.ENTER.getPart(), "\n");
    }
    public final String getMySQLString(String path) {
        return this.getString(path)
                .replace(FileReplace.HOST.getPart(), MySQLData.HOST.getData())
                .replace(FileReplace.PORT.getPart(), MySQLData.PORT.getData())
                .replace(FileReplace.DATABASE.getPart(), MySQLData.DATABASE.getData())
                .replace(FileReplace.USER.getPart(), MySQLData.USER.getData())
                .replace(FileReplace.PASSWORD.getPart(), MySQLData.PASSWORD.getData());
    }
}
