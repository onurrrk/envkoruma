# 🛡️ EnvKoruma Plugin

Minecraft oyuncularının yanlışlıkla item atmasını önleyen basit ama etkili bir koruma eklentisi.

## ✨ Özellikler

- Oyuncular kendi envanter korumasını `/envkoruma aç` ve `/envkoruma kapat` komutlarıyla yönetebilir.
- Koruma açıkken oyuncular yere item atamaz.
- Oyuncuya uygun mesajlar gösterilir.
- Adminler `/envadmin reload` komutuyla config dosyasını yeniden yükleyebilir.
- Mesajlar ve sistem durumu `config.yml` üzerinden özelleştirilebilir.
- Tüm mesajlar yapılandırılabilir ve prefix desteklidir.

## 🧱 Desteklenen Sunucu Forkları

| Sunucu Türü  | Destek |
|--------------|--------|
| Paper        | ✅     |
| Purpur       | ✅     |
| Folia        | ✅     |
| Spigot       | ✅     |


## 🔧 Kurulum

1. `EnvKoruma.jar` dosyasını `plugins/` klasörüne atın.
2. Sunucunuzu yeniden başlatın.
3. `plugins/EnvKoruma/config.yml` dosyasını ihtiyacınıza göre düzenleyin.

## ⚙️ Config Yapısı

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
