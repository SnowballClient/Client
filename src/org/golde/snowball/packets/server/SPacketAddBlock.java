package org.golde.snowball.packets.server;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import org.golde.snowball.Registrator;
import org.golde.snowball.Snowball;
import org.golde.snowball.converters.SnowballMaterial;
import org.golde.snowball.converters.SnowballStepSound;
import org.golde.snowball.files.SBFiles;
import org.golde.snowball.files.SBLang;
import org.golde.snowball.shared.nbt.NBTConstants;
import org.golde.snowball.util.FileUtil;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class SPacketAddBlock extends SPacket {

	public SPacketAddBlock() { }

	private String registryName;
	private String name;
	private String nameLocalised;
	private int id;
	private String textureName;

	private float properties_light;
	private float properties_resistance;
	private float properties_hardness;
	private boolean properties_transparent;
	private Material properties_material;
	private boolean properties_silktouch;
	private SoundType properties_stepsound;

	@Override
	public void processPacket(INetHandlerPlayClient handler) {
		System.out.println("== Recieved Block ==");
		System.out.println("Id: " + id);
		System.out.println("Name: " + name);
		System.out.println("Texture: " + textureName);
		System.out.println();

		DummyBlock block = new DummyBlock(name, textureName);
		Registrator.registerBlock(id, name, block);
		
		SPacketInfo.CURRENT_LOADING_SCREEN.setMinorTaskProgress("Adding Block '" + nameLocalised + "'", SPacketInfo.CURRENT_LOADING_SCREEN.getMinorTaskProgress() + 1);
	}

	@Override
	public void readPacketData(PacketBuffer buf) throws IOException {
		NBTTagCompound tag = buf.readNBTTagCompoundFromBuffer();
		id = tag.getInteger(NBTConstants.KEY_ID);
		nameLocalised = tag.getString(NBTConstants.KEY_NAME);
		registryName = tag.getString(NBTConstants.KEY_REGISTRY);

		name = String.valueOf(id);
		SBLang.addKeyValue("tile.snowball_" + name + ".name", nameLocalised);
		textureName = name;
		String texture = tag.getString(NBTConstants.KEY_TEXTURE);



		NBTTagCompound properties = tag.getCompoundTag(NBTConstants.KEY_PROPERTIES);
		properties_light = properties.getFloat(NBTConstants.KEY_BLOCK_PROPERTIES_LIGHT);
		properties_hardness = properties.getFloat(NBTConstants.KEY_BLOCK_PROPERTIES_HARDNESS);
		properties_resistance = properties.getFloat(NBTConstants.KEY_BLOCK_PROPERTIES_RESISTANCE);
		properties_transparent = properties.getBoolean(NBTConstants.KEY_BLOCK_PROPERTIES_TRANSPARENT);
		properties_material = SnowballMaterial.valueOf(properties.getString(NBTConstants.KEY_BLOCK_PROPERTIES_MATERIAL)).getClient();
		properties_silktouch = properties.getBoolean(NBTConstants.KEY_BLOCK_PROPERTIES_SILKTOUCH);
		properties_stepsound = SnowballStepSound.valueOf(properties.getString(NBTConstants.KEY_BLOCK_PROPERTIES_STEPSOUND)).getClient();
		
		String JSON_MODEL_STATE = tag.getString(NBTConstants.KEY_BLOCK_MODEL_JSON_STATE);
		String JSON_MODEL_BLOCK = tag.getString(NBTConstants.KEY_BLOCK_MODEL_JSON_BLOCK);
		String JSON_MODEL_ITEM = tag.getString(NBTConstants.KEY_ITEM_MODEL_JSON_ITEM);
		String JSON_NAME = "snowball_" + id + ".json";
		
		FileUtil.writeFile(new File(SBFiles.getDataDirectoryResourcePackAssetsBlockstates(), JSON_NAME), JSON_MODEL_STATE);
		FileUtil.writeFile(new File(SBFiles.getDataDirectoryResourcePackAssetsModelsBlock(), JSON_NAME), JSON_MODEL_BLOCK);
		FileUtil.writeFile(new File(SBFiles.getDataDirectoryResourcePackAssetsModelsItem(), JSON_NAME), JSON_MODEL_ITEM);

		System.out.println("Properties: " + properties.toString());

		BufferedImage img = FileUtil.fromBase64(texture);
		File outputfile = new File(SBFiles.getDataDirectoryResourcePackAssetsTexturesBlocks(), textureName + ".png");
		Snowball.instance.getLogger().info("Writing texture " + texture + " to: " + outputfile.getAbsolutePath());
		try {
			ImageIO.write(img, "png", outputfile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private class DummyBlock extends Block {

		protected DummyBlock(String theName, String theTextureName) {
			super(properties_material);
			this.setCreativeTab(CreativeTabs.SNOWBALL);
			setUnlocalizedName("snowball_" + theName); //setBlockName
			//setBlockTextureName("snowball:" + theTextureName);
			setSoundType(properties_stepsound); //setStepSound
			if(properties_light != NBTConstants.INT_NULL) {
				setLightLevel(properties_light);
			}

			setHardness(properties_hardness);
			setResistance(properties_resistance);

		}

		@Override
		public boolean isOpaqueCube(IBlockState state)
		{
			return !properties_transparent;
		}

		@Override
		public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side)
	    {
	        IBlockState iblockstate = blockAccess.getBlockState(pos.offset(side));
	        Block block = iblockstate.getBlock();

	        if (properties_transparent)
	        {
	            if (blockState != iblockstate)
	            {
	                return true;
	            }

	            if (block == this)
	            {
	                return false;
	            }
	        }

	        return block == this ? false : super.shouldSideBeRendered(blockState, blockAccess, pos, side);
	    }

		@Override
		public BlockRenderLayer getBlockLayer()
	    {
	        return properties_transparent ? BlockRenderLayer.CUTOUT : BlockRenderLayer.SOLID;
	    }

		@Override
		protected boolean canSilkHarvest() {
			return properties_silktouch;
		}

		@Override
		public int quantityDropped(Random random)
		{
			return properties_silktouch ? 0 : 1;
		}

	}

}
