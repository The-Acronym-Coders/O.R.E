package com.acronym.ore.config;

import com.acronym.ore.api.generation.Generation;
import com.acronym.ore.api.generation.GenerationRegistry;
import com.acronym.ore.reference.Reference;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.config.Configuration;

import java.io.File;
import java.util.Collection;

/**
 * Created by Jared on 8/4/2016.
 */
public class Config {
    public static void load() throws Exception {
        Configuration configuration = new Configuration(new File(Reference.CONFIG_DIR, String.format("%s.cfg", Reference.MODID)));
        configuration.load();
        registerJsons();
        configuration.save();
    }

    public static void registerJsons() throws Exception {
        File generation = new File(Reference.CONFIG_DIR, "generation.json");

        /*TODO create file from template if it doesn't exist
        if (!seed.exists()) {
            try {
                FileUtils.copyURLToFile(FluxedCrystals.class.getResource("/assets/" + Reference.modid + "/jsons/seedData.json"), seed);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }*/

        JSONParser<Generation> readerGeneration = new JSONParser<Generation>(generation, Generation.class);
        addGenerator(readerGeneration.getElements("ore"));
    }

    public static void addGenerator(Collection<? extends Generation> types) {
        for (Generation type : types) {
            GenerationRegistry.addGeneration(type.register());
        }
    }

    public static boolean isBlock(ItemStack stack) {
        ResourceLocation name = Block.REGISTRY.getNameForObject(Block.getBlockFromItem(stack.getItem()));
        return !name.toString().equals("minecraft:air") && Block.REGISTRY.containsKey(name);
    }

}
