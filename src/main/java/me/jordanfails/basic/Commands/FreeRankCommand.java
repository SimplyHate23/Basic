package me.jordanfails.basic.Commands;

import com.minnymin.command.Command;
import com.minnymin.command.CommandArgs;
import me.jordanfails.basic.Inventories.Inventories;
import me.jordanfails.basic.Utils.ConfigurationService;
import org.bukkit.entity.Player;

public class FreeRankCommand {
    @Command(
            name = "freerank",
            inGameOnly = true
    )

    public void execute(CommandArgs args){
        Player player = args.getPlayer();
        if(!ConfigurationService.getFreeRankConfig().contains(player.getUniqueId().toString())) {
            Inventories.freeRankGUI(player);
            return;
        }else if(ConfigurationService.getFreeRankConfig().contains(player.getUniqueId().toString())){
            Inventories.claimedRankGUI(player);
            return;
        }
    }
}