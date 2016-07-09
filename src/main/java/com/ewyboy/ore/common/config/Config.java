package com.ewyboy.ore.common.config;

import com.ewyboy.ore.ORE;
import com.ewyboy.ore.common.utility.Reference;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.config.GuiConfig;
import net.minecraftforge.fml.client.config.IConfigElement;

import java.io.File;
import java.util.Arrays;

/** Created by EwyBoy **/
public class Config extends GuiConfig {

    public static final String CONFIG_WORLDGEN = "worldgen";
    public static Configuration configuration;

    public Config(GuiScreen parentScreen) {
        super(
                parentScreen,
                Arrays.asList(new IConfigElement[] {
                        new ConfigElement(ORE.configuration.getCategory(CONFIG_WORLDGEN)),
                }),
                Reference.ModInfo.MOD_ID, false, false, "O.R.E Configuration");
        titleLine2 = ORE.configuration.getConfigFile().getAbsolutePath();
    }

    public static Configuration initConfig(File configFile) {
        if (configuration == null) {
            configuration = new Configuration(configFile);
            loadConfiguration();
        }
        return configuration;
    }

    public static void loadConfiguration() {
        ConfigWorldGen.init(configuration);
        configuration.save();
    }
}