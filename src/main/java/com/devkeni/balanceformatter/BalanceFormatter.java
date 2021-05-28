package com.devkeni.balanceformatter;

import com.devkeni.balanceformatter.placeholder.PlaceholderHook;
import com.devkeni.balanceformatter.util.NumberUtils;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.plugin.java.JavaPlugin;

public final class BalanceFormatter extends JavaPlugin {

    Economy economy;
    NumberUtils numberUtils;
    PlaceholderHook placeholder;

    @Override
    public void onEnable() {

        //Configuration
        saveDefaultConfig();

        //Economy
        economy = getServer().getServicesManager().getRegistration(Economy.class).getProvider();

        if (economy == null) {
            getLogger().severe("A error ocurred when loading vault service.");
            getLogger().severe("Try update your vault and restart the server.");
            setEnabled(false);
            return;
        }

        //Utils
        numberUtils = new NumberUtils(this, economy);

        //Placeholder
        placeholder = new PlaceholderHook(this, numberUtils);
        placeholder.register();
    }

    @Override
    public void onDisable() {
        placeholder.unregister();
    }

}