package net.ilexiconn.llibrary.server.config;

import net.ilexiconn.llibrary.client.gui.element.color.ColorMode;

import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;


import java.awt.Color;
import java.io.File;
import java.io.IOException;

public class LLibraryConfig {

    private int accentColor = 0xFF038288;

    private ColorMode colorMode = ColorMode.LIGHT;

    private boolean patreonEffects = true;

    private boolean versionCheck = false;

    private boolean tabsAlwaysVisible = false;

    private boolean tabsLeftSide = true;

    public int getPrimaryColor() {
        return this.colorMode.getPrimaryColor();
    }

    public int getSecondaryColor() {
        return this.colorMode.getSecondaryColor();
    }

    public int getTertiaryColor() {
        return this.colorMode.getTertiaryColor();
    }

    public int getPrimarySubcolor() {
        return this.colorMode.getPrimarySubcolor();
    }

    public int getSecondarySubcolor() {
        return this.colorMode.getSecondarySubcolor();
    }

    public int getTextColor() {
        return this.colorMode.getTextColor();
    }

    public int getInvertedTextColor() {
        return this.colorMode.getInvertedTextColor();
    }

    public int getAccentColor() {
        return this.accentColor;
    }

    public void setAccentColor(int accentColor) {
        this.accentColor = accentColor;
    }

    public int getDarkAccentColor() {
        int r = this.accentColor >> 16 & 255;
        int g = this.accentColor >> 8 & 255;
        int b = this.accentColor & 255;
        float[] hsb = Color.RGBtoHSB(r, g, b, null);
        Color newColor = Color.getHSBColor(hsb[0], hsb[1], hsb[2] * 0.85F);
        return newColor.getRGB() | 0xFF000000;
    }

    public String getColorMode() {
        return this.colorMode.getName();
    }

    public void setColorMode(String colorMode) {
        this.colorMode = ColorMode.getColorMode(colorMode);
    }

    public boolean hasPatreonEffects() {
        return this.patreonEffects;
    }

    public void setPatreonEffects(boolean patreonEffects) {
        this.patreonEffects = patreonEffects;
    }

    public boolean hasVersionCheck() {
        return this.versionCheck;
    }

    public void setVersionCheck(boolean versionCheck) {
        this.versionCheck = versionCheck;
    }

    public boolean areTabsAlwaysVisible() {
        return this.tabsAlwaysVisible;
    }

    public void setTabsAlwaysVisible(boolean tabsAlwaysVisible) {
        this.tabsAlwaysVisible = tabsAlwaysVisible;
    }

    public boolean areTabsLeftSide() {
        return this.tabsLeftSide;
    }

    public void setTabsLeftSide(boolean tabsLeftSide) {
        this.tabsLeftSide = tabsLeftSide;
    }



    public void load() {
        
    }

    public void save() {
        
    }
}
