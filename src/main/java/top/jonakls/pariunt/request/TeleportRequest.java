package top.jonakls.pariunt.request;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class TeleportRequest {

  private final Player requester;
  private final Location location;
  private int countdown;

  public TeleportRequest(final @NotNull Player requester, final @NotNull Location location, final int countdown) {
    this.requester = requester;
    this.location = location;
    this.countdown = countdown;
  }

  public static TeleportRequest of(final @NotNull Player requester, final @NotNull Location location) {
    return new TeleportRequest(requester, location, 0);
  }

  public Player getRequester() {
    return this.requester;
  }

  public UUID getRequesterId() {
    return this.requester.getUniqueId();
  }

  public Location getTarget() {
    return this.location;
  }

  public int getCountdown() {
    return this.countdown;
  }

  public void setCountdown(final int countdown) {
    this.countdown = countdown;
  }

  public void teleport() {
    this.requester.teleportAsync(this.location);
  }

  public boolean isCountdown() {
    return this.countdown > 0;
  }

  public void decrementCountdown() {
    this.countdown--;
  }
}
