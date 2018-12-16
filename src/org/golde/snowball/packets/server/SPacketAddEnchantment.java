package org.golde.snowball.packets.server;

import java.io.IOException;
import java.util.Arrays;

import org.golde.snowball.Snowball;
import org.golde.snowball.converters.SnowballEnchantmentEnum;
import org.golde.snowball.files.SBLang;
import org.golde.snowball.shared.Constants;
import org.golde.snowball.shared.nbt.NBTConstants;
import org.golde.snowball.shared.util.StringHelper;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantment.Rarity;
import net.minecraft.enchantment.EnchantmentData;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemEnchantedBook;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.util.ResourceLocation;

public class SPacketAddEnchantment extends SPacket {

	private String name;
	private String nameLocalised;
	private int id;
	
	private Rarity rarity;
	private EnumEnchantmentType etype;
	private EntityEquipmentSlot[] slot;
	
	private int property_minEnchantmentLevel;
	private int property_maxEnchantmentLevel;
	private boolean property_cursedEnchantment;
	private boolean property_treasureEnchantment;

	@Override
	public void processPacket(INetHandlerPlayClient handler) {
		
		Enchantment dummy = new DummyEnchantment();
		Enchantment.REGISTRY.register(id, new ResourceLocation(Constants.MINECRAFT_KEY, name), dummy);
		
		Snowball.instance.getLogger().info("=== Recieved Enchantment ===");
		Snowball.instance.getLogger().info("name: " + name);
		Snowball.instance.getLogger().info("rarity: " + rarity.name());
		Snowball.instance.getLogger().info("item: " + etype.name());
		Snowball.instance.getLogger().info("slots: " + Arrays.toString(slot));
		
		SBLang.addKeyValue("enchantment.snowball_" + id, nameLocalised);
		
		SPacketInfo.CURRENT_LOADING_SCREEN.setMinorTaskProgress("Adding enchantment '" + nameLocalised + "'", SPacketInfo.CURRENT_LOADING_SCREEN.getMinorTaskProgress() + 1);
	}
	
	@Override
	public void readPacketData(PacketBuffer buf) throws IOException {
		NBTTagCompound tag = buf.readNBTTagCompoundFromBuffer();
		
		id = tag.getInteger(NBTConstants.KEY_ID);
		nameLocalised = tag.getString(NBTConstants.KEY_NAME);
		name = StringHelper.sanatise(nameLocalised);
		
		rarity = SnowballEnchantmentEnum.SnowballEnchantmentRarity.valueOf(tag.getString(NBTConstants.KEY_ENCHANTMENT_RARITY)).getClient();
		etype = SnowballEnchantmentEnum.SnowballEnchantmentType.valueOf(tag.getString(NBTConstants.KEY_ENCHANTMENT_ITEM)).getClient();
		
		NBTTagList tempSlotArrayList = tag.getTagList(NBTConstants.KEY_ENCHANTMENT_SLOTS, 8);
		EntityEquipmentSlot[] tempSlotArray = new EntityEquipmentSlot[tempSlotArrayList.tagCount()];
		for(int i = 0; i < tempSlotArray.length; i++) {
			tempSlotArray[i] = SnowballEnchantmentEnum.SnowballEnchantmentSlot.valueOf(tempSlotArrayList.getStringTagAt(i)).getClient();
		}
		
		slot = tempSlotArray;
		
		NBTTagCompound properties = tag.getCompoundTag(NBTConstants.KEY_PROPERTIES);
		
		property_minEnchantmentLevel = properties.getInteger(NBTConstants.KEY_ENCHANTMENT_PROPERTIES_minEnchantmentLevel);
		property_maxEnchantmentLevel = properties.getInteger(NBTConstants.KEY_ENCHANTMENT_PROPERTIES_maxEnchantmentLevel);
		property_cursedEnchantment = properties.getBoolean(NBTConstants.KEY_ENCHANTMENT_PROPERTIES_cursedEnchantment);
		property_treasureEnchantment = properties.getBoolean(NBTConstants.KEY_ENCHANTMENT_PROPERTIES_treasureEnchantment);
		
	}
	
	private class DummyEnchantment extends Enchantment {

		protected DummyEnchantment() {
			super(rarity, etype, slot);
			setName("snowball_" + id);
		}
		
		@Override
		public int getMinLevel() {
			return property_minEnchantmentLevel;
		}
		
		@Override
		public int getMaxLevel() {
			return property_maxEnchantmentLevel;
		}
		
		@Override
		public boolean isCurseEnchantment() {
			return property_cursedEnchantment;
		}
		
		@Override
		public boolean isTreasureEnchantment() {
			return property_treasureEnchantment;
		}
		
	}

}
