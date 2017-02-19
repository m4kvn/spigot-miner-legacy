package com.masahirosaito.spigot.allmining.commands

import com.masahirosaito.spigot.allmining.AllMining
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class AMCommand(plugin: AllMining) : CommandExecutor {
    val subCommands = listOf(OnCommand(plugin), OffCommand(plugin))

    override fun onCommand(sender: CommandSender?, command: Command?,
                           label: String?, args: Array<out String>?): Boolean {

        if (sender !is Player) return true

        if (args == null || args.isEmpty()) return true

        subCommands.find { it.name == args[0]}?.let {
            if (it.hasPermission(sender)) {
                it.execute(sender, args.drop(1))
            }
        }

        return true
    }
}