package org.golde.snowball.packets.server;

import java.io.IOException;

import org.golde.snowball.packets.SnowballPacket;

import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;

public abstract class SPacket extends SnowballPacket {

	@Override
	public void writePacketData(PacketBuffer buf) throws IOException {
		//Packets from server don't need to write we only read
	}

}
