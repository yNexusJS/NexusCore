package br.com.nexus.plugin.util.configsUtil;

import br.com.nexus.plugin.cache.CacheBungee;
import br.com.nexus.plugin.util.ConfigurationFile;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

@RequiredArgsConstructor
public class LobbyList {

    private final ConfigurationFile configurationFile;

    @SneakyThrows
    public void loadingLobby() {
        for(String keyName : configurationFile.getConfig().getSection("Lobbys").getKeys()) {
            String lobby_name = configurationFile.getConfig().getString("Lobbys."+keyName);
            CacheBungee.lobbyList.add(lobby_name);
        }
    }

}
