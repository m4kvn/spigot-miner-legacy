package com.masahirosaito.spigot.allmining.nms

import org.bukkit.block.Block
import org.bukkit.entity.Player

interface NMS {

    fun callBlockBreakEvent(player: Player, block: Block)
}