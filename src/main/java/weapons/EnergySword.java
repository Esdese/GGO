package weapons;

import java.util.List;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.meta.ItemMeta;

import mainPackage.PrimaryWeapon;
import mainPackage.RNG;
import mainPackage.Weapon;

public class EnergySword extends PrimaryWeapon implements RNG {
	private ItemMeta im;
	private Enchantment[] enchants;
	private EnergySword tieredSword;
	public EnergySword(ItemMeta imx, EnergySword tieredSwordx) {
		im = imx;
		tieredSword = tieredSwordx;
	}
	
	public List<Enchantment> roll() {
		return tieredSword.roll();
	}
	
	private List<Enchantment> result = roll();
	enchants = result.toArray();
	
	
	
}
