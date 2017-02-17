package com.masahirosaito.spigot.allmining

import org.bukkit.plugin.java.JavaPlugin
import java.net.URL

class UpdateChecker(val organization: String, val repository: String, val name: String) {

    val url = URL("https://bintray.com/$organization/$repository/$name/_latestVersion")

    private fun getLatestVersion(): String {
        return url.openConnection().inputStream.bufferedReader().lines()
                .filter { it.contains(Regex("\"/$organization/$repository/$name/([\\d.]+\").*(title)")) }
                .map { it.replace(Regex("<.+?>"), "").trim() }.toArray().first() as String
    }

    fun isLatestVersion(plugin: JavaPlugin): Boolean {
        try {
            return plugin.description.version == getLatestVersion()
        } catch(e: Exception) {
            return true
        }
    }

    fun sendVersionMessage(plugin: JavaPlugin) {
        Thread {
            if (!isLatestVersion(plugin)) {
                plugin.logger.info("New version available!")
                plugin.logger.info("Download from => $url")
            }
        }.start()
    }
}