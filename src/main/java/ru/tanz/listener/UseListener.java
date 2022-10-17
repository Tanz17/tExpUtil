package ru.tanz.listener;

import net.minecraft.server.v1_12_R1.NBTTagCompound;
import org.bukkit.craftbukkit.v1_12_R1.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import ru.tanz.ExpPlugin;
import ru.tanz.utils.OtherUtil;

public class UseListener implements Listener {

    @EventHandler
    public void onUse(PlayerInteractEvent event){
        Player player = event.getPlayer();
        ItemStack item = event.getItem();

        if(!OtherUtil.hasNBTTag(item, "key")){
            return;
        }

        int level = Integer.parseInt(OtherUtil.getNBTTag(item, "key"));
        if(ExpPlugin.getInstance().getLevelList().contains(level)){
            player.setLevel(player.getLevel() + level);
            event.getItem().setAmount(event.getItem().getAmount() - 1);
            event.setCancelled(true);
        }



    }


}
