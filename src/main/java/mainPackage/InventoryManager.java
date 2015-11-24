package mainPackage;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class InventoryManager implements Listener {
	
	Material[] weapons = {};
	
	@EventHandler
	public void onInventoryChange(InventoryClickEvent e) {
		ItemStack is = e.getCurrentItem();
		for (int i = 0; i < weapons.length; i++) {
			if (is.getType() == weapons[i]) {
			//TODO all this stuff
			}
		}
	}
}
