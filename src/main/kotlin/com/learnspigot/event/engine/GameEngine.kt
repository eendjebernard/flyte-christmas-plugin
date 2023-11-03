package com.learnspigot.event.engine

import com.learnspigot.event.ChristmasEvent.Companion.LOBBY_SPAWN
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.TextDecoration
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import java.util.*

object GameEngine {

    var type: GameType? = null
    var game: Game? = null

    val points = mutableMapOf<UUID, Int>()

    fun start() {
        game = type!!.clazz.getDeclaredConstructor().newInstance()

        Bukkit.getOnlinePlayers().forEach {
            it.teleport(type!!.spawns.random())
        }

        Bukkit.broadcast(Component.text("                                                                               ").decorate(TextDecoration.STRIKETHROUGH)
                .appendNewline()
                .append(type!!.title).append(Component.text(" game starte")
                .appendNewline()
                .append(Component.text("                                                                               ").decorate(TextDecoration.STRIKETHROUGH))))
    }

    fun stop() {
        Bukkit.getOnlinePlayers().forEach {
            it.teleport(LOBBY_SPAWN)
        }

        type = null
        game!!.stop()
        game = null

        Bukkit.broadcast(Component.text("                                                                               ").decorate(TextDecoration.STRIKETHROUGH)
            .appendNewline()
            .append(Component.text("game ended"))
            .appendNewline()
            .append(Component.text("                                                                               ").decorate(TextDecoration.STRIKETHROUGH)))
    }

    fun onPlayerJoin(player: Player) {
        points.getOrPut(player.uniqueId) { 1 }
        game?.onPlayerJoin(player)
    }

    fun onPlayerQuit(player: Player) {
        game?.onPlayerQuit(player)
    }

}