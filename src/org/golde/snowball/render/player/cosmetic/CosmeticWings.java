package org.golde.snowball.render.player.cosmetic;

import org.golde.snowball.render.player.cosmetic.Cosmetic.ModelCosmeticBase;
import org.golde.snowball.shared.enums.EnumCosmetic;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;

public class CosmeticWings extends Cosmetic {

	private static final ResourceLocation TEXTURE = new ResourceLocation("snowball/textures/entity/wings.png");
	private static ModelCosmeticBase MODEL;

	public CosmeticWings(RenderPlayer renderPlayer) {
		super(renderPlayer);
		MODEL = new ModelWings();
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
		return EnumCosmetic.WINGS;
	}

	private class ModelWings extends ModelCosmeticBase {

		ModelRenderer LeftWingPart1;
		ModelRenderer LeftWingPart2;
		ModelRenderer LeftWingPart3;
		ModelRenderer LeftWingPart4;
		ModelRenderer LeftWingPart5;
		ModelRenderer LeftWingPart6;
		ModelRenderer LeftWingPart7;
		ModelRenderer LeftWingPart8;
		ModelRenderer LeftWingPart0;
		ModelRenderer RightWingPart0;
		ModelRenderer RightWingPart1;
		ModelRenderer RightWingPart2;
		ModelRenderer RightWingPart3;
		ModelRenderer RightWingPart4;
		ModelRenderer RightWingPart5;
		ModelRenderer RightWingPart6;
		ModelRenderer RightWingPart7;
		ModelRenderer RightWingPart8;

