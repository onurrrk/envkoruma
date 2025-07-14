# 🛡️ InventoryProtection Plugin

A simple yet effective protection plugin that prevents Minecraft players from accidentally dropping items.

## ✨ Features

- Players can toggle their own inventory protection with `/envkoruma aç` and `/envkoruma kapat`.
- When protection is enabled, players cannot drop items on the ground.
- Players receive appropriate messages when actions are blocked.
- Admins can reload the configuration with `/envadmin reload`.
- All messages and system status are configurable via `config.yml`.
- Customizable messages with prefix support.

## 🧱 Supported Server Forks

| Server Type  | Supported     |
|--------------|---------------|
| Paper        | ✅             |
| Purpur       | ✅             |
| Folia        | ✅             |
| Spigot       | ✅             |
| ⚠️ Bukkit    | Partial support |

## 🔧 Installation

1. Place the `EnvKoruma.jar` file into the `plugins/` directory.
2. Restart your server.
3. Customize the `plugins/EnvKoruma/config.yml` file as needed.

## ⚙️ Config Structure

```yaml
sistem-durumu: true

prefix: "§b[Koruma] §7"

mesajlar:
  koruma-acildi: "Envanter koruma açıldı."
  koruma-kapatildi: "Envanter koruma kapatıldı."
  reload: "Yapılandırma dosyası yeniden yüklendi."
  item-atma-engellendi: "Envanter koruman açık, item atamazsın."
  zaten-acik: "Zaten envanter koruman açık!"
  zaten-kapali: "Zaten envanter koruman kapalı!"
