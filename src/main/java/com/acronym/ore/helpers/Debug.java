package com.acronym.ore.helpers;

import com.acronym.ore.networking.PacketHandler;
import com.acronym.ore.networking.PacketSendKey;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import org.lwjgl.input.Keyboard;

import static com.acronym.ore.reference.Reference.ModInfo.MODID;

/**
 * Created by EwyBoy
 **/
public class Debug {

    public static KeyBinding debug;

    public static void initKeyBindings() {
        debug = new KeyBinding("debug", Keyboard.KEY_DELETE, "key.categories." + MODID);
        ClientRegistry.registerKeyBinding(debug);
    }

    @SubscribeEvent
    public void onKeyInput(InputEvent.KeyInputEvent event) {
        if (Debug.debug.isPressed()) {
            PacketHandler.INSTANCE.sendToServer(new PacketSendKey());
        }
    }
}
