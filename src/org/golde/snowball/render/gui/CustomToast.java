package org.golde.snowball.render.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.toasts.GuiToast;
import net.minecraft.client.gui.toasts.IToast;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;

public class CustomToast implements IToast {

	private final String title;
	private final String desc;
	private final ItemStack icon;

	private long initialCounter;
	private boolean hasSetInitialCounter;

	public CustomToast(String title, String desc) {
		this(title, desc, null);
	}

	public CustomToast(String title, String desc, ItemStack icon) {
		this.title = title;
		this.desc = desc;
		this.icon = icon;
	}
	
	public void show() {
		Minecraft.getMinecraft().func_193033_an().func_192988_a(this);
	}

	@Override
	public Visibility func_193653_a(GuiToast guiToast, long p_193653_2_) {
		
		if (this.hasSetInitialCounter)
		{
			this.initialCounter = p_193653_2_;
			this.hasSetInitialCounter = false;
		}


		guiToast.func_192989_b().getTextureManager().bindTexture(field_193654_a);
		GlStateManager.color(1.0F, 1.0F, 1.0F);
		guiToast.drawTexturedModalRect(0, 0, 0, 32, 160, 32);
		guiToast.func_192989_b().fontRendererObj.drawString(title, 30, 7, -11534256);
		guiToast.func_192989_b().fontRendererObj.drawString(desc, 30, 18, -16777216);
		RenderHelper.enableGUIStandardItemLighting();
		if(icon != null && icon.getItem() != null) {
			guiToast.func_192989_b().getRenderItem().renderItemAndEffectIntoGUI((EntityLivingBase)null, icon, 8, 8);
		}
		
		return p_193653_2_ - this.initialCounter >= 5000L ? IToast.Visibility.HIDE : IToast.Visibility.SHOW;

	}

}
