package br.com.nexus.plugin.commands;

import br.com.nexus.plugin.Main;
import br.com.nexus.plugin.cache.CacheBungee;
import br.com.nexus.plugin.util.configsUtil.ServerPrefix;
import br.com.nexus.plugin.util.TextComponentUtil;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class CommandOnline extends Command {

    public CommandOnline(TextComponentUtil textComponentUtil, ServerPrefix serverPrefix, Main main) {
        super("online");
        this.textComponentUtil = textComponentUtil;
        this.serverPrefix = serverPrefix;
        this.main = main;
    }

    private final TextComponentUtil textComponentUtil;
    private final ServerPrefix serverPrefix;
    private final Main main;

    @Override
    public void execute(CommandSender commandSender, String[] strings) {

        if(!(commandSender instanceof ProxiedPlayer)) {
            commandSender.sendMessage(textComponentUtil.createTextComponent("§cApenas jogadores podem executar esse comando."));
            return;
        }

        ProxiedPlayer proxiedPlayer = (ProxiedPlayer) commandSender;
        if(CacheBungee.servidoresListIgnore.contains(proxiedPlayer.getServer().getInfo().getName())) return;

        proxiedPlayer.sendMessage(textComponentUtil.createTextComponent(""));
        proxiedPlayer.sendMessage(textComponentUtil.createTextComponent(" §a§l● §f" + main.getProxy().getPlayers().size() + " §eJogadores online em toda a rede."));
        proxiedPlayer.sendMessage(textComponentUtil.createTextComponent(" §a§l● §f" + proxiedPlayer.getServer().getInfo().getPlayers().size() + " §eJogadores online neste servidor."));
        proxiedPlayer.sendMessage(textComponentUtil.createTextComponent(""));
        for(String server : CacheBungee.servidoresListOnline) {
            ServerInfo serverInfo = main.getProxy().getServerInfo(server);
            proxiedPlayer.sendMessage(textComponentUtil.createTextComponent(" §a§l● §f" + serverInfo.getPlayers().size() + "§e online no " + serverPrefix.getPrefixServer(serverInfo.getName())));
        }
        proxiedPlayer.sendMessage(textComponentUtil.createTextComponent(""));
    }
}
