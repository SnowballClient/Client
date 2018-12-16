package org.golde.snowball.texture;

import java.io.File;

import net.minecraft.client.resources.ResourcePackRepository;

public class SnowballResourcePackRepositoryEntry extends ResourcePackRepository.Entry{

	public SnowballResourcePackRepositoryEntry(ResourcePackRepository resourcePackRepository, File resourcePackFileIn) {
		resourcePackRepository.super(resourcePackFileIn);
	}

	@Override
	public String getResourcePackName() {
		return "Snowball Client Assets";
	}
	
}
