package com.albertdayoung.allgamblingandcasino.gui;

import org.bukkit.Material;
import org.bukkit.entity.Player;

import com.albertdayoung.allgamblingandcasino.gambling.RouletteGame;
import com.albertdayoung.allgamblingandcasino.gui.components.buttons.RouletteBetNumber;
import com.albertdayoung.allgamblingandcasino.gui.components.helpers.GuiContainerLayout;
import com.albertdayoung.allgamblingandcasino.gui.components.theme.Theme;

import dev.triumphteam.gui.paper.Gui;
import dev.triumphteam.gui.paper.builder.item.ItemBuilder;
import dev.triumphteam.nova.ListState;
import net.kyori.adventure.text.Component;

public class MainRoulette extends GuiContainerLayout {

    public static final Material oddItem = Material.RED_WOOL;
    public static final Material evenItem = Material.BLACK_WOOL;
    public static final Material zeroItem = Material.LIME_WOOL;

    public static final Material selectedItem = Material.LIGHT_GRAY_WOOL;

    public static int i = 1;

    public static final Material HALFS_MATERIAL = Material.GRAY_DYE;

    public static final Material EVEN_MATERIAL = Material.WHITE_STAINED_GLASS_PANE;
    public static final Material ODD_MATERIAL = Material.BLACK_STAINED_GLASS_PANE;

    
    public static void open(Player _player) {
        RouletteGame game = new RouletteGame(_player, 10);
        Gui.of(6)
            .title(Component.text("Bet on Roulette"))
            .component(component -> {

                final var betMultiplierValue = component.remember(1);
                final ListState<String> bets = component.rememberMutableList();
                game.setBetMultiplier(betMultiplierValue);
                game.setBets(bets);

                component.render(container -> {

                    // ------------------- RENDER NUMBERS -------------------

                    Material usedMaterial;
                    i = 1;

                    for (int row = 1;row <= 4;row++) {
                        for (int column = 1;column <= 9;column++) {
                            if (i % 2 == 0) {
                                usedMaterial = evenItem;
                            } else {
                                usedMaterial = oddItem;
                            }

                            RouletteBetNumber bet = new RouletteBetNumber(container, i);
                            if (game.getBets().contains(String.valueOf(i))) {
                                usedMaterial = selectedItem;
                            }
                            bet.invoke(row, column, usedMaterial, game);

                            i++;
                        }
                    }
                    
                    usedMaterial = zeroItem;
                    if (game.getBets().contains("GREEN")) {
                        usedMaterial = selectedItem;
                    }
                    
                    container.setItem(6, 1, ItemBuilder.from(usedMaterial)
                                                                .name(Component.text(String.format("Bet on (%s)", 0)))
                                                                .asGuiItem((player, context) -> {
                                                                    game.addBet("GREEN");
                                                                })
                                                    );


                    
                    // ------------------- RENDER MULTIPLIERS -------------------

                    container.setItem(6, 5, ItemBuilder.from(Theme.PRIMARY_BUTTON_MATERIAL)
                                        .name(Component.text(String.format("Bet %s time(s)", betMultiplierValue.get())))
                                        .amount(betMultiplierValue.get())
                                        .asGuiItem((player, context) -> {
                                            if (game.getBets().isEmpty()) {
                                                player.sendMessage("You haven't bet on anything");
                                            } else {
                                                game.roll();
                                                player.sendMessage(
                                                    game.getBets().toString() + ", " + 
                                                    game.getBetMultipliers().toString()
                                                    );
                                                game.reset();

                                            }
                                        })
                            );
                    container.setItem(6, 4, ItemBuilder.from(Theme.BACK_BUTTON_MATERIAL)
                                        .name(Component.text("Decrement bet multiplier"))
                                        .asGuiItem((player, context) -> {
                                            game.decrementBetMultiplier(1);
                                        })
                            );
                    container.setItem(6, 6, ItemBuilder.from(Theme.NEXT_BUTTON_MATERIAL)
                                        .name(Component.text("Increment bet multiplier"))
                                        .asGuiItem((player, context) -> {
                                            game.incrementBetMultiplier(1);
                                        })
                            );


                    
                    // ------------------- RENDER GROUPS -------------------
        
                    container.setItem(5, 2, ItemBuilder.from(Theme.INACTIVE_ITEM_MATERIAL)
                                        .name(Component.text("Bet 1st (12)"))
                                        .asGuiItem((player, context) -> {
                                            game.addBet("GROUP-1");
                                        })
                            );
                    container.setItem(5, 5, ItemBuilder.from(Theme.INACTIVE_ITEM_MATERIAL)
                                        .name(Component.text("Bet 2nd (12)"))
                                        .asGuiItem((player, context) -> {
                                            game.addBet("GROUP-2");
                                        })
                            );
                    container.setItem(5, 8, ItemBuilder.from(Theme.INACTIVE_ITEM_MATERIAL)
                                        .name(Component.text("Bet 3rd (12)"))
                                        .asGuiItem((player, context) -> {
                                            game.addBet("GROUP-3");
                                        })
                            );


                    
                    // ------------------- RENDER HALFS -------------------

                    container.setItem(5, 1, ItemBuilder.from(HALFS_MATERIAL)
                            .name(Component.text("Bet 1st Half (1-18)"))
                            .asGuiItem((player, context) -> {
                                game.addBet("HALFS-1");
                            })
                    );
                    container.setItem(5, 9, ItemBuilder.from(HALFS_MATERIAL)
                            .name(Component.text("Bet 2nd Half (19-36)"))
                            .asGuiItem((player, context) -> {
                                game.addBet("HALFS-2");
                            })
                    );


                    
                    // ------------------- RENDER EVEN ODDS -------------------

                    container.setItem(6, 2, ItemBuilder.from(EVEN_MATERIAL)
                                        .name(Component.text("Bet Even"))
                                        .asGuiItem((player, context) -> {
                                            game.addBet("EVEN");
                                        })
                            );
                    container.setItem(6, 3, ItemBuilder.from(ODD_MATERIAL)
                                        .name(Component.text("Bet Odds"))
                                        .asGuiItem((player, context) -> {
                                            game.addBet("ODDS");
                                        })
                            );


                    
                    // ------------------- RENDER RED BLACK -------------------

                    usedMaterial = Material.RED_STAINED_GLASS_PANE;

                    if (game.getBets().contains("RED")) {
                        usedMaterial = Material.LIGHT_GRAY_STAINED_GLASS_PANE;
                    }
                    container.setItem(6, 7, ItemBuilder.from(usedMaterial)
                                                                .name(Component.text("Bet Red"))
                                                                .asGuiItem((player, context) -> {
                                                                    game.addBet("RED");
                                                                })
                                                    );

                    usedMaterial = Material.BLACK_STAINED_GLASS_PANE;

                    if (game.getBets().contains("BLACK")) {
                        usedMaterial = Material.LIGHT_GRAY_STAINED_GLASS_PANE;
                    }
                    
                    container.setItem(6, 8, ItemBuilder.from(usedMaterial)
                                                                .name(Component.text("Bet Black"))
                                                                .asGuiItem((player, context) -> {
                                                                    game.addBet("BLACK");
                                                                })
                                                    );
                });

            })
            .build()
            .open(_player);
    }
}