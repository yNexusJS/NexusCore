package br.com.nexus.plugin.api;

import br.com.nexus.plugin.cache.CacheBungee;
import br.com.nexus.plugin.object.TagModel;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class NexusCoreAPI {

    public TagModel getTagUtil(ProxiedPlayer proxiedPlayer) {
        for(TagModel tagModel : CacheBungee.tagModelArrayList) {
            if (proxiedPlayer.hasPermission(tagModel.getPermission())) {
                return tagModel;
            }
        }
        return new TagModel("§7", "Z", "tag.default");
    }

    public String getPrefixServer(String server) {
        if(CacheBungee.servidorPrefix.containsKey(server)) return CacheBungee.servidorPrefix.get(server);
        return server;
    }

    public Boolean isIgnoreServer(String server) {
        return CacheBungee.servidoresListIgnore.contains(server);
    }

}
