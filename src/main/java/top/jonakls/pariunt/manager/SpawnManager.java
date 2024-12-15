package top.jonakls.pariunt.manager;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

public class SpawnManager {

  private final Plugin plugin;
  private Location spawnLocation;

  public SpawnManager(final @NotNull Plugin plugin) {
    this.plugin = plugin;
  }

  public void setSpawnLocation(final @NotNull Location location) {
    this.spawnLocation = location;
  }

  public Location getSpawnLocation() {
    return this.spawnLocation;
  }

  public void teleportToSpawn(final @NotNull Player player) {
    player.teleportAsync(this.spawnLocation, PlayerTeleportEvent.TeleportCause.COMMAND);
  }

  public void saveSpawnLocation() {
    this.plugin.getConfig()
      .set("config.spawn-location", this.spawnLocation);
    this.plugin.saveConfig();
  }

  public void loadSpawnLocation() {
    this.spawnLocation = this.plugin.getConfig()
                           .getLocation("config.spawn-location");
  }
}
