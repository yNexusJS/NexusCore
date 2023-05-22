package br.com.nexus.plugin.util;

import br.com.nexus.plugin.cache.CacheBungee;
import br.com.nexus.plugin.object.TagModel;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.config.Configuration;

@RequiredArgsConstructor
public class TagUtil {

    private final ConfigurationFile configurationFile;

    @SneakyThrows
    public void loadCacheTags() {
        Configuration configuration = configurationFile.getConfig();
        clearCargo();
        loadCargo(configuration);
    }

    public TagModel getCargoProxiedPlayer(ProxiedPlayer player) {
        for(TagModel tagModel : CacheBungee.tagModelArrayList) {
            if (player.hasPermission(tagModel.getPermission())) {
                return tagModel;
            }
        }
        return new TagModel("§7", "Z", "tag.default");
    }

    private void clearCargo() {
        CacheBungee.tagModelArrayList.clear();
    }

    private void loadCargo(Configuration configuration) {
        for(String keyName : configuration.getSection("Cargo").getKeys()) {
            String patchConfiguration = "Cargo."+keyName+".";

            String tag = getKey(patchConfiguration, "Tag", configuration);
            String hierarquia = getKey(patchConfiguration, "Hierarquia", configuration);
            String permission = getKey(patchConfiguration, "Permission", configuration);

            TagModel tagModel = new TagModel(tag, hierarquia, permission);
            CacheBungee.tagModelArrayList.add(tagModel);
        }
    }

    private String getKey(String patchConfiguration, String select, Configuration configuration) {
        return configuration.getString(patchConfiguration+select);
    }

}
