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

public class CommandSuperiores extends Command {

    public CommandSuperiores(ConfigurationFile configurationFile, TextComponentUtil textComponentUtil, TagUtil tagUtil, ServerPrefix serverPrefix) {
        super("ss");
        this.configurationFile = configurationFile;
        this.textComponentUtil = textComponentUtil;
        this.tagUtil = tagUtil;
        this.serverPrefix = serverPrefix;
    }

    private final ConfigurationFile configurationFile;
    private final TextComponentUtil textComponentUtil;
    private final TagUtil tagUtil;
    private final ServerPrefix serverPrefix;

    @Override
    @SneakyThrows
    public void execute(CommandSender commandSender, String[] args) {
        if (!(commandSender instanceof ProxiedPlayer)) {
            commandSender.sendMessage(textComponentUtil.createTextComponent("§cVocê não pode executar esse comando."));
            return;
        }

        ProxiedPlayer player = (ProxiedPlayer) commandSender;
        if(CacheBungee.servidoresListIgnore.contains(player.getServer().getInfo().getName())) return;
        if (!player.hasPermission(configurationFile.getConfig().getString("Superiores-permission"))) {
            player.sendMessage(textComponentUtil.createTextComponent("§cVocê não pode executar esse comando"));
            return;
        }

        if (args.length == 0) {
            player.sendMessage(textComponentUtil.createTextComponent("§cUtilize o comando da seguinte fomra: /ss <mensagem>."));
            return;
        }

        StringBuilder constructorMessage = new StringBuilder();
        StringBuilder msg = new StringBuilder();
        constructorMessage.append("§6§l[SUPERIORES] ").append("servidor ").append("cargo ").append("nick ").append("msg");

        for (String arg : args) {
            msg.append(arg).append(" ");
        }

        String send_message = getMessageSend(constructorMessage, msg.toString(), player);

        for (ProxiedPlayer proxiedPlayer : CacheBungee.superioresList) {
            if(CacheBungee.servidoresListIgnore.contains(proxiedPlayer.getServer().getInfo().getName())) continue;
            proxiedPlayer.sendMessage(textComponentUtil.createTextComponent(send_message));
        }
    }

    public String getMessageSend(StringBuilder constructorMessage, String msg, ProxiedPlayer player) {
        return constructorMessage.toString().replace("servidor", "§8(" + serverPrefix.getPrefixServer(player.getServer().getInfo().getName()) + ")")
                .replace("cargo", tagUtil.getCargoProxiedPlayer(player).getTag())
                .replace("nick", player.getName() + ":")
                .replace("msg", "§f" + msg.replaceAll("&", "§"));
    }

}
