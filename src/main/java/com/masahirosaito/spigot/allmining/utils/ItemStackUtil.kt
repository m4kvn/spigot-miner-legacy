package com.masahirosaito.spigot.allmining.utils

import org.bukkit.inventory.ItemStack

val ItemStack.remainingDurability: Int get() = type.maxDurability - durability