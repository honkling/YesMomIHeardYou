package me.honkling.yesmomiheardyou;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class ReloadCommand implements CommandExecutor {
    public static void register() {
        YesMomIHeardYou.instance.getCommand("yesmomiheardyou").setExecutor(new ReloadCommand());
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        YesMomIHeardYou instance = YesMomIHeardYou.instance;
        instance.reloadConfig();
        instance.clearScheduler();
        instance.createScheduler();
        sender.sendMessage(instance.miniMessage.deserialize("<green>Reloaded!"));
        return true;
    }
}
