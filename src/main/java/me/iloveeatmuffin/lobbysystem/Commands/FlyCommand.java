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

public class FlyCommand implements CommandExecutor {

    private ArrayList<Player> flying_people = new ArrayList<>();
    private LobbySystem plugin;
    String onMessage = FileManager.get().getString("fly-on-message");
    String offMessage = FileManager.get().getString("fly-off-message");
    String permission = FileManager.get().getString("non-permission");

    public FlyCommand(LobbySystem plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (args.length == 0) {
                flyMethod(player);
            } else if (args.length == 1) {
                if (player.hasPermission("lobbysystem.fly.others")) {
                    Player target = Bukkit.getPlayer(args[0]);
                    flyMethod(target);
                } else {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', permission));
                }
            }
            }
        return true;
    }

    public void flyMethod(Player player) {
        if (player.hasPermission("lobbysystem.fly")) {
            if (flying_people.contains(player)) {
                flying_people.remove(player);

                player.sendMessage(ChatColor.translateAlternateColorCodes('&', offMessage));
                player.setAllowFlight(false);
            } else if (!flying_people.contains(player)) {
                flying_people.add(player);

                player.sendMessage(ChatColor.translateAlternateColorCodes('&', onMessage));
                player.setAllowFlight(true);
            }
        }
    }
}
