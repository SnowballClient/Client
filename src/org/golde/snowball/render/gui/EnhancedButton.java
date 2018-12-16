package org.golde.snowball.render.gui;

import java.util.Arrays;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;

public class EnhancedButton extends GuiButton {

	protected final GuiScreen guiScreenParent;
	protected String[] floatingText = null;
	
	public EnhancedButton(GuiScreen guiScreenParent, int buttonId, int x, int y, String buttonText) {
		this(guiScreenParent, buttonId, x, y, 200, 20, buttonText);
	}

	public EnhancedButton(GuiScreen guiScreenParent, int buttonId, int x, int y, int widthIn, int heightIn, String buttonText) {
		super(buttonId, x, y, widthIn, heightIn, buttonText);
		this.guiScreenParent = guiScreenParent;
	}
	
	public EnhancedButton setFloatingText(String[] text) {
		this.floatingText = text;
		return this;
	}
	
	public EnhancedButton setFloatingText(String text) {
		this.floatingText = new String[] {text};
		return this;
	}
	
	public void oldDraw(Minecraft mc, int mouseX, int mouseY, float particleTicks) {
		super.draw(mc, mouseX, mouseY, particleTicks);
	}
	
	@Override
	public void draw(Minecraft mc, int mouseX, int mouseY, float particleTicks) {
		
		super.draw(mc, mouseX, mouseY, particleTicks);
		doHoverText(mc, mouseX, mouseY);
		
	}
	
	public final void doHoverText(Minecraft mc, int mouseX, int mouseY) {
		if(this.visible && isMouseOver() && floatingText != null && (floatingText.length > 0)) {
			drawHoveringText(mc, Arrays.asList(floatingText), mouseX, mouseY);
		}
	}

	//Copied from GuiScreen.java (Modified to fix lighting errors)
	public final void drawHoveringText(Minecraft mc, List<String> textLines, int x, int y)
	{
		if (!textLines.isEmpty())
		{
			GlStateManager.disableRescaleNormal();
			GlStateManager.disableDepth();
			int i = 0;

			for (String s : textLines)
			{
				int j = mc.fontRendererObj.getStringWidth(s);

				if (j > i)
				{
					i = j;
				}
			}

			int l1 = x + 12;
			int i2 = y - 12;
			int k = 8;

			if (textLines.size() > 1)
			{
				k += 2 + (textLines.size() - 1) * 10;
			}

			if (l1 + i > guiScreenParent.width)
			{
				l1 -= 28 + i;
			}

			if (i2 + k + 6 > guiScreenParent.height)
			{
				i2 = guiScreenParent.height - k - 6;
			}

			this.zLevel = 300.0F;
			int l = -267386864;
			this.drawGradientRect(l1 - 3, i2 - 4, l1 + i + 3, i2 - 3, -267386864, -267386864);
			this.drawGradientRect(l1 - 3, i2 + k + 3, l1 + i + 3, i2 + k + 4, -267386864, -267386864);
			this.drawGradientRect(l1 - 3, i2 - 3, l1 + i + 3, i2 + k + 3, -267386864, -267386864);
			this.drawGradientRect(l1 - 4, i2 - 3, l1 - 3, i2 + k + 3, -267386864, -267386864);
			this.drawGradientRect(l1 + i + 3, i2 - 3, l1 + i + 4, i2 + k + 3, -267386864, -267386864);
			int i1 = 1347420415;
			int j1 = 1344798847;
			this.drawGradientRect(l1 - 3, i2 - 3 + 1, l1 - 3 + 1, i2 + k + 3 - 1, 1347420415, 1344798847);
			this.drawGradientRect(l1 + i + 2, i2 - 3 + 1, l1 + i + 3, i2 + k + 3 - 1, 1347420415, 1344798847);
			this.drawGradientRect(l1 - 3, i2 - 3, l1 + i + 3, i2 - 3 + 1, 1347420415, 1347420415);
			this.drawGradientRect(l1 - 3, i2 + k + 2, l1 + i + 3, i2 + k + 3, 1344798847, 1344798847);

			for (int k1 = 0; k1 < textLines.size(); ++k1)
			{
				String s1 = textLines.get(k1);
				mc.fontRendererObj.drawStringWithShadow(s1, (float)l1, (float)i2, -1);

				if (k1 == 0)
				{
					i2 += 2;
				}

				i2 += 10;
			}

			this.zLevel = 0.0F;
			GlStateManager.enableDepth();
			GlStateManager.enableRescaleNormal();
		}
	}
	
	public EnhancedButton setEnabled(boolean enabled) {
		this.enabled = enabled;
		return this;
	}

}
