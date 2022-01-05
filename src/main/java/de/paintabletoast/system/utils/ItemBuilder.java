package de.paintabletoast.system.utils;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;

public class ItemBuilder extends ItemStack {
    private ItemMeta itemmeta;
    public ItemBuilder(final Material material) {
        super(material);
        itemmeta = this.getItemMeta();
    }
    public ItemBuilder(final Material material, final int amount) {
        super(material, amount);
        itemmeta = this.getItemMeta();
    }
    public ItemBuilder(final Material material, final int amount, final byte subid) {
        super(material, amount, subid);
        itemmeta = this.getItemMeta();
    }
    public final ItemBuilder setDisplayName(final String displayName) {
        itemmeta.setDisplayName(displayName);
        this.setItemMeta(itemmeta);
        return this;
    }
    public final String getDisplayName() {
        return itemmeta.getDisplayName();
    }
    public final ItemBuilder setLore(final String lore, final String split) {
        itemmeta.setLore(Arrays.asList(lore.split(split)));
        this.setItemMeta(itemmeta);
        return this;
    }
    public final ItemBuilder setLore(final ArrayList<String> lore) {
        itemmeta.setLore(lore);
        this.setItemMeta(itemmeta);
        return this;
    }
    public final ArrayList<String> getLore() {
        return (ArrayList<String>) itemmeta.getLore();

    }
    public final ItemBuilder setCustomModelData(final int customModelData) {
        this.setCustomModelData(customModelData);
        return this;
    }
    public final int getCustomModelData() {
        return this.getCustomModelData();
    }
}
