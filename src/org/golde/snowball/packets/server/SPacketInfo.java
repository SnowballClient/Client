package org.golde.snowball.packets.server;

import java.io.IOException;

import org.golde.snowball.Snowball;
import org.golde.snowball.render.gui.LoadingCustom;

import net.minecraft.client.Minecraft;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;

public class SPacketInfo extends SPacket {

	private int customObjectCount;
	public static LoadingCustom CURRENT_LOADING_SCREEN;
	
	@Override
	public void readPacketData(PacketBuffer buf) throws IOException {
		customObjectCount = buf.readInt();
	}

	@Override
	public void processPacket(INetHandlerPlayClient handler) {
		
		Snowball.instance.isOnSnowballServer = true;
		
		CURRENT_LOADING_SCREEN = new LoadingCustom("Initalising Snowball...", 1);
		Minecraft.getMinecraft().addScheduledTask(() -> { Minecraft.getMinecraft().displayGuiScreen(CURRENT_LOADING_SCREEN); });
		CURRENT_LOADING_SCREEN.startMajorTask("Loading Custom Objects", customObjectCount);
	}

}
