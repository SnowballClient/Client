package net.ilexiconn.llibrary.client.gui.element;

import net.ilexiconn.llibrary.LLibrary;




public class LabelElement<T extends IElementGUI> extends Element<T> {
    private String text;

    public LabelElement(T handler, String text, float posX, float posY) {
        super(handler, posX, posY, 0, 0);
        this.text = text;
    }

    @Override
    public void render(float mouseX, float mouseY, float partialTicks) {
        this.gui.getFontRenderer().drawString(this.text, this.getPosX(), this.getPosY(), LLibrary.CONFIG.getTextColor(), false);
    }
}

