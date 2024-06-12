package me.honkling.yesmomiheardyou.reflection;

import net.minecraft.network.chat.LastSeenMessagesValidator;
import net.minecraft.server.network.ServerGamePacketListenerImpl;
import xyz.jpenilla.reflectionremapper.proxy.annotation.FieldGetter;
import xyz.jpenilla.reflectionremapper.proxy.annotation.Proxies;

@Proxies(ServerGamePacketListenerImpl.class)
public interface PlayerNetworkProxy {
    @FieldGetter("lastSeenMessages")
    LastSeenMessagesValidator getLastSeenMessages(ServerGamePacketListenerImpl instance);
}
