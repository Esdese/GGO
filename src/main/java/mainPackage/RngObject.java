package mainPackage;

import java.util.List;
import java.util.Random;

import org.bukkit.enchantments.Enchantment;

public class RngObject {
	//chance is the denominator (1/x)
	//null or List<Object>
	public RngObject() {
		
	}
	
	public List<Enchantment> applyRNG(Integer[] chance, Enchantment[] enchants) {
		Random theRandom = new Random();
		List<Enchantment> answer = null;
		for (int i = 0; i < chance.length; i++) {
			if (theRandom.nextInt(chance[i]) == 0) {
				answer.add(enchants[i]);
			}
		}
		return answer;
	}
}