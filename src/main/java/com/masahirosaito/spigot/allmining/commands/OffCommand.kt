package com.masahirosaito.spigot.allmining.commands

import com.masahirosaito.spigot.allmining.AllMining
import org.bukkit.ChatColor
import org.bukkit.entity.Player

class OffCommand(
    override val plugin: AllMining,
    override val name: String = "off",
    override val permission: String = "allmining.command.off"
) : SubCommand {

    override fun execute(player: Player, args: List<String>) {
        plugin.playerdata.data[player.uniqueId] = false
        plugin.messenger.send(player, "${ChatColor.BLUE}AllMining OFF${ChatColor.RESET}")
    }
}