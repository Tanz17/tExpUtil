package ru.tanz.utils;

import net.minecraft.server.v1_12_R1.NBTTagCompound;
import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.v1_12_R1.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;

public class OtherUtil {

    public static String color(String s){
        return ChatColor.translateAlternateColorCodes('&', s);
    }

    public static boolean hasNBTTag(ItemStack item, String key){
        net.minecraft.server.v1_12_R1.ItemStack stack = CraftItemStack.asNMSCopy(item);
        NBTTagCompound compound = stack.getTag();

        if(compound == null || compound.getString(key) == null || compound.getString(key).isEmpty()){
            return false;
        }
        return true;
    }

    public static String getNBTTag(ItemStack item, String key){
        try{
            net.minecraft.server.v1_12_R1.ItemStack stack = CraftItemStack.asNMSCopy(item);
            NBTTagCompound compound = stack.getTag();


            assert compound != null;
            return  compound.getString(key).isEmpty() ? null : compound.getString(key);
        } catch (Exception ex){
            return null;
        }
    }

}
