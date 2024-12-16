package top.jonakls.pariunt.request;

import org.jetbrains.annotations.NotNull;

import java.util.Iterator;

public class TeleportTask implements Runnable {

  private final TeleportRegistry registry;

  public TeleportTask(final @NotNull TeleportRegistry registry) {
    this.registry = registry;
  }

  @Override
  public void run() {
    System.out.println("Running task for teleport requests...");
    final Iterator<TeleportRequest> iterator = this.registry.iterator();

    while (iterator.hasNext()) {
      final TeleportRequest request = iterator.next();

      if (request.getCountdown() == 0) {
        request.getRequester()
          .sendMessage("Teleporting to spawn...");
        iterator.remove();
        request.teleport();
        continue;
      }

      request.getRequester()
        .sendMessage("Teleporting in " + request.getCountdown() + " seconds...");
      request.decrementCountdown();
    }
  }
}
