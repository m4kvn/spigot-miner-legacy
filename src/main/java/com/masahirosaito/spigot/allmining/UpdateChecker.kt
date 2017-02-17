package com.masahirosaito.spigot.allmining

import org.bukkit.plugin.java.JavaPlugin
import java.net.URL

class UpdateChecker {

    val url = URL("https://www.spigotmc.org/resources/allmining.36443/history")

     fun getLatestVersion(): String {
        return (url.openConnection().apply { setRequestProperty("User-Agent","Mozilla/5.0") }
                .inputStream.bufferedReader()
                .lines().filter { it.contains(Regex("<span class=\"muted\">")) }
                .map { it.replace(Regex("<.+?>"), "").trim() }
                .toArray().first() as String).replace(Regex("[^\\d.]"), "").trim()
    }

    fun sendVersionMessage(plugin: JavaPlugin) {
        Thread {
            val latestVersion = getLatestVersion()
            if (plugin.description.version < latestVersion) {
                plugin.logger.info("New version $latestVersion available!")
                plugin.logger.info("Download from => ${url.toString().replace("history", "")}")
            }
        }.start()
    }
}