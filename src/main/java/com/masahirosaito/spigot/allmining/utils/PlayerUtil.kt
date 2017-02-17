package com.masahirosaito.spigot.allmining.utils

import org.bukkit.GameMode
import org.bukkit.entity.ExperienceOrb
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

val Player.itemInMainHand: ItemStack get() = inventory.itemInMainHand

fun Player.isCreativeMode() = gameMode == GameMode.CREATIVE

fun Player.spawnExp(amount: Int) {
    location.world.spawn(location, ExperienceOrb::class.java).experience = amount
}