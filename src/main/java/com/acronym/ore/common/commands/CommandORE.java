package com.acronym.ore.common.commands;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CommandORE extends CommandBase {

    private static List<CommandBase> modCommands = new ArrayList<>();
    private static List<String> commands = new ArrayList<>();

    @Override
    public String getCommandName() {
        return "ORE";
    }

    @Override
    public String getCommandUsage(ICommandSender commandSender) {
        return "/ORE";
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        if (args.length >= 1) {
            for (CommandBase command : modCommands) {
                if (command.getCommandName().equalsIgnoreCase(args[0]) && sender.canCommandSenderUseCommand(command.getRequiredPermissionLevel(), command.getCommandName())) {
                    command.execute(server, sender, args);
                }
            }
        }
    }

    @Override
    public List<String> getTabCompletionOptions(MinecraftServer server, ICommandSender sender, String[] args, @Nullable BlockPos pos) {
        if (args.length == 1) return getListOfStringsMatchingLastWord(args, commands);
        else if (args.length >= 2) {
            for (CommandBase command : modCommands) {
                if (command.getCommandName().equalsIgnoreCase(args[0])) {
                    return command.getTabCompletionOptions(server, sender, args, pos);
                }
            }
        }
        return null;
    } static {
        modCommands.add(new CommandGenerate());
        commands.addAll(modCommands.stream().map(CommandBase::getCommandName).collect(Collectors.toList()));
    }
}
