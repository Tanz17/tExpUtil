package ru.tanz;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.bukkit.plugin.java.JavaPlugin;
import ru.tanz.commands.ExpBottle;
import ru.tanz.listener.UseListener;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ExpPlugin extends JavaPlugin {

    final List<Integer> levelList = new ArrayList<>();
    @Getter
    private static ExpPlugin instance;

    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();
        Logger log = Logger.getLogger("");
        log.info("§e| §c████████╗ █████╗ ███╗  ██╗███████╗");
        log.info("§e| §c╚══██╔══╝██╔══██╗████╗ ██║╚════██║");
        log.info("§e| §c   ██║   ███████║██╔██╗██║  ███╔═╝");
        log.info("§e| §c   ██║   ██╔══██║██║╚████║██╔══╝  ");
        log.info("§e| §c   ██║   ██║  ██║██║ ╚███║███████╗");
        log.info("§e| §c   ╚═╝   ╚═╝  ╚═╝╚═╝  ╚══╝╚══════╝");
        log.info("");
        log.info("§e| §fПлагин §ctExpBottle §7& §fВерсия плагина - §a1.0");
        log.info("§e| §fРазработчик - §e§nvk.com/tanz_ind");
        fillList();
        registerListeners();
        registerCommands();


    }
    public void fillList(){
        levelList.clear();
        levelList.addAll(getConfig().getIntegerList("max-exp"));
    }

    @Override
    public void onDisable(){
        Logger log = Logger.getLogger("");
        log.info("§e| §c████████╗ █████╗ ███╗  ██╗███████╗");
        log.info("§e| §c╚══██╔══╝██╔══██╗████╗ ██║╚════██║");
        log.info("§e| §c   ██║   ███████║██╔██╗██║  ███╔═╝");
        log.info("§e| §c   ██║   ██╔══██║██║╚████║██╔══╝  ");
        log.info("§e| §c   ██║   ██║  ██║██║ ╚███║███████╗");
        log.info("§e| §c   ╚═╝   ╚═╝  ╚═╝╚═╝  ╚══╝╚══════╝");
        log.info("");
        log.info("§e| §fПлагин §ctExpBottle §7& §fВерсия плагина - §a1.0");
        log.info("§e| §fРазработчик - §e§nvk.com/tanz_ind");
    }


    public void registerListeners(){
        getServer().getPluginManager().registerEvents(new UseListener(), this);
    }

    public void registerCommands(){

        getCommand("texpbottle").setExecutor(new ExpBottle());
    }
}
