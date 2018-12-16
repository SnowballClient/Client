package org.golde.snowball.render.gui;

import java.io.IOException;

import net.ilexiconn.qubble.client.gui.QubbleGUI;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiControls;
import net.minecraft.client.gui.GuiCustomizeSkin;
import net.minecraft.client.gui.GuiLanguage;
import net.minecraft.client.gui.GuiLockIconButton;
import net.minecraft.client.gui.GuiOptionButton;
import net.minecraft.client.gui.GuiOptionSlider;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiScreenOptionsSounds;
import net.minecraft.client.gui.GuiScreenResourcePacks;
import net.minecraft.client.gui.GuiSnooper;
import net.minecraft.client.gui.GuiVideoSettings;
import net.minecraft.client.gui.GuiYesNo;
import net.minecraft.client.gui.ScreenChatOptions;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.EnumDifficulty;

public class GuiClientMainMenuSettings extends GuiScreen
{

    private final GuiScreen lastScreen;

    public GuiClientMainMenuSettings(GuiScreen p_i1046_1_)
    {
        this.lastScreen = p_i1046_1_;
    }

    /**
     * Adds the buttons (and other controls) to the screen in question. Called when the GUI is displayed and when the
     * window resizes, the buttonList is cleared beforehand.
     */
    public void initGui()
    {
        
        this.buttonList.add(new EnhancedButton(this, 0, this.width / 2 - 155, this.height / 6 + 48 - 6, 150, 20, "Submit Bug Report"));
        this.buttonList.add(new EnhancedButton(this, 1, this.width / 2 + 5, this.height / 6 + 48 - 6, 150, 20, "Model Editor"));
        this.buttonList.add(new EnhancedButton(this, 2, this.width / 2 - 155, this.height / 6 + 72 - 6, 150, 20, "Credits"));
        this.buttonList.add(new EnhancedButton(this, 3, this.width / 2 + 5, this.height / 6 + 72 - 6, 150, 20, "Secret Developer Settings").setEnabled(false));
//        this.buttonList.add(new EnhancedButton(this, 4, this.width / 2 - 155, this.height / 6 + 96 - 6, 150, 20, I18n.format("button5")));
//        this.buttonList.add(new EnhancedButton(this, 5, this.width / 2 + 5, this.height / 6 + 96 - 6, 150, 20, I18n.format("button6")));
//        this.buttonList.add(new EnhancedButton(this, 6, this.width / 2 - 155, this.height / 6 + 120 - 6, 150, 20, I18n.format("button7")));
//        this.buttonList.add(new EnhancedButton(this, 7, this.width / 2 + 5, this.height / 6 + 120 - 6, 150, 20, "button7"));
        this.buttonList.add(new GuiButton(200, this.width / 2 - 100, this.height / 6 + 168, I18n.format("gui.done")));
    }

    /**
     * Called by the controls from the buttonList when activated. (Mouse pressed for buttons)
     */
    protected void actionPerformed(GuiButton button) throws IOException
    {
        if (button.enabled)
        {
           if(button.id == 200) {
        	   this.mc.displayGuiScreen(this.lastScreen);
           }
           else if(button.id == 0) {
        	   mc.displayGuiScreen(new GuiBugReportChooser(this));
           }
           else if(button.id == 1) {
        	   mc.displayGuiScreen(new QubbleGUI(this));
           }
           else if(button.id == 2) {
        	   mc.displayGuiScreen(new GuiCredits(this));
           }
           
           else if(button.id == 3) {
        	   mc.displayGuiScreen(new GuiChangelogTest());
           }
           
        }
    }

    /**
     * Draws the screen and all the components in it.
     */
    public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {
        this.drawDefaultBackground();
        this.drawCenteredString(this.fontRendererObj, "Snowball Settings & Utilities", this.width / 2, 15, 16777215);
        super.drawScreen(mouseX, mouseY, partialTicks);
    }
}
