package com.masahirosaito.spigot.allmining

import com.masahirosaito.spigot.mscore.Messenger
import com.masahirosaito.spigot.mscore.UpdateChecker
import org.bukkit.plugin.java.JavaPlugin

class AllMining : JavaPlugin() {
    lateinit var messenger: Messenger

    override fun onEnable() {
        messenger = Messenger(this, true)

        checkUpdate()
    }

    override fun onDisable() {
        // Plugin shutdown logic
    }

    private fun checkUpdate() {
        UpdateChecker("masahirosaito-repo", "Spigot-Plugin", "AllMining").sendVersionMessage(this)
    }
}
