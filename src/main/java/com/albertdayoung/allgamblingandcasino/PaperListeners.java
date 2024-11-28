package com.albertdayoung.allgamblingandcasino;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerChatEvent;


public class PaperListeners implements Listener {
    
    @EventHandler
    public void onPlayerDeath(final EntityDeathEvent event) {
        Entity entity = event.getEntity();
        EntityDamageEvent damageEvent = entity.getLastDamageCause();

        // We check if the entity that dies is a Player
        if (entity instanceof Player player) {
            DamageCause damageCause = damageEvent.getCause();

            UUID playerUuid = player.getUniqueId();

            // Next we process logic on if the dead Player had a bet on it
            UUID playerBetOwner = PeakGambling.deathBets.getBetOwner(playerUuid);

            if (PeakGambling.deathBets.isBetOnPlayer(playerUuid)) {
                if (PeakGambling.deathBets.getBetDeathCause(playerUuid).equals(damageCause)) {
                    PeakGambling.getEconomy().depositPlayer(Bukkit.getServer().getPlayer(playerBetOwner), PeakGambling.deathBets.getBet(playerUuid).getBetAmount()*2);
                    Bukkit.getServer().broadcastMessage(String.format("[%s] (%s) bet on (%s) died and got ($%s)", PeakGambling.PLUGIN, Bukkit.getServer().getPlayer(playerBetOwner).getName(), Bukkit.getServer().getPlayer(playerUuid).getName(), String.valueOf(PeakGambling.deathBets.getBet(playerUuid).getBetAmount())));
                    PeakGambling.deathBets.removeBet(playerBetOwner, playerUuid);
                } else {
                    PeakGambling.deathBets.removeBet(playerBetOwner, playerUuid);
                }
            }
            //Bukkit.getServer().broadcastMessage(String.format("Player '%s' died by '%s' with cause '%s'", player.getName(), entityEvent.getDamager().getType().toString(), damageCause.toString()));
        }
    }


    @EventHandler
    public void onSigmaMessageChat(PlayerChatEvent event) {
        String message = event.getMessage();
        if (message != null && message.contains("sigma")) {
            event.getPlayer().kickPlayer("DON'T!!!");
        }
    }
}
