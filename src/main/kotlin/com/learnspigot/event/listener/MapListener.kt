package com.learnspigot.event.listener

import gg.flyte.twilight.event.event
import org.bukkit.event.EventPriority
import org.bukkit.event.block.BlockBreakEvent
import org.bukkit.event.block.BlockPlaceEvent
import org.bukkit.event.entity.EntityDamageEvent

object MapListener {

    init {
        event<BlockBreakEvent> {
            isCancelled = !player.isOp
        }

        event<BlockPlaceEvent> {
            isCancelled = !player.isOp
        }

        event<EntityDamageEvent> {
            isCancelled = !entity.isOp
        }
    }

}