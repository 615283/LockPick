package com.georlegacy.general.lockpick.utils;

import org.bukkit.configuration.file.YamlConfiguration;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.IOException;

public class ContainerLock {

    private YamlConfiguration chests() {
        return YamlConfiguration.loadConfiguration(new File("plugins/LockPick/chests.yml"));
    }

    private boolean save(YamlConfiguration chests) {
        try {
            chests.save(new File("plugins/LockPick/chests.yml"));
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void lock(int x, int y, int z) {
        YamlConfiguration c = chests();
        c.set("chests."+ x+"-"+y+"-"+z, true);
        save(c);
    }

    public void unlock(int x, int y, int z) {
        YamlConfiguration c = chests();
        c.set("chests."+ x+"-"+y+"-"+z, false);
        save(c);

    }

}
