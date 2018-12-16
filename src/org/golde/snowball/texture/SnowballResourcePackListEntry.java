package org.golde.snowball.texture;

import org.golde.snowball.Snowball;

import net.minecraft.client.gui.GuiScreenResourcePacks;
import net.minecraft.client.resources.IResourcePack;
import net.minecraft.client.resources.ResourcePackListEntry;
import net.minecraft.client.resources.ResourcePackListEntryServer;

public class SnowballResourcePackListEntry extends ResourcePackListEntryServer {

	public SnowballResourcePackListEntry(GuiScreenResourcePacks p_i46594_1_) {
		super(p_i46594_1_, Snowball.instance.getResourcePack().getResourcePack());
	}

	@Override
	protected String getResourcePackName()
    {
        return "Snowball Client Assets";
    }

	@Override
    public boolean isServerPack()
    {
        return false;
    }

}
