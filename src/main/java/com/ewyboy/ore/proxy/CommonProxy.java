package com.ewyboy.ore.proxy;

import com.ewyboy.ore.ORE;
import com.ewyboy.ore.common.config.Config;
import com.ewyboy.ore.common.world.WorldGenInit;

import java.io.File;

/** Created by EwyBoy **/
public abstract class CommonProxy implements IProxy {

    @Override
    public void registerWorldGen() {
        WorldGenInit.init();
    }

    @Override
    public void registerConfiguration(File configFile) {
        ORE.configuration = Config.initConfig(configFile);
    }

    @Override
    public void registerEvents() {}
}
