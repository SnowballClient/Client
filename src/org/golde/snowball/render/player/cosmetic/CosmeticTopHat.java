package org.golde.snowball.render.player.cosmetic;

import org.golde.snowball.shared.enums.EnumCosmetic;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;

public class CosmeticTopHat extends Cosmetic {

	private static final ResourceLocation TEXTURE = new ResourceLocation("snowball/textures/entity/hat.png");
	private static ModelCosmeticBase MODEL;
	
	public CosmeticTopHat(RenderPlayer renderPlayer) {
		super(renderPlayer);
		MODEL = new ModelTopHat();
	}

	@Override
	public ResourceLocation getTexture() {
		return TEXTURE;
	}

	@Override
	public ModelCosmeticBase getModel() {
		return MODEL;
	}
	
	@Override
	public EnumCosmetic getEnum() {
		return EnumCosmetic.TOP_HAT;
	}
	
	private class ModelTopHat extends ModelCosmeticBase {
		public ModelRenderer bipedBottomHat;
		public ModelRenderer bipedTopHat;

		public ModelTopHat() {
			bipedBottomHat = new ModelRenderer(model, 0, 0);
			bipedBottomHat.addBox(-5.5F, -9F, -5.5F, 11, 2, 11);
			bipedTopHat = new ModelRenderer(model, 0, 13);
			bipedTopHat.addBox(-3.5F, -17F, -3.5F, 7, 8, 7);
		}

		@Override
		public void render(EntityPlayer p, float scale) {
			bipedBottomHat.rotateAngleY = model.bipedHead.rotateAngleY;
			bipedBottomHat.rotateAngleX = model.bipedHead.rotateAngleX;
			bipedBottomHat.rotationPointX = 0.0F;
			bipedBottomHat.rotationPointY = 0.0F;
			bipedBottomHat.render(scale);
			bipedTopHat.rotateAngleY = model.bipedHead.rotateAngleY;
			bipedTopHat.rotateAngleX = model.bipedHead.rotateAngleX;
			bipedTopHat.rotationPointX = 0.0F;
			bipedTopHat.rotationPointY = 0.0F;
			bipedTopHat.render(scale);
		}
	}

}
