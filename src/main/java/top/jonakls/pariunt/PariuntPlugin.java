package top.jonakls.pariunt;

import dev.triumphteam.cmd.bukkit.BukkitCommandManager;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import top.jonakls.pariunt.command.PariuntCommand;

import java.io.File;

public class PariuntPlugin extends JavaPlugin {

  private final BukkitCommandManager<CommandSender> commandManager = BukkitCommandManager.create(this);

  @Override
  public void onEnable() {
    this.setupConfigurations();
    this.getLogger()
      .info("Pariunt plugin enabled");
  }

  @Override
  public void onDisable() {
    this.getLogger()
      .info("Pariunt plugin disabled");
  }

  private void setupConfigurations() {
    final File config = new File(getDataFolder(), "config.yml");
    if (!config.exists()) {
      getConfig().options()
        .copyDefaults(true);
      saveConfig();
    }
  }

  private void setupCommands() {
    this.commandManager.registerCommand(new PariuntCommand());
  }

  public BukkitCommandManager<CommandSender> getCommandManager() {
    return this.commandManager;
  }
}
