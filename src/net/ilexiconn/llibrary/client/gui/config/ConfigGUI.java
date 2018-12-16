package net.ilexiconn.llibrary.client.gui.config;

import net.ilexiconn.llibrary.LLibrary;
import net.ilexiconn.llibrary.client.gui.ElementGUI;
import net.ilexiconn.llibrary.client.gui.element.ButtonElement;
import net.ilexiconn.llibrary.client.gui.element.Element;
import net.ilexiconn.llibrary.client.gui.element.LabelElement;
import net.ilexiconn.llibrary.client.gui.element.ListElement;
import net.ilexiconn.llibrary.client.gui.element.color.ColorScheme;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class ConfigGUI {
    public static final ColorScheme RETURN = ColorScheme.create(() -> LLibrary.CONFIG.getPrimaryColor(), () -> LLibrary.CONFIG.getSecondaryColor());
    public static final ColorScheme SIDEBAR = ColorScheme.create(() -> LLibrary.CONFIG.getPrimaryColor(), () -> LLibrary.CONFIG.getTertiaryColor());
    public static final ResourceLocation SETTINGS_ICON = new ResourceLocation("llibrary", "textures/gui/settings.png");

    public ColorScheme getReturnButtonColorScheme() {
        return RETURN;
    }

    public ColorScheme getSidebarColorScheme() {
        return SIDEBAR;
    }

    public int getTopBackgroundColor() {
        return LLibrary.CONFIG.getPrimaryColor();
    }

    public int getContentBackgroundColor() {
        return LLibrary.CONFIG.getColorMode().equals("dark") ? 0xFF191919 : 0xFFFFFFFF;
    }

    public int getAccentColor() {
        return LLibrary.CONFIG.getAccentColor();
    }

    public int getTextColor() {
        return LLibrary.CONFIG.getTextColor();
    }

}
