package org.golde.snowball.util;

public class GameIsRunningInDemoException extends Exception {

	@Override
	public String getMessage() {
		return "Snowball does not work with the demo version of the game. Please purchace Minecraft!";
	}
	
}
