package net.ilexiconn.llibrary.client.gui.element;

import net.minecraft.util.ResourceLocation;




public class TextureElement<T extends IElementGUI> extends Element<T> {
    private ResourceLocation texture;
    private int u;
    private int v;

    public TextureElement(T gui, ResourceLocation texture, float posX, float posY, int u, int v, int width, int height) {
        super(gui, posX, posY, width, height);
        this.texture = texture;
        this.u = u;
        this.v = v;
    }

    @Override
    public void render(float mouseX, float mouseY, float partialTicks) {
        this.gui.getTextureManager().bindTexture(this.texture);
        this.gui.drawTexturedModalRect(this.getPosX(), this.getPosY(), this.u, this.v, this.getWidth(), this.getHeight());
    }
}
