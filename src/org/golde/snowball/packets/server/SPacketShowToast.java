package org.golde.snowball.packets.server;

import java.io.IOException;

import org.golde.snowball.render.gui.CustomToast;
import org.golde.snowball.shared.nbt.NBTConstants;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;

public class SPacketShowToast extends SPacket {

	private String title;
	private String desc;
	private ItemStack icon;
	
	@Override
	public void readPacketData(PacketBuffer buf) throws IOException {
		NBTTagCompound tag = buf.readNBTTagCompoundFromBuffer();
		
		title = tag.getString(NBTConstants.KEY_SPacketShowToast_title);
		desc = tag.getString(NBTConstants.KEY_SPacketShowToast_desc);
		if(tag.getBoolean(NBTConstants.KEY_SPacketShowToast_hasIcon)) {
			icon = buf.readItemStackFromBuffer();
		}
		else {
			icon = null;
		}
	}

	@Override
	public void processPacket(INetHandlerPlayClient handler) {
		CustomToast toast = (icon == null) ? new CustomToast(title, desc) : new CustomToast(title, desc, icon);
		toast.show();
	}

}
