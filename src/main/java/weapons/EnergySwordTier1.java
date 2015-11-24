package weapons;

import java.util.List;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.meta.ItemMeta;

import mainPackage.RngObject;
import mainPackage.Weapon;

public class EnergySwordTier1 extends EnergySword {
	
	public EnergySwordTier1(ItemMeta imx) {
		super(imx, new EnergySwordTier1(imx));
	}

	RngObject handler = new RngObject();
	Integer[] chance = {};
	Enchantment[] enchants = {};
	
	public List<Enchantment> roll() {
		 return handler.applyRNG(chance, enchants);
	}
}
