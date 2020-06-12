package ca.slackinjack.fasterplacement;

import net.minecraft.util.MovingObjectPosition;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.util.BlockPos;
import net.minecraftforge.client.event.DrawBlockHighlightEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.relauncher.ReflectionHelper;
import org.lwjgl.input.Keyboard;

@Mod(modid = FasterPlacement.MODID, acceptedMinecraftVersions = "1.8.9")

public class FasterPlacement {
	public final Minecraft mc = Minecraft.getMinecraft();
	public static final String MODID = "fasterplacement";
        public static final KeyBinding toggleKey = new KeyBinding("FasterPlacement Toggle", Keyboard.KEY_P, "key.categories.gameplay");
	private static BlockPos lastBlockPos;
        static {
		ClientRegistry.registerKeyBinding(toggleKey);
        }
        private boolean enabled;
	
	@EventHandler
	public void onInit(FMLInitializationEvent event) {
		MinecraftForge.EVENT_BUS.register(this);
	}
	
	@SubscribeEvent
	public void onHighlightBlock(DrawBlockHighlightEvent event) {
            if (mc.currentScreen == null && this.enabled) {
                if (event.target.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK) {
                    if (lastBlockPos != event.target.getBlockPos()) {
                        ReflectionHelper.setPrivateValue(Minecraft.class, (Minecraft)mc, 0, "field_71467_ac");
                    }
                    else if (lastBlockPos == event.target.getBlockPos()) {
                        ReflectionHelper.setPrivateValue(Minecraft.class, (Minecraft)mc, 4, "field_71467_ac");
                    }
                lastBlockPos = event.target.getBlockPos();
                }
            }
	}
        
        @SubscribeEvent
	public void onToggleKeyPress(InputEvent.KeyInputEvent event) {
            if (toggleKey.isPressed()) {
                AddChatMessage chat = new AddChatMessage();
                this.enabled = !this.enabled;
                if (this.enabled) {
                    chat.add("Enabled!", 0);
                }
                else {
                    chat.add("Disabled!", 1);
                }
            }
	}
}