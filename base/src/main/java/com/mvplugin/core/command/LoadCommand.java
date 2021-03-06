package com.mvplugin.core.command;

import com.dumptruckman.minecraft.pluginbase.command.CommandContext;
import com.dumptruckman.minecraft.pluginbase.command.CommandInfo;
import com.dumptruckman.minecraft.pluginbase.messages.Message;
import com.dumptruckman.minecraft.pluginbase.minecraft.BasePlayer;
import com.dumptruckman.minecraft.pluginbase.permission.Perm;
import com.mvplugin.core.exceptions.WorldManagementException;
import com.mvplugin.core.plugin.MultiverseCore;
import com.mvplugin.core.util.Perms;
import org.jetbrains.annotations.NotNull;

@CommandInfo(
        primaryAlias = "load",
        desc = "Loads a Multiverse World that has been unloaded.",
        usage = "{NAME}",
        directlyPrefixedAliases = "load",
        min = 1,
        max = 1
)
public class LoadCommand extends MultiverseCommand {

    public static final Message LOAD_HELP = Message.createMessage("command.load.help",
            "$hLoads a world that has previously been imported but is currently unloaded."
            + "\n$hExamples:"
            + "\n$c  /mv load $rgargamel");

    public static final Message LOAD_SUCCESS = Message.createMessage("command.load.success",
            "$+Successfully loaded the world '$v%s$+'!");

    protected LoadCommand(@NotNull final MultiverseCore plugin) {
        super(plugin);
    }

    @Override
    public Perm getPerm() {
        return Perms.CMD_LOAD;
    }

    @NotNull
    @Override
    public Message getHelp() {
        return LOAD_HELP;
    }

    @Override
    public boolean runCommand(@NotNull final BasePlayer sender, @NotNull final CommandContext context) {
        final String worldName = context.getString(0);

        try {
            getPlugin().getWorldManager().loadWorld(worldName);
            getMessager().message(sender, LOAD_SUCCESS, worldName);
        } catch (final WorldManagementException e) {
            e.sendException(getMessager(), sender);
        }

        return true;
    }
}
