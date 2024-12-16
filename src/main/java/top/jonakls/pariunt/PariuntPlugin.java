package top.jonakls.pariunt;

import dev.triumphteam.cmd.bukkit.BukkitCommandManager;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;
import top.jonakls.pariunt.command.PariuntCommand;
import top.jonakls.pariunt.command.spawn.DelSpawnCommand;
import top.jonakls.pariunt.command.spawn.SetSpawnCommand;
import top.jonakls.pariunt.command.spawn.SpawnCommand;
import top.jonakls.pariunt.listener.PlayerSpawnListener;
import top.jonakls.pariunt.manager.SpawnManager;
import top.jonakls.pariunt.request.TeleportRegistry;
import top.jonakls.pariunt.request.TeleportTask;

import java.io.File;

public class PariuntPlugin extends JavaPlugin {

  private final BukkitCommandManager<CommandSender> commandManager = BukkitCommandManager.create(this);
  private SpawnManager spawnManager = null;
  private TeleportRegistry registry = null;
  private BukkitTask task = null;

  @Override
  public void onEnable() {
    this.setupConfigurations();
    this.getLogger()
      .info("Pariunt plugin enabled");

    this.spawnManager = new SpawnManager(this);
    this.spawnManager.loadSpawnLocation();

    this.setupCommands();
    this.setupListener();
    this.setupTask();
  }

  @Override
  public void onDisable() {
    this.getLogger()
      .info("Pariunt plugin disabled");
    this.task.cancel();
  }

  private void setupListener() {
    this.getServer()
      .getPluginManager()
      .registerEvents(new PlayerSpawnListener(this), this);
  }

  private void setupConfigurations() {
    final File config = new File(getDataFolder(), "config.yml");
    if (!config.exists()) {
      getConfig().options()
        .copyDefaults(true);
      saveConfig();
    } else {
      saveDefaultConfig();
    }
  }

  private void setupCommands() {
    this.commandManager.registerCommand(
      new PariuntCommand(this),
      new SpawnCommand(this),
      new SetSpawnCommand(this),
      new DelSpawnCommand(this)
    );
  }

  private void setupTask() {
    final ConfigurationSection section = getConfig()
                                           .getConfigurationSection("config.spawn-request");

    if (section == null) {
      return;
    }

    if (!section.getBoolean("enabled")) {
      return;
    }

    this.registry = new TeleportRegistry();

    this.task = this.getServer()
                  .getScheduler()
                  .runTaskTimer(this, new TeleportTask(registry), 0, 20L);
    this.getSLF4JLogger()
      .info("Teleport task scheduled to run every seconds");
  }

  public BukkitCommandManager<CommandSender> getCommandManager() {
    return this.commandManager;
  }

  public SpawnManager getSpawnManager() {
    return this.spawnManager;
  }

  public TeleportRegistry getTeleportRegistry() {
    return this.registry;
  }
}
