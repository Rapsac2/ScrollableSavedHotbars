package net.rapsac.scrollablesavedhotbars;

import net.fabricmc.api.ClientModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.HotbarStorage;
import net.minecraft.client.option.HotbarStorageEntry;
import net.minecraft.client.util.InputUtil;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.item.ItemStack;


public class ScrollableSavedHotbarsClient implements ClientModInitializer {
	public static final String MOD_ID = "scrollablesavedhotbar";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	
	private static int index;
	private static int scrollAmountInt; 
	private static HotbarStorage hotbars;
	

	@Environment (EnvType.CLIENT)
	public static Boolean Scrolling(double scrollAmount) {
			
		if(MinecraftClient.getInstance().player.isCreative() && InputUtil.isKeyPressed(MinecraftClient.getInstance().getWindow().getHandle(), 342)){ //342 = Left_alt; 88 = x  If you can get a reference to the "loadToolbarActivatorKey" keybind from "GameOptions.class", write me and i will fix this.
		
			MinecraftClient client = MinecraftClient.getInstance(); // get client
			
			hotbars = client.getCreativeHotbarStorage();
			
			scrollAmountInt = (int)scrollAmount; 
			index = index - scrollAmountInt;

			if(index < 0 && scrollAmountInt > 0){ //scroll overflow logic
				index = 8;
			}else if((index > 8 || index == -1) && scrollAmount < 0){
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
				index = -1; // index reset to -1
			}
			return false; // alt_key is not pressed
		}
	}

	@Override
	public void onInitializeClient(){
			index = -1;
	}
}