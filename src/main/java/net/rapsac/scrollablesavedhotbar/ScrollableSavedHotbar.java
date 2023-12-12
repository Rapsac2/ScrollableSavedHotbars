package net.rapsac.scrollablesavedhotbar;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;

import org.lwjgl.glfw.GLFW;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.minecraft.client.option.GameOptions;

import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.client.util.InputUtil.Key;
import net.minecraft.client.MinecraftClient;


public class ScrollableSavedHotbar implements ModInitializer {
	public static final String MOD_ID = "scrollablesavedhotbar";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	
	public static KeyBinding keyBinding;
	public MinecraftClient minecraftClient;
	

	@Override
	public void onInitialize() {
		minecraftClient = MinecraftClient.getInstance();
		keyBinding = minecraftClient.options.jumpKey; // diese zeile ist das problem
		//keyBinding = MinecraftClient.getInstance().options.jumpKey;
		//while (jumpKey.isPressed()) {
		//public static final GameOptions gameOptions1 = new GameOptions(minecraftClient, null);
		//} 
		
		//GameOptions.loadToolbarActivatorKey;
		
		LOGGER.info("mod is starting");
		//while(keyBinding.isPressed()) {
		//	LOGGER.info("Jump");
		//}
		/*
		 * ClientTickEvents.END_CLIENT_TICK.register(client -> {
			while (binding1.wasPressed()) {
				client.player.sendMessage(new LiteralText("Key 1 was pressed!"), false);
			}
		});
		 * 
		 * 
		 * 
		 */
		
		
	}
}