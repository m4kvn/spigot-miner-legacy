package com.masahirosaito.spigot.allmining.utils

import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

val Player.itemInMainHand: ItemStack get() = inventory.itemInMainHand
