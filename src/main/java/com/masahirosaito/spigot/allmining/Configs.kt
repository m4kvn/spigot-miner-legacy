package com.masahirosaito.spigot.allmining

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.annotations.SerializedName
import com.masahirosaito.spigot.mscore.materials.DamagedMaterial
import org.bukkit.Material
import java.io.File

data class Configs(

        @SerializedName("Display a mining data when mined ores")
        val onMiningData: Boolean = true,

        @SerializedName("Mine ore only during sneaking")
        val onSneaking: Boolean = false,

        @SerializedName("Mine ore also in creative mode")
        val onCreative: Boolean = false,

        @SerializedName("Number of ores that can be mined at one time")
        val maxOres: Int = 64,

        @SerializedName("Types of tools for mining")
        val pickaxes: List<Material> = listOf(
                Material.DIAMOND_PICKAXE,
                Material.GOLD_PICKAXE,
                Material.IRON_PICKAXE,
                Material.STONE_PICKAXE,
                Material.WOOD_PICKAXE
        ),

        @SerializedName("Types of ores that can be mined")
        val ores: List<DamagedMaterial> = listOf(
                DamagedMaterial(Material.COAL_ORE, 0),
                DamagedMaterial(Material.IRON_ORE, 0),
                DamagedMaterial(Material.GOLD_ORE, 0),
                DamagedMaterial(Material.REDSTONE_ORE, 0),
                DamagedMaterial(Material.GLOWING_REDSTONE_ORE, 0),
                DamagedMaterial(Material.LAPIS_ORE, 0),
                DamagedMaterial(Material.EMERALD_ORE, 0),
                DamagedMaterial(Material.DIAMOND_ORE, 0),
                DamagedMaterial(Material.QUARTZ_ORE, 0),
                DamagedMaterial(Material.GLOWSTONE, 0),
                DamagedMaterial(Material.OBSIDIAN, 0)
        )
) {
    companion object {
        fun load(file: File): Configs {
            return Gson().fromJson(file.readText().let {
                if (it.isNullOrBlank()) Configs().toJson() else it
            }, Configs::class.java).apply { file.writeText(toJson()) }
        }
    }

    private fun toJson(): String = GsonBuilder().setPrettyPrinting().create().toJson(this)
}