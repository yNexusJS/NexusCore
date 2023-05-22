package br.com.nexus.plugin.commands;

import br.com.nexus.plugin.Main;
import br.com.nexus.plugin.cache.CacheBungee;
import br.com.nexus.plugin.util.configsUtil.ServerPrefix;
import br.com.nexus.plugin.util.TextComponentUtil;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.api.scheduler.ScheduledTask;

import java.util.HashMap;
import java.util.Random;

public class CommandLobby extends Command {

    public CommandLobby(TextComponentUtil textComponentUtil, ServerPrefix serverPrefix, Main main) {
        super("lobby");
        this.textComponentUtil = textComponentUtil;
        this.serverPrefix = serverPrefix;
        this.main = main;
    }

    private final TextComponentUtil textComponentUtil;
    private final ServerPrefix serverPrefix;
    private final Main main;

    private HashMap<ProxiedPlayer, ScheduledTask> listPlayers = new HashMap<>();

    @Override
    public void execute(CommandSender commandSender, String[] strings) {
        if (!(commandSender instanceof ProxiedPlayer)) {
            commandSender.sendMessage(textComponentUtil.createTextComponent("§cApenas jogadores podem executar esse comando."));
            return;
        }
        ProxiedPlayer player = (ProxiedPlayer) commandSender;
        if(CacheBungee.servidoresListIgnore.contains(player.getServer().getInfo().getName())) return;
        if (!isLobby(player)) {
            ServerInfo lobby = main.getProxy().getServerInfo(randomLobby());
            player.sendMessage(textComponentUtil.createTextComponent("§aEnviando para o lobby §8("+serverPrefix.getPrefixServer(lobby.getName())+")§a..."));
            player.connect(lobby);
        } else player.sendMessage(textComponentUtil.createTextComponent("§eVocê já está no lobby."));
    }

    public String randomLobby() {
        Random r = new Random();
        int randomNumber = r.nextInt(CacheBungee.lobbyList.size());
        return CacheBungee.lobbyList.get(randomNumber);
    }

    public boolean isLobby(ProxiedPlayer player) {
        return CacheBungee.lobbyList.contains(player.getServer().getInfo().getName());
    }
}
