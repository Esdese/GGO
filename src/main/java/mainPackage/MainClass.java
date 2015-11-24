package mainPackage;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class MainClass extends JavaPlugin {
	public static void main(String[] args) {
		
	}
	public void onEnable() {
		Bukkit.getServer().getPluginManager().registerEvents(new SpawnerListener(), this);
	}
}
