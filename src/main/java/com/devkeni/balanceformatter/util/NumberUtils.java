package com.devkeni.balanceformatter.util;

import com.devkeni.balanceformatter.BalanceFormatter;

import java.text.DecimalFormat;

public class NumberUtils {

    private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("###.#");

    private static String formatLarge(double n, int iteration) {
        double f = n / 1000.0D;
        return f < 1000 || iteration >= getNumberFormat().length - 1 ?
                DECIMAL_FORMAT.format(f) + getNumberFormat()[iteration] : formatLarge(f, iteration + 1);
    }

    public static String format(double value) {
        return value < 1000 ? DECIMAL_FORMAT.format(value) : formatLarge(value, 0);
    }

    private static String[] getNumberFormat() {
        return BalanceFormatter.getInstance().getConfig().getString("money-format").split(";");
    }

}