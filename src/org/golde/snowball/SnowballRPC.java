package org.golde.snowball;

import club.minnced.discord.rpc.DiscordEventHandlers;
import club.minnced.discord.rpc.DiscordRPC;
import club.minnced.discord.rpc.DiscordRichPresence;

public class SnowballRPC {

	public static void start() {
		 DiscordRPC lib = DiscordRPC.INSTANCE;
		 DiscordEventHandlers handlers = new DiscordEventHandlers();
	        handlers.ready = (user) -> System.out.println("Discord RPC Ready!");
	        lib.Discord_Initialize("517839092168261634", handlers, true, "");
	        DiscordRichPresence presence = new DiscordRichPresence();
	        presence.startTimestamp = System.currentTimeMillis() / 1000; //playing text
	        presence.details = "Playing Minecraft 1.12.2";
	        presence.largeImageKey = "snowgolem";
	        presence.largeImageText = "Using Snowball Client v" + Snowball.instance.VERSION;
	        lib.Discord_UpdatePresence(presence);
	        // in a worker thread
	        new Thread(() -> {
	            while (!Thread.currentThread().isInterrupted()) {
	                lib.Discord_RunCallbacks();
	                try {
	                    Thread.sleep(2000);
	                } catch (InterruptedException ignored) {}
	            }
	        }, "RPC-Callback-Handler").start();
	}
	
}
