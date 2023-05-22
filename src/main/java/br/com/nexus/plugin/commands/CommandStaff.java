package br.com.nexus.plugin.commands;

import br.com.nexus.plugin.cache.CacheBungee;
import br.com.nexus.plugin.util.ConfigurationFile;
import br.com.nexus.plugin.util.configsUtil.ServerPrefix;
import br.com.nexus.plugin.util.TagUtil;
import br.com.nexus.plugin.util.TextComponentUtil;
import lombok.SneakyThrows;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class CommandStaff extends Command {

    public CommandStaff(ConfigurationFile configurationFile, TextComponentUtil textComponentUtil, ServerPrefix serverPrefix, TagUtil tagUtil) {
        super("staff");
        this.configurationFile = configurationFile;
        this.textComponentUtil = textComponentUtil;
        this.serverPrefix = serverPrefix;
        this.tagUtil = tagUtil;
    }

    private final ConfigurationFile configurationFile;
    private final TextComponentUtil textComponentUtil;
    private final ServerPrefix serverPrefix;
    private final TagUtil tagUtil;

    @Override @SneakyThrows
    public void execute(CommandSender commandSender, String[] strings) {
        if(commandSender instanceof ProxiedPlayer) {
            ProxiedPlayer player = (ProxiedPlayer) commandSender;
            if(CacheBungee.servidoresListIgnore.contains(player.getServer().getInfo().getName())) return;
            if (!player.hasPermission(configurationFile.getConfig().getString("Staff-listar"))) {
                player.sendMessage(textComponentUtil.createTextComponent("§cVocê não pode executar esse comando"));
                return;
            }
        }
        commandSender.sendMessage(textComponentUtil.createTextComponent(""));
        commandSender.sendMessage(textComponentUtil.createTextComponent("§eStaffers online na rede:"));
        for(ProxiedPlayer proxiedPlayer : CacheBungee.staffList) {
            commandSender.sendMessage(textComponentUtil.createTextComponent("  §f§l➥ " +
                    tagUtil.getCargoProxiedPlayer(proxiedPlayer).getTag() + " " + proxiedPlayer.getDisplayName() + " §8(" +
                    serverPrefix.getPrefixServer(proxiedPlayer.getServer().getInfo().getName())+")"));
        }
        commandSender.sendMessage(textComponentUtil.createTextComponent(""));

    }
}
