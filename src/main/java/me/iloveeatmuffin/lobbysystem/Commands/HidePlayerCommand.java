package me.iloveeatmuffin.lobbysystem.Commands;

import me.iloveeatmuffin.lobbysystem.Configs.FileManager;
import me.iloveeatmuffin.lobbysystem.LobbySystem;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.UUID;

public class HidePlayerCommand implements CommandExecutor {
    private ArrayList<Player> hiding_people = new ArrayList<>();
    public LobbySystem plugin;
    String onMessage = FileManager.get().getString("hide-on-message");
    String offMessage = FileManager.get().getString("hide-off-message");
    String permission = FileManager.get().getString("non-permission");

    public HidePlayerCommand(LobbySystem plugin) {
        this.plugin = plugin;
    }


    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;


            if (args.length == 0) {
                hideMethod(player);
            } else if (args.length == 1) {
                if (player.hasPermission("lobbysystem.hide.others")) {
                    Player target = Bukkit.getPlayer(args[0]);
                    hideMethod(target);
                } else {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', permission));
                }
            }
        }
        return true;
    }

    public void hideMethod(Player player) {
        if (player.hasPermission("lobbysystem.hide")) {
            if (hiding_people.contains(player)) {
                for (Player online : Bukkit.getServer().getOnlinePlayers()) {
                    hiding_people.remove(player);
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', offMessage));
                    player.showPlayer(LobbySystem.getPlugin(LobbySystem.class), online);
                }
            } else if (!hiding_people.contains(player)) {
                for (Player online : Bukkit.getServer().getOnlinePlayers()) {
                    hiding_people.add(player);
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', onMessage));
                    player.hidePlayer(LobbySystem.getPlugin(LobbySystem.class), online);
                }
            }
        }
    }
}


