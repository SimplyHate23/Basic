package me.jordanfails.basic;

import com.minnymin.command.CommandFramework;
import lombok.Getter;
import me.jordanfails.basic.Commands.*;
import me.jordanfails.basic.Manager.*;
import me.jordanfails.basic.PartnerPackage.Listener.Package;
import me.jordanfails.basic.PartnerPackage.PartnerPackageCommand;
import me.jordanfails.basic.Utils.ConfigurationService;
import me.jordanfails.basic.Utils.Cooldown;
import me.jordanfails.basic.Utils.DateTimeFormats;
import me.jordanfails.basic.Vouchers.VouchersCommand;
import me.jordanfails.basic.Vouchers.listeners.ClearTimerPerk;
import me.jordanfails.basic.Vouchers.listeners.CraftingPerk;
import me.jordanfails.basic.Vouchers.listeners.FeedPerk;
import me.jordanfails.basic.Vouchers.listeners.FlyPerk;
import me.qiooip.lazarus.config.Config;
import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.permission.Permission;
import org.apache.commons.lang.time.DurationFormatUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.scheduler.BukkitTask;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.UUID;

import static java.util.Calendar.HOUR;
import static java.util.Calendar.MINUTE;

@Getter
public final class Basic extends JavaPlugin {
    private static Basic plugin;

    private CommandFramework commandFramework;
    public static ArrayList<UUID> staff = new ArrayList<>();


    private static Permission perms = null;
    private static Chat chat = null;

    public static final String PREFIX;
    public static final String NOPLAYER;
    public static final String PREFIXNOPERMS;
    public static final String NOPERMS;
    public static final String RANKPREFIX;

    @Override
    public void onEnable() {
        plugin = this;
        this.commandFramework = new CommandFramework(this);
        long timeAtStart = System.currentTimeMillis();
        registerCommands();
        registerManagers();
        setupPermissions();
        setupChat();
        ConfigurationService.setup();
        ConfigurationService.save();
        getConfig().options().copyDefaults();
        saveDefaultConfig();
        long timeAtEnd = System.currentTimeMillis();
        long timeTakenInMS = timeAtEnd - timeAtStart;
        Cooldown.createCooldown("lff_cooldown");
        if(!getDescription().getName().equals("Basic") && getDescription().getAuthors().equals("JordanFails")){
            getServer().getPluginManager().disablePlugin(this);
        }

        Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW + "[Basic]" + ChatColor.GREEN + " Plugin loaded in " + timeTakenInMS + "ms.");


