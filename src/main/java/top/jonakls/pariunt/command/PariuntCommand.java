package top.jonakls.pariunt.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class PariuntCommand extends Command {

  protected PariuntCommand(
    @NotNull String name, @NotNull String description,
    @NotNull String usageMessage, @NotNull List<String> aliases
  ) {
    super(name, description, usageMessage, aliases);
  }

  @Override
  public boolean execute(@NotNull CommandSender commandSender, @NotNull String s, @NotNull String[] strings) {
    return false;
  }
}
