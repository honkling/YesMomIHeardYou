package me.honkling.yesmomiheardyou;

import it.unimi.dsi.fastutil.objects.ObjectList;
import me.honkling.yesmomiheardyou.reflection.Reflection;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.minecraft.network.chat.LastSeenMessagesValidator;
import net.minecraft.network.chat.LastSeenTrackedEntry;
import net.minecraft.server.level.ServerPlayer;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_20_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

public class YesMomIHeardYou extends JavaPlugin {
    public static YesMomIHeardYou instance;
    public MiniMessage miniMessage = MiniMessage.miniMessage();
    private int schedulerTask;

    @Override
    public void onEnable() {
        instance = this;

        saveDefaultConfig();
        Reflection.class.getClassLoader(); // initialize Reflection

        ReloadCommand.register();
        createScheduler();
    }

    public void createScheduler() {
        long period = 20 * getConfig().getLong("clear-every-x-seconds", 5);
        BukkitScheduler scheduler = Bukkit.getScheduler();
        schedulerTask = scheduler.scheduleSyncRepeatingTask(this, this::cleanPendingMessages, 0L, period);
    }

    public void clearScheduler() {
        BukkitScheduler scheduler = Bukkit.getScheduler();
        scheduler.cancelTask(schedulerTask);
    }

    private void cleanPendingMessages() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            ServerPlayer handle = ((CraftPlayer) player).getHandle();
            LastSeenMessagesValidator validator = Reflection.PLAYER_NETWORK.getLastSeenMessages(handle.connection);
            ObjectList<LastSeenTrackedEntry> messages = Reflection.MESSAGE_TRACKER.getTrackedMessages(validator);
            int lastSeenCount = Reflection.MESSAGE_TRACKER.getLastSeenCount(validator);

            if (getConfig().getBoolean("debug", false)) {
                var amount = messages.size() - lastSeenCount;
                getLogger().info(String.format("Clearing %s messages for %s", amount, player.getName()));
            }

            messages.clear();

            for (int i = 0; i < lastSeenCount; i++)
                messages.add(null);
        }
    }
}
