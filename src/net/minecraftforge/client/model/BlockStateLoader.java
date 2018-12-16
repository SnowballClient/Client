package net.minecraftforge.client.model;

import java.io.Reader;

import com.google.gson.Gson;

import net.minecraft.client.renderer.block.model.ModelBlockDefinition;
import net.minecraft.util.ResourceLocation;

//Changed so it only loads vanilla stuff. Didnt want to break more code so thats why this class exists
public class BlockStateLoader {

	public static ModelBlockDefinition load(Reader reader, final Gson vanillaGSON)
	{
		return vanillaGSON.fromJson(reader, ModelBlockDefinition.class);
	}
	
}
