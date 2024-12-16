package top.jonakls.pariunt.command.spawn;

import dev.triumphteam.cmd.bukkit.annotation.Permission;
import dev.triumphteam.cmd.core.BaseCommand;
import dev.triumphteam.cmd.core.annotation.Command;
import dev.triumphteam.cmd.core.annotation.Default;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import top.jonakls.pariunt.PariuntPlugin;

@Command(value = "delspawn")
@Permission("pariunt.command.delspawn")
public class DelSpawnCommand extends BaseCommand {

  private final PariuntPlugin plugin;

  public DelSpawnCommand(final @NotNull PariuntPlugin plugin) {
    this.plugin = plugin;
  }

  @Default
  public void execute(final @NotNull Player player) {
    // Code here
  }
}
