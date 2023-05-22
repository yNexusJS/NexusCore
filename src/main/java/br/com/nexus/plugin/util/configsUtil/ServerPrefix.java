package br.com.nexus.plugin.util.configsUtil;

import br.com.nexus.plugin.cache.CacheBungee;
import br.com.nexus.plugin.util.ConfigurationFile;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

@RequiredArgsConstructor
public class ServerPrefix {

    private final ConfigurationFile configurationFile;

    @SneakyThrows
    public void loadPrefix() {
        for(String keyName : configurationFile.getConfig().getSection("Server").getKeys()) {
            String patchConfiguration = "Server."+keyName+".";
            String server_name = configurationFile.getConfig().getString(patchConfiguration+"server_name");
            String server_prefix = configurationFile.getConfig().getString(patchConfiguration+"server_prefix");
            CacheBungee.servidorPrefix.put(server_name, server_prefix);
        }
    }

    @SneakyThrows
    public String getPrefixServer(String server) {
        if(CacheBungee.servidorPrefix.containsKey(server)) return CacheBungee.servidorPrefix.get(server);
        return server;
    }


}
