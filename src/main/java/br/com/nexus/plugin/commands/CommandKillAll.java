package br.com.nexus.plugin.commands;

import br.com.nexus.plugin.Main;
import br.com.nexus.plugin.cache.CacheBungee;
import br.com.nexus.plugin.util.ConfigurationFile;
import br.com.nexus.plugin.util.TextComponentUtil;
import br.com.nexus.plugin.util.configsUtil.LobbyList;
import br.com.nexus.plugin.util.configsUtil.ServerPrefix;
import lombok.SneakyThrows;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

import java.util.Random;


public class CommandKillAll extends Command {

    private final TextComponentUtil textComponentUtil;
    private final ConfigurationFile configurationFile;
    private final Main main;
    private final ServerPrefix serverPrefix;

    public CommandKillAll(TextComponentUtil textComponentUtil, ConfigurationFile configurationFile, Main main, ServerPrefix serverPrefix) {
        super("killAll");
        this.textComponentUtil = textComponentUtil;
        this.main = main;
        this.configurationFile = configurationFile;
        this.serverPrefix = serverPrefix;
    }

    @Override @SneakyThrows
    public void execute(CommandSender commandSender, String[] args) {
        if (!(commandSender instanceof ProxiedPlayer)) {
            commandSender.sendMessage(textComponentUtil.createTextComponent("§cApenas jogadores podem executar esse comando."));
            return;
        }
        ProxiedPlayer proxiedPlayer = (ProxiedPlayer) commandSender;
        if(CacheBungee.servidoresListIgnore.contains(proxiedPlayer.getServer().getInfo().getName())) return;

        if (!proxiedPlayer.hasPermission(configurationFile.getConfig().getString("Staff-KillAll"))) {
            proxiedPlayer.sendMessage(textComponentUtil.createTextComponent("§cVocê não tem permissão para executar esse comando."));
            return;
        }

        for(ProxiedPlayer player : BungeeCord.getInstance().getPlayers()) {
            if(player.getServer().getInfo().equals(player.getServer().getInfo())) continue;
            if(proxiedPlayer.hasPermission(configurationFile.getConfig().getString("Staff-KillAll"))) {
                if(proxiedPlayer != player) player.sendMessage(textComponentUtil.createTextComponent("§aTodos os jogadores foram enviados para o lobby, porém, você é adminstrador e não foi levado."));
                continue;
            }
            ServerInfo lobby = main.getProxy().getServerInfo(randomLobby());
            player.sendMessage(textComponentUtil.createTextComponent("§aEnviando para o lobby §8(" + serverPrefix.getPrefixServer(lobby.getName()) + ")§a..."));
        }
        proxiedPlayer.sendMessage(textComponentUtil.createTextComponent(""));
        proxiedPlayer.sendMessage(textComponentUtil.createTextComponent("§aVocê usou o comando para enviar todos para o lobby."));
        proxiedPlayer.sendMessage(textComponentUtil.createTextComponent(""));

    }

    public String randomLobby() {
        Random r = new Random();
        int randomNumber = r.nextInt(CacheBungee.lobbyList.size());
        return CacheBungee.lobbyList.get(randomNumber);
    }

}
