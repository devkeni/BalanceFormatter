package com.devkeni.balanceformatter.placeholder;

import com.devkeni.balanceformatter.util.NumberUtils;
import lombok.RequiredArgsConstructor;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

@RequiredArgsConstructor
public class PlaceholderHook extends PlaceholderExpansion {

    private final Plugin plugin;
    private final Economy economy;

    @Override
    public @NotNull String getAuthor() {
        return "DevKeni";
    }

    @Override
    public @NotNull String getIdentifier() {
        return plugin.getName();
    }

    @Override
    public @NotNull String getVersion() {
        return plugin.getDescription().getVersion();
    }

    @Override
    public String onPlaceholderRequest(Player player, @NotNull String params) {
        return NumberUtils.format(economy.getBalance(player));
    }

}