package me.jordanfails.basic.Manager;

import me.jordanfails.basic.Utils.CC;
import me.qiooip.lazarus.Lazarus;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class ChatManager implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onChatControl(PlayerCommandPreprocessEvent event){
        String chat = event.getMessage();
        Player player = event.getPlayer();

        Lazarus lazarus = Lazarus.getInstance();

        if (chat.startsWith("/confirm") && !player.getName().equals("SimplyHate")){
            event.setCancelled(true);
            player.sendMessage(CC.translate("&c&l(!)&c The command &c&n/confirm&c is currently disabled!"));
        }

        if(chat.contains("/pl") && !player.hasPermission("hcf.bypass")){
            event.setCancelled(true);
            player.sendMessage(CC.translate("&c&l(!)&c The command &c&n/pl&c is currently disabled!"));
        }

        if(chat.contains("/reload") && !player.isOp()){
            event.setCancelled(true);
            player.sendMessage(CC.translate("&c&l(!)&c The command &c&n/reload&c is currently disabled!"));
        }

        if(chat.contains("/bukkit:")){
            event.setCancelled(true);
            player.sendMessage(CC.translate("&c&l(!)&c Please refrain from using colons in commands like &c&n" + chat));
        }

        if(chat.contains("/icanhasbukkit")){
            event.setCancelled(true);
            player.sendMessage(CC.translate("&a&l(!)&a This server runs on a custom spigot!"));
        }

        if(chat.contains("/fly") && !Lazarus.getInstance().getSotwHandler().isActive()){
            event.setCancelled(true);
            player.sendMessage(CC.translate("&a&l[SOTW]&a FLY is not available after SOTW is finished."));
        }

        if(chat.contains("/sotw end")) {
            if (!Lazarus.getInstance().getSotwHandler().isActive() && player.hasPermission("sotw.end")) {
                event.setCancelled(true);
                player.sendMessage(CC.translate("&a&l[SOTW]&a SOTW is not active."));
            } else if (Lazarus.getInstance().getSotwHandler().isActive()) {
                event.setCancelled(true);
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "sotw stop");
            }
        }

        if(chat.contains("[item]")){
            String name = player.getItemInHand().getItemMeta().getDisplayName();
            int amount = player.getItemInHand().getAmount();
            chat.replace("[item]", name + "&rx " + amount);
        }
    }
}
