package de.paintabletoast.system.manager;

import de.paintabletoast.system.utils.ConfigBuilder;

import java.util.HashMap;

public class FileManager {
    private HashMap<String, ConfigBuilder> configs = new HashMap();
    public ConfigBuilder getConfig(String name) {
        if (!configs.containsKey(name)) {
            configs.put(name, new ConfigBuilder(name));
        }
        return configs.get(name);
    }
}
