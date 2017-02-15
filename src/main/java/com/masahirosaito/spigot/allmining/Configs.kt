package com.masahirosaito.spigot.allmining

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.annotations.SerializedName
import java.io.File

data class Configs(

        @SerializedName("採掘時にデータを表示する")
        val onMiningData: Boolean = true,

        @SerializedName("スニーキング状態の時のみ採掘する")
        val onSneaking: Boolean = false,

        @SerializedName("クリエイティブモード状態のときも採掘する")
        val onCreative: Boolean = false
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