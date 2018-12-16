package org.golde.snowball.render.player.cosmetic;

import org.golde.snowball.render.player.cosmetic.Cosmetic.ModelCosmeticBase;
import org.golde.snowball.shared.enums.EnumCosmetic;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;

public class CosmeticSunglasses extends Cosmetic{
	
	private static final ResourceLocation TEXTURE = new ResourceLocation("snowball/textures/entity/sunglasses.png");
	private static ModelCosmeticBase MODEL;
	
	public CosmeticSunglasses(RenderPlayer renderPlayer) {
		super(renderPlayer);
		MODEL = new ModelSunglasses();
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
		return EnumCosmetic.SUNGLASSES;
	}
	
	private class ModelSunglasses extends ModelCosmeticBase {

		public ModelRenderer SunglassesFront;
		public ModelRenderer SunglassesFront2;
		public ModelRenderer SunglassesBridge;
		public ModelRenderer RightSunglasses;
		public ModelRenderer LeftSunglasses;
		public ModelRenderer RightSunglassesBridge;
		public ModelRenderer LeftSunglassesBridge;
		
		public ModelSunglasses() {
			SunglassesFront = new ModelRenderer(model, 0, 0);
			SunglassesFront.addBox(-3F, -4F, -5F, 2, 2, 1);
			SunglassesFront2 = new ModelRenderer(model, 6, 0);
			SunglassesFront2.addBox(1F, -4F, -5F, 2, 2, 1);
			SunglassesBridge = new ModelRenderer(model, 0, 4);
			SunglassesBridge.addBox(-1F, -4F, -5F, 2, 1, 1);
			RightSunglasses = new ModelRenderer(model, 0, 6);
			RightSunglasses.addBox(4.0F, -4.0F, -4.0F, 1, 1, 4);
			LeftSunglasses = new ModelRenderer(model, 12, 0);
			LeftSunglasses.addBox(-5.0F, -4.0F, -4.0F, 1, 1, 4);
			LeftSunglassesBridge = new ModelRenderer(model, 6, 5);
			LeftSunglassesBridge.addBox(-5.0F, -4.0F, -5.0F, 2, 1, 1);
			RightSunglassesBridge = new ModelRenderer(model, 6, 7);
			RightSunglassesBridge.addBox(3.0F, -4.0F, -5.0F, 2, 1, 1);
		}
		
		@Override
		public void render(EntityPlayer p, float scale) {
			SunglassesFront.rotateAngleY = model.bipedHead.rotateAngleY;
			SunglassesFront.rotateAngleX = model.bipedHead.rotateAngleX;
			SunglassesFront.render(scale);
			SunglassesFront2.rotateAngleY = model.bipedHead.rotateAngleY;
			SunglassesFront2.rotateAngleX = model.bipedHead.rotateAngleX;
			SunglassesFront2.render(scale);
			SunglassesBridge.rotateAngleY = model.bipedHead.rotateAngleY;
			SunglassesBridge.rotateAngleX = model.bipedHead.rotateAngleX;
			SunglassesBridge.render(scale);
			RightSunglasses.rotateAngleY = model.bipedHead.rotateAngleY;
			RightSunglasses.rotateAngleX = model.bipedHead.rotateAngleX;
			RightSunglasses.render(scale);
			LeftSunglasses.rotateAngleY = model.bipedHead.rotateAngleY;
			LeftSunglasses.rotateAngleX = model.bipedHead.rotateAngleX;
			LeftSunglasses.render(scale);
			LeftSunglassesBridge.rotateAngleY = model.bipedHead.rotateAngleY;
			LeftSunglassesBridge.rotateAngleX = model.bipedHead.rotateAngleX;
			LeftSunglassesBridge.render(scale);
			RightSunglassesBridge.rotateAngleY = model.bipedHead.rotateAngleY;
			RightSunglassesBridge.rotateAngleX = model.bipedHead.rotateAngleX;
			RightSunglassesBridge.render(scale);
		}
		
	}
}
