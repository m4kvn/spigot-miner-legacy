package com.masahirosaito.spigot.allmining

import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.Statistic
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.Damageable

class MiningData(
    private val player: Player,
    private val blockType: Material,
) {
    private val oldStatistic = player.getStatistic(Statistic.MINE_BLOCK, blockType)
    private val oldDurability = player.inventory.itemInMainHand.remainingDurability

    fun getData(oreBlock: OreBlock): String {
        val brokenBlockNum = oreBlock.brokenBlocks.size
        val droppedExperience = oreBlock.exp
        val newStatistic = player.getStatistic(Statistic.MINE_BLOCK, blockType)
        val newDurability = player.inventory.itemInMainHand.remainingDurability

        return buildString {
            append("[Blocks=${ChatColor.BLUE}$brokenBlockNum${ChatColor.RESET}] ")
            append("[Exp=${ChatColor.GREEN}$droppedExperience${ChatColor.RESET}] ")
            append("[Statistic=${ChatColor.AQUA}$oldStatistic→$newStatistic${ChatColor.RESET}] ")
            append("[Durability=${ChatColor.GOLD}$oldDurability→$newDurability${ChatColor.RESET}] ")
        }
    }

    private val ItemStack.remainingDurability: Int
        get() = type.maxDurability - ((itemMeta as? Damageable)?.damage ?: 0)
}