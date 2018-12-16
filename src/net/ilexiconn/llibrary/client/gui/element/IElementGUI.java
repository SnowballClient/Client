package net.ilexiconn.llibrary.client.gui.element;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureManager;



import java.util.Collection;


public interface IElementGUI {
    void addElement(Element element);

    void addElements(Collection<Element> elements);

    void removeElement(Element element);

    void clearElements();

    void sendElementToBack(Element element);

    void sendElementToFront(Element element);

    boolean isElementOnTop(Element element);

    void playClickSound();

    FontRenderer getFontRenderer();

    TextureManager getTextureManager();

    void drawTexturedModalRect(int x, int y, int textureX, int textureY, int width, int height);

    void drawTexturedModalRect(float xCoord, float yCoord, int minU, int minV, int maxU, int maxV);

    void drawTexturedModalRect(int xCoord, int yCoord, TextureAtlasSprite textureSprite, int widthIn, int heightIn);

    int getHeight();

    int getWidth();
}
