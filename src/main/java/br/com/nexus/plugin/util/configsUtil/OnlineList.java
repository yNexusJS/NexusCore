package br.com.nexus.plugin.util.configsUtil;

import br.com.nexus.plugin.cache.CacheBungee;
import br.com.nexus.plugin.util.ConfigurationFile;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

@RequiredArgsConstructor
public class OnlineList {

    private final ConfigurationFile configurationFile;

    @SneakyThrows
    public void loadListOnlineServer() {
        for(String keyName : configurationFile.getConfig().getSection("Online").getKeys()) {
            String serversList = configurationFile.getConfig().getString("Online."+keyName);
            CacheBungee.servidoresListOnline.add(serversList);
        }
    }

}
