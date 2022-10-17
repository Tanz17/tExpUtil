package ru.tanz.utils;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.val;
import net.minecraft.server.v1_12_R1.NBTBase;
import net.minecraft.server.v1_12_R1.NBTTagCompound;
import net.minecraft.server.v1_12_R1.NBTTagString;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_12_R1.inventory.CraftItemStack;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.Collection;

@FieldDefaults(level= AccessLevel.PRIVATE, makeFinal = false)
public class ItemBuilder {
    ItemStack itemStack;

    public ItemBuilder(Material material){
        itemStack = new ItemStack(material);
    }

    public ItemBuilder setName(String name){
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
        itemStack.setItemMeta(itemMeta);
        return this;
    }

    public ItemBuilder setLore(Collection<String> lore){
        lore.forEach(OtherUtil::color);
        itemStack.setLore(new ArrayList<>(lore));
        return this;
    }
    public ItemBuilder addEnchant(String enchant, int level){
        if(Enchantment.getByName(enchant) != null){
            itemStack.addEnchantment(Enchantment.getByName(enchant), level);
        }
        return this;
    }
    public ItemBuilder addItemFlags(Collection<String> flags){
        if(!flags.isEmpty()) {
            flags.forEach(s -> itemStack.addItemFlags(ItemFlag.valueOf(s)));
        }
        return this;
    }
    public ItemBuilder setNBTTag(String key, NBTBase value){
        net.minecraft.server.v1_12_R1.ItemStack stack = CraftItemStack.asNMSCopy(itemStack);

        NBTTagCompound compound = stack.getTag();
        compound.set(key, value);
        stack.setTag(compound);

        ItemMeta meta = CraftItemStack.getItemMeta(stack);
        itemStack.setItemMeta(meta);
        return this;
    }
    public ItemBuilder setNBTTag(String key, String value){
        setNBTTag(key, new NBTTagString(value));
        return this;
    }


    public ItemBuilder addLoreLine(String line, int value){
        val lore = itemStack.getLore();
        lore.add(value, line);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setLore(lore);
        itemStack.setItemMeta(itemMeta);
        return this;
    }

    public ItemBuilder setUnbreakable(boolean set){
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setUnbreakable(set);
        itemStack.setItemMeta(itemMeta);
        return this;
    }

    public ItemBuilder setDurability(short value){
        itemStack.setDurability(value == 0 ? itemStack.getDurability() : value);
        return this;
    }


    public ItemBuilder addCustomEffect(PotionEffectType potionEffectType, int duration, int power){
        PotionMeta meta = (PotionMeta) itemStack.getItemMeta();
        meta.addCustomEffect(new PotionEffect(potionEffectType, duration, power), false);
        itemStack.setItemMeta(meta);
        return this;
    }

    public ItemBuilder addRgb(int r, int g, int b){
        PotionMeta meta = (PotionMeta) itemStack.getItemMeta();
        meta.setColor(Color.fromRGB(r, g, b));
        itemStack.setItemMeta(meta);
        return this;
    }

    public ItemBuilder setAmount(int amount){
        itemStack.setAmount(amount);
        return this;
    }

    public ItemStack build(){
        return itemStack;
    }

}
