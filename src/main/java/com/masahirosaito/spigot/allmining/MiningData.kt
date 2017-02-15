package com.masahirosaito.spigot.allmining

import com.masahirosaito.spigot.mscore.utils.itemInMainHand
import com.masahirosaito.spigot.mscore.utils.remainingDurability
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.Statistic
import org.bukkit.entity.Player

class MiningData(val player: Player, val blockType: Material) {
    val oldStatistic = player.getStatistic(Statistic.MINE_BLOCK, blockType)
    val oldDurability = player.itemInMainHand.remainingDurability

    fun getData(oreBlock: OreBlock): String {
        val brokenBlockNum = oreBlock.brokenBlocks.size
        val droppedExperience = oreBlock.exp
        val newStatistic = player.getStatistic(Statistic.MINE_BLOCK, blockType)
        val newDurability = player.itemInMainHand.remainingDurability

        return buildString {
            append("[Blocks=${ChatColor.BLUE}$brokenBlockNum${ChatColor.RESET}] ")
            append("[Exp=${ChatColor.GREEN}$droppedExperience${ChatColor.RESET}] ")
            append("[Statistic=${ChatColor.AQUA}$oldStatistic→$newStatistic${ChatColor.RESET}] ")
            append("[Durability=${ChatColor.GOLD}$oldDurability→$newDurability${ChatColor.RESET}] ")
        }
    }
}