package ru.tanz.commands;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import ru.tanz.ExpPlugin;
import ru.tanz.utils.ItemBuilder;
import ru.tanz.utils.OtherUtil;

import java.util.List;
import java.util.stream.Collectors;

public class ExpBottle implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(args.length < 1 || args.length > 1){
            sender.sendMessage(OtherUtil
                    .color(ExpPlugin.getInstance().getConfig().getString("messages.help")));
            return true;
        }

        if(!(sender instanceof Player)){
            sender.sendMessage(OtherUtil

                    .color(ExpPlugin.getInstance().getConfig().getString("messages.null-player")));
            return true;
        }
        if(!sender.hasPermission("texpbottle.use")){
            sender.sendMessage(OtherUtil
                    .color(ExpPlugin.getInstance().getConfig().getString("messages.no-perm")));
            return true;
        }
        if(args[0].equalsIgnoreCase("reload")){
            sender.sendMessage(OtherUtil
                    .color(ExpPlugin.getInstance().getConfig().getString("messages.reload")));

            ExpPlugin.getInstance().reloadConfig();
            ExpPlugin.getInstance().fillList();
            return true;
        }
        

        Player target = ((Player) sender).getPlayer();

        if(!ExpPlugin.getInstance().getLevelList().contains(Integer.parseInt(args[0]))){
            sender.sendMessage(OtherUtil
                    .color(ExpPlugin.getInstance().getConfig().getString("messages.exist-exp")));
        } else{
            int level = Integer.parseInt(args[0]);
            String name = OtherUtil
                    .color(ExpPlugin.getInstance().getConfig().getString("exp-bottle-settings.name"));

            List<String> lore = ExpPlugin.getInstance().getConfig().getStringList("exp-bottle-settings.lore")
                    .stream()
                        .map(x -> x.replace("%level%", String.valueOf(level))).collect(Collectors.toList());


            List<String> itemFlags = ExpPlugin.getInstance().getConfig().getStringList("exp-bottle-settings.flags");

            ItemBuilder itemBuilder = new ItemBuilder(Material.EXP_BOTTLE)
                    .setName(name.replace("%level%", String.valueOf(level)))
                            .setLore(lore)
                                .addItemFlags(itemFlags)
                                    .setNBTTag("key", args[0]);

            if(target.getLevel() >= level) {
                target.getInventory().addItem(itemBuilder.build());
                target.setLevel(target.getLevel() - level);
                target.sendMessage(OtherUtil.color(ExpPlugin.getInstance().getConfig().getString("messages.success-give")
                        .replace("%level%", String.valueOf(level))));
            } else {
                target.sendMessage(OtherUtil.color(ExpPlugin.getInstance().getConfig().getString("messages.small-level")));

            }
        }
        return false;
    }
}
