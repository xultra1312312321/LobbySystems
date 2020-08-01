package me.iloveeatmuffin.lobbysystem;

import me.iloveeatmuffin.lobbysystem.Commands.FlyCommand;
import me.iloveeatmuffin.lobbysystem.Commands.HidePlayerCommand;
import me.iloveeatmuffin.lobbysystem.Commands.MuteGlobalChat;
import me.iloveeatmuffin.lobbysystem.Commands.SpeedCommand;
import me.iloveeatmuffin.lobbysystem.Configs.FileManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class LobbySystem extends JavaPlugin {

    @Override
    public void onEnable() {
        System.out.println("=======================");
        System.out.println("  作者:ILoveEatMuffin  ");
        System.out.println("     大廳插件開啟中      ");
        System.out.println("       版本1.0         ");
        System.out.println("=======================");
        getConfig().options().copyDefaults();
        saveDefaultConfig();

        FileManager.setup();
        FileManager.get().addDefault("fly-on-message", "fly-on-message");
        FileManager.get().addDefault("fly-off-message", "fly-off-message");
        FileManager.get().addDefault("speed-on-message", "speed-on-message");
        FileManager.get().addDefault("speed-off-message", "speed-off-message");
        FileManager.get().addDefault("hide-on-message", "hide-on-message");
        FileManager.get().addDefault("hide-off-message", "hide-off-message");
        FileManager.get().addDefault("non-permission", "non-permission");




        FileManager.get().options().copyDefaults(true);
        FileManager.save();

        getCommand("fly").setExecutor(new FlyCommand(this));
        getCommand("speed").setExecutor(new SpeedCommand(this));
        getCommand("hide").setExecutor(new HidePlayerCommand(this));
        getCommand("globalmute").setExecutor(new MuteGlobalChat(this));
        getServer().getPluginManager().registerEvents(new MuteGlobalChat(), this);


    }

    @Override
    public void onDisable() {
        System.out.println("=======================");
        System.out.println("  作者:ILoveEatMuffin  ");
        System.out.println("     大廳插件關閉中      ");
        System.out.println("       版本1.0         ");
        System.out.println("=======================");
    }
}
