package org.golde.snowball.converters;

import org.golde.snowball.Snowball;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;

public enum SnowballStepSound {

	WOOD("WOOD", "a", "a"),
	GROUND("GROUND", "b", "b"),
	PLANT("PLANT", "c", "c"),
	STONE("STONE", "d", "d"),
	METAL("METAL", "e", "e"),
	GLASS("GLASS", "f", "f"),
	CLOTH("CLOTH", "g", "g"),
	SAND("SAND", "h", "h"),
	SNOW("SNOW", "i", "i"),
	LADDER("LADDER", "j", "j"),
	ANVIL("ANVIL", "k", "k"),
	SLIME("SLIME", "l", "l"),
	;
	
	private final String clientDeob; 
	private final String client;
	private final String server;
	private SnowballStepSound(String clientDeob, String client, String server) {
		this.clientDeob = clientDeob;
		this.client = client;
		this.server = server;
	}
	
	public SoundType getClient() {
		try {
			return (SoundType)SoundType.class.getField(Snowball.instance.isDeobfuscated() ? clientDeob : client).get(null);
		} 
		catch (IllegalArgumentException |  IllegalAccessException | NoSuchFieldException | SecurityException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
