package com.masahirosaito.spigot.allmining

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.masahirosaito.spigot.allmining.commands.AMCommand
import com.masahirosaito.spigot.allmining.listeners.BlockBreakListener
import com.masahirosaito.spigot.allmining.nms.NMS
import com.masahirosaito.spigot.allmining.nms.NMS_V1_19
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
        nms = NMS_V1_19()
        playerdata = Playerdata.load(File(dataFolder, "playerdata.json").load())

        BlockBreakListener(this).register()
        getCommand("am")?.setExecutor(AMCommand(this))
    }

    override fun onDisable() {
        playerdata.save(File(dataFolder, "playerdata.json"))
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
                    it.ifBlank { Playerdata().toJson() }
                }, Playerdata::class.java).save(file)
            }
        }

        fun save(file: File): Playerdata = this.apply { file.writeText(toJson()) }

        private fun toJson(): String = GsonBuilder().setPrettyPrinting().create().toJson(this)
    }
}
