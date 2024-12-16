package top.jonakls.pariunt.listener;

import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.jetbrains.annotations.NotNull;
import top.jonakls.pariunt.PariuntPlugin;
import top.jonakls.pariunt.manager.SpawnManager;

public class PlayerSpawnListener implements Listener {

  private final SpawnManager spawnManager;

  public PlayerSpawnListener(final @NotNull PariuntPlugin plugin) {
    this.spawnManager = plugin.getSpawnManager();
  }

  @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
  public void onSpawnTeleport(final @NotNull PlayerJoinEvent event) {
    if (this.spawnManager.enableSpawnOnJoin()) {
      final Location spawnLocation = this.spawnManager.getSpawnLocation();

      if (spawnLocation == null) {
        event.getPlayer()
          .sendMessage("Spawn location is not set");
        return;
      }
      event.getPlayer()
        .teleportAsync(spawnLocation);
    }
  }
}
