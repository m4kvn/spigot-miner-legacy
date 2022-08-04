package com.masahirosaito.spigot.allmining.nms

import org.bukkit.Material
import org.bukkit.block.Block
import org.bukkit.entity.Player

interface NMS {
    val pickaxes: List<Material>
    fun callBlockBreakEvent(player: Player, block: Block)
}