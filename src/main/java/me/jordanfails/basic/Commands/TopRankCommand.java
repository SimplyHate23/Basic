package me.jordanfails.basic.Commands;

import com.minnymin.command.Command;
import com.minnymin.command.CommandArgs;
import me.jordanfails.basic.Utils.CC;
import org.bukkit.command.CommandSender;

public class TopRankCommand {
    //TODO: TO CHANGE TO RANK NAME!

    @Command(
            name = "toprank"
    )

    public void execute(CommandArgs args){
        CommandSender sender = args.getSender();

        sender.sendMessage(CC.translate("&eInterested in purchasing the &6Top Rank&e rank? Head over to"));
        sender.sendMessage(CC.translate("&6&nsamplefeatures.org&e."));
    }
}
