package com.masahirosaito.spigot.allmining

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.masahirosaito.spigot.allmining.commands.AMCommand
import com.masahirosaito.spigot.allmining.listeners.BlockBreakListener
import com.masahirosaito.spigot.allmining.nms.NMS
import com.masahirosaito.spigot.allmining.nms.NMS_V1_11_R1
import com.masahirosaito.spigot.allmining.nms.NMS_v1_10_R1
import org.bukkit.event.Listener
import org.bukkit.plugin.java.JavaPlugin
import java.io.File
import java.util.*

class AllMining : JavaPlugin() {
    lateinit var configs: Configs
    lateinit var messenger: Messenger
    lateinit var nms: NMS
    lateinit var playerdata: Playerdata

    override fun onEnable() {
        configs = Configs.load(File(dataFolder, "configs.json").load())
        messenger = Messenger(this, false)
        nms = getNMS()
        playerdata = Playerdata.load(File(dataFolder, "playerdata.json").load())

        checkUpdate()

        BlockBreakListener(this).register()
        getCommand("am").executor = AMCommand(this)
    }

    override fun onDisable() {
        playerdata.save(File(dataFolder, "playerdata.json"))
    }

    private fun checkUpdate() {
        UpdateChecker(this).sendVersionMessage()
    }

    private fun getNMS(): NMS = when (server.bukkitVersion) {
        "1.10.2-R0.1-SNAPSHOT" -> NMS_v1_10_R1()
        "1.11.2-R0.1-SNAPSHOT" -> NMS_V1_11_R1()
        else -> throw Exception()
    }

    private fun <T : Listener> T.register() = apply { server.pluginManager.registerEvents(this, this@AllMining) }

    private fun File.load(): File = this.apply {
        if (!parentFile.exists()) parentFile.mkdirs()
        if (!exists()) createNewFile()
    }

    data class Playerdata(val data: MutableMap<UUID, Boolean> = mutableMapOf()) {
        companion object {
            fun load(file: File): Playerdata {
                return Gson().fromJson(file.readText().let {
                    if (it.isNullOrBlank()) Playerdata().toJson() else it
                }, Playerdata::class.java).save(file)
            }
        }

        fun save(file: File): Playerdata = this.apply { file.writeText(toJson()) }

        private fun toJson(): String = GsonBuilder().setPrettyPrinting().create().toJson(this)
    }
}
