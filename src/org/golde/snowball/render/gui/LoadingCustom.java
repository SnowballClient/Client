package org.golde.snowball.render.gui;

import java.util.function.Supplier;

import com.ibm.icu.impl.duration.impl.Utils;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.resources.I18n;

import static org.lwjgl.opengl.GL11.*;

public class LoadingCustom extends GuiScreen  {

	private final String title;
	private volatile String majorTaskMessage = "";
	private volatile Supplier<String> minorTaskMessageProvider = () -> "";
	private volatile int majorTaskNumber;
	private final int majorTaskCount;
	private volatile int minorTaskProgress;
	private volatile int minorTaskMaximum;

	private volatile boolean doneWorking = false;

	/**
	 * Creates a new GuiWDLSaveProgress.
	 *
	 * @param title The title.
	 * @param taskCount The total number of major tasks that there will be.
	 */
	public LoadingCustom(String title, int taskCount) {
		this.title = title;
		this.majorTaskCount = taskCount;
		this.majorTaskNumber = 0;
	}

	/**
	 * Starts a new major task with the given message.
	 */
	public void startMajorTask(String message, int minorTaskMaximum) {
		this.majorTaskMessage = message;
		this.majorTaskNumber++;

		this.minorTaskMessageProvider = () -> message;
		this.minorTaskProgress = 0;
		this.minorTaskMaximum = minorTaskMaximum;
	}

	/**
	 * Updates the progress on the current minor task.
	 *
	 * @param message
	 *            The message -- should be something like "saving chunk at x,z";
	 *            the current position and maximum and the percent are
	 *            automatically appended after it.
	 */
	public void setMinorTaskProgress(String message, int progress) {
		this.minorTaskMessageProvider = () -> message;
		this.minorTaskProgress = progress;
	}

	/**
	 * Updates the progress on the current minor task.
	 *
	 * @param messageProvider
	 *            Provides the message to be displayed.
	 */
	public void setMinorTaskProgress(Supplier<String> messageProvider, int progress) {
		this.minorTaskMessageProvider = messageProvider;
		this.minorTaskProgress = progress;
	}

	/**
	 * Updates the progress on the minor task.
	 */
	public void setMinorTaskProgress(int progress) {
		this.minorTaskProgress = progress;
	}

	/**
	 * Updates the number of minor tasks.
	 */
	public void setMinorTaskCount(int count) {
		this.minorTaskMaximum = count;
	}

	/**
	 * Sets the GUI as done working, meaning it will be closed next tick.
	 */
	public void setDoneWorking() {
		this.doneWorking = true;
	}
	
	public int getMinorTaskProgress() {
		return minorTaskProgress;
	}

	/**
	 * Draws the screen and all the components in it. Args : mouseX, mouseY,
	 * renderPartialTicks
	 */
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		this.drawDefaultBackground();

