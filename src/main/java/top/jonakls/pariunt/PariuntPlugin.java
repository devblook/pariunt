package top.jonakls.pariunt;

import org.bukkit.plugin.java.JavaPlugin;

public class PariuntPlugin extends JavaPlugin {
  @Override
  public void onEnable() {
    this.getLogger()
      .info("Pariunt plugin enabled");
  }

  @Override
  public void onDisable() {
    this.getLogger()
      .info("Pariunt plugin disabled");
  }
}