		public ModelWings() {
			LeftWingPart1 = new ModelRenderer(model, 56, 0);
			LeftWingPart1.addBox(-1F, 1F, 3F, 1, 10, 1);
			LeftWingPart1.setRotationPoint(0F, 0F, 0F);
			LeftWingPart1.setTextureSize(64, 32);
			LeftWingPart1.mirror = true;
			setRotation(LeftWingPart1, 0F, 0.5007752F, 0.0174533F);
			LeftWingPart2 = new ModelRenderer(model, 50, 0);
			LeftWingPart2.addBox(-1F, 0F, 4F, 1, 10, 2);
			LeftWingPart2.setRotationPoint(0F, 0F, 0F);
			LeftWingPart2.setTextureSize(64, 32);
			LeftWingPart2.mirror = true;
			setRotation(LeftWingPart2, 0F, 0.5182285F, 0.0349066F);
			LeftWingPart3 = new ModelRenderer(model, 46, 0);
			LeftWingPart3.addBox(-1F, -1F, 6F, 1, 10, 1);
			LeftWingPart3.setRotationPoint(0F, 0F, 0F);
			LeftWingPart3.setTextureSize(64, 32);
			LeftWingPart3.mirror = true;
			setRotation(LeftWingPart3, 0F, 0.5356818F, 0.0523599F);
			LeftWingPart4 = new ModelRenderer(model, 38, 0);
			LeftWingPart4.addBox(-1F, -2F, 7F, 1, 10, 3);
			LeftWingPart4.setRotationPoint(0F, 0F, 0F);
			LeftWingPart4.setTextureSize(64, 32);
			LeftWingPart4.mirror = true;
			setRotation(LeftWingPart4, 0F, 0.5531351F, 0.0698132F);
			LeftWingPart5 = new ModelRenderer(model, 34, 0);
			LeftWingPart5.addBox(-1F, -1F, 10F, 1, 10, 1);
			LeftWingPart5.setRotationPoint(0F, 0F, 0F);
			LeftWingPart5.setTextureSize(64, 32);
			LeftWingPart5.mirror = true;
			setRotation(LeftWingPart5, 0F, 0.5531351F, 0.0523599F);
			LeftWingPart6 = new ModelRenderer(model, 30, 0);
			LeftWingPart6.addBox(-1F, 0F, 11F, 1, 10, 1);
			LeftWingPart6.setRotationPoint(0F, 0F, 0F);
			LeftWingPart6.setTextureSize(64, 32);
			LeftWingPart6.mirror = true;
			setRotation(LeftWingPart6, 0F, 0.5705884F, 0.0349066F);
			LeftWingPart7 = new ModelRenderer(model, 26, 0);
			LeftWingPart7.addBox(-1F, 1F, 12F, 1, 10, 1);
			LeftWingPart7.setRotationPoint(0F, 0F, 0F);
			LeftWingPart7.setTextureSize(64, 32);
			LeftWingPart7.mirror = true;
			setRotation(LeftWingPart7, 0F, 0.5880417F, 0.0174533F);
			LeftWingPart8 = new ModelRenderer(model, 22, 0);
			LeftWingPart8.addBox(-1F, 3F, 13F, 1, 10, 1);
			LeftWingPart8.setRotationPoint(0F, 0F, 0F);
			LeftWingPart8.setTextureSize(64, 32);
			LeftWingPart8.mirror = true;
			setRotation(LeftWingPart8, 0F, 0.5880417F, 0F);
			LeftWingPart0 = new ModelRenderer(model, 60, 0);
			LeftWingPart0.addBox(-1F, 2F, 2F, 1, 10, 1);
			LeftWingPart0.setRotationPoint(0F, 0F, 0F);
			LeftWingPart0.setTextureSize(64, 32);
			LeftWingPart0.mirror = true;
			setRotation(LeftWingPart0, 0F, 0.4833219F, 0F);
			RightWingPart0 = new ModelRenderer(model, 60, 21);
			RightWingPart0.addBox(0F, 2F, 2F, 1, 10, 1);
			RightWingPart0.setRotationPoint(0F, 0F, 0F);
			RightWingPart0.setTextureSize(64, 32);
			RightWingPart0.mirror = true;
			setRotation(RightWingPart0, 0F, -0.4833166F, 0F);
			RightWingPart1 = new ModelRenderer(model, 56, 21);
			RightWingPart1.addBox(0F, 1F, 3F, 1, 10, 1);
			RightWingPart1.setRotationPoint(0F, 0F, 0F);
			RightWingPart1.setTextureSize(64, 32);
			RightWingPart1.mirror = true;
			setRotation(RightWingPart1, 0F, -0.5007699F, -0.0174533F);
			RightWingPart2 = new ModelRenderer(model, 50, 20);
			RightWingPart2.addBox(0F, 0F, 4F, 1, 10, 2);
			RightWingPart2.setRotationPoint(0F, 0F, 0F);
			RightWingPart2.setTextureSize(64, 32);
			RightWingPart2.mirror = true;
			setRotation(RightWingPart2, 0F, -0.5182232F, -0.0349066F);
			RightWingPart3 = new ModelRenderer(model, 46, 21);
			RightWingPart3.addBox(0F, -1F, 6F, 1, 10, 1);
			RightWingPart3.setRotationPoint(0F, 0F, 0F);
			RightWingPart3.setTextureSize(64, 32);
			RightWingPart3.mirror = true;
			setRotation(RightWingPart3, 0.0174533F, -0.5356765F, -0.0523599F);
			RightWingPart4 = new ModelRenderer(model, 38, 19);
			RightWingPart4.addBox(0F, -2F, 7F, 1, 10, 3);
			RightWingPart4.setRotationPoint(0F, 0F, 0F);
			RightWingPart4.setTextureSize(64, 32);
			RightWingPart4.mirror = true;
			setRotation(RightWingPart4, 0.0174533F, -0.5531297F, -0.0698132F);
			RightWingPart5 = new ModelRenderer(model, 34, 21);
			RightWingPart5.addBox(0F, -1F, 10F, 1, 10, 1);
			RightWingPart5.setRotationPoint(0F, 0F, 0F);
			RightWingPart5.setTextureSize(64, 32);
			RightWingPart5.mirror = true;
			setRotation(RightWingPart5, 0.0174533F, -0.570583F, -0.0523599F);
			RightWingPart6 = new ModelRenderer(model, 30, 21);
			RightWingPart6.addBox(0F, 0F, 11F, 1, 10, 1);
			RightWingPart6.setRotationPoint(0F, 0F, 0F);
			RightWingPart6.setTextureSize(64, 32);
			RightWingPart6.mirror = true;
			setRotation(RightWingPart6, 0.0174533F, -0.5880363F, -0.0349066F);
			RightWingPart7 = new ModelRenderer(model, 26, 21);
			RightWingPart7.addBox(0F, 1F, 12F, 1, 10, 1);
			RightWingPart7.setRotationPoint(0F, 0F, 0F);
			RightWingPart7.setTextureSize(64, 32);
			RightWingPart7.mirror = true;
			setRotation(RightWingPart7, 0.0174533F, -0.6054896F, -0.0174533F);
			RightWingPart8 = new ModelRenderer(model, 22, 21);
			RightWingPart8.addBox(0F, 3F, 13F, 1, 10, 1);
			RightWingPart8.setRotationPoint(0F, 0F, 0F);
			RightWingPart8.setTextureSize(64, 32);
			RightWingPart8.mirror = true;
			setRotation(RightWingPart8, 0.0174533F, -0.6229429F, 0F);
		}

		private void setRotation(ModelRenderer model, float x, float y, float z) {
			model.rotateAngleX = x;
			model.rotateAngleY = y;
			model.rotateAngleZ = z;
		}

		@Override
		public void render(EntityPlayer p, float scale) {
			LeftWingPart1.render(scale);
			LeftWingPart2.render(scale);
			LeftWingPart3.render(scale);
			LeftWingPart4.render(scale);
			LeftWingPart5.render(scale);
			LeftWingPart6.render(scale);
			LeftWingPart7.render(scale);
			LeftWingPart8.render(scale);
			LeftWingPart0.render(scale);
			RightWingPart0.render(scale);
			RightWingPart1.render(scale);
			RightWingPart2.render(scale);
			RightWingPart3.render(scale);
			RightWingPart4.render(scale);
			RightWingPart5.render(scale);
			RightWingPart6.render(scale);
			RightWingPart7.render(scale);
			RightWingPart8.render(scale);
		}

	}

}
