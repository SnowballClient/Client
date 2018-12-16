package org.golde.snowball.util;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;

public class RenderUtils {

	public static void drawPic(final double x, final double y, final double h, final double w, final ResourceLocation pic) {
        final Tessellator tessellator = Tessellator.getInstance();
        final BufferBuilder vertexbuffer = tessellator.getBuffer();
        Minecraft.getMinecraft().getTextureManager().bindTexture(pic);
        GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
        vertexbuffer.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
        vertexbuffer.pos(x, y + w, 0.0).tex(0.0, 1.0).color(255, 255, 255, 255).endVertex();
        vertexbuffer.pos(x + h, y + w, 0.0).tex(1.0, 1.0).color(255, 255, 255, 255).endVertex();
        vertexbuffer.pos(x + h, y, 0.0).tex(1.0, 0.0).color(255, 255, 255, 255).endVertex();
        vertexbuffer.pos(x, y, 0.0).tex(0.0, 0.0).color(255, 255, 255, 255).endVertex();
        tessellator.draw();
    }
	
}
