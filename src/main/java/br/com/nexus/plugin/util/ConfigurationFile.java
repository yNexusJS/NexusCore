package br.com.nexus.plugin.util;

import br.com.nexus.plugin.Main;
import lombok.RequiredArgsConstructor;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.File;
import java.io.IOException;

@RequiredArgsConstructor
public class ConfigurationFile {

    private final Main main;

    public void loadConfig() throws IOException {
        File file = getFile();
        if(!hasFile(file)) createFile(file);
        Configuration configuration = getConfigurationByFile(file);
        setDefaultConfiguration(configuration);
        saveConfig(file, configuration);
    }

    public Configuration getConfig() throws IOException {
        return ConfigurationProvider.getProvider(YamlConfiguration.class).load(getFile());
    }

    public void saveConfig(Configuration configuration) throws IOException {
        ConfigurationProvider.getProvider(YamlConfiguration.class).save(configuration, getFile());
    }

    private void setDefaultConfiguration(Configuration configuration) {
        if(!configuration.contains("Cargo")) setDefaultGroup(configuration);
        if(!configuration.contains("Anuncio-permission")) setDefaultAnuncio(configuration);
        if(!configuration.contains("Superiores-permission")) setDefaultSuperiores(configuration);
        if(!configuration.contains("Staff-permission")) setDefaultStaff(configuration);
        if(!configuration.contains("Staff-patrao")) setDefaultPatrao(configuration);
        if(!configuration.contains("Staff-listar")) setDefaultListar(configuration);
        if(!configuration.contains("Staff-tpb")) setDefaultTPB(configuration);
        if(!configuration.contains("Staff-killall")) setDefaultKillAll(configuration);
        if(!configuration.contains("Staff-motd")) setDefaultMotd(configuration);
        if(!configuration.contains("Lobbys")) setDefaultLobby(configuration);
        if(!configuration.contains("Server")) setDefaultServer(configuration);
        if(!configuration.contains("Online")) setDefaultOnline(configuration);
        if(!configuration.contains("Ignore-server")) setIgnoreServer(configuration);
    }

    private void setDefaultMotd(Configuration configuration) {
        configuration.set("Staff-motd", "NexusCore.motd");
    }

    private void setDefaultAnuncio(Configuration configuration) {
        configuration.set("Anuncio-permission", "NexusCore.anuncio");
    }

    private void setDefaultKillAll(Configuration configuration) {
        configuration.set("Staff-KillAll", "NexusCore.KillAll");
    }

    private void setIgnoreServer(Configuration configuration) {
        configuration.set("Ignore-server.Login1", "login");
    }

    private void setDefaultOnline(Configuration configuration) {
        configuration.set("Online.rankup", "rankup");
        configuration.set("Online.lobby1", "lobby1");
        configuration.set("Online.lobby2", "lobby2");
    }

    private void setDefaultListar(Configuration configuration) {
        configuration.set("Staff-listar", "NexusCore.listar");
    }

    private void setDefaultTPB(Configuration configuration) {
        configuration.set("Staff-TPB", "NexusCore.TPB");
    }

    private void setDefaultLobby(Configuration configuration) {
        configuration.set("Lobbys.lobby1", "lobby-1");
        configuration.set("Lobbys.lobby2", "lobby-2");
    }

    private void setDefaultStaff(Configuration configuration) {
        configuration.set("Staff-permission", "NexusCore.staff");
    }

    private void setDefaultPatrao(Configuration configuration) {
        configuration.set("Staff-patrao", "NexusCore.patrao");
    }

    private void setDefaultSuperiores(Configuration configuration) {
        configuration.set("Superiores-permission", "NexusCore.superiores");
    }

    private void setDefaultGroup(Configuration configuration) {
        setConfigurationCargo("Dono", "&5[Dono]", "A", "tag.dono", configuration);
        setConfigurationCargo("Diretor", "&4[Diretor]", "B", "tag.diretor", configuration);
        setConfigurationCargo("Admin", "&c[Admin]", "C", "tag.admin", configuration);
        setConfigurationCargo("Moderador", "&3[Moderador]", "D", "tag.moderador", configuration);
    }

    private void setDefaultServer(Configuration configuration) {
        setConfigurationServer("lobby1", "lobby1", "Saguão #1", configuration);
        setConfigurationServer("lobby2", "lobby2", "Saguão #2", configuration);
        setConfigurationServer("rankup", "rankup", "RankUP", configuration);
        setConfigurationServer("survival", "survival", "Survival", configuration);
    }

    private void setConfigurationServer(String keyName, String serverName, String serverPrefix, Configuration configuration) {
        String patchConfiguration = "Server."+keyName+".";
        configuration.set(patchConfiguration+"server_name", serverName);
        configuration.set(patchConfiguration+"server_prefix", serverPrefix);
    }

    private void setConfigurationCargo(String keyName, String tagName, String hierarquia, String permission, Configuration configuration) {
        String patchConfiguration = "Cargo."+keyName+".";
        configuration.set(patchConfiguration+"Tag", tagName);
        configuration.set(patchConfiguration+"Hierarquia", hierarquia);
        configuration.set(patchConfiguration+"Permission", permission);
    }

    private void saveConfig(File file, Configuration configuration) throws IOException {
        ConfigurationProvider.getProvider(YamlConfiguration.class).save(configuration, file);
    }

    private File getFile() {
        if(!hasDataFolder()) createDataFolder();
        return new File(main.getDataFolder(), "configuration.yml");
    }

    private boolean hasDataFolder() {
        return main.getDataFolder().exists();
    }

    private void createDataFolder() {
        main.getDataFolder().mkdir();
    }

    private Configuration getConfigurationByFile(File file) throws IOException {
        return ConfigurationProvider.getProvider(YamlConfiguration.class).load(file);
    }

    private boolean hasFile(File file) {
        return file.exists();
    }

    private void createFile(File file) throws IOException {
        file.createNewFile();
    }

}
