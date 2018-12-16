package net.ilexiconn.llibrary.client.gui.element;

import net.ilexiconn.llibrary.LLibrary;
import net.ilexiconn.llibrary.client.gui.element.color.ColorScheme;
import net.minecraft.client.gui.FontRenderer;



import java.util.function.Function;


public class ButtonElement<T extends IElementGUI> extends Element<T> {
    public static final ColorScheme CLOSE = ColorScheme.create(() -> LLibrary.CONFIG.getDarkAccentColor(), () -> 0xFFE04747);

    protected String text;
    protected Function<ButtonElement<T>, Boolean> function;

    public ButtonElement(T gui, String text, float posX, float posY, int width, int height, Function<ButtonElement<T>, Boolean> function) {
        super(gui, posX, posY, width, height);
        this.text = text;
        this.function = function;
    }

    @Override
    public void render(float mouseX, float mouseY, float partialTicks) {
        this.drawRectangle(this.getPosX(), this.getPosY(), this.getWidth(), this.getHeight(), this.isEnabled() && this.isSelected(mouseX, mouseY) ? this.getColorScheme().getSecondaryColor() : this.getColorScheme().getPrimaryColor());
        FontRenderer fontRenderer = this.gui.getFontRenderer();
        fontRenderer.drawString(this.text, this.getPosX() + (this.getWidth() / 2) - (fontRenderer.getStringWidth(this.text) / 2), this.getPosY() + (this.getHeight() / 2) - (fontRenderer.FONT_HEIGHT / 2), LLibrary.CONFIG.getTextColor(), false);
    }

    @Override
    public boolean mouseClicked(float mouseX, float mouseY, int button) {
        if (this.isSelected(mouseX, mouseY)) {
            if (this.function.apply(this)) {
                this.gui.playClickSound();
            }
            return true;
        } else {
            return false;
        }
    }
}
