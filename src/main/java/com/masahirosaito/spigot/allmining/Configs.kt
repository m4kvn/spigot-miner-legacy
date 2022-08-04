package com.masahirosaito.spigot.allmining

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.annotations.SerializedName
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
        Material.GOLDEN_PICKAXE,
        Material.IRON_PICKAXE,
        Material.STONE_PICKAXE,
        Material.WOODEN_PICKAXE,
    ),

    @SerializedName("Types of ores that can be mined")
    val ores: List<Material> = listOf(
        Material.OBSIDIAN,
        Material.GLOWSTONE,
        Material.COAL_ORE,
        Material.IRON_ORE,
        Material.GOLD_ORE,
        Material.REDSTONE_ORE,
        Material.LAPIS_ORE,
        Material.NETHER_QUARTZ_ORE,
        Material.NETHER_GOLD_ORE,
        Material.EMERALD_ORE,
        Material.DIAMOND_ORE,
    )
) {
    companion object {
        fun load(file: File): Configs {
            return Gson().fromJson(file.readText().let {
                it.ifBlank { Configs().toJson() }
            }, Configs::class.java).apply { file.writeText(toJson()) }
        }
    }

    private fun toJson(): String = GsonBuilder().setPrettyPrinting().create().toJson(this)
}