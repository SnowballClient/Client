package org.golde.snowball.packets.server;

import java.io.IOException;

import org.golde.snowball.Snowball;

import net.minecraft.client.Minecraft;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;

public class SPacketRefreshResources extends SPacket {

	@Override
	public void readPacketData(PacketBuffer buf) throws IOException {
		
	}

	@Override
	public void processPacket(INetHandlerPlayClient handler) {
		Snowball.instance.logger.info("Refreshing Reosurces...");
		//SPacketInfo.CURRENT_LOADING_SCREEN.startMajorTask("Reloading assets", 1);
		//SPacketInfo.CURRENT_LOADING_SCREEN.setMinorTaskCount(0);
		Minecraft.getMinecraft().scheduleResourcesRefresh();
		
	}

}
