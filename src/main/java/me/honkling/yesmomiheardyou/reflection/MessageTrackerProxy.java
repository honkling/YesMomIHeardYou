package me.honkling.yesmomiheardyou.reflection;

import it.unimi.dsi.fastutil.objects.ObjectList;
import net.minecraft.network.chat.LastSeenMessagesValidator;
import net.minecraft.network.chat.LastSeenTrackedEntry;
import xyz.jpenilla.reflectionremapper.proxy.annotation.FieldGetter;
import xyz.jpenilla.reflectionremapper.proxy.annotation.Proxies;

@Proxies(LastSeenMessagesValidator.class)
public interface MessageTrackerProxy {
    @FieldGetter("trackedMessages")
    ObjectList<LastSeenTrackedEntry> getTrackedMessages(LastSeenMessagesValidator instance);

    @FieldGetter("lastSeenCount")
    int getLastSeenCount(LastSeenMessagesValidator instance);
}
