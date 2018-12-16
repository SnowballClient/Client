package org.golde.snowball.packets.server;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.golde.snowball.SnowballPlayer;
import org.golde.snowball.shared.enums.EnumCosmetic;
import org.golde.snowball.shared.nbt.NBTConstants;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;

public class SPacketUpdatePlayerLooks extends SPacket{

	String skin_url;
	String username;
	String custom_username;
	List<EnumCosmetic> cosmetics = new ArrayList<EnumCosmetic>();
	
	@Override
	public void readPacketData(PacketBuffer buf) throws IOException {
		NBTTagCompound tag = buf.readNBTTagCompoundFromBuffer();
		skin_url = tag.getString(NBTConstants.KEY_SPacketUpdatePlayerLooks_skin);
		username = tag.getString(NBTConstants.KEY_SPacketUpdatePlayerLooks_username);
		custom_username = tag.getString(NBTConstants.KEY_SPacketUpdatePlayerLooks_custom_username);
		
		NBTTagList cosmeticList = tag.getTagList(NBTConstants.KEY_SPacketUpdatePlayerLooks_cosmetics, 8);
		
		System.out.println("==================== CosmeticList Size: " + cosmeticList.tagCount());
		
		for(int i = 0; i < cosmeticList.tagCount(); i++) {
			cosmetics.add(EnumCosmetic.valueOf(cosmeticList.getStringTagAt(i)));
			System.out.println(cosmeticList.getStringTagAt(i));
		}
		
		System.out.println("custom_username: " + custom_username);
	}

	@Override
	public void processPacket(INetHandlerPlayClient handler) {
		
		if(username != null && !username.isEmpty()) {
			SnowballPlayer player = SnowballPlayer.get(username);
			if(skin_url == null || skin_url.equalsIgnoreCase("null")) {
				player.resetCustomSkin();
			}
			else {
				player.setCustomSkin(skin_url);
			}
			
			player.setCustomUsername(custom_username);
			player.setCosmetics(cosmetics);
			
		}

	}

}
