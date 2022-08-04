package com.masahirosaito.spigot.allmining

import org.bukkit.block.Block
import org.bukkit.entity.Item
import org.bukkit.entity.Player
import org.bukkit.metadata.FixedMetadataValue

data class OreBlock(
    val blocks: MutableSet<Block> = mutableSetOf(),
    val brokenBlocks: MutableSet<Block> = mutableSetOf(),
    var exp: Int = 0
) {
    fun breakAll(player: Player, plugin: AllMining) {
        blocks.forEach {
            it.setMetadata(plugin.name, FixedMetadataValue(plugin, this))
            plugin.nms.callBlockBreakEvent(player, it)
            it.removeMetadata(plugin.name, plugin)
        }
    }

    fun warpItems(player: Player) {
        val items = mutableSetOf<Item>()
        val brokenBlockItems = brokenBlocks.flatMap { block ->
            block.world
                .getNearbyEntities(block.location, 1.0, 1.0, 1.0)
                .filterIsInstance<Item>()
        }
        items.addAll(brokenBlockItems)
        items.forEach { item ->
            item.world.dropItem(player.location, item.itemStack)
            item.remove()
        }
    }
}