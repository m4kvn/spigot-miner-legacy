package com.masahirosaito.spigot.allmining.commands

import com.masahirosaito.spigot.allmining.AllMining
import org.bukkit.entity.Player

interface SubCommand {
    val plugin: AllMining
    val name: String
    val permission: String

    fun execute(player: Player, args: List<String>)

    fun hasPermission(player: Player): Boolean {
        return if (permission.isNullOrBlank()) true else player.hasPermission(permission)
    }
}