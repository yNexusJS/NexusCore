package br.com.nexus.plugin.listeners;

import br.com.nexus.plugin.cache.CacheBungee;
import br.com.nexus.plugin.util.ConfigurationFile;
import br.com.nexus.plugin.util.TagUtil;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.PostLoginEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

@RequiredArgsConstructor
public class PostLoginListener implements Listener {

    private final TagUtil tagUtil;
    private final ConfigurationFile configurationFile;

    @EventHandler @SneakyThrows
    public void onLogin(PostLoginEvent e) {
        ProxiedPlayer proxiedPlayer = e.getPlayer();
        CacheBungee.onlinePlayersTag.put(proxiedPlayer, tagUtil.getCargoProxiedPlayer(proxiedPlayer));
        if(proxiedPlayer.hasPermission(configurationFile.getConfig().getString("Superiores-permission"))) CacheBungee.superioresList.add(proxiedPlayer);
        if(proxiedPlayer.hasPermission(configurationFile.getConfig().getString("Staff-permission"))) CacheBungee.staffList.add(proxiedPlayer);
    }



}
