package br.com.nexus.plugin.commands;

import br.com.nexus.plugin.cache.CacheBungee;
import br.com.nexus.plugin.util.ConfigurationFile;
import br.com.nexus.plugin.util.TagUtil;
import br.com.nexus.plugin.util.TextComponentUtil;
import lombok.SneakyThrows;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class CommandPatrao extends Command {

    public CommandPatrao(TextComponentUtil textComponentUtil, TagUtil tagUtil, ConfigurationFile configurationFile) {
        super("!");
        this.textComponentUtil = textComponentUtil;
        this.tagUtil = tagUtil;
        this.configurationFile = configurationFile;
    }

    private final TextComponentUtil textComponentUtil;
    private final TagUtil tagUtil;
    private final ConfigurationFile configurationFile;

    @Override
    @SneakyThrows
    public void execute(CommandSender commandSender, String[] args) {

        if (!(commandSender instanceof ProxiedPlayer)) {
            commandSender.sendMessage(textComponentUtil.createTextComponent("§cApenas jogadores podem executar esse comando."));
            return;
        }
        ProxiedPlayer player = (ProxiedPlayer) commandSender;

        if(CacheBungee.servidoresListIgnore.contains(player.getServer().getInfo().getName())) return;
        if (!player.hasPermission(configurationFile.getConfig().getString("Staff-patrao"))) {
            player.sendMessage(textComponentUtil.createTextComponent("§cVocê não pode executar esse comando"));
            return;
        }

        if (args.length == 0) {
            commandSender.sendMessage(textComponentUtil.createTextComponent("§cUtilize o comando da segunte forma: /! <mensagem>."));
            return;
        }

        StringBuilder message = new StringBuilder();
        for (String arg : args) {
            message.append(arg).append(" ");
        }

        sendMessageForAll(message.toString(), player);
        sendMessageForConsole(message.toString(), player);
    }


    public void sendMessageForAll(String mensagem, ProxiedPlayer player) {
        BungeeCord.getInstance().broadcast(textComponentUtil.createTextComponent(""));
        BungeeCord.getInstance().broadcast(textComponentUtil.createTextComponent("§6§l ● §f" +
                tagUtil.getCargoProxiedPlayer(player).getTag() +" "+ player.getDisplayName() + ": §f" + mensagem.replaceAll("&", "§")));
        BungeeCord.getInstance().broadcast(textComponentUtil.createTextComponent(""));
    }

    public void sendMessageForConsole(String mensagem, ProxiedPlayer player) {
        BungeeCord.getInstance().getConsole().sendMessage(textComponentUtil.createTextComponent(""));
        BungeeCord.getInstance().getConsole().sendMessage(textComponentUtil.createTextComponent("§6§l ● §f" +
                tagUtil.getCargoProxiedPlayer(player).getTag() +" "+ player.getDisplayName() + ": §f" + mensagem.replaceAll("&", "§")));
        BungeeCord.getInstance().getConsole().sendMessage(textComponentUtil.createTextComponent(""));
    }
}
