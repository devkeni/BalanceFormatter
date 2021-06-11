package com.devkeni.balanceformatter;

import com.devkeni.balanceformatter.placeholder.PlaceholderHook;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.plugin.java.JavaPlugin;

public final class BalanceFormatter extends JavaPlugin {

    private PlaceholderHook placeholder;

    @Override
    public void onEnable() {

        // Configuration
        saveDefaultConfig();

        // Economy
        Economy economy = getServer().getServicesManager().getRegistration(Economy.class).getProvider();

        if (economy == null) {
            getLogger().severe("A error ocurred when loading vault service.");
            getLogger().severe("Try update your vault and restart the server.");
            setEnabled(false);
            return;
        }

        // Placeholder
        placeholder = new PlaceholderHook(this, economy);
        placeholder.register();
    }

    @Override
    public void onDisable() {
        placeholder.unregister();
    }

    public static BalanceFormatter getInstance() {
        return getPlugin(BalanceFormatter.class);
    }

}