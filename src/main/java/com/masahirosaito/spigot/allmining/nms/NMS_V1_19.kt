package com.masahirosaito.spigot.allmining.nms

import net.minecraft.core.BlockPosition
import net.minecraft.server.level.EntityPlayer
import net.minecraft.server.level.PlayerInteractManager
import org.bukkit.Material
import org.bukkit.block.Block
import org.bukkit.craftbukkit.v1_19_R1.entity.CraftPlayer
import org.bukkit.entity.Player

@Suppress("ClassName")
class NMS_V1_19 : NMS {

    override fun callBlockBreakEvent(player: Player, block: Block) {
        val craftPlayer = player as CraftPlayer
        val blockPosition = BlockPosition(block.x, block.y, block.z)
        craftPlayer.handle.playerInteractManager.breakBlock(blockPosition)
    }

    override val pickaxes: List<Material>
        get() = listOf(
            Material.DIAMOND_PICKAXE,
            Material.GOLDEN_PICKAXE,
            Material.IRON_PICKAXE,
            Material.STONE_PICKAXE,
            Material.WOODEN_PICKAXE,
        )
}

private val EntityPlayer.playerInteractManager: PlayerInteractManager get() = d

private fun PlayerInteractManager.breakBlock(blockPosition: BlockPosition) = a(blockPosition)
