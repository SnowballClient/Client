package org.golde.snowball.converters;

import org.golde.snowball.Snowball;

import net.minecraft.enchantment.Enchantment.Rarity;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.inventory.EntityEquipmentSlot;

public class SnowballEnchantmentEnum {

	public static enum SnowballEnchantmentRarity {
		COMMON("COMMON", "a", "COMMON"), 
		UNCOMMON("UNCOMMON", "b", "UNCOMMON"), 
		RARE("RARE", "c", "RARE"), 
		VERY_RARE("VERY_RARE", "d", "VERY_RARE");
		
		private final String clientDeob;
		private final String client; 
		private final String server;
		private SnowballEnchantmentRarity(String clientDeob, String client, String server) {
			this.clientDeob = clientDeob;
			this.client = client;
			this.server = server;
		}
		
		public Rarity getClient() {
			try {
				return (Rarity)Rarity.class.getField(Snowball.instance.isDeobfuscated() ? clientDeob : client).get(null);
			} 
			catch (IllegalArgumentException |  IllegalAccessException | NoSuchFieldException | SecurityException e) {
				e.printStackTrace();
			}
			return null;
		}
	}
	
	public static enum SnowballEnchantmentType {
		ALL("ALL", "a", "ALL"),
		ARMOR("ARMOR", "b", "ARMOR"),
		ARMOR_FEET("ARMOR_FEET", "c", "ARMOR_FEET"),
		ARMOR_LEGS("ARMOR_LEGS", "d", "ARMOR_LEGS"),
		ARMOR_CHEST("ARMOR_CHEST", "e", "ARMOR_CHEST"),
		ARMOR_HEAD("ARMOR_HEAD", "f", "ARMOR_HEAD"),
		WEAPON("WEAPON", "g", "WEAPON"),
		DIGGER("DIGGER", "h", "DIGGER"),
		FISHING_ROD("FISHING_ROD", "i", "FISHING_ROD"),
		BREAKABLE("BREAKABLE", "j", "BREAKABLE"),
		BOW("BOW", "k", "BOW"),
		WEARABLE("WEARABLE", "l", "WEARABLE");
		
		private final String clientDeob;
		private final String client; 
		private final String server;
		private SnowballEnchantmentType(String clientDeob, String client, String server) {
			this.clientDeob = clientDeob;
			this.client = client;
			this.server = server;
		}
		
		public EnumEnchantmentType getClient() {
			try {
				return (EnumEnchantmentType)EnumEnchantmentType.class.getField(Snowball.instance.isDeobfuscated() ? clientDeob : client).get(null);
			} 
			catch (IllegalArgumentException |  IllegalAccessException | NoSuchFieldException | SecurityException e) {
				e.printStackTrace();
			}
			return null;
		}
	}
	
	public static enum SnowballEnchantmentSlot {
		MAINHAND("MAINHAND", "a", "MAINHAND"),
		OFFHAND("OFFHAND", "b", "OFFHAND"),
		FEET("FEET", "c", "FEET"),
		LEGS("LEGS", "d", "LEGS"),
		CHEST("CHEST", "e", "CHEST"),
		HEAD("HEAD", "f", "HEAD");
		
		private final String clientDeob;
		private final String client; 
		private final String server;
		private SnowballEnchantmentSlot(String clientDeob, String client, String server) {
			this.clientDeob = clientDeob;
			this.client = client;
			this.server = server;
		}
		
		public EntityEquipmentSlot getClient() {
			try {
				return (EntityEquipmentSlot)EntityEquipmentSlot.class.getField(Snowball.instance.isDeobfuscated() ? clientDeob : client).get(null);
			} 
			catch (IllegalArgumentException |  IllegalAccessException | NoSuchFieldException | SecurityException e) {
				e.printStackTrace();
			}
			return null;
		}
	}

}
