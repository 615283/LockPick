package com.georlegacy.general.lockpick.utils;

import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class ContainerState {

    public boolean locked(int x, int y, int z) {
        YamlConfiguration yc = YamlConfiguration.loadConfiguration(new File("plugins/LockPick/chests.yml"));
        return yc.getBoolean("chests."+x+"-"+y+"-"+z);
    }

}
