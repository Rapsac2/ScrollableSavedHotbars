package net.rapsac.scrollablesavedhotbar;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.system.windows.INPUT;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Shadow;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.option.HotbarStorage;
import net.minecraft.client.option.HotbarStorageEntry;
import net.minecraft.client.util.InputUtil;
import net.minecraft.client.util.NarratorManager;
import net.minecraft.util.Identifier;
import org.lwjgl.glfw.GLFW;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.option.HotbarStorageEntry;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

import net.minecraft.client.option.GameOptions;
import net.minecraft.client.option.HotbarStorage;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.client.util.InputUtil.Key;
import net.minecraft.text.Text;
import net.minecraft.text.PlainTextContent.Literal;
import net.minecraft.client.MinecraftClient;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.client.option.HotbarStorageEntry;

import org.jetbrains.annotations.Nullable;
import org.lwjgl.glfw.GLFW;
import net.rapsac.scrollablesavedhotbar.mixin.KeyMixin;


public class ScrollableSavedHotbar implements ModInitializer { // ModInitializer ( ClientModInitializer idt falsch und wirft eine Fehler meldung)
	public static final String MOD_ID = "scrollablesavedhotbar";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	
	
	//public MinecraftClient minecraftClient1 = new Client;
	//public KeyBinding somethingkey = MinecraftClient.getInstance().options.jumpKey;
	//public KeyBinding keyBind = MinecraftClient.options.jumpKey
	
	public static GameOptions jumpKeyBind; // = KeyMixin.options;
	public static KeyBinding ghj;
	private static int index;
	private static Key[] key;
	private static int scrollAmountInt; 
	private static HotbarStorage hotbars;
	public MinecraftClient client;

	@Environment (EnvType.CLIENT) //das ist neu
	public static Boolean Scrolling(double scrollAmount) {
		
		//if(jumpKeyBind != null){
			
			if(InputUtil.isKeyPressed(MinecraftClient.getInstance().getWindow().getHandle(), 342)){ //342 müsste Left_alt sein   88 ist x
			//if(jumpKeyBind.jumpKey.isPressed()){
			//if(KeyMixin.sprung.isPressed()){
				MinecraftClient client2 = MinecraftClient.getInstance();
				//client2 = MinecraftClient.getInstance();
				hotbars = client2.getCreativeHotbarStorage();
				scrollAmountInt = (int)scrollAmount;//(int) Math.signum(scrollAmount)
				if(index >= 0 && index <= 8){
					//KeyBinding.setKeyPressed(key[index], false);
				}
				if(hotbars == null){
					LOGGER.info("Hotbars ist NULL");
				}
				
				LOGGER.info("scroll");
				LOGGER.info("scrollAmoount "+scrollAmount);
				LOGGER.info("scrollAmountInt"+scrollAmountInt);
				index = index - scrollAmountInt;
				if(index < 0 && scrollAmountInt > 0){
					index = 8;
					LOGGER.info("index von 0 auf 8");
				}else if((index > 8 || index == -1) && scrollAmount < 0){
					index = 0;
					LOGGER.info("index von 8 auf 0");
				}
				if(index >= 0 && index <= 8){
				LOGGER.info(index + " key press");
				HotbarStorageEntry hotbar = hotbars.getSavedHotbar(index);
				
				for (int i = 0; i < 9; i++) {
            		ItemStack itemStack = hotbar.get(i);

            		ItemStack itemStack2 = itemStack.isItemEnabled(client2.world.getEnabledFeatures()) ? itemStack.copy() : ItemStack.EMPTY;
           			client2.player.getInventory().setStack(i, itemStack2);
            		client2.interactionManager.clickCreativeStack(itemStack2, 36 + i);
        		}
				
				//KeyBinding.setKeyPressed(key[index], true);
				}
				
				return true;
			} else {//reset ob taste gedrückt ist
				if(index != -1){
					//KeyBinding.setKeyPressed(key[index], false);
					index = -1;
					LOGGER.info("set index -1");
				}
				LOGGER.info("alt is not pressed");
				//KeyBinding.setKeyPressed(key[index], false);
				return false;
			}
	}

	    
    	



	
		//minecraftClient1 = MinecraftClient.getInstance();
		//keyBinding = minecraftClient1.options.jumpKey;
		
		
		//keyBinding = minecraftClient1.options.jumpKey; // diese zeile ist das problem
		
		//keyBinding = MinecraftClient.getInstance().options.jumpKey;
		//while (jumpKey.isPressed()) {
		//public static final GameOptions gameOptions1 = new GameOptions(minecraftClient, null);
		//} 
		
		//GameOptions.loadToolbarActivatorKey;
		
		//LOGGER.info("mod is starting");
		//while(keyBinding.isPressed()) {
		//	LOGGER.info("Jump");
		//}
		//keyBinding = minecraftClient1.options.inventoryKey;


		//ClientTickEvents.END_CLIENT_TICK.register(client -> {
		//	if (client.options.jumpKey.wasPressed()) {
				//client.player.sendMessage(new Literal("Key 1 was pressed!"), false);
		//		LOGGER.info("Jump");
		//	}
	//	});
		
		
		//this.eventDeltaHorizontalWheel -= (double)i;
        //       this.eventDeltaVerticalWheel -= (double)j;
        //       int k = j == 0 ? -i : j;
        //       if (this.client.player.isSpectator()) {
        //          if (this.client.inGameHud.getSpectatorHud().isOpen()) {
        //             this.client.inGameHud.getSpectatorHud().cycleSlot(-k);
	

	@Override
	public void onInitialize(){
			LOGGER.info("-------------------Rapsac_______--------------");
		
			//jumpKeyBind = KeyMixin.options;
			//ghj = GameOptions.jumpKey;
			//key = InputUtil.fromTranslationKey(GLFW_KEY_1);
			//key = new Key[9];
			//key[0] = InputUtil.fromTranslationKey("key.keyboard.0");
			//for(int i = 0; i < 9; i++){
			//	LOGGER.info(i + "");
			//	key[i] = InputUtil.fromTranslationKey("key.keyboard." + 1);//(i+1));	
			//}
			index = 4;
			//key = InputUtil.fromTranslationKey("key.keyboard.space");
			//LOGGER.info("option");
	}
}