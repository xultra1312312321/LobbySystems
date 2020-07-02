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

public class SpeedCommand implements CommandExecutor {
    private ArrayList<Player> Speed_people = new ArrayList<>();
    private LobbySystem plugin;
    String onMessage = FileManager.get().getString("speed-on-message");
    String offMessage = FileManager.get().getString("speed-off-message");
    String permission = FileManager.get().getString("non-permission");
    String permission1 = FileManager.get().getString("console-non-permission:");



    public SpeedCommand(LobbySystem plugin) {
        this.plugin = plugin;
    }


    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (args.length == 0) {
                SpeedMethod(player);
            } else if (args.length == 1) {
                if (player.hasPermission("lobbysystem.speed.others")) {
                    Player target = Bukkit.getPlayer(args[0]);
                    SpeedMethod(target);
                } else {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', permission));
                }
            } else {
                System.out.println(ChatColor.translateAlternateColorCodes('&', permission1));
            }
        }
        return true;
    }

    private void SpeedMethod(Player player) {
        if (player.hasPermission("lobbysystem.speed")) {
            if (Speed_people.contains(player)) {
                Speed_people.remove(player);
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', offMessage));
                player.setWalkSpeed((float)0.2);
                player.setFlySpeed((float)0.1);
            } else if (!Speed_people.contains(player)) {
                Speed_people.add(player);
                player.setWalkSpeed((float)1);
                player.setFlySpeed((float)1);
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', onMessage));

            }
        }
    }
}


