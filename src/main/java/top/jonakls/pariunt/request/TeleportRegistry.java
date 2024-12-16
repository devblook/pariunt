package top.jonakls.pariunt.request;

import org.jetbrains.annotations.NotNull;

import java.util.Iterator;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class TeleportRegistry implements Iterable<TeleportRequest> {

  private static final Map<UUID, TeleportRequest> REQUEST_MAP = new ConcurrentHashMap<>();

  public void registerRequest(final @NotNull TeleportRequest request) {
    REQUEST_MAP.put(request.getRequesterId(), request);
  }

  public void removeRequest(final @NotNull UUID requester) {
    REQUEST_MAP.remove(requester);
  }

  public TeleportRequest findRequest(final @NotNull UUID requester) {
    return REQUEST_MAP.get(requester);
  }

  public void clearRequests() {
    REQUEST_MAP.clear();
  }

  @Override
  public @NotNull Iterator<TeleportRequest> iterator() {
    return REQUEST_MAP.values()
             .iterator();
  }
}
