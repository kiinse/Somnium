package kiinse.plugin.somnium.command;

import kiinse.plugin.somnium.Somnium;
import kiinse.plugin.somnium.files.messages.Message;
import kiinse.plugin.somnium.util.SomniumMessages;
import kiinse.plugins.api.darkwaterapi.commands.moderncommands.annotation.Command;
import kiinse.plugins.api.darkwaterapi.commands.moderncommands.interfaces.CommandClass;
import kiinse.plugins.api.darkwaterapi.files.locale.interfaces.PlayerLocale;
import kiinse.plugins.api.darkwaterapi.files.messages.SendMessagesImpl;
import kiinse.plugins.api.darkwaterapi.files.messages.interfaces.Messages;
import kiinse.plugins.api.darkwaterapi.files.messages.interfaces.SendMessages;
import kiinse.plugins.api.darkwaterapi.utilities.PlayerUtilsImpl;
import kiinse.plugins.api.darkwaterapi.utilities.interfaces.PlayerUtils;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class ForceSkipCommand implements CommandClass {

    private final Somnium somnium;
    private final PlayerUtils playerUtils;
    private final SendMessages sendMessages;

    public ForceSkipCommand(@NotNull Somnium somnium) {
        this.playerUtils = new PlayerUtilsImpl();
        this.somnium = somnium;
        this.sendMessages = new SendMessagesImpl(somnium);
    }

    @Override
    @Command(command = "/forceskip", permission = "somnium.forceskip", disallowNonPlayer = true)
    public void mainCommand(CommandSender sender, String[] args) {
        var world = playerUtils.getPlayer(sender).getWorld();
        var checker = somnium.getChecker();
        if (checker.isSkipping(world)) {
            sendMessages.sendMessageWithPrefix(sender, Message.FORCESKIP_ALREADY);
        } else {
            sendMessages.sendMessageWithPrefix(sender, Message.FORCESKIP_SKIPPING);
            checker.forceSkip(world);
        }
    }
}
