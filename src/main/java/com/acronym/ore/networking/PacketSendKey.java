package com.acronym.ore.networking;

import com.acronym.ore.config.Config;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

import static net.minecraft.init.Blocks.*;

/**
 * Created by EwyBoy
 **/
public class PacketSendKey implements IMessage{

    @Override
    public void fromBytes(ByteBuf buf) {}

    @Override
    public void toBytes(ByteBuf buf) {}

    public static class Handler implements IMessageHandler<PacketSendKey, IMessage> {

        @Override
        public IMessage onMessage(PacketSendKey message, MessageContext ctx) {
            FMLCommonHandler.instance().getWorldThread(ctx.netHandler).addScheduledTask(() -> handle(message, ctx));
            return null;
        }

        private void handle(PacketSendKey message, MessageContext ctx) {
            EntityPlayerMP player = ctx.getServerHandler().playerEntity;
            World world = player.worldObj;

            if (Config.debugMode) {
                player.addChatComponentMessage(new TextComponentString("WARNING! World Stripping Initialized! Lag May Occur.."));
                for(int x = (int)(player.getPosition().getX() - 32.0D); (double)x <= player.getPosition().getX() + 32.0D; ++x) {
                    for(int y = 0; (double)y <= player.getPosition().getY() + 32.0D; ++y) {
                        for(int z = (int)(player.getPosition().getZ() - 32.0D); (double)z <= player.getPosition().getZ() + 32.0D; ++z) {
                            BlockPos targetBlock = new BlockPos(x,y,z);
                            //TODO Rewrite this part cause this is a shitty way of doing it but it works
                            if (
                                    world.getBlockState(targetBlock).getBlock().equals(STONE) ||
                                            world.getBlockState(targetBlock).getBlock().equals(GRASS) ||
                                            world.getBlockState(targetBlock).getBlock().equals(DIRT) ||
                                            world.getBlockState(targetBlock).getBlock().equals(SAND) ||
                                            world.getBlockState(targetBlock).getBlock().equals(GRAVEL) ||
                                            world.getBlockState(targetBlock).getBlock().equals(LAVA) ||
                                            world.getBlockState(targetBlock).getBlock().equals(FLOWING_LAVA) ||
                                            world.getBlockState(targetBlock).getBlock().equals(WATER) ||
                                            world.getBlockState(targetBlock).getBlock().equals(FLOWING_WATER) ||
                                            world.getBlockState(targetBlock).getBlock().equals(LOG) ||
                                            world.getBlockState(targetBlock).getBlock().equals(LOG2) ||
                                            world.getBlockState(targetBlock).getBlock().equals(LEAVES) ||
                                            world.getBlockState(targetBlock).getBlock().equals(LEAVES2)
                                    )
                            {
                                world.setBlockToAir(targetBlock);
                            }
                        }
                    }
                }
                player.addChatComponentMessage(new TextComponentString("World Stripping Successfully Done!"));
            }
        }
    }
}
