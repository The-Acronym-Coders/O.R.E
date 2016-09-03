package com.acronym.ore.proxy;

import com.acronym.ore.client.KeyBindingHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;

/**
 * Created by Jared on 8/21/2016.
 */
public class ClientProxy extends CommonProxy {

    @Override
    public void registerKeybindings() {
        ClientRegistry.registerKeyBinding(KeyBindingHandler.strip);
        MinecraftForge.EVENT_BUS.register(new KeyBindingHandler());
    }

    @Override
    public void initEngines() {
        super.initEngines();
    }
}
