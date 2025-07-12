package me.envkoruma;

import java.io.File;
import java.io.IOException;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class EnvKoruma extends JavaPlugin implements CommandExecutor, Listener {
   private File dbFile;
   private YamlConfiguration dbConfig;

   public void onEnable() {
      this.saveDefaultConfig();
      this.loadDatabase();
      this.getCommand("envkoruma").setExecutor(this);
      this.getCommand("envadmin").setExecutor(this);
      Bukkit.getPluginManager().registerEvents(this, this);
   }

   public void onDisable() {
      this.saveDatabase();
   }

   private void loadDatabase() {
      this.dbFile = new File(this.getDataFolder(), "database.yml");
      if (!this.dbFile.exists()) {
         try {
            this.dbFile.getParentFile().mkdirs();
            this.dbFile.createNewFile();
            this.dbConfig = new YamlConfiguration();
            this.dbConfig.save(this.dbFile);
         } catch (IOException var2) {
            var2.printStackTrace();
         }
      }

      this.dbConfig = YamlConfiguration.loadConfiguration(this.dbFile);
   }

   private void saveDatabase() {
      try {
         this.dbConfig.save(this.dbFile);
      } catch (IOException var2) {
         var2.printStackTrace();
      }

   }

   private boolean getKullaniciDurumu(String name) {
      return this.dbConfig.getBoolean("oyuncular." + name, false);
   }

   private boolean sistemAcik() {
      return this.getConfig().getBoolean("sistem-durumu", true);
   }

   private String msg(String key) {
      String prefix = this.getConfig().getString("prefix", "");
      String text = this.getConfig().getString("mesajlar." + key, "Tanımsız mesaj: " + key);
      return prefix + text;
   }

   public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
      String komut = cmd.getName().toLowerCase();
      if (komut.equals("envkoruma")) {
         if (!(sender instanceof Player)) {
            sender.sendMessage("§cBu komut sadece oyun içinde kullanılabilir.");
            return true;
         } else {
            Player p = (Player)sender;
            if (!p.hasPermission("envkoruma.kullan")) {
               return true;
            } else if (!this.sistemAcik()) {
               return true;
            } else if (args.length == 0) {
               p.sendMessage("§cKullanım: /envkoruma aç | kapat");
               return true;
            } else {
               boolean durum = this.getKullaniciDurumu(p.getName());
               if (args[0].equalsIgnoreCase("aç")) {
                  if (durum) {
                     p.sendMessage(this.msg("zaten-acik"));
                  } else {
                     this.dbConfig.set("oyuncular." + p.getName(), true);
                     this.saveDatabase();
                     p.sendMessage(this.msg("koruma-acildi"));
                  }

                  return true;
               } else if (args[0].equalsIgnoreCase("kapat")) {
                  if (!durum) {
                     p.sendMessage(this.msg("zaten-kapali"));
                  } else {
                     this.dbConfig.set("oyuncular." + p.getName(), false);
                     this.saveDatabase();
                     p.sendMessage(this.msg("koruma-kapatildi"));
                  }

                  return true;
               } else {
                  p.sendMessage("§cGeçersiz argüman. /envkoruma aç | kapat");
                  return true;
               }
            }
         }
      } else if (komut.equals("envadmin")) {
         if (!sender.hasPermission("envkoruma.admin")) {
            return true;
         } else if (args.length == 1 && args[0].equalsIgnoreCase("reload")) {
            this.reloadConfig();
            sender.sendMessage(this.msg("reload"));
            return true;
         } else {
            sender.sendMessage("§cKullanım: /envadmin reload");
            return true;
         }
      } else {
         return false;
      }
   }

   @EventHandler
   public void oyuncuItemAtinca(PlayerDropItemEvent e) {
      if (this.sistemAcik()) {
         if (this.getKullaniciDurumu(e.getPlayer().getName())) {
            e.setCancelled(true);
            e.getPlayer().sendMessage(this.msg("item-atma-engellendi"));
         }

      }
   }
}
