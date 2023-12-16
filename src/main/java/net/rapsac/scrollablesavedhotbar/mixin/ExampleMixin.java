package net.rapsac.scrollablesavedhotbar.mixin;

import net.minecraft.server.MinecraftServer;
import org.spongepowered.asm.mixin.Mixin;
//import org.spongepowered.asm.mixin.injection.At;
//import org.spongepowered.asm.mixin.injection.Inject;
//import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
//import net.minecraft.client.Mouse;
//import net.rapsac.scrollablesavedhotbar.ScrollableSavedHotbar;
//import net.minecraft.entity.player.PlayerEntity;
//import net.minecraft.entity.player.PlayerInventory;
//import org.spongepowered.asm.mixin.Final;
//import org.spongepowered.asm.mixin.Mixin;
//import org.spongepowered.asm.mixin.Shadow;
//import org.spongepowered.asm.mixin.injection.At;
//import org.spongepowered.asm.mixin.injection.Inject;
//import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftServer.class)
public class ExampleMixin {
	//@Inject(at = @At("HEAD"), method = "loadWorld")
	//private void init(CallbackInfo info) {
		// This code is injected into the start of MinecraftServer.loadWorld()V
//	}
}


	//@Inject(method = "onMouseScroll(JDD)V", at = @At("HEAD"), cancellable = true)
	//@Inject(method = "onMouseScroll(JDD)V", at = @At(value = "INVOKE", target = "scrollInHotbar(D)V"),cancellable = true)
	//public void mouseScroll(){
	//	ScrollableSavedHotbar.Scrolling();
	//}
//@Mixin(PlayerInventory.class)
//public class ExampleMixin {

	//@Inject(at = @At(value = "HEAD"), method = "scrollInHotbar", cancellable = true)
	//private void onScroll(double scrollAmount, CallbackInfo ci){
	//	if(ScrollableSavedHotbar.Scrolling(scrollAmount)){
	//		ci.cancel();
	//	}
	//}

//}
