package top.jonakls.pariunt.command.spawn;

import dev.triumphteam.cmd.bukkit.annotation.Permission;
import dev.triumphteam.cmd.core.BaseCommand;
import dev.triumphteam.cmd.core.annotation.Command;
import dev.triumphteam.cmd.core.annotation.Default;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import top.jonakls.pariunt.PariuntPlugin;
import top.jonakls.pariunt.manager.SpawnManager;

@Command(value = "spawn", alias = {"sp"})
@Permission("pariunt.command.spawn")
public class SpawnCommand extends BaseCommand {

  private final PariuntPlugin plugin;

  public SpawnCommand(final @NotNull PariuntPlugin plugin) {
    this.plugin = plugin;
  }

  @Default
  public void execute(final @NotNull Player player) {
    final SpawnManager spawnManager = plugin.getSpawnManager();
    spawnManager.teleportToSpawn(player);
  }
}
