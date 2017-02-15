package com.masahirosaito.spigot.allmining

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.annotations.SerializedName
import com.masahirosaito.spigot.mscore.materials.DamagedMaterial
import org.bukkit.Material
import java.io.File

data class Configs(

        @SerializedName("採掘時にデータを表示する")
        val onMiningData: Boolean = true,

        @SerializedName("スニーキング状態の時のみ採掘する")
        val onSneaking: Boolean = false,

        @SerializedName("クリエイティブモード状態のときも採掘する")
        val onCreative: Boolean = false,

        @SerializedName("採掘を行う道具の種類")
        val pickaxes: List<Material> = listOf(
                Material.DIAMOND_PICKAXE,
                Material.GOLD_PICKAXE,
                Material.IRON_PICKAXE,
                Material.STONE_PICKAXE,
                Material.WOOD_PICKAXE
        ),

        @SerializedName("採掘できる鉱石の種類")
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