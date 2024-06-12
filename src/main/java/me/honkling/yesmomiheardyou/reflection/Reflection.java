package me.honkling.yesmomiheardyou.reflection;

import xyz.jpenilla.reflectionremapper.ReflectionRemapper;
import xyz.jpenilla.reflectionremapper.proxy.ReflectionProxyFactory;

public class Reflection {
    public static final PlayerNetworkProxy PLAYER_NETWORK;
    public static final MessageTrackerProxy MESSAGE_TRACKER;

    static {
        final ReflectionRemapper mapper = ReflectionRemapper.forReobfMappingsInPaperJar();
        final ReflectionProxyFactory factory = ReflectionProxyFactory.create(mapper, Reflection.class.getClassLoader());

        PLAYER_NETWORK = factory.reflectionProxy(PlayerNetworkProxy.class);
        MESSAGE_TRACKER = factory.reflectionProxy(MessageTrackerProxy.class);
    }
}
