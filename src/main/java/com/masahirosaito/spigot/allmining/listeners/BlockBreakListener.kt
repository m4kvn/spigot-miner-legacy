package com.masahirosaito.spigot.allmining.listeners

import com.masahirosaito.spigot.allmining.AllMining
import com.masahirosaito.spigot.allmining.MiningData
import com.masahirosaito.spigot.allmining.OreBlock
import com.masahirosaito.spigot.allmining.utils.cancel
import com.masahirosaito.spigot.mscore.materials.DamagedMaterial
import com.masahirosaito.spigot.mscore.utils.getRelatives
import com.masahirosaito.spigot.mscore.utils.isCreativeMode
import com.masahirosaito.spigot.mscore.utils.itemInMainHand
import com.masahirosaito.spigot.mscore.utils.spawnExp
import org.bukkit.Material
import org.bukkit.block.Block
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockBreakEvent
import org.bukkit.inventory.ItemStack

class BlockBreakListener(val plugin: AllMining) : Listener {
    val configs = plugin.configs

    @EventHandler(priority = EventPriority.MONITOR)
    fun onOreBreak(event: BlockBreakEvent) {
        when {
            event.isCancelled -> return
            event.block.isInValid() -> return
            event.player.isInValid() -> return
            event.player.itemInMainHand.isInValid() -> when {
                event.block.hasMetadata(plugin.name) -> return event.cancel()
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
        isCreativeMode() && !configs.onCreative -> true
        !isSneaking && configs.onSneaking -> true
        else -> false
    }

    private fun ItemStack.isInValid(): Boolean = !configs.pickaxes.contains(type)

    private fun Block.isInValid(): Boolean = !configs.ores.contains(DamagedMaterial.new(this))

    private fun Block.getRelativeOres(): MutableSet<Block> {
        val type = DamagedMaterial.new(this)
        val unCheckedBlocks = mutableSetOf(this)
        val checkedBlocks = mutableSetOf<Block>()

        while (unCheckedBlocks.isNotEmpty()) {
            unCheckedBlocks.first().let { b ->
                unCheckedBlocks.remove(b)
                checkedBlocks.add(b)
                unCheckedBlocks.addAll(b.getRelatives(1)
                        .filter { DamagedMaterial.new(it) == type }
                        .filterNot { checkedBlocks.contains(it) })
            }
        }

        return checkedBlocks
    }
}