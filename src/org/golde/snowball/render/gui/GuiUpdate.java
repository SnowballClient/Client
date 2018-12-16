package org.golde.snowball.render.gui;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.golde.snowball.Snowball;

import com.google.common.collect.Lists;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiOptionButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiYesNoCallback;
import net.minecraft.client.resources.I18n;

public class GuiUpdate extends GuiScreen
{

	public static boolean HAS_ALREADY_DISPLAYED = false;
	private GuiScreen pastScreen;
	
	public GuiUpdate(GuiScreen pastScreen) {
		this.pastScreen = pastScreen;
	}
    
    public void initGui()
    {
        this.buttonList.add(new GuiOptionButton(0, this.width / 2 - 155, this.height / 6 + 96, "Continue Playing"));
        this.buttonList.add(new GuiOptionButton(1, this.width / 2 - 155 + 160, this.height / 6 + 96, "Open the Changelog!"));

    }

    /**
     * Called by the controls from the buttonList when activated. (Mouse pressed for buttons)
     */
    protected void actionPerformed(GuiButton button) throws IOException
    {
        if(button.id != 0) {
        	try {
				openWebLink(new URI("https://github.com/SnowballClient/files/blob/master/CHANGELOG.md"));
			} 
        	catch (URISyntaxException e) {
				e.printStackTrace();
			}
        }
        HAS_ALREADY_DISPLAYED = true;
        Minecraft.getMinecraft().displayGuiScreen(pastScreen);
    }

    /**
     * Draws the screen and all the components in it.
     */
    public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {
        this.drawDefaultBackground();
        this.drawCenteredString(this.fontRendererObj, "Hey hey, an update is available!", this.width / 2, 70, 16777215);
        this.drawCenteredString(this.fontRendererObj, "Version " + Snowball.instance.getUpdateResults().getVersion() +  " is ready to download.", this.width / 2, 90, 16777215);
        int i = 90;

        super.drawScreen(mouseX, mouseY, partialTicks);
    }

}

