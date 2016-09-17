package com.acronym.ore.common.commands;

import com.acronym.ore.api.generation.Generation;
import com.acronym.ore.api.generation.GenerationRegistry;
import com.acronym.ore.api.generation.OreWorldGenerator;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Jared on 8/20/2016.
 */
public class CommandGenerate extends CommandBase {

    @Override
    public String getCommandName() {
        return "generate";
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 2;
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "/ore " + getCommandName() + "<generation name> <x> <y> <z>";
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        Generation gen = GenerationRegistry.getGenerationFromName(args[1]);
        if (gen == null) throw new CommandException("No Generation with that name!");

        //TODO whatever this shit is @Jared
        /*try {

            gen(server.getEntityWorld(), server.getEntityWorld().rand, new BlockPos(Integer.parseInt(args[2]), Integer.parseInt(args[3]), Integer.parseInt(args[4])), (int) Reference.ENGINE_JAVASCRIPT.eval(gen.getBlockCount()), gen.getWorldGenerator().getConstructor(Map.class, int.class, Map.class).newInstance(gen.getBlocks(), gen.getBlockCount(), gen.getParams()));
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException | ScriptException e) {
            e.printStackTrace();
        }*/
    }

    protected void gen(World worldIn, Random random, BlockPos pos, int blockCount, OreWorldGenerator generator) {
        for (int j = 0; j < blockCount; ++j) {
            generator.generateFromCommand(worldIn, random, pos);
        }
    }

    @Override
    public List<String> getTabCompletionOptions(MinecraftServer server, ICommandSender sender, String[] args, @Nullable BlockPos pos) {
        List retList = new ArrayList();
        if (args.length > 1 && args.length < 3) {
            for (Generation gen : GenerationRegistry.getGenerations()) {
                System.out.println(gen.getName());
                retList.add(gen.getName());
            }
            return retList;
        }
        if (args.length > 2 && args.length < 6) {
            return getTabCompletionCoordinate(args, 0, pos);
        }
        return super.getTabCompletionOptions(server, sender, args, pos);
    }

    //TODO add tab completion
}
