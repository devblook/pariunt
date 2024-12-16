package top.jonakls.pariunt.manager;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.jetbrains.annotations.NotNull;
import top.jonakls.pariunt.PariuntPlugin;
import top.jonakls.pariunt.request.TeleportRegistry;
import top.jonakls.pariunt.request.TeleportRequest;

public class SpawnManager {

  private final PariuntPlugin plugin;
  private Location spawnLocation;

  public SpawnManager(final @NotNull PariuntPlugin plugin) {
    this.plugin = plugin;
  }

  public void setSpawnLocation(final @NotNull Location location) {
    this.spawnLocation = location;
  }

  public Location getSpawnLocation() {
    return this.spawnLocation;
  }

  public void teleportToSpawn(final @NotNull Player player) {
    if (this.spawnLocation == null) {
      player.sendMessage("Spawn location is not set");
      return;
    }

    final TeleportRegistry registry = this.plugin.getTeleportRegistry();
    if (registry == null) {
      this.plugin.getSLF4JLogger()
        .info("Teleporting player to spawn location without request");
      player.teleportAsync(this.spawnLocation, PlayerTeleportEvent.TeleportCause.COMMAND);
      return;
    }

    this.plugin.getSLF4JLogger()
      .info("Teleporting player to spawn location");

    TeleportRequest request = registry.findRequest(player.getUniqueId());
    if (request != null) {
      player.sendMessage("You already have a pending request");
      return;
    }

    request = TeleportRequest.of(player, this.spawnLocation);
    request.setCountdown(this.maxSpawnCountdown());
    registry.registerRequest(request);
  }

  public boolean enableSpawnOnJoin() {
    return this.plugin.getConfig()
             .getBoolean("config.spawn-on-join");
  }

  public int maxSpawnCountdown() {
    return this.plugin.getConfig()
             .getInt("config.spawn-request.time");
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
