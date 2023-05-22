package br.com.nexus.plugin.commands;

import br.com.nexus.plugin.cache.CacheBungee;
import br.com.nexus.plugin.util.ConfigurationFile;
import br.com.nexus.plugin.util.TextComponentUtil;
import lombok.SneakyThrows;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class CommandTPB extends Command {

    private final TextComponentUtil textComponentUtil;
    private final ConfigurationFile configurationFile;

    public CommandTPB(TextComponentUtil textComponentUtil, ConfigurationFile configurationFile) {
        super("tpb");
        this.textComponentUtil = textComponentUtil;
        this.configurationFile = configurationFile;
    }


    @Override @SneakyThrows
    public void execute(CommandSender commandSender, String[] args) {
        if(!(commandSender instanceof ProxiedPlayer)) {
            commandSender.sendMessage(textComponentUtil.createTextComponent("§cApenas jogadores podem executar esse comando."));
            return;
        }
        ProxiedPlayer proxiedPlayer = (ProxiedPlayer) commandSender;
        if(CacheBungee.servidoresListIgnore.contains(proxiedPlayer.getServer().getInfo().getName())) return;

        if(!proxiedPlayer.hasPermission(configurationFile.getConfig().getString("VeantyCore.TPB"))) {
            proxiedPlayer.sendMessage(textComponentUtil.createTextComponent("§cVocê não tem permissão para executar esse comando."));
            return;
        }

        if(args.length != 1) {
            proxiedPlayer.sendMessage(textComponentUtil.createTextComponent(""));
            proxiedPlayer.sendMessage(textComponentUtil.createTextComponent("§CUtilize o comando da seguinte forma: /tpb <nick>"));
            proxiedPlayer.sendMessage(textComponentUtil.createTextComponent(""));
            return;
        }

        ProxiedPlayer target = BungeeCord.getInstance().getPlayer(args[0]);
        if(target == null) {
            proxiedPlayer.sendMessage(textComponentUtil.createTextComponent("§cEsse jogador não está online."));
            return;
        }
        proxiedPlayer.connect(target.getServer().getInfo());
        proxiedPlayer.sendMessage(textComponentUtil.createTextComponent("§cVocê foi teleportado ao servidor do jogador: " + target.getName() + "."));
    }
}
