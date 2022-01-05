package de.paintabletoast.system.var;

import de.paintabletoast.system.boot.Core;
import de.paintabletoast.system.manager.FileManager;
import de.paintabletoast.system.manager.PluginHandler;
import de.paintabletoast.system.utils.ConfigBuilder;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public enum ConfigFile implements PluginHandler {
    MESSAGE,
    CONFIG,
    PERMISSION,
    MYSQL,
    SOUNDS,
    SETTINGS;
    private String path;
    private final Core core = JavaPlugin.getPlugin(Core.class);
    private final FileManager filemanager = new FileManager();
    private ConfigBuilder config;
    ConfigFile() {
        this.path = this.name().toLowerCase() + ".yml";
        config = filemanager.getConfig(getPath());
    }
    private final String getPath() {
        return path;
    }
    public final ConfigBuilder getConfig() {
        return config;
    }
    public final void saveDefaultConfig() {
        final File file = new File("plugins/" + core.getName() +"/" + path);
        if(!file.exists()) config.saveDefaultConfig();
        log(Message.FILE_LOAD_CONFIG.getMessage().replace(FileReplace.FILE.getPart(), this.getPath()));
    }
}
