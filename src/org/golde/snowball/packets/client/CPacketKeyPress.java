package org.golde.snowball.packets.client;

import java.io.IOException;
import java.util.UUID;

import net.minecraft.client.Minecraft;
import net.minecraft.network.PacketBuffer;

public class CPacketKeyPress extends CPacket {

	private int key;
	private String name;
	
	public CPacketKeyPress() { }
	
	public CPacketKeyPress(String name, int key) {
		this.key = key;
		this.name = name;
	}
	
	@Override
	public void writePacketData(PacketBuffer buf) throws IOException {
		buf.writeString(name);
		buf.writeInt(key);
		buf.writeBoolean(!(Minecraft.getMinecraft().currentScreen == null)); //null == playing game, else is in some kind of gui
	}

}
