package br.com.nexus.plugin.cache;

import br.com.nexus.plugin.object.TagModel;
import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.util.ArrayList;
import java.util.HashMap;

public class CacheBungee {

    public static ArrayList<TagModel> tagModelArrayList = new ArrayList<>();
    public static ArrayList<String> servidoresListOnline = new ArrayList<>();
    public static ArrayList<String> servidoresListIgnore = new ArrayList<>();
    public static ArrayList<ProxiedPlayer> superioresList = new ArrayList<>();
    public static ArrayList<ProxiedPlayer> staffList = new ArrayList<>();
    public static ArrayList<String> lobbyList = new ArrayList<>();
    public static HashMap<ProxiedPlayer, TagModel> onlinePlayersTag = new HashMap<>();
    public static HashMap<String, String> servidorPrefix = new HashMap<>();

}
