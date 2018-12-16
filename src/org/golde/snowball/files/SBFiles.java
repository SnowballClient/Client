package org.golde.snowball.files;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.golde.snowball.util.FileUtil;

import net.minecraft.client.Minecraft;

public class SBFiles {

	private static File dataDirectory;
	private static File dataDirectoryResourcePack;
	private static File dataDirectoryResourcePackAssets;
	
	private static File dataDirectoryResourcePackAssetsBlockstates;
	
	private static File dataDirectoryResourcePackAssetsModels;
	private static File dataDirectoryResourcePackAssetsModelsBlock;
	private static File dataDirectoryResourcePackAssetsModelsItem;
	
	private static File dataDirectoryResourcePackAssetsTextures;
	private static File dataDirectoryResourcePackAssetsTexturesBlocks;
	private static File dataDirectoryResourcePackAssetsTexturesItems;
	
	public static void init() {
		dataDirectory = new File(Minecraft.getMinecraft().mcDataDir, "SnowballData");
		dataDirectory.mkdirs();
		
		dataDirectoryResourcePack = new File(dataDirectory, "textures");
		dataDirectoryResourcePack.mkdirs();
		
		try {
			FileUtils.cleanDirectory(dataDirectoryResourcePack);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		FileUtil.copyAsset("textures.zip", dataDirectory);
		try {
			FileUtil.unzip(new File(dataDirectory, "textures.zip").getAbsolutePath(), dataDirectory.getAbsolutePath());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		new File(dataDirectory, "textures.zip").delete();
		
		
		dataDirectoryResourcePackAssets = new File(new File(dataDirectoryResourcePack, "assets"), "snowball");
		dataDirectoryResourcePackAssets.mkdirs();
		
		dataDirectoryResourcePackAssetsTextures = new File(dataDirectoryResourcePackAssets, "textures");
		dataDirectoryResourcePackAssetsTextures.mkdirs();
		dataDirectoryResourcePackAssetsTexturesBlocks = new File(dataDirectoryResourcePackAssetsTextures, "blocks");
		dataDirectoryResourcePackAssetsTexturesBlocks.mkdirs();
		dataDirectoryResourcePackAssetsTexturesItems = new File(dataDirectoryResourcePackAssetsTextures, "items");
		dataDirectoryResourcePackAssetsTexturesItems.mkdirs();
		
		dataDirectoryResourcePackAssetsBlockstates = new File(dataDirectoryResourcePackAssets, "blockstates");
		dataDirectoryResourcePackAssetsBlockstates.mkdirs();
		
		dataDirectoryResourcePackAssetsModels = new File(dataDirectoryResourcePackAssets, "models");
		dataDirectoryResourcePackAssetsModels.mkdirs();
		dataDirectoryResourcePackAssetsModelsBlock = new File(dataDirectoryResourcePackAssetsModels, "block");
		dataDirectoryResourcePackAssetsModelsBlock.mkdirs();
		dataDirectoryResourcePackAssetsModelsItem = new File(dataDirectoryResourcePackAssetsModels, "item");
		dataDirectoryResourcePackAssetsModelsItem.mkdirs();
		

		
	}
	
	public static File getDataDirectoryResourcePack() {
		return dataDirectoryResourcePack;
	}
	
	public static File getDataDirectoryResourcePackAssetsTexturesItems() {
		return dataDirectoryResourcePackAssetsTexturesItems;
	}
	
	public static File getDataDirectoryResourcePackAssetsTexturesBlocks() {
		return dataDirectoryResourcePackAssetsTexturesBlocks;
	}
	
	public static File getDataDirectoryResourcePackAssetsBlockstates() {
		return dataDirectoryResourcePackAssetsBlockstates;
	}
	
	public static File getDataDirectoryResourcePackAssetsModelsBlock() {
		return dataDirectoryResourcePackAssetsModelsBlock;
	}
	
	public static File getDataDirectoryResourcePackAssetsModelsItem() {
		return dataDirectoryResourcePackAssetsModelsItem;
	}
	
}
