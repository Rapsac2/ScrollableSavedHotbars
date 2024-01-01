package net.rapsac.scrollablesavedhotbars;

import net.fabricmc.api.ClientModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.HotbarStorage;
import net.minecraft.client.option.HotbarStorageEntry;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.item.ItemStack;


public class ScrollableSavedHotbarsClient implements ClientModInitializer {
	public static final String MOD_ID = "scrollablesavedhotbars";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	
	private static int index = -1;
	private static int scrollAmountInt; 
	private static HotbarStorage hotbars;
	

	@Environment (EnvType.CLIENT)
	public static Boolean Scrolling(double scrollAmount) {
			
		MinecraftClient client = MinecraftClient.getInstance();
		if(client.options.loadToolbarActivatorKey.isPressed() && client.player.isCreative()){
			
			hotbars = client.getCreativeHotbarStorage();
			
			scrollAmountInt = (int)scrollAmount; 
			index = index - scrollAmountInt;

			if(index < 0 && scrollAmountInt > 0){ //scroll overflow logic
				index = 8;
			}else if((index > 8 || index == -1) && scrollAmountInt < 0){
				index = 0;
			}

			if(index >= 0 && index <= 8){          // code from: https://github.com/ToBinio/useful-saved-hotbars
				HotbarStorageEntry hotbar = hotbars.getSavedHotbar(index);
				
				for (int i = 0; i < 9; i++) { 
        			ItemStack itemStack = hotbar.get(i);
            		ItemStack itemStack2 = itemStack.isItemEnabled(client.world.getEnabledFeatures()) ? itemStack.copy() : ItemStack.EMPTY;
           			client.player.getInventory().setStack(i, itemStack2);
            		client.interactionManager.clickCreativeStack(itemStack2, 36 + i);
        		}
			}
			return true;

		} else {
			if(index != -1){
				index = -1; // index reset
			}
			return false;
		}
	}

	@Override
	public void onInitializeClient(){
		ClientTickEvents.END_CLIENT_TICK.register(client -> {
			if(index != -1){
				if (client.options.dropKey.isPressed() || client.options.useKey.isPressed() || client.options.attackKey.isPressed()) {
					index = -1;
				}
			}
		});
	}
}
