package br.com.nexus.plugin.listeners;

import br.com.nexus.plugin.Main;
import br.com.nexus.plugin.cache.CacheBungee;
import br.com.nexus.plugin.util.TextComponentUtil;
import br.com.nexus.plugin.util.configsUtil.ServerPrefix;
import lombok.RequiredArgsConstructor;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.PlayerDisconnectEvent;
import net.md_5.bungee.api.event.ServerDisconnectEvent;
import net.md_5.bungee.api.event.ServerKickEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;
import sun.plugin2.main.client.DisconnectedExecutionContext;

import java.util.Random;

@RequiredArgsConstructor
public class ReconnectPlayers implements Listener {

    private final Main main;
    private final TextComponentUtil textComponentUtil;
    private final ServerPrefix serverPrefix;

    @EventHandler
    public void shotdownServer(ServerKickEvent e) {
        if(e.getPlayer().getServer() == null) return;
        if(!e.getPlayer().getServer().getInfo().equals(e.getKickedFrom())) return;
        ProxiedPlayer proxiedPlayer = e.getPlayer();
        ServerInfo lobby = main.getProxy().getServerInfo(randomLobby());
        proxiedPlayer.sendMessage(textComponentUtil.createTextComponent("§aEnviando para o lobby §8(" + serverPrefix.getPrefixServer(lobby.getName()) + ")§a..."));
        e.setCancelServer(lobby);
        e.setCancelled(true);
    }

    public String randomLobby() {
        Random r = new Random();
        int randomNumber = r.nextInt(CacheBungee.lobbyList.size());
        return CacheBungee.lobbyList.get(randomNumber);
    }

}
