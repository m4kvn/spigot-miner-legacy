package com.masahirosaito.spigot.allmining.commands

import com.masahirosaito.spigot.allmining.AllMining
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class AMCommand(plugin: AllMining) : CommandExecutor {
    val playerdata = plugin.playerdata
    val messenger = plugin.messenger

    override fun onCommand(sender: CommandSender?, command: Command?,
                           label: String?, args: Array<out String>?): Boolean {

        if (sender !is Player) return true

        if (args == null || args.isEmpty()) return true

        return when (args[0]) {
            "on" -> on(sender)
            "off" -> off(sender)
            else -> true
        }
    }

    fun on(player: Player): Boolean {
        playerdata.data.put(player.uniqueId, true)
        messenger.send(player, "AllMining ON")
        return true
    }

    fun off(player: Player): Boolean {
        playerdata.data.put(player.uniqueId, false)
        messenger.send(player, "AllMining OFF")
        return true
    }
}