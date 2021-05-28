package com.devkeni.balanceformatter.util;

import lombok.RequiredArgsConstructor;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.text.DecimalFormat;

@RequiredArgsConstructor
public class NumberUtils {

    private final Plugin plugin;
    private final Economy economy;

    private final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("###,###.##");

    public String getMoneyFormatted(Player player) {
        return format(economy.getBalance(player));
    }

    private String formatLarge(double n, int iteration) {
        double f = n / 1000.0D;
        return f < 1000 || iteration >= getNumberFormat().length - 1 ?
                formatShort(f) + getNumberFormat()[iteration] : formatLarge(f, iteration + 1);
    }

    private String formatShort(double value) {
        return DECIMAL_FORMAT.format(value);
    }

    private String format(double value) {
        return value < 1000 ?  formatShort(value) : formatLarge(value, 0);
    }

    private String[] getNumberFormat() {
        return plugin.getConfig().getString("money-format").split(";");
    }

}