package org.golde.snowball.render.gui;

import java.awt.Dimension;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Nullable;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiOptionButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiUtilRenderComponents;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;

public class GuiChangelogTest extends GuiScreen {

	private Info modInfo;

	private int listWidth;
	private List<String> textToDisplay = Arrays.asList(new String[] {
			TextFormatting.AQUA + "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.\n",
			"Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo. Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt. Neque porro quisquam est, qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit, sed quia non numquam eius modi tempora incidunt ut labore et dolore magnam aliquam quaerat voluptatem. Ut enim ad minima veniam, quis nostrum exercitationem ullam corporis suscipit laboriosam, nisi ut aliquid ex ea commodi consequatur? Quis autem vel eum iure reprehenderit qui in ea voluptate velit esse quam nihil molestiae consequatur, vel illum qui dolorem eum fugiat quo voluptas nulla pariatur?\n",
			"At vero eos et accusamus et iusto odio dignissimos ducimus qui blanditiis praesentium voluptatum deleniti atque corrupti quos dolores et quas molestias excepturi sint occaecati cupiditate non provident, similique sunt in culpa qui officia deserunt mollitia animi, id est laborum et dolorum fuga. Et harum quidem rerum facilis est et expedita distinctio. Nam libero tempore, cum soluta nobis est eligendi optio cumque nihil impedit quo minus id quod maxime placeat facere possimus, omnis voluptas assumenda est, omnis dolor repellendus. Temporibus autem quibusdam et aut officiis debitis aut rerum necessitatibus saepe eveniet ut et voluptates repudiandae sint et molestiae non recusandae. Itaque earum rerum hic tenetur a sapiente delectus, ut aut reiciendis voluptatibus maiores alias consequatur aut perferendis doloribus asperiores repellat."
	});

	@Override
	public void initGui() {
		for(String s : textToDisplay) {
			listWidth = Math.max(listWidth,fontRendererObj.getStringWidth(s) + 10);
		}
		listWidth = Math.min(listWidth, 150);

		modInfo = new Info(this.width - this.listWidth - 30, textToDisplay);

        this.buttonList.add(new GuiButton(200, this.width / 2 - 100, this.height - 28, "Ok Cool... Let me play the game now."));
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		drawDefaultBackground();
		modInfo.drawScreen(mouseX, mouseY, partialTicks);
		super.drawScreen(mouseX, mouseY, partialTicks);
	}

	@Override
	public void handleMouseInput() throws IOException {
		super.handleMouseInput();

		int mouseX = Mouse.getEventX() * this.width / this.mc.displayWidth;
		int mouseY = this.height - Mouse.getEventY() * this.height / this.mc.displayHeight - 1;
		modInfo.handleMouseInput(mouseX, mouseY);
	}
	
	@Override
	protected void actionPerformed(GuiButton button) throws IOException {
		if(button.id == 200) {
			mc.displayGuiScreen(null);
		}
	}


	private class Info extends GuiScrollingList {
		@Nullable

		private List<ITextComponent> lines = null;

		public Info(int width, List<String> lines) {
			super(GuiChangelogTest.this.mc, width + 140, GuiChangelogTest.this.height, 52, GuiChangelogTest.this.height - 88 + 4, 20, 60, GuiChangelogTest.this.width, GuiChangelogTest.this.height);
			this.lines    = resizeContent(lines);

			this.setHeaderInfo(true, getHeaderHeight());
		}

		@Override protected int getSize() { return 0; }
		@Override protected void elementClicked(int index, boolean doubleClick) { }
		@Override protected boolean isSelected(int index) { return false; }
		@Override protected void drawBackground() {}
		@Override protected void drawSlot(int slotIdx, int entryRight, int slotTop, int slotBuffer, Tessellator tess) { }

		private List<ITextComponent> resizeContent(List<String> lines)
		{
			List<ITextComponent> ret = new ArrayList<ITextComponent>();
			for (String line : lines)
			{
				if (line == null)
				{
					ret.add(null);
					continue;
				}

				ITextComponent chat = /*ForgeHooks.newChatWithLinks(line, false);*/ new TextComponentString(line);
				int maxTextLength = this.listWidth - 8;
				if (maxTextLength >= 0)
				{
					ret.addAll(GuiUtilRenderComponents.splitText(chat, maxTextLength, GuiChangelogTest.this.fontRendererObj, false, true));
				}
			}
			return ret;
		}

		private int getHeaderHeight()
		{
			int height = 0;
			height += (lines.size() * 10);
			if (height < this.bottom - this.top - 8) height = this.bottom - this.top - 8;
			return height;
		}


		@Override
		protected void drawHeader(int entryRight, int relativeY, Tessellator tess)
		{
			int top = relativeY;

			for (ITextComponent line : lines)
			{
				if (line != null)
				{
					GlStateManager.enableBlend();
					GuiChangelogTest.this.fontRendererObj.drawStringWithShadow(line.getFormattedText(), this.left + 4, top, 0xFFFFFF);
					GlStateManager.disableAlpha();
					GlStateManager.disableBlend();
				}
				top += 10;
			}
		}

		@Override
		protected void clickHeader(int x, int y)
		{
			int offset = y;
			if (offset <= 0)
				return;

			int lineIdx = offset / 10;
			if (lineIdx >= lines.size())
				return;

			ITextComponent line = lines.get(lineIdx);
			if (line != null)
			{
				int k = -4;
				for (ITextComponent part : line) {
					if (!(part instanceof TextComponentString))
						continue;
					k += GuiChangelogTest.this.fontRendererObj.getStringWidth(((TextComponentString)part).getText());
					if (k >= x)
					{
						GuiChangelogTest.this.handleComponentClick(part);
						break;
					}
				}
			}
		}
	}

}
