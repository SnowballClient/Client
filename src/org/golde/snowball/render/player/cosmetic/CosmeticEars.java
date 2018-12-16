package org.golde.snowball.render.player.cosmetic;

import org.golde.snowball.render.player.cosmetic.Cosmetic.ModelCosmeticBase;
import org.golde.snowball.shared.enums.EnumCosmetic;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;

public class CosmeticEars extends Cosmetic {

	private static final ResourceLocation TEXTURE = new ResourceLocation("snowball/textures/entity/ears.png");
	private static ModelCosmeticBase MODEL;
	
	public CosmeticEars(RenderPlayer renderPlayer) {
		super(renderPlayer);
		MODEL = new ModelEars();
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
		return EnumCosmetic.EARS;
	}
	
	private class ModelEars extends ModelCosmeticBase {

		public ModelRenderer bipedEars2;
		
		public ModelEars() {
			bipedEars2 = new ModelRenderer(model);
			bipedEars2.addBox(-3F, -6F, -1F, 6, 6, 1);
		}
		
		@Override
		public void render(EntityPlayer p, float scale) {
			bipedEars2.rotateAngleY = model.bipedHead.rotateAngleY;
			bipedEars2.rotateAngleX = model.bipedHead.rotateAngleX;
			bipedEars2.rotationPointX = 0.0F;
			bipedEars2.rotationPointY = 0.0F;
			bipedEars2.render(scale);
		}
		
	}

}
