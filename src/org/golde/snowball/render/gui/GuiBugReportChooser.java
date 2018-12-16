package org.golde.snowball.render.gui;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import net.ilexiconn.qubble.client.gui.QubbleGUI;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;

public class GuiBugReportChooser extends GuiScreen
{

	private final GuiScreen lastScreen;

	public GuiBugReportChooser(GuiScreen p_i1046_1_)
	{
		this.lastScreen = p_i1046_1_;
	}

	/**
	 * Adds the buttons (and other controls) to the screen in question. Called when the GUI is displayed and when the
	 * window resizes, the buttonList is cleared beforehand.
	 */
	public void initGui()
	{

		this.buttonList.add(new GuiButton(0, this.width / 2 - 155, this.height / 6 + 48 - 6, 150, 20, "Bug in the Client"));
		this.buttonList.add(new GuiButton(1, this.width / 2 + 5, this.height / 6 + 48 - 6, 150, 20, "Bug in the SpigotAPI"));
		this.buttonList.add(new GuiButton(2, this.width / 2 - 155, this.height / 6 + 72 - 6, 150, 20, "Bug in the DiscordBot"));
		this.buttonList.add(new GuiButton(3, this.width / 2 + 5, this.height / 6 + 72 - 6, 150, 20, "Bug in the Installer"));
		//        this.buttonList.add(new GuiButton(4, this.width / 2 - 155, this.height / 6 + 96 - 6, 150, 20, "Bug in the Installer"));
		//        this.buttonList.add(new GuiButton(5, this.width / 2 + 5, this.height / 6 + 96 - 6, 150, 20, I18n.format("button6")));
		//        this.buttonList.add(new GuiButton(6, this.width / 2 - 155, this.height / 6 + 120 - 6, 150, 20, I18n.format("button7")));
		//        this.buttonList.add(new GuiButton(7, this.width / 2 + 5, this.height / 6 + 120 - 6, 150, 20, I18n.format("button8")));
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
			else {
				try {
					openWebLink(getUri(button.id));
				} 
				catch (URISyntaxException e) {
					e.printStackTrace();
				}
			}

		}
	}

	private URI getUri(int btn) throws URISyntaxException {
		String url = "https://github.com/SnowballClient/{repo_name}/issues/new";
		//url = url.replace("{repo_name}", "");
		
		if(btn == 0) {
			url = url.replace("{repo_name}", "Client");
		} 
		else if(btn == 1) {
			url = url.replace("{repo_name}", "SpigotPluginAPI");
		}
		else if(btn == 2) {
			url = url.replace("{repo_name}", "DiscordBot");
		}
		else if(btn == 3) {
			url = url.replace("{repo_name}", "Installer");
		}
		
		return new URI(url);
	}

	/**
	 * Draws the screen and all the components in it.
	 */
	public void drawScreen(int mouseX, int mouseY, float partialTicks)
	{
		this.drawDefaultBackground();
		this.drawCenteredString(this.fontRendererObj, "Submit a bug report!", this.width / 2, 15, 16777215);
		super.drawScreen(mouseX, mouseY, partialTicks);
	}
}