package com.albertdayoung.allgamblingandcasino.gui;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

import com.albertdayoung.allgamblingandcasino.PeakGambling;
import com.albertdayoung.allgamblingandcasino.gui.components.helpers.GuiContainerLayout;
import static com.albertdayoung.allgamblingandcasino.gui.components.theme.Theme.defaultChatMessage;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.albertdayoung.allgamblingandcasino.gui.pages.betOnPlayer.BetOnDeathTypePage;
import com.albertdayoung.allgamblingandcasino.utils.dataclasses.DeathOptionsData;

import dev.triumphteam.gui.paper.Gui;
import dev.triumphteam.gui.paper.builder.item.ItemBuilder;
import dev.triumphteam.nova.MutableState;
import net.kyori.adventure.text.Component;

public class MainPlinko extends GuiContainerLayout {
    MutableState<Integer> betAmount;

    public static Material INCREMENT_MATERIAL = Material.LIME_STAINED_GLASS_PANE;
    public static Material DECREMENT_MATERIAL = Material.RED_STAINED_GLASS_PANE;


    public MainPlinko() {
        this.betAmount = null;
    }

    public void open(Player _player) {
        Gui.of(1)
            .title(Component.text(String.format("Amount per ball (Max: $%s)", String.valueOf(round(PeakGambling.getEconomy().getBalance(_player), 2)))))
            .component(component -> {

                this.betAmount = component.remember(10);
                

                component.render(container -> {
                    container.setItem(1, 1, ItemBuilder.from(DECREMENT_MATERIAL)
                            .name(Component.text("Decrement by 50"))
                            .amount(50)
                            .asGuiItem((player, context) -> {
                                if (!(this.betAmount.get() < 50)) {
                                    this.betAmount.update(previous -> previous - 50);
                                }
                            })
                    );
                    container.setItem(1, 2, ItemBuilder.from(DECREMENT_MATERIAL)
                            .name(Component.text("Decrement by 25"))
                            .amount(25)
                            .asGuiItem((player, context) -> {
                                if (!(this.betAmount.get() < 25)) {
                                    this.betAmount.update(previous -> previous - 25);
                                }
                            })
                    );
                    container.setItem(1, 3, ItemBuilder.from(DECREMENT_MATERIAL)
                            .name(Component.text("Decrement by 10"))
                            .amount(10)
                            .asGuiItem((player, context) -> {
                                if (!(this.betAmount.get() < 10)) {
                                    this.betAmount.update(previous -> previous - 10);
                                }
                            })
                    );
                    container.setItem(1, 4, ItemBuilder.from(DECREMENT_MATERIAL)
                            .name(Component.text("Decrement by 1"))
                            .amount(1)
                            .asGuiItem((player, context) -> {
                                if (!(this.betAmount.get() < 1)) {
                                    this.betAmount.update(previous -> previous - 1);
                                }
                            })
                    );


                    
                    container.setItem(1, 6, ItemBuilder.from(INCREMENT_MATERIAL)
                            .name(Component.text("Increment by 1"))
                            .amount(1)
                            .asGuiItem((player, context) -> {
                                this.betAmount.update(previous -> previous + 1);
                            })
                    );
                    container.setItem(1, 7, ItemBuilder.from(INCREMENT_MATERIAL)
                            .name(Component.text("Increment by 10"))
                            .amount(10)
                            .asGuiItem((player, context) -> {
                                this.betAmount.update(previous -> previous + 10);
                            })
                    );
                    container.setItem(1, 8, ItemBuilder.from(INCREMENT_MATERIAL)
                            .name(Component.text("Increment by 25"))
                            .amount(25)
                            .asGuiItem((player, context) -> {
                                this.betAmount.update(previous -> previous + 25);
                            })
                    );
                    container.setItem(1, 9, ItemBuilder.from(INCREMENT_MATERIAL)
                            .name(Component.text("Increment by 50"))
                            .amount(50)
                            .asGuiItem((player, context) -> {
                                this.betAmount.update(previous -> previous + 50);
                            })
                    );


                    container.setItem(1, 5, ItemBuilder.from(Material.GOLD_BLOCK)
                            .name(Component.text(String.format("Bet $%s", this.betAmount.get())))
                            .asGuiItem((player, context) -> {
                                if (this.betAmount.get() <= PeakGambling.getEconomy().getBalance(_player)) {
                                    //betOnDeathTypePage.open(_player);
                                }
                                
                                player.sendMessage(defaultChatMessage("Insufficient balance"));
                                //_player.sendMessage(String.format("It works ($%s)", this.betAmount.get().toString()));
                            })
                    );
                });

            })
            .build()
            .open(_player);
    }

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
