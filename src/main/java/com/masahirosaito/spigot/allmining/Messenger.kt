package com.masahirosaito.spigot.allmining

import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin

class Messenger(
    private val plugin: JavaPlugin,
    private val onDebug: Boolean,
) {

    private fun prefix(obj: Any) = "[${plugin.name}] $obj"

    private fun log(obj: Any) {
        Bukkit.getServer().consoleSender.sendMessage(prefix(obj))
    }

    fun debug(obj: Any) {
        if (onDebug) log("${ChatColor.AQUA}[DEBUG]${ChatColor.RESET} $obj")
    }

    fun send(player: Player, obj: Any) {
        player.sendMessage(prefix(obj))
    }
}