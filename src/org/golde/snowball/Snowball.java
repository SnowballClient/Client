package org.golde.snowball;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.FileChannel;
import java.nio.channels.GatheringByteChannel;
import java.nio.channels.ScatteringByteChannel;
import java.nio.charset.Charset;
import java.util.Random;
import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.golde.snowball.Updater.UpdateResult;
import org.golde.snowball.Updater.UpdateResults;
import org.golde.snowball.files.SBFiles;
import org.golde.snowball.files.SBLang;
import org.golde.snowball.packets.SnowballPacket;
import org.golde.snowball.packets.client.CPacketKeyPress;
import org.golde.snowball.packets.server.SPacketAddBlock;
import org.golde.snowball.render.gui.LoadingCustom;
import org.golde.snowball.shared.Constants;
import org.golde.snowball.shared.CustomPayloadConstants;
import org.golde.snowball.texture.SnowballResourcePackRepositoryEntry;
import org.golde.snowball.util.BetterKeyboard;
import org.lwjgl.input.Keyboard;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.Unpooled;
import io.netty.util.ByteProcessor;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.resources.ResourcePackRepository;
import net.minecraft.client.resources.ResourcePackRepository.Entry;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.client.CPacketCustomPayload;
import net.minecraft.network.play.server.SPacketCustomPayload;
import net.minecraft.util.text.ChatType;
import net.minecraft.util.text.TextComponentString;

public class Snowball {

	public final Logger logger = LogManager.getLogger("Snowball");

	public static final Snowball instance = new Snowball();

	public final Random RANDOM = new Random();

	private Entry resourcePack;

	private String PLAYER_NAME;

	public boolean isOnSnowballServer = false;

	private boolean isRunningInEclipse = true;

	public final String VERSION = "1.0.0";
	public final String[] MAIN_MENU_STRINGS = {"Minecraft 1.12.2", "Snowball v" + VERSION,};

	private Updater snowballUpdater = new Updater();
	private UpdateResults updateResults;

	private String newVersion = null;

	public void constructor() {
		SBFiles.init();
		letsSeeIfWereRunningInEclipse();
		SnowballRPC.start();

		updateResults = snowballUpdater.checkForUpdates();

		if(updateResults != null && updateResults.getResult() != null) {
			if(updateResults.getResult() == UpdateResult.FAIL) {
				logger.error("[Magical Auto-Updater] Failed to check for updates: " + updateResults.getVersion());
			} 
			else if(updateResults.getResult() == UpdateResult.NO_UPDATE) {
				logger.info("[Magical Auto-Updater] No update found.");
			}
			else if(updateResults.getResult() == UpdateResult.UPDATE_AVAILABLE) {
				logger.info("[Magical Auto-Updater] Update " + updateResults.getVersion() + " has been released!");
			}
			else if(updateResults.getResult() == UpdateResult.DEV) {
				logger.warn("[Magical Auto-Updater] Your using a developer version of Snowball that has not been released to the public. Take care!");
			}
		}
		else {
			logger.error("Update was null????");
		}

	}

	public Logger getLogger() {
		return logger;
	}

	public Entry getResourcePack() {
		return resourcePack;
	}

	public boolean isDeobfuscated() {
		return isRunningInEclipse;
	}

	public void start() {

		SBLang.init();
		PLAYER_NAME = Minecraft.getMinecraft().getSession().getProfile().getName();

		ResourcePackRepository rpr = Minecraft.getMinecraft().getResourcePackRepository();
		resourcePack = new SnowballResourcePackRepositoryEntry(rpr, SBFiles.getDataDirectoryResourcePack());
		try {
			resourcePack.updateResourcePack();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Minecraft.getMinecraft().defaultResourcePacks.add(resourcePack.getResourcePack());
		Minecraft.getMinecraft().refreshResources();

		//INIT
		net.ilexiconn.llibrary.LLibrary.PROXY.onPreInit();
		net.ilexiconn.qubble.Qubble.PROXY.onPreInit();

		net.ilexiconn.llibrary.LLibrary.PROXY.onInit();
		net.ilexiconn.qubble.Qubble.PROXY.onInit();

		net.ilexiconn.llibrary.LLibrary.PROXY.onPostInit();
		net.ilexiconn.qubble.Qubble.PROXY.onPostInit();


	}

	public void refreshAssets() {
		SBLang.refreshAssets();
		if(Minecraft.getMinecraft().currentScreen instanceof LoadingCustom) { //sometimes gets stuck idk y
			Minecraft.getMinecraft().displayGuiScreen(null);
		}
	}

	public void onCustomPayloadRecieve(PacketBuffer buff) {
		String data = buff.readStringFromBuffer(256);

		Snowball.instance.getLogger().info("I recieved: " + data);

		if(data.equalsIgnoreCase(CustomPayloadConstants.RESPAWN)){ //bungeecord
			isOnSnowballServer = true;
			logger.info("respawn");
		}
	}

	private void sendCustomPayload(String cpd) {
		PacketBuffer packetbuffer = new PacketBuffer(Unpooled.buffer());
		packetbuffer.writeString(cpd);
		Minecraft.getMinecraft().getConnection().sendPacket(new CPacketCustomPayload(CustomPayloadConstants.CHANNEL_NAME, packetbuffer));
	}

	public void tick() {
		for(int i = 0; i < Keyboard.KEYBOARD_SIZE; i++) {
			if(BetterKeyboard.wasKeyPressed(i)) {
				if(Minecraft.getMinecraft().getConnection() != null) {
					sendClientPacket(new CPacketKeyPress(PLAYER_NAME, i));
				}
			}
		}
	}

	public void sendClientPacket(SnowballPacket packetIn) {
		if(Minecraft.getMinecraft().getConnection() != null && isOnSnowballServer) {
			Minecraft.getMinecraft().getConnection().sendPacket(packetIn);
		}
	}

	public void startConnectingToServer() {
		isOnSnowballServer = false;
		System.out.println("Logged into server");

	}

	private void letsSeeIfWereRunningInEclipse() {
		try {
			Field f = GuiMainMenu.class.getDeclaredField("splashText");
			isRunningInEclipse = true;
		} catch (Exception e) {
			isRunningInEclipse = false;
		} 
		logger.info("Deobfuscated: " + isRunningInEclipse);
	}

	public UpdateResults getUpdateResults() {
		return updateResults;
	}

}
