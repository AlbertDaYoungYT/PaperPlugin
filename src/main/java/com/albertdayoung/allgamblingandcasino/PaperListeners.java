package com.albertdayoung.allgamblingandcasino;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerChatEvent;

import static com.albertdayoung.allgamblingandcasino.gui.components.theme.Theme.defaultChatMessage;
import com.albertdayoung.allgamblingandcasino.utils.dataclasses.BetOnPlayerDeathData;


public class PaperListeners implements Listener {
    
    @EventHandler
    public void onPlayerDeath(final EntityDeathEvent event) {
        Entity entity = event.getEntity();
        EntityDamageEvent damageEvent = entity.getLastDamageCause();

        String deathActionType = "";

        // We check if the entity that dies is a Player
        if (entity instanceof Player player) {
            DamageCause damageCause = damageEvent.getCause();

            UUID betTarget = player.getUniqueId();
            UUID betOwner = null;
            OfflinePlayer betTargetPlayer = Bukkit.getServer().getOfflinePlayer(player.getUniqueId());
            OfflinePlayer betOwnerPlayer = null;
            
            BetOnPlayerDeathData bet = PeakGambling.deathBets.getBet(betTarget);
            
            // Next we process logic on if the dead Player had a bet on it
            if (PeakGambling.deathBets.isBetOnPlayer(betTarget)) {
                betOwner = PeakGambling.deathBets.getBetOwner(betTarget);
                betOwnerPlayer = Bukkit.getServer().getOfflinePlayer(PeakGambling.deathBets.getBetOwner(betTarget));
                if (PeakGambling.deathBets.getBetDeathCause(betTarget).equals(damageCause)) {
                    deathActionType = "TARGET_DIED_FROM_BET_CAUSE";
                } else {
                    deathActionType = "TARGET_DIED_FROM_ANOTHER_CAUSE";
                }
                PeakGambling.LOGGER.info(String.format("[%s] Player Death Event Triggered by (%s), action (%s)", PeakGambling.PLUGIN, betTargetPlayer.getName(), deathActionType));
            }
            
            if (betOwner != null && betOwnerPlayer != null) {
                switch (deathActionType) {
                    case "TARGET_DIED_FROM_ANOTHER_CAUSE":
                        Bukkit.getServer().getPlayer(betTarget).sendMessage(PeakGambling.miniMessage.deserialize(String.format("[<dark_green>%s<white>]: <black>(<red>%s<black>)<dark_green> made a bet on your death but failed, so you got <black>(<green>$%s<black>)", PeakGambling.PLUGIN, betOwnerPlayer.getName(), String.valueOf(bet.getBetAmount()))));
                        
                        PeakGambling.getEconomy().depositPlayer(betTargetPlayer, bet.getBetAmount());
    
                        PeakGambling.deathBets.removeBet(betOwner, betTarget);
                        break;
    
                    case "TARGET_DIED_FROM_BET_CAUSE":
                        Bukkit.getServer().broadcast(defaultChatMessage(String.format("<black>(<red>%s<black>)<dark_green> bet on <black>(<red>%s<black>)<dark_green> died and got <black>(<green>$%s<black>)", betOwnerPlayer.getName(), betTargetPlayer.getName(), String.valueOf(bet.getBetAmount()))));
    
                        PeakGambling.getEconomy().depositPlayer(betOwnerPlayer, bet.getBetAmount()*2);
                        PeakGambling.getEconomy().withdrawPlayer(betTargetPlayer, bet.getBetAmount());
    
                        PeakGambling.deathBets.removeBet(betOwner, betTarget);
                        break;
    
                    case "TARGET_DIED_FROM_ANOTHER_PLAYER":
                        //Bukkit.getServer().getPlayer(betTarget).sendMessage(Component.text(String.format("[%s]: (%s) had a bet on him, so you got ($%s)", PeakGambling.PLUGIN, betOwnerPlayer.getName(), String.valueOf(bet.getBetAmount()))));
    
                        //PeakGambling.getEconomy().depositPlayer(Bukkit.getServer().getPlayer(betOwner), bet.getBetAmount()*2);
                        //PeakGambling.getEconomy().withdrawPlayer(Bukkit.getServer().getPlayer(betTarget), bet.getBetAmount());
                        
                        
                        PeakGambling.deathBets.removeBet(betOwner, betTarget);
                        break;
                        
                    default:
                        throw new AssertionError();
                }
            }
            //Bukkit.getServer().broadcastMessage(String.format("Player '%s' died by '%s' with cause '%s'", player.getName(), entityEvent.getDamager().getType().toString(), damageCause.toString()));
        }
    }


    @EventHandler
    public void onSigmaMessageChat(PlayerChatEvent event) {
        String message = event.getMessage().toLowerCase();
        if (message != null && message.contains("sigma")) {
            event.getPlayer().kickPlayer("DON'T!!!");
        } else
        if (message != null && message.contains("skibidi")) {
            event.getPlayer().kickPlayer("DON'T!!!");
        } else
        if (message != null && message.contains("rizz")) {
            event.getPlayer().kickPlayer("DON'T!!!");
        } 
    }
}
