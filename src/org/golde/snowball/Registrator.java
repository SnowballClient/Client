package org.golde.snowball;

import java.util.HashMap;
import java.util.Map;

import com.google.common.collect.UnmodifiableIterator;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;

public class Registrator {

	private static Map<Integer, Boolean> registeredIds = new HashMap<Integer, Boolean>();
	
	
	public static void registerBlock(int id, String name, Block block) {
		Block.REGISTRY.register(id, new ResourceLocation("snowball", "snowball_" + id), block);
		//block.registerBlockIcons(Minecraft.getMinecraft().getTextureMapBlocks());
		registerItem(id, name, block);
		registeredIds.put(id, true);
		
		
		UnmodifiableIterator unmodifiableiterator = block.getBlockState().getValidStates().iterator();

        while (unmodifiableiterator.hasNext())
        {
            IBlockState iblockstate = (IBlockState)unmodifiableiterator.next();
            int k = Block.REGISTRY.getIDForObject(block) << 4 | block.getMetaFromState(iblockstate);
            Block.BLOCK_STATE_IDS.put(iblockstate, k);
            Snowball.instance.logger.info("Registered state for block '" + name + "' with id " + k );
        }
		
        Minecraft.getMinecraft().getRenderItem().registerBlock(block, "snowball:snowball_" + id);
        
	}
	
	public static void registerItem(int id, String name, Block block) {
		ItemBlock item = new ItemBlock(block);
		registerItem(id, name, item);
		Item.BLOCK_TO_ITEM.put(block, item);
	}
	
	public static void registerItem(int id, String name, Item item) {
		//Item.itemRegistry.addObject(id, "snowball:" + name, item);
		Item.REGISTRY.register(id, new ResourceLocation("snowball", "snowball_" + id), item);
		if(item instanceof ItemBlock) {
			//item.registerIcons(Minecraft.getMinecraft().getTextureMapBlocks());
			
		}
		else {
			//item.registerIcons(Minecraft.getMinecraft().getTextureMapItems());
			registeredIds.put(id, false);
			Minecraft.getMinecraft().getRenderItem().registerItem(item, "snowball:snowball_" + id);
		}
	}
	
}
