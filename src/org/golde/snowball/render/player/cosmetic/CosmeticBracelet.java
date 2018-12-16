package org.golde.snowball.render.player.cosmetic;

import org.golde.snowball.render.player.cosmetic.Cosmetic.ModelCosmeticBase;
import org.golde.snowball.shared.enums.EnumCosmetic;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;

public class CosmeticBracelet extends Cosmetic {

	private static final ResourceLocation TEXTURE = new ResourceLocation("snowball/textures/entity/bracelet.png");
	private static ModelCosmeticBase MODEL;
	
	public CosmeticBracelet(RenderPlayer renderPlayer) {
		super(renderPlayer);
		MODEL = new ModelBracelet();
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
		return EnumCosmetic.BRACELET;
	}
	
	private class ModelBracelet extends ModelCosmeticBase {

		public ModelRenderer bipedFrontBracelet;
		public ModelRenderer bipedLeftBracelet;
		public ModelRenderer bipedBackBracelet;
		public ModelRenderer bipedRightBracelet;
		
		public ModelBracelet() {
			bipedFrontBracelet = new ModelRenderer(model, 0, 0);
			bipedFrontBracelet.addBox(4F, 9F, -3F, 4, 1, 1);
			bipedLeftBracelet = new ModelRenderer(model, 10, 0);
			bipedLeftBracelet.addBox(3F, 9F, -3F, 1, 1, 6);
			bipedBackBracelet = new ModelRenderer(model, 0, 2);
			bipedBackBracelet.addBox(4F, 9F, 2F, 4, 1, 1);
			bipedBackBracelet.setRotationPoint(0F, 0F, 0F);
			bipedRightBracelet = new ModelRenderer(model, 0, 4);
			bipedRightBracelet.addBox(8F, 9F, -3F, 1, 1, 6);
			bipedRightBracelet.setRotationPoint(0F, 0F, 0F);
		}
		
		@Override
		public void render(EntityPlayer p, float scale) {
			bipedFrontBracelet.rotateAngleY = model.bipedLeftArm.rotateAngleY;
			bipedFrontBracelet.rotateAngleX = model.bipedLeftArm.rotateAngleX;
			bipedFrontBracelet.rotationPointX = 0.0F;
			bipedFrontBracelet.rotationPointY = 0.0F;
			bipedFrontBracelet.render(scale);
			bipedLeftBracelet.rotateAngleY = model.bipedLeftArm.rotateAngleY;
			bipedLeftBracelet.rotateAngleX = model.bipedLeftArm.rotateAngleX;
			bipedLeftBracelet.rotationPointX = 0.0F;
			bipedLeftBracelet.rotationPointY = 0.0F;
			bipedLeftBracelet.render(scale);
			bipedBackBracelet.rotateAngleY = model.bipedLeftArm.rotateAngleY;
			bipedBackBracelet.rotateAngleX = model.bipedLeftArm.rotateAngleX;
			bipedBackBracelet.rotationPointX = 0.0F;
			bipedBackBracelet.rotationPointY = 0.0F;
			bipedBackBracelet.render(scale);
			bipedRightBracelet.rotateAngleY = model.bipedLeftArm.rotateAngleY;
			bipedRightBracelet.rotateAngleX = model.bipedLeftArm.rotateAngleX;
			bipedRightBracelet.rotationPointX = 0.0F;
			bipedRightBracelet.rotationPointY = 0.0F;
			bipedRightBracelet.render(scale);
		}
		
	}

}
