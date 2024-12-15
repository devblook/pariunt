package top.jonakls.pariunt.command;

import dev.triumphteam.cmd.core.BaseCommand;
import dev.triumphteam.cmd.core.annotation.Command;
import dev.triumphteam.cmd.core.annotation.Default;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

@Command(value = "pariunt", alias = {"par"})
public class PariuntCommand extends BaseCommand {

  @Default
  public void execute(final @NotNull Player player) {
    player.sendMessage("Hello, world!");

  }
}
