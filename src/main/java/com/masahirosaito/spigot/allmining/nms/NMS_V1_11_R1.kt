package com.masahirosaito.spigot.allmining.nms

import net.minecraft.server.v1_11_R1.BlockPosition
import org.bukkit.block.Block
import org.bukkit.craftbukkit.v1_11_R1.entity.CraftPlayer
import org.bukkit.entity.Player

class NMS_V1_11_R1 : NMS {

    override fun callBlockBreakEvent(player: Player, block: Block) {
        (player as CraftPlayer).handle.playerInteractManager.breakBlock(BlockPosition(block.x, block.y, block.z))
    }
}