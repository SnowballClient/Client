package org.golde.snowball.render.player.cosmetic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.golde.snowball.shared.enums.EnumCosmetic;

import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;

public abstract class Cosmetic implements LayerRenderer<AbstractClientPlayer>{

	private static HashMap<String, List<EnumCosmetic>> COSMETICS_PER_PLAYER = new HashMap<String, List<EnumCosmetic>>();
	private static List<Cosmetic> COSMETICS = new ArrayList<Cosmetic>();

	private final RenderPlayer renderPlayer;

	public Cosmetic(RenderPlayer renderPlayer) {
		this.renderPlayer = renderPlayer;
		COSMETICS.add(this);
	}

//	public static final void addCosmetic(String player, EnumCosmetic cosmetic) {
//		List<EnumCosmetic> cos = COSMETICS_PER_PLAYER.get(player);
//		if(!cos.contains(cosmetic)) {
//			cos.add(cosmetic);
//			COSMETICS_PER_PLAYER.put(player, cos);
//		}
//	}
//
//	public static final void removeCosmetic(String player, EnumCosmetic cosmetic) {
//		List<EnumCosmetic> cos = COSMETICS_PER_PLAYER.get(player);
//		if(cos.contains(cosmetic)) {
//			cos.remove(cosmetic);
//			COSMETICS_PER_PLAYER.put(player, cos);
//		}
//	}
	
	public static final void setCosmetic(String player, List<EnumCosmetic> cosmetics) {
		COSMETICS_PER_PLAYER.put(player, cosmetics);
	}

	@Override
	public void doRenderLayer(AbstractClientPlayer entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {

		String name = entitylivingbaseIn.getNameClear();

		if(COSMETICS_PER_PLAYER.get(name) == null) {
			COSMETICS_PER_PLAYER.put(name, new ArrayList<EnumCosmetic>() {});
		}

		if(COSMETICS_PER_PLAYER.containsKey(name)) {
			List<EnumCosmetic> playerCosmetics = COSMETICS_PER_PLAYER.get(name);
			for(Cosmetic cosmetic : COSMETICS) {
				if(playerCosmetics.contains(cosmetic.getEnum())) {
					cosmetic.render(entitylivingbaseIn, limbSwing, limbSwingAmount, partialTicks, ageInTicks, netHeadYaw, headPitch, scale);
				}
			}
		}
	}

	private void render(AbstractClientPlayer player, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
		GlStateManager.pushMatrix();
		renderPlayer.bindTexture(getTexture());
		getModel().render(player, scale);
		//getModel().render(player, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
		GlStateManager.popMatrix();
	}

	@Override
	public boolean shouldCombineTextures() { return false; }

	public abstract ResourceLocation getTexture();
	public abstract ModelCosmeticBase getModel();
	public abstract EnumCosmetic getEnum();

	public abstract class ModelCosmeticBase extends ModelBase {

		public ModelCosmeticBase() {

		}

		protected final ModelBiped model= renderPlayer.getMainModel();
		
		public abstract void render(EntityPlayer p, float scale);

	}

}
