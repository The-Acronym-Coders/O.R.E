package com.acronym.ore.helpers;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
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
        EntityPlayer player = Minecraft.getMinecraft().thePlayer;
        World world = Minecraft.getMinecraft().theWorld;

        if (Debug.debug.isPressed()) {
            stripWorld(world, player);
        }
    }

    public void stripWorld(World world, EntityPlayer player) {
        Logger.info("Hello from the other side?!");
        for (int y = player.getPosition().getY(); y > 0; y--){
            world.setBlockToAir(player.getPosition().down(y));
        }
    }
}
