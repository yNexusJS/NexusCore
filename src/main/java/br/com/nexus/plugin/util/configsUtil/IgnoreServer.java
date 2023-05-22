package br.com.nexus.plugin.util.configsUtil;

import br.com.nexus.plugin.cache.CacheBungee;
import br.com.nexus.plugin.util.ConfigurationFile;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

@RequiredArgsConstructor
public class IgnoreServer {

    private final ConfigurationFile configurationFile;

    @SneakyThrows
    public void loadingIgnoreServer() {
        for(String keyName : configurationFile.getConfig().getSection("Ignore-server").getKeys()) {
            String ignoreServerName = configurationFile.getConfig().getString("Ignore-server."+keyName);
            CacheBungee.servidoresListIgnore.add(ignoreServerName);
        }
    }

}
