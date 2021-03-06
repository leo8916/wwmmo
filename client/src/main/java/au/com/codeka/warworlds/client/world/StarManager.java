package au.com.codeka.warworlds.client.world;

import android.support.annotation.Nullable;

import java.util.HashMap;
import java.util.Map;

import au.com.codeka.warworlds.client.App;
import au.com.codeka.warworlds.client.concurrency.Threads;
import au.com.codeka.warworlds.client.store.ProtobufStore;
import au.com.codeka.warworlds.client.util.eventbus.EventHandler;
import au.com.codeka.warworlds.common.Log;
import au.com.codeka.warworlds.common.proto.Star;
import au.com.codeka.warworlds.common.proto.StarUpdatedPacket;

/**
 * Manages the {@link Star}s we keep cached and stuff.
 */
public class StarManager {
  private static final Log log = new Log("StarManager");
  public static final StarManager i = new StarManager();

  private final ProtobufStore<Star> stars;

  private StarManager() {
    stars = App.i.getDataStore().stars();
  }

  public void create() {
    App.i.getEventBus().register(eventListener);
  }

  /** Gets the star with the given ID. Might return null if don't have that star cached yet. */
  @Nullable
  public Star getStar(long id) {
    // TODO: this probably shouldn't happen on a UI thread...
    return stars.get(id);
  }

  private final Object eventListener = new Object() {
    /**
     * When the server that a star has been updated, we'll want to update our cached copy of it.
     */
    @EventHandler(thread = Threads.BACKGROUND)
    public void onStarUpdatedPacket(StarUpdatedPacket pkt) {
      log.info("Stars updating, saving to database.");
      long startTime = System.nanoTime();
      Map<Long, Star> values = new HashMap<>();
      for (Star star : pkt.stars) {
        values.put(star.id, star);
      }
      stars.putAll(values);
      long endTime = System.nanoTime();
      log.info("Updated %d stars in DB in %d ms", pkt.stars.size(), (endTime - startTime) / 1000000L);
    }
  };
}
