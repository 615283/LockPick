//Base archetype by James Conway (615283)

package com.georlegacy.general.lockpick;

import com.georlegacy.general.lockpick.commands.LockPickCommand;
import com.georlegacy.general.lockpick.listeners.ContainerOpen;
import com.georlegacy.general.lockpick.tabcompleters.LockPickTabCompleter;
import com.georlegacy.general.lockpick.utils.ConfigUtil;
import com.georlegacy.general.lockpick.utils.ContainerLock;
import com.georlegacy.general.lockpick.utils.ContainerState;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public class LockPick extends JavaPlugin {

    private ContainerLock containerLock = new ContainerLock();
    public ContainerLock getContainerLock() {
        return containerLock;
    }

    private ContainerState containerState = new ContainerState();
    public ContainerState getContainerState() {
        return containerState;
    }

    private ConfigUtil configUtil = new ConfigUtil(this);
    public ConfigUtil getConfigUtil() {
        return configUtil;
    }

    @Override
    public void onEnable() {
        getDataFolder().mkdirs();
        File chests = new File("plugins/LockPick/chests.yml");
        if (!chests.exists()) {
            try {
                chests.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (getConfigUtil().createConfig()) {
            getLogger().info("config.yml successfully created.");
        }

        getCommand("lockpick").setExecutor(new LockPickCommand(this));
        getCommand("lockpick").setTabCompleter(new LockPickTabCompleter());

        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new ContainerOpen(this), this);
    }

    @Override
    public void onDisable() {

    }

}
