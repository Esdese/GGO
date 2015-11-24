package mainPackage;

import java.util.List;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;

public class SpawnerListener implements Listener, RNG {
	@EventHandler
	public void onEvent(CreatureSpawnEvent e) {
		
	}
	public List<Enchantment> roll() {
		return null;
		//TODO everything
	}

}
