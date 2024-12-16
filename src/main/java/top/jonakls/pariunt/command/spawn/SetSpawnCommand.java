package top.jonakls.pariunt.command.spawn;

import dev.triumphteam.cmd.bukkit.annotation.Permission;
import dev.triumphteam.cmd.core.BaseCommand;
import dev.triumphteam.cmd.core.annotation.Command;
import dev.triumphteam.cmd.core.annotation.Default;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import top.jonakls.pariunt.PariuntPlugin;
import top.jonakls.pariunt.manager.SpawnManager;

@Command(value = "setspawn")
@Permission("pariunt.command.setspawn")
public class SetSpawnCommand extends BaseCommand {

  private final PariuntPlugin plugin;

  public SetSpawnCommand(final @NotNull PariuntPlugin plugin) {
    this.plugin = plugin;
  }

  @Default
  public void execute(final @NotNull Player player) {
    final Location spawnLocation = player.getLocation();
    final SpawnManager spawnManager = plugin.getSpawnManager();

    spawnManager.setSpawnLocation(spawnLocation);
    player.sendMessage("Spawn location set");
    spawnManager.saveSpawnLocation();
  }
}
