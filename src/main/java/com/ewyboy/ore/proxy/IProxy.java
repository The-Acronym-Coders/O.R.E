package com.ewyboy.ore.proxy;

import java.io.File;

/** Created by EwyBoy **/
public interface IProxy {

    /**
     * Register World Gen
     */
    void registerWorldGen();

    /**
     * Register Config
     */
    void registerConfiguration(File configFile);

    /**
     * Register Events
     */
    void registerEvents();

}
