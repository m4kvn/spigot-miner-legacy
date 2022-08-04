package com.masahirosaito.spigot.allmining.listeners

import com.masahirosaito.spigot.allmining.AllMining
import com.masahirosaito.spigot.allmining.MiningData
import com.masahirosaito.spigot.allmining.OreBlock
import org.bukkit.GameMode
import org.bukkit.block.Block
import org.bukkit.entity.ExperienceOrb
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockBreakEvent
import org.bukkit.inventory.ItemStack

class BlockBreakListener(private val plugin: AllMining) : Listener {
    private val configs = plugin.configs

    @EventHandler(priority = EventPriority.MONITOR)
    fun onOreBreak(event: BlockBreakEvent) {
        when {
            event.isCancelled -> return
            event.block.isInValid() -> return
            event.player.isInValid() -> return
            event.player.inventory.itemInMainHand.isInValid() -> when {
                event.block.hasMetadata(plugin.name) -> {
                    event.isCancelled = true
                    return
                }

                else -> return
            }
        }

        if (event.block.hasMetadata(plugin.name)) {
            val ore = event.block.getMetadata(plugin.name)[0].value() as OreBlock

            if (event.expToDrop > 0) {
                event.player.spawnExp(event.expToDrop)
                ore.exp += event.expToDrop
                event.expToDrop = 0
            }
            ore.brokenBlocks.add(event.block)

        } else {
            val player = event.player
            val miningData = MiningData(player, event.block.type)
            val oreBlock = OreBlock(blocks = event.block.getRelativeOres())

            oreBlock.breakAll(player, plugin)
            oreBlock.warpItems(player)

            if (configs.onMiningData) {
                plugin.messenger.send(player, miningData.getData(oreBlock))
            }
        }
    }

    private fun Player.isInValid(): Boolean = when {
        !hasPermission("allmining.execute") -> true
        !(plugin.playerdata.data[uniqueId] ?: true) -> true
        gameMode == GameMode.CREATIVE && !configs.onCreative -> true
        !isSneaking && configs.onSneaking -> true
        else -> false
    }

    private fun Player.spawnExp(amount: Int) {
        location.world?.spawn(location, ExperienceOrb::class.java)?.experience = amount
    }

    private fun ItemStack.isInValid(): Boolean = !configs.pickaxes.contains(type)

    private fun Block.isInValid(): Boolean = !configs.ores.contains(type)

    private fun Block.getRelativeOres(): MutableSet<Block> {
        val unCheckedBlocks = mutableSetOf(this)
        val checkedBlocks = mutableSetOf<Block>()

        while (unCheckedBlocks.isNotEmpty() && (checkedBlocks.size < configs.maxOres)) {
            unCheckedBlocks.first().let { b ->
                unCheckedBlocks.remove(b)
                checkedBlocks.add(b)
                unCheckedBlocks.addAll(b.getRelativeBlocks(1)
                    .filter { it.type == type }
                    .filterNot { checkedBlocks.contains(it) })
            }
        }

        return checkedBlocks
    }

    private fun Block.getRelativeBlocks(distance: Int): List<Block> {
        val blocks = mutableListOf<Block>()
        val range = -distance..distance
        for (x in range) for (y in range) for (z in range) {
            if (x != 0 || y != 0 || z != 0) {
                blocks.add(getRelative(x, y, z))
            }
        }
        return blocks
    }
}