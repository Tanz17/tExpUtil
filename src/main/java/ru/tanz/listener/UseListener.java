package ru.tanz.listener;

import net.minecraft.server.v1_12_R1.NBTTagCompound;
import org.bukkit.craftbukkit.v1_12_R1.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import ru.tanz.ExpPlugin;

public class UseListener implements Listener {

    @EventHandler
    public void onUse(PlayerInteractEvent event){
        Player player = event.getPlayer();
        ItemStack item = event.getItem();

        if(!hasNBTTag(item, "key")){
            return;
        }

        int level = Integer.parseInt(getNBTTag(item, "key"));
        if(ExpPlugin.getInstance().getLevelList().contains(level)){
            player.setLevel(player.getLevel() + level);
            event.getItem().setAmount(event.getItem().getAmount() - 1);
            event.setCancelled(true);
        }



    }

    public boolean hasNBTTag(ItemStack item, String key){
        net.minecraft.server.v1_12_R1.ItemStack stack = CraftItemStack.asNMSCopy(item);
        NBTTagCompound compound = stack.getTag();

        if(compound == null || compound.getString(key) == null || compound.getString(key).isEmpty()){
            return false;
        }
        return true;
    }

    public String getNBTTag(ItemStack item, String key){
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
