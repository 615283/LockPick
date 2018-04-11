package com.georlegacy.general.lockpick.tabcompleters;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

public class LockPickTabCompleter implements TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
        if (args.length==3 && args[0].equalsIgnoreCase("give")) {
            List<String> items = new ArrayList<String>();
            items.add("MasterKey");
            items.add("LockPick");
            return items;
        } else if (args.length==0) {
            List<String> subs = new ArrayList<String>();
            subs.add("give");
            subs.add("version");
            return subs;
        }
        else {
            return null;
        }
    }

}
