package com.masahirosaito.spigot.allmining


import org.bukkit.Material
import org.bukkit.block.Block

data class DamagedMaterial(val material: Material, val damage: Short) {
    companion object {
        fun new(block: Block): DamagedMaterial {
            return DamagedMaterial(block.type, block.state.data.toItemStack().durability)
        }

        fun get(block: Block): DamagedMaterial = when (block.type) {
            Material.GLOWING_REDSTONE_ORE -> DamagedMaterial(Material.REDSTONE_ORE,
                    block.state.data.toItemStack().durability)
            else -> DamagedMaterial.new(block)
        }
    }
}