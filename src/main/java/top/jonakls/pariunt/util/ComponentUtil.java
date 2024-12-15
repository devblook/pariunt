package top.jonakls.pariunt.util;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.title.Title;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionType;
import org.jetbrains.annotations.NotNull;

import java.time.Duration;

public class ComponentUtil {

  private static final MiniMessage MINI_MESSAGE = MiniMessage.miniMessage();

  private ComponentUtil() {
    throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
  }

  public static String capitalize(final String string) {
    return string.substring(0, 1)
             .toUpperCase() + string.substring(1)
                                .toLowerCase();
  }

  public static Component toComponent(final @NotNull String string) {
    return MINI_MESSAGE.deserialize(string);
  }

  public static void sendTitle(final @NotNull Player player, final @NotNull ConfigurationSection section) {
    final boolean enabled = section.getBoolean("enabled", true);

    if (!enabled) {
      return;
    }

    player.showTitle(Title.title(
      toComponent(section.getString("title", "null")),
      toComponent(section.getString("subtitle", "null")),
      Title.Times.times(
        Duration.ofMillis(section.getLong("fadeIn", 20)),
        Duration.ofMillis(section.getLong("stay", 60)),
        Duration.ofMillis(section.getLong("fadeOut", 20))
      )));
  }

  public static void playSound(final @NotNull Player player, final @NotNull ConfigurationSection section) {
    final boolean enabled = section.getBoolean("enabled", true);

    if (!enabled) {
      return;
    }

    player.playSound(
      player.getLocation(),
      section.getString("sound", "minecraft:block.note_block.chime"),
      section.getInt("volume", 1),
      section.getInt("pitch", 1)
    );
  }

  public static void showEffect(final @NotNull Player player, final @NotNull ConfigurationSection section) {
    final boolean enabled = section.getBoolean("enabled", true);

    if (!enabled) {
      return;
    }

    PotionType potionType = PotionType.valueOf(section.getString("type", "SWIFTNESS"));

    player.addPotionEffect(new PotionEffect(
      PotionEffectType.NIGHT_VISION,
      section.getInt("duration", 20),
      section.getInt("amplifier", 1)
    ));
  }
}
