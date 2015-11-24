package mainPackage;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class GunFireListener implements Listener {
	
	@EventHandler
	public void onPlayerInteraction(PlayerInteractEvent e) {
		Action action = e.getAction();
		//if the player is holding an item
		if (e.isBlockInHand()) {
			//if it has lore (weapon)
			if (e.getItem().getItemMeta().hasLore()) {
				//if attempting fire
				if (action == Action.LEFT_CLICK_AIR || action == Action.LEFT_CLICK_BLOCK) {
					//TODO get weapon data/fire
					Weapon weap = (Weapon) e.getItem().
				}
			}
		}

	}
	
}
