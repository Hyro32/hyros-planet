package one.hyro.bungee;

import one.hyro.bungee.listeners.PlayerDisconnectListener;
import one.hyro.bungee.listeners.PostLoginListener;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Plugin;

public final class HyroBungee extends Plugin {
    private static HyroBungee instance;

    @Override
    public void onEnable() {
        instance = this;

        getProxy().getPluginManager().registerListener(this, new PlayerDisconnectListener());
        getProxy().getPluginManager().registerListener(this, new PostLoginListener());

        ProxyServer.getInstance().getConsole().sendMessage(ChatColor.GOLD + "[Bungee] Plugin enabled");
    }

    @Override
    public void onDisable() {
        ProxyServer.getInstance().getConsole().sendMessage(ChatColor.RED + "[Bungee] Plugin disabled");
    }

    public static HyroBungee getInstance() {
        return instance;
    }
}