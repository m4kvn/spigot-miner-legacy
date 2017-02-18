package com.masahirosaito.spigot.allmining

import com.google.gson.Gson
import org.bukkit.plugin.java.JavaPlugin
import java.net.URL

class UpdateChecker(val plugin: JavaPlugin) {

    data class Tag(val name: String)

    val tags = getTags(URL("https://api.github.com/repos/MasahiroSaito/AllMining/tags"))

    fun getLatestVersion(): String = (tags.maxBy { it.name.toDouble() } ?: Tag(plugin.description.version)).name

    fun sendVersionMessage() {
        Thread {
            val latestVersion = getLatestVersion()
            if (plugin.description.version < latestVersion) {
                plugin.logger.info("New version $latestVersion available!")
                plugin.logger.info("Download from => https://www.spigotmc.org/resources/allmining.36443/")
            }
        }.start()
    }

    private fun getTags(url: URL): Array<Tag> =
            Gson().fromJson(url.openConnection().inputStream.bufferedReader().readLine(), Array<Tag>::class.java)
}