package br.com.nexus.plugin;

import br.com.nexus.plugin.commands.*;
import br.com.nexus.plugin.listeners.DisconnectListener;
import br.com.nexus.plugin.listeners.PostLoginListener;
import br.com.nexus.plugin.listeners.ReconnectPlayers;
import br.com.nexus.plugin.util.*;
import br.com.nexus.plugin.util.configsUtil.IgnoreServer;
import br.com.nexus.plugin.util.configsUtil.LobbyList;
import br.com.nexus.plugin.util.configsUtil.OnlineList;
import br.com.nexus.plugin.util.configsUtil.ServerPrefix;
import lombok.SneakyThrows;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.plugin.Plugin;

public class Main extends Plugin {

    private final ConfigurationFile configurationFile = new ConfigurationFile(this);
    private final TextComponentUtil textComponentUtil = new TextComponentUtil();
    private final ServerPrefix serverPrefix = new ServerPrefix(configurationFile);
    private final LobbyList lobbyList = new LobbyList(configurationFile);
    private final TagUtil tagUtil = new TagUtil(configurationFile);
    private final IgnoreServer ignoreServer = new IgnoreServer(configurationFile);
    private final OnlineList onlineList = new OnlineList(configurationFile);
    public String prefixPlugin = "§4[NexusCore-Bungeecord] ";

    @SneakyThrows
    @Override
    public void onEnable() {
        BungeeCord.getInstance().getConsole().sendMessage(textComponentUtil.createTextComponent(prefixPlugin+"§aPlugin iniciado com sucesso."));
        configurationFile.loadConfig();
        BungeeCord.getInstance().getConsole().sendMessage(textComponentUtil.createTextComponent(prefixPlugin+"§aConfiguration iniciada com sucesso."));
        tagUtil.loadCacheTags();
        BungeeCord.getInstance().getConsole().sendMessage(textComponentUtil.createTextComponent(prefixPlugin+"§aSistema de tags iniciado com sucesso."));
        registerListener();
        BungeeCord.getInstance().getConsole().sendMessage(textComponentUtil.createTextComponent(prefixPlugin+"§aSistema de eventos iniciado com sucesso."));
        registerCommand();
        BungeeCord.getInstance().getConsole().sendMessage(textComponentUtil.createTextComponent(prefixPlugin+"§aSistema de comandos iniciado com sucesso."));
        serverPrefix.loadPrefix();
        BungeeCord.getInstance().getConsole().sendMessage(textComponentUtil.createTextComponent(prefixPlugin+"§aSistema de prefixo de servidores iniciado com sucesso."));
        lobbyList.loadingLobby();
        BungeeCord.getInstance().getConsole().sendMessage(textComponentUtil.createTextComponent(prefixPlugin+"§aSistema de lobbys iniciado com sucesso."));
        onlineList.loadListOnlineServer();
        BungeeCord.getInstance().getConsole().sendMessage(textComponentUtil.createTextComponent(prefixPlugin+"§aSistema de servidores que aparecem no online do servidor iniciado com sucesso."));
        ignoreServer.loadingIgnoreServer();
        BungeeCord.getInstance().getConsole().sendMessage(textComponentUtil.createTextComponent(prefixPlugin+"§aSistema de ignorar determinados servidores iniciado com sucesso."));
    }

    public void registerListener() {
        getProxy().getPluginManager().registerListener(this, new PostLoginListener(tagUtil, configurationFile));
        getProxy().getPluginManager().registerListener(this, new DisconnectListener());
        getProxy().getPluginManager().registerListener(this, new ReconnectPlayers(this, textComponentUtil, serverPrefix));
    }

    public void registerCommand() {
        getProxy().getPluginManager().registerCommand(this, new CommandAlerta(textComponentUtil, configurationFile));
        getProxy().getPluginManager().registerCommand(this, new CommandTPB(textComponentUtil, configurationFile));
        getProxy().getPluginManager().registerCommand(this, new CommandKillAll(textComponentUtil, configurationFile, this, serverPrefix));
        getProxy().getPluginManager().registerCommand(this, new CommandLobby(textComponentUtil, serverPrefix, this));
        getProxy().getPluginManager().registerCommand(this, new CommandPatrao(textComponentUtil, tagUtil, configurationFile));
        getProxy().getPluginManager().registerCommand(this, new CommandSuperiores(configurationFile, textComponentUtil, tagUtil, serverPrefix));
        getProxy().getPluginManager().registerCommand(this, new CommandChatStaff(configurationFile, textComponentUtil, tagUtil, serverPrefix));
        getProxy().getPluginManager().registerCommand(this, new CommandStaff(configurationFile, textComponentUtil, serverPrefix, tagUtil));
        getProxy().getPluginManager().registerCommand(this, new CommandOnline(textComponentUtil, serverPrefix, this));
    }

    @Override
    public void onDisable() {
        BungeeCord.getInstance().getConsole().sendMessage(textComponentUtil.createTextComponent(prefixPlugin+"§cPlugin desligado com sucesso."));
    }


}
