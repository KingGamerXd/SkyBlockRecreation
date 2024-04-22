package net.hamza.skyblock.item;

import net.minecraft.server.v1_8_R3.NBTTagCompound;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;

import java.util.Objects;

public class NBTItem {

    private ItemStack item;

    private NBTItem(ItemStack stack){
        this.item = stack;
    }

    public static NBTItem of(ItemStack stack){
        Objects.requireNonNull(stack , " [NBTItem] Item is null!");
        return new NBTItem(stack);
    }

    public NBTItem setString(String key , String value){
        net.minecraft.server.v1_8_R3.ItemStack nmsItem = CraftItemStack.asNMSCopy(item);
        NBTTagCompound compound = nmsItem.getTag() != null ? nmsItem.getTag() : new NBTTagCompound();
        compound.setString(key , value);
        nmsItem.setTag(compound);
        item = CraftItemStack.asBukkitCopy(nmsItem);
        return this;
    }

    public String getString(String key){
        net.minecraft.server.v1_8_R3.ItemStack nmsItem = CraftItemStack.asNMSCopy(item);
        NBTTagCompound compound = nmsItem.getTag();
        return compound != null && compound.hasKey(key) ? compound.getString(key) : null;
    }

    public NBTItem setInt(String key , int value){
        net.minecraft.server.v1_8_R3.ItemStack nmsItem = CraftItemStack.asNMSCopy(item);
        NBTTagCompound compound = nmsItem.getTag() != null ? nmsItem.getTag() : new NBTTagCompound();
        compound.setInt(key , value);
        nmsItem.setTag(compound);
        item = CraftItemStack.asBukkitCopy(nmsItem);
        return this;
    }

    public Integer getInt(String key){
        net.minecraft.server.v1_8_R3.ItemStack nmsItem = CraftItemStack.asNMSCopy(item);
        NBTTagCompound compound = nmsItem.getTag();
        return compound != null && compound.hasKey(key) ? compound.getInt(key) : null;
    }

    public NBTItem setDouble(String key , double value){
        net.minecraft.server.v1_8_R3.ItemStack nmsItem = CraftItemStack.asNMSCopy(item);
        NBTTagCompound compound = nmsItem.getTag() != null ? nmsItem.getTag() : new NBTTagCompound();
        compound.setDouble(key , value);
        nmsItem.setTag(compound);
        item = CraftItemStack.asBukkitCopy(nmsItem);
        return this;
    }

    public Double getDouble(String key){
        net.minecraft.server.v1_8_R3.ItemStack nmsItem = CraftItemStack.asNMSCopy(item);
        NBTTagCompound compound = nmsItem.getTag();
        return compound != null && compound.hasKey(key) ? compound.getDouble(key) : null;
    }


    public NBTItem setLong(String key , long value){
        net.minecraft.server.v1_8_R3.ItemStack nmsItem = CraftItemStack.asNMSCopy(item);
        NBTTagCompound compound = nmsItem.getTag() != null ? nmsItem.getTag() : new NBTTagCompound();
        compound.setLong(key , value);
        nmsItem.setTag(compound);
        item = CraftItemStack.asBukkitCopy(nmsItem);
        return this;
    }

    public Long getLong(String key){
        net.minecraft.server.v1_8_R3.ItemStack nmsItem = CraftItemStack.asNMSCopy(item);
        NBTTagCompound compound = nmsItem.getTag();
        return compound != null && compound.hasKey(key) ? compound.getLong(key) : null;
    }

    public NBTItem setBoolean(String key , boolean value){
        net.minecraft.server.v1_8_R3.ItemStack nmsItem = CraftItemStack.asNMSCopy(item);
        NBTTagCompound compound = nmsItem.getTag() != null ? nmsItem.getTag() : new NBTTagCompound();
        compound.setBoolean(key , value);
        nmsItem.setTag(compound);
        item = CraftItemStack.asBukkitCopy(nmsItem);
        return this;
    }

    public Boolean getBoolean(String key){
        net.minecraft.server.v1_8_R3.ItemStack nmsItem = CraftItemStack.asNMSCopy(item);
        NBTTagCompound compound = nmsItem.getTag();
        return compound != null && compound.hasKey(key) && compound.getBoolean(key);
    }

    public ItemStack build(){
        return item;
    }

    @Override
    public String toString() {
        if (CraftItemStack.asNMSCopy(item).getTag() == null) return "";
        return CraftItemStack.asNMSCopy(item).getTag().toString();
    }
}
