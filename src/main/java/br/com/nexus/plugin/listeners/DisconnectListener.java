package br.com.nexus.plugin.listeners;

import br.com.nexus.plugin.cache.CacheBungee;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.PlayerDisconnectEvent;
import net.md_5.bungee.api.event.ServerDisconnectEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class DisconnectListener implements Listener {

    @EventHandler
    public void disconnectPlayer(PlayerDisconnectEvent e) {
        CacheBungee.onlinePlayersTag.remove(e.getPlayer());
        CacheBungee.superioresList.remove(e.getPlayer());
        CacheBungee.staffList.remove(e.getPlayer());
    }

}
