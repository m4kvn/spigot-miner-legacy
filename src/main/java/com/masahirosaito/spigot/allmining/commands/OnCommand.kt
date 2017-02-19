package com.masahirosaito.spigot.allmining.commands

import com.masahirosaito.spigot.allmining.AllMining
import org.bukkit.ChatColor
import org.bukkit.entity.Player

class OnCommand(
        override val plugin: AllMining,
        override val name: String = "on",
        override val permission: String = "allmining.command.on"
) : SubCommand {

    override fun execute(player: Player, args: List<String>) {
        plugin.playerdata.data.put(player.uniqueId, true)
        plugin.messenger.send(player, "${ChatColor.RED}AllMining ON${ChatColor.RESET}")
    }
}