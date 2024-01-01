package net.rapsac.scrollablesavedhotbars.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import net.rapsac.scrollablesavedhotbars.ScrollableSavedHotbarsClient;
import net.minecraft.entity.player.PlayerInventory;


@Mixin(PlayerInventory.class)
public class ScrollMixin{

	@Inject(at = @At(value = "HEAD"), method = "scrollInHotbar", cancellable = true)
	private void onScroll(double scrollAmount, CallbackInfo ci){
		if(ScrollableSavedHotbarsClient.Scrolling(scrollAmount)){
			ci.cancel(); 
		}
	}

}
