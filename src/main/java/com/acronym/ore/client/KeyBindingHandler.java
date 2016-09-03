package com.acronym.ore.client;

import com.acronym.ore.common.network.PacketHandler;
import com.acronym.ore.common.network.PacketStripWorld;
import com.acronym.ore.common.reference.Reference;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import org.lwjgl.input.Keyboard;

/**
 * Created by EwyBoy
 **/
public class KeyBindingHandler {

    public static KeyBinding strip;

    public static void initKeyBinding() {
        strip = new KeyBinding(Reference.Keybinding.KeybindingName, Keyboard.KEY_DELETE, Reference.ModInfo.NAME);
    }

    @SubscribeEvent
    public void onKeyInput(InputEvent.KeyInputEvent event) {
        if (strip.isPressed()) PacketHandler.INSTANCE.sendToServer(new PacketStripWorld());
    }
}
