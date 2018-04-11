package com.georlegacy.general.lockpick.utils;

import com.georlegacy.general.lockpick.LockPick;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class ConfigUtil {
    private LockPick lp;
    public ConfigUtil(LockPick lp) {
        this.lp = lp;
    }

    public boolean createConfig() {
        File config = new File("plugins/LockPick/config.yml");
        if (config.exists()) return false;
        else {
            lp.saveResource("config.yml", true);
            return true;
        }
    }

    public double getChance() {
        File configFile = new File("plugins/LockPick/config.yml");
        YamlConfiguration config = YamlConfiguration.loadConfiguration(configFile);
        double chance = config.getDouble("Chance");
        if (0 > chance||1 < chance) {
            lp.getLogger().warning("The value for 'Chance' in config.yml is not between 0-1, the plugin will now shutdown.");
            lp.getServer().getPluginManager().disablePlugin(lp);
            return 0;
        } else {
            return config.getDouble("Chance");
        }
    }

}
