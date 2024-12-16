package top.jonakls.pariunt.command;

import dev.triumphteam.cmd.bukkit.annotation.Permission;
import dev.triumphteam.cmd.core.BaseCommand;
import dev.triumphteam.cmd.core.annotation.Command;
import dev.triumphteam.cmd.core.annotation.Default;
import dev.triumphteam.cmd.core.annotation.SubCommand;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import top.jonakls.pariunt.PariuntPlugin;
import top.jonakls.pariunt.util.ComponentUtil;

@Command(value = "pariunt", alias = {"par"})
public class PariuntCommand extends BaseCommand {

  private final PariuntPlugin plugin;

  public PariuntCommand(final @NotNull PariuntPlugin plugin) {
    this.plugin = plugin;
  }

  @Default
  public void execute(final @NotNull Player player) {
    player.sendMessage("Hello, world!");
  }

  @SubCommand("reload")
  @Permission("pariunt.command.reload")
  public void reload(final @NotNull Player player) {
    final ConfigurationSection messages = this.plugin.getConfig()
      .getConfigurationSection("config.messages");

    if (messages == null) {
      player.sendMessage(ComponentUtil.toComponent("Failed to reload config!"));
      return;
    }

    this.plugin.getSpawnManager()
      .saveSpawnLocation();
    this.plugin.reloadConfig();
    player.sendMessage(ComponentUtil.toComponent(messages.getString("reload-message", "Config reloaded!")));
    this.plugin.getSpawnManager()
      .loadSpawnLocation();
  }
}
