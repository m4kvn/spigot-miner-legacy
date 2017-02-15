package com.masahirosaito.spigot.allmining.utils

import org.bukkit.event.Cancellable

fun Cancellable.cancel() {
    isCancelled = true
}