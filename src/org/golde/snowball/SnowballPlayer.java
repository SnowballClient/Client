package org.golde.snowball;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.golde.snowball.render.player.cosmetic.Cosmetic;
import org.golde.snowball.shared.Constants;
import org.golde.snowball.shared.enums.EnumCosmetic;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IImageBuffer;
import net.minecraft.client.renderer.ThreadDownloadImageData;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.util.ResourceLocation;

public class SnowballPlayer {

	private static final Map<String, SnowballPlayer> PLAYERS = new HashMap<String, SnowballPlayer>();
	
	private ResourceLocation customSkin;
	private String customSkinUrl = "";
	private String username;
	
	public static SnowballPlayer get(String username) {
		if(PLAYERS.containsKey(username)) {
			return PLAYERS.get(username);
		} else {
			SnowballPlayer toReturn = new SnowballPlayer(username);
			PLAYERS.put(username, toReturn);
			return toReturn;
		}
	}
	
	private SnowballPlayer(String username) {
		this.username = username;
	}
	
	public void setCustomSkin(String url) {
		if(customSkinUrl == null || !customSkinUrl.equals(url)) {
			downloadCustomSkin(username, url);
		}
		customSkinUrl = url;
	}
	
	private void downloadCustomSkin(String theUsername, String url) {
		final ResourceLocation resourceLocation = new ResourceLocation(Constants.MINECRAFT_KEY,"skins/" + theUsername + ".png");
		TextureManager textureManager = Minecraft.getMinecraft().getTextureManager();

		IImageBuffer iImageBuffer = new IImageBuffer() {

			@Override
			public BufferedImage parseUserSkin(BufferedImage image) {
				return image;
			}

			@Override
			public void skinAvailable() {
				customSkin = resourceLocation;
			}
		};

		ThreadDownloadImageData threadDownloadImageData = new ThreadDownloadImageData((File) null, url, (ResourceLocation) null, iImageBuffer);
		textureManager.loadTexture(resourceLocation, threadDownloadImageData);
	}

	public void resetCustomSkin() {
		customSkin = null;
	}
	
	public ResourceLocation getCustomSkin() {
		return customSkin;
	}
	
	public String getCustomUsername() {
		return username;
	}
	
	public void setCustomUsername(String username) {
		this.username = username;
	}
	
	public void setCosmetics(List<EnumCosmetic> cosmetics) {
		Cosmetic.setCosmetic(username, cosmetics);
	}

}
