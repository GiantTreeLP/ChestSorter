package de.gianttree.mc.chestsorter

import de.gianttree.mc.chestsorter.commands.ChestSorterCommand
import org.bukkit.plugin.java.JavaPlugin

class ChestSorter : JavaPlugin() {

    override fun onEnable() {
        this.registerCommands()
    }

    private fun registerCommands() {
        this.getCommand("chestsorter")?.apply {
            val tabExecutor = ChestSorterCommand()
            this.tabCompleter = tabExecutor
            this.setExecutor(tabExecutor)
        }
    }

    override fun onDisable() {
        // Plugin shutdown logic
    }
}
