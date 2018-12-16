package org.golde.snowball.packets.client;

import java.io.IOException;

import org.golde.snowball.packets.SnowballPacket;

import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;

public abstract class CPacket extends SnowballPacket {

	@Override
	public void readPacketData(PacketBuffer buf) throws IOException {
		//Sending from client, we dont need to read
	}

	@Override
	public void processPacket(INetHandlerPlayClient handler) {
		//Sending from client, we dont need to prossess
	}

}