        BukkitScheduler scheduler = getServer().getScheduler();


    }

    private boolean setupChat() {
        RegisteredServiceProvider<Chat> rsp = getServer().getServicesManager().getRegistration(Chat.class);
        chat = rsp.getProvider();
        return chat != null;
    }

    private boolean setupPermissions() {
        RegisteredServiceProvider<Permission> rsp = getServer().getServicesManager().getRegistration(Permission.class);
        perms = rsp.getProvider();
        return perms != null;
    }

    public static Permission getPermissions() {
        return perms;
    }

    public static Chat getChat() {
        return chat;
    }


    public static Basic get() {
        return plugin;
    }


    @Override
    public void onDisable() {
        plugin = null;
    }

    private void registerCommands(){
        this.commandFramework.registerCommands(new AbilityGUICommand());
        this.commandFramework.registerCommands(new PlayerInfoCommand());
        this.commandFramework.registerCommands(new MuteChatCommand());
        if(me.qiooip.lazarus.config.Config.FACTION_MAX_ALLIES == 0){
        }else if(me.qiooip.lazarus.config.Config.FACTION_MAX_ALLIES > 0){
            this.commandFramework.registerCommands(new LFACommand());
        }
        if(Config.FACTION_NAME_MAXIMUM_LENGTH == 15){
        }else if(Config.FACTION_NAME_MAXIMUM_LENGTH > 15){
            this.commandFramework.registerCommands(new FreeRankCommand());
        }
        this.commandFramework.registerCommands(new NitroCommand());
        this.commandFramework.registerCommands(new SupportCommand());
        this.commandFramework.registerCommands(new DiscordCommand());
        this.commandFramework.registerCommands(new LunarAmountCommand());
        this.commandFramework.registerCommands(new OnlineStaffCommand());
        this.commandFramework.registerCommands(new SaleCommand());
        this.commandFramework.registerCommands(new StaffInfoCommand());
        this.commandFramework.registerCommands(new YouTubeCommand());
        this.commandFramework.registerCommands(new StoreCommand());
        this.commandFramework.registerCommands(new TimelineCommand());
        this.commandFramework.registerCommands(new HatCommand());
        this.commandFramework.registerCommands(new DelayCommand());
        this.commandFramework.registerCommands(new InfoCommand());
        this.commandFramework.registerCommands(new GiveawayCommand());
        this.commandFramework.registerCommands(new ChatColorCommand());
        this.commandFramework.registerCommands(new PingCommand());
        this.commandFramework.registerCommands(new VouchersCommand());
        this.commandFramework.registerCommands(new PartnerPackageCommand());
        this.commandFramework.registerCommands(new SoundsCommand());
        this.commandFramework.registerCommands(new TopRankCommand());
        this.commandFramework.registerCommands(new FreeRankCommand());
        this.commandFramework.registerCommands(new StartRebootCommand());
        this.commandFramework.registerCommands(new ProfileCommand());
        this.commandFramework.registerCommands(new FriendsCommand());
        this.commandFramework.registerCommands(new CraftCommand());
        this.commandFramework.registerCommands(new SetSlotsCommand());
        this.commandFramework.registerCommands(new PunishmentWipeCommand());
        this.commandFramework.registerCommands(new FakeBanCommand());
        this.commandFramework.registerCommands(new WhoisCommand());
        this.commandFramework.registerCommands(new RandomTeleportCommand());
        this.commandFramework.registerCommands(new GrantStatusCommand());
        this.commandFramework.registerCommands(new SkullCommand());
        this.commandFramework.registerCommands(new CurrentRankCommand());
    }

    private void registerManagers(){
        PluginManager manager = getServer().getPluginManager();
        manager.registerEvents(new JoinLeaveManager(), this);
        manager.registerEvents(new PunishListener(), this);
        manager.registerEvents(new ChatManager(), this);
        manager.registerEvents(new LFFManager(), this);
        //manager.registerEvents(new RedeemManager(), this);
        //manager.registerEvents(new CraftingPerk(), this);
        //manager.registerEvents(new FlyPerk(), this);
        //manager.registerEvents(new FeedPerk(), this);
        //manager.registerEvents(new Package(), this);
        //manager.registerEvents(new ClearTimerPerk(), this);
        manager.registerEvents(new ProfileManager(), this);
        manager.registerEvents(new InventoryManager(), this);
        manager.registerEvents(new SkullListener(), this);
    }

    public static String getReaming(long millis) {
        return getRemaining(millis, true, true);
    }

    public static String getRemaining(long millis, boolean milliseconds) {
        return getRemaining(millis, milliseconds, true);
    }

    public static String getRemaining(long duration, boolean milliseconds, boolean trail) {
        if ((milliseconds) && (duration < MINUTE)) {
            return ((DecimalFormat) (trail ? DateTimeFormats.REMAINING_SECONDS_TRAILING
                    : DateTimeFormats.REMAINING_SECONDS).get()).format(duration * 0.001D) + 's';
        }
        return DurationFormatUtils.formatDuration(duration, (duration >= HOUR ? "HH:" : "") + "mm:ss");
    }

    static{
        PREFIX = ChatColor.GOLD + ChatColor.BOLD.toString() + "Sample §7» " + ChatColor.WHITE;
        RANKPREFIX = ChatColor.GOLD + ChatColor.BOLD.toString() + "Rank §7» " + ChatColor.WHITE;
        NOPLAYER = ChatColor.RED + "You must be a player to use this command. ";
        PREFIXNOPERMS = ChatColor.RED + "You lack the sufficient permissions to use this prefix. ";
        NOPERMS = ChatColor.RED + "You lack the sufficient permissions to execute this command. ";
    }

}