		if (this.doneWorking) {
			this.mc.displayGuiScreen((GuiScreen) null);
		} else {
			drawBorder(32, 32, 0, 0, height, width);

			String majorTaskInfo = majorTaskMessage;
			if (majorTaskCount > 1) {
				majorTaskInfo = I18n.format(
						"snowball.gui.downloadProgress.progressInfo", majorTaskMessage,
						majorTaskNumber, majorTaskCount);
			}
			String minorTaskInfo = minorTaskMessageProvider.get();
			if (minorTaskMaximum > 1) {
				minorTaskInfo = I18n.format(
						"snowball.gui.downloadProgress.progressInfo", minorTaskInfo,
						minorTaskProgress, minorTaskMaximum);
			}

			this.drawCenteredString(this.fontRendererObj, this.title,
					this.width / 2, 8, 0xFFFFFF);

			this.drawCenteredString(this.fontRendererObj,
					majorTaskInfo, this.width / 2, 100, 0xFFFFFF);

			if (minorTaskMaximum > 0) {
				this.drawProgressBar(110, 84, 89,
						(majorTaskNumber * minorTaskMaximum) + minorTaskProgress,
						(majorTaskCount + 1) * minorTaskMaximum);
			} else {
				this.drawProgressBar(110, 84, 89, majorTaskNumber,
						majorTaskCount);
			}

			this.drawCenteredString(this.fontRendererObj, minorTaskInfo,
					this.width / 2, 130, 0xFFFFFF);
			this.drawProgressBar(140, 64, 69, minorTaskProgress, minorTaskMaximum);

			super.drawScreen(mouseX, mouseY, partialTicks);
		}
	}

	/**
	 * Draws a progress bar on the screen. (A lot of things are always kept the
	 * same and thus aren't arguments, such as x-position being the center of
	 * the screen).
	 *
	 * @param y
	 *            Y-position of the progress bar.
	 * @param emptyV
	 *            The vertical coordinate of the empty part in the icon map
	 *            (icons.png)
	 * @param filledV
	 *            The vertical coordinate of the full part in the icon map
	 *            (icons.png)
	 * @param progress
	 *            The progress into the bar.
	 * @param maximum
	 *            The maximum value of progress.
	 */
	private void drawProgressBar(int y, int emptyV, int filledV,
			int progress, int maximum) {
		if (maximum == 0) {
			return;
		}

		this.mc.getTextureManager().bindTexture(Gui.ICONS);

		final int fullWidth = 182;
		final int currentWidth = (progress * fullWidth) / maximum;
		final int height = 5;

		final int x = (this.width / 2) - (fullWidth / 2);
		final int u = 0; //Texture position.

		drawTexturedModalRect(x, y, u, emptyV, fullWidth, height);
		drawTexturedModalRect(x, y, u, filledV, currentWidth, height);
	}
	
	private static void drawBorder(int topMargin, int bottomMargin, int top, int left, int bottom, int right) {
		GlStateManager.disableLighting();
		GlStateManager.disableFog();
		GlStateManager.disableDepth();
		byte padding = 4;

		Minecraft.getMinecraft().getTextureManager().bindTexture(Gui.OPTIONS_BACKGROUND);
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);

		float textureSize = 32.0F;

		Tessellator t = Tessellator.getInstance();
		BufferBuilder b = t.getBuffer();

		//Box code is GuiSlot.overlayBackground
		//Upper box
		int upperBoxEnd = top + topMargin;

		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		b.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
		b.pos(left, upperBoxEnd, 0.0D).tex(0.0D, upperBoxEnd
				/ textureSize).color(64, 64, 64, 255).endVertex();
		b.pos(right, upperBoxEnd, 0.0D).tex(right / textureSize,
				upperBoxEnd / textureSize).color(64, 64, 64, 255).endVertex();
		b.pos(right, top, 0.0D).tex(right / textureSize, top / textureSize)
		.color(64, 64, 64, 255).endVertex();
		b.pos(left, top, 0.0D).tex(0.0D, top / textureSize)
		.color(64, 64, 64, 255).endVertex();
		t.draw();

		// Lower box
		int lowerBoxStart = bottom - bottomMargin;

		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		b.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
		b.pos(left, bottom, 0.0D).tex(0.0D, bottom / textureSize)
		.color(64, 64, 64, 255).endVertex();
		b.pos(right, bottom, 0.0D).tex(right / textureSize, bottom
				/ textureSize).color(64, 64, 64, 255).endVertex();
		b.pos(right, lowerBoxStart, 0.0D)
		.tex(right / textureSize, lowerBoxStart / textureSize)
		.color(64, 64, 64, 255).endVertex();
		b.pos(left, lowerBoxStart, 0.0D).tex(0.0D, lowerBoxStart
				/ textureSize).color(64, 64, 64, 255).endVertex();
		t.draw();

		//Gradients
		GlStateManager.enableBlend();
		GlStateManager.tryBlendFuncSeparate(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA, 0, 1);
		GlStateManager.disableAlpha();
		GlStateManager.shadeModel(GL_SMOOTH);
		GlStateManager.disableTexture2D();
		b.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
		b.pos(left, upperBoxEnd + padding, 0.0D).tex(0.0D, 1.0D)
		.color(0, 0, 0, 0).endVertex();
		b.pos(right, upperBoxEnd + padding, 0.0D).tex(1.0D, 1.0D)
		.color(0, 0, 0, 0).endVertex();
		b.pos(right, upperBoxEnd, 0.0D).tex(1.0D, 0.0D).color(0, 0, 0, 255)
		.endVertex();
		b.pos(left, upperBoxEnd, 0.0D).tex(0.0D, 0.0D).color(0, 0, 0, 255)
		.endVertex();
		t.draw();
		b.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
		b.pos(left, lowerBoxStart, 0.0D).tex(0.0D, 1.0D).color(0, 0, 0, 255)
		.endVertex();
		b.pos(right, lowerBoxStart, 0.0D).tex(1.0D, 1.0D).color(0, 0, 0, 255)
		.endVertex();
		b.pos(right, lowerBoxStart - padding, 0.0D).tex(1.0D, 0.0D)
		.color(0, 0, 0, 0).endVertex();
		b.pos(left, lowerBoxStart - padding, 0.0D).tex(0.0D, 0.0D)
		.color(0, 0, 0, 0).endVertex();
		t.draw();

		GlStateManager.enableTexture2D();
		GlStateManager.shadeModel(GL_FLAT);
		GlStateManager.enableAlpha();
		GlStateManager.disableBlend();
	}
	
}
