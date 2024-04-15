package net.hamza.skyblock.item;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ItemBuilder {

    private ItemStack item;
    private final ItemMeta meta;


    public ItemBuilder(ItemStack stack){
        this.item = stack;
        this.meta = stack.getItemMeta();
    }

    public ItemBuilder(Material material , int amount){
       this(new ItemStack(material , amount));
    }

    public ItemBuilder(Material material){
        this(material , 1);
    }


    public ItemBuilder setAmount(int amount){
        item.setAmount(amount);
        return this;
    }


    public ItemBuilder setDisplayName(String name){
        meta.setDisplayName(name);
        item.setItemMeta(meta);
        return this;
    }

    public ItemBuilder setLore(List<String> lore) {
        meta.setLore(lore);
        item.setItemMeta(meta);
        return this;
    }

    public ItemBuilder setLore(String... lines) {
        List<String> lore = Arrays.stream(lines).map(line -> ChatColor.translateAlternateColorCodes('&', line)).collect(Collectors.toList());
        return setLore(lore);
    }

    public ItemBuilder setLore(String lore){
        return setLore(new String[]{lore});
    }

    public ItemStack build(){
        return item;
    }


}
