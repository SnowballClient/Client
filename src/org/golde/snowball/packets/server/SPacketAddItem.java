package org.golde.snowball.packets.server;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.golde.snowball.Registrator;
import org.golde.snowball.Snowball;
import org.golde.snowball.files.SBFiles;
import org.golde.snowball.files.SBLang;
import org.golde.snowball.shared.nbt.NBTConstants;
import org.golde.snowball.util.FileUtil;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;

public class SPacketAddItem extends SPacket {

	private String name;
	private String textureName;
	private String nameLocalised;
	private int id;

	public SPacketAddItem() {}

	@Override
	public void processPacket(INetHandlerPlayClient handler) {

		System.out.println("== Recieved Item ==");
		System.out.println("Id: " + id);
		System.out.println("Name: " + name);
		System.out.println("Texture: " + textureName);
		System.out.println();

		DummyItem item = new DummyItem(name, textureName);
		Registrator.registerItem(id, name, item);
		
		SPacketInfo.CURRENT_LOADING_SCREEN.setMinorTaskProgress("Adding Item '" + nameLocalised + "'", SPacketInfo.CURRENT_LOADING_SCREEN.getMinorTaskProgress() + 1);
	}

	@Override
	public void readPacketData(PacketBuffer data) throws IOException {
		NBTTagCompound tag = data.readNBTTagCompoundFromBuffer();
		id = tag.getInteger(NBTConstants.KEY_ID);
		nameLocalised = tag.getString(NBTConstants.KEY_NAME);

		name = String.valueOf(id);
		SBLang.addKeyValue("item.snowball_" + name + ".name", nameLocalised);
		textureName = name;
		
		
		String JSON_MODEL_ITEM = tag.getString(NBTConstants.KEY_ITEM_MODEL_JSON_ITEM);
		String JSON_NAME = "snowball_" + id + ".json";
		FileUtil.writeFile(new File(SBFiles.getDataDirectoryResourcePackAssetsModelsItem(), JSON_NAME), JSON_MODEL_ITEM);
		
		
		String texture = tag.getString(NBTConstants.KEY_TEXTURE);

		BufferedImage img = FileUtil.fromBase64(texture);
		File outputfile = new File(SBFiles.getDataDirectoryResourcePackAssetsTexturesItems(), textureName + ".png");
		Snowball.instance.getLogger().info("Writing texture " + texture + " to: " + outputfile.getAbsolutePath());
		try {
			ImageIO.write(img, "png", outputfile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private class DummyItem extends Item {

		protected DummyItem(String theName, String theTextureName) {
			this.setCreativeTab(CreativeTabs.SNOWBALL);
			setUnlocalizedName("snowball_" + theName);
			//setTextureName("snowball:" + theTextureName);

		}

	}
	
}
