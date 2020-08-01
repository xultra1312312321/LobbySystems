package me.iloveeatmuffin.lobbysystem.Commands;

import me.iloveeatmuffin.lobbysystem.Configs.FileManager;
import me.iloveeatmuffin.lobbysystem.LobbySystem;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class MuteGlobalChat implements CommandExecutor, Listener {


    private static boolean globalmute = true;
    public static final String cmd_perm = "lobbysystem.globalmute";
    public static final String bypass_perm = "lobbysystem.globalmute.bypass";
    String permission = FileManager.get().getString("non-permission");
    public MuteGlobalChat(LobbySystem plugin) {
    }

    public MuteGlobalChat() {

    }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            if (!sender.hasPermission(cmd_perm)) {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', permission));
                return true;
            }
        }


        if (args.length == 0) {
            if (globalmute) {
                deactivateGlobalMute();
            } else {
                activateGlobalMute();
            }
        } else if (args.length == 1) {
            if (args[0].equalsIgnoreCase("on")) {
                activateGlobalMute();
            } else if (args[0].equalsIgnoreCase("off")) {
                deactivateGlobalMute();
            } else {
                sendHelp(sender);
            }
        } else {
            sendHelp(sender);
        }

        return true;
    }

    private void activateGlobalMute() {
        globalmute = true;
        Bukkit.broadcastMessage("已開啟聊天");
    }

    private void deactivateGlobalMute() {
        globalmute = false;
        Bukkit.broadcastMessage("已關閉聊天");
    }

    private void sendHelp(CommandSender sender) {
        sender.sendMessage("§c/globalmute - 切換聊天室");
        sender.sendMessage("§c/globalmute (on/off) - 開啟/關閉聊天室");
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        if (globalmute) {
            if (!player.isOp() && !player.hasPermission(bypass_perm)) {
                event.setCancelled(true);
                player.sendMessage("聊天室還在關閉");
            }
        }
    }
}



