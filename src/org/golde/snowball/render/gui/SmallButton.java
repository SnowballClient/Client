package org.golde.snowball.render.gui;

import java.util.Arrays;
import java.util.List;

import org.golde.snowball.shared.Constants;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiButtonLanguage;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.config.GuiUtils;

public class SmallButton extends EnhancedButton {

	private final ResourceLocation texture;
	private String floatingText = null;
	

	public SmallButton(GuiScreen guiScreenParent, int buttonId, int x, int y, String img) {
		super(guiScreenParent, buttonId, x, y, 20, 20, "");
		if(img == null || img.isEmpty()) {
			texture = new ResourceLocation("missingno");
		}
		else {
			texture = new ResourceLocation("snowball/textures/gui/" +img + ".png");
		}
	}

	@Override
	public void draw(Minecraft mc, int mouseX, int mouseY, float p_191745_4_)
	{
		super.oldDraw(mc, mouseX, mouseY, p_191745_4_);

		if(this.visible) {
			mc.getTextureManager().bindTexture(texture);
			this.drawModalRectWithCustomSizedTexture(this.xPosition + 3, this.yPosition + 3, 0.0F, 0.0F, 14, 14, 14, 14);
		}
		
		doHoverText(mc, mouseX, mouseY);
	}

}
