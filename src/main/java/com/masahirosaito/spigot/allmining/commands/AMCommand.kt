package com.masahirosaito.spigot.allmining.commands

import com.masahirosaito.spigot.allmining.AllMining
import org.bukkit.ChatColor
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.contract

class AMCommand(
    private val plugin: AllMining,
) : CommandExecutor {
    private val permission = "allmining.command"
    private val subCommands = listOf(
        OnCommand(plugin),
        OffCommand(plugin),
    )

    override fun onCommand(
        sender: CommandSender,
        command: Command,
        label: String,
        args: Array<out String>,
    ): Boolean {
        if (sender.isNotPlayer()) return true
        if (sender.hasNotPermission(permission)) {
            return sendPermissionMsg(sender, permission)
        }
        if (args.isEmpty()) return true

        val subCommand = subCommands
            .find { it.name == args[0] } ?: return true
        if (!subCommand.hasPermission(sender)) {
            return sendPermissionMsg(sender, subCommand.permission)
        }

        subCommand.execute(sender, args.drop(1))

        return true
    }

    private fun sendPermissionMsg(player: Player, permission: String): Boolean {
        plugin.messenger.send(player, buildString {
            append(ChatColor.RED)
            append("You don't have permission <$permission>")
            append(ChatColor.RESET)
        })
        return true
    }
}

fun CommandSender.hasNotPermission(permission: String) = hasPermission(permission).not()

@OptIn(ExperimentalContracts::class)
fun CommandSender.isNotPlayer(): Boolean {
    contract {
        returns(false) implies (this@isNotPlayer is Player)
    }
    return this !is Player
}