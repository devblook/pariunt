package top.jonakls.pariunt.command.spawn;

import dev.triumphteam.cmd.core.annotation.Command;
import dev.triumphteam.cmd.core.annotation.Default;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

@Command(value = "spawn", alias = {"sp"})
public class SpawnCommand {

  @Default
  public void execute(final @NotNull Player player) {
    // Code here
  }
}
