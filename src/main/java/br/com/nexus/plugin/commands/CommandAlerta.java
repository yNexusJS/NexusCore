package br.com.nexus.plugin.commands;

import br.com.nexus.plugin.cache.CacheBungee;
import br.com.nexus.plugin.util.ConfigurationFile;
import br.com.nexus.plugin.util.TextComponentUtil;
import lombok.SneakyThrows;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class CommandAlerta extends Command {

    public CommandAlerta(TextComponentUtil textComponentUtil, ConfigurationFile configurationFile) {
        super("alerta");
        this.textComponentUtil = textComponentUtil;
        this.configurationFile = configurationFile;
    }

    private final TextComponentUtil textComponentUtil;
    private final ConfigurationFile configurationFile;

    @Override @SneakyThrows
    public void execute(CommandSender commandSender, String[] args) {

        if(commandSender instanceof ProxiedPlayer) {
            ProxiedPlayer player = (ProxiedPlayer) commandSender;
            if(!player.hasPermission(configurationFile.getConfig().getString("Anuncio-permission"))) {
                player.sendMessage(textComponentUtil.createTextComponent("§cVocê não tem permissão para executar este comando."));
                return;
            }
            if(CacheBungee.servidoresListIgnore.contains(player.getServer().getInfo().getName())) return;
        }

        if(args.length == 0) {
            commandSender.sendMessage(textComponentUtil.createTextComponent("§cUtilize o comando da segunte forma: /alerta <mensagem>."));
            return;
        }

        StringBuilder message = new StringBuilder();
        for (String arg : args) {
            message.append(arg).append(" ");
        }

        sendMessageForAll(message.toString());
        sendMessageForConsole(message.toString());
    }


    public void sendMessageForAll(String mensagem) {
        BungeeCord.getInstance().broadcast(textComponentUtil.createTextComponent(""));
        BungeeCord.getInstance().broadcast(textComponentUtil.createTextComponent("§5§lVEANTY: §f" + mensagem.toString().replaceAll("&", "§")));
        BungeeCord.getInstance().broadcast(textComponentUtil.createTextComponent(""));
    }

    public void sendMessageForConsole(String mensagem) {
        BungeeCord.getInstance().getConsole().sendMessage(textComponentUtil.createTextComponent(""));
        BungeeCord.getInstance().getConsole().sendMessage(textComponentUtil.createTextComponent("§5§lVEANTY: §f" + mensagem.toString().replaceAll("&", "§")));
        BungeeCord.getInstance().getConsole().sendMessage(textComponentUtil.createTextComponent(""));
    }
}
