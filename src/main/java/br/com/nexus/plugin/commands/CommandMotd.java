package br.com.nexus.plugin.commands;

import br.com.nexus.plugin.cache.CacheBungee;
import br.com.nexus.plugin.util.ConfigurationFile;
import br.com.nexus.plugin.util.TextComponentUtil;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class CommandMotd extends Command {

    public CommandMotd(TextComponentUtil textComponentUtil, ConfigurationFile configurationFile) {
        super("Motd");
        this.textComponentUtil = textComponentUtil;
        this.configurationFile = configurationFile;
    }

    private final TextComponentUtil textComponentUtil;
    private final ConfigurationFile configurationFile;

    @Override
    public void execute(CommandSender commandSender, String[] strings) {
        if (!(commandSender instanceof ProxiedPlayer)) {
            commandSender.sendMessage(textComponentUtil.createTextComponent("§cApenas jogadores podem executar esse comando."));
            return;
        }
        ProxiedPlayer proxiedPlayer = (ProxiedPlayer) commandSender;
        if(CacheBungee.servidoresListIgnore.contains(proxiedPlayer.getServer().getInfo().getName())) return;

    }
}
