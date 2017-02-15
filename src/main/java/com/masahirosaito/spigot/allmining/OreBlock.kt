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
        mutableSetOf<Item>().apply {
            brokenBlocks.forEach {
                addAll(it.world.getNearbyEntities(it.location, 1.0, 1.0, 1.0)
                        .filter { it is Item }
                        .map { it as Item })
            }
        }.forEach {
            it.apply { world.dropItem(player.location, it.itemStack) }.remove()
        }
    }
}