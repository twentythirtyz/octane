# Octane

[![Build](https://github.com/twentythirtyz/octane/actions/workflows/build.yml/badge.svg)](https://github.com/twentythirtyz/octane/actions/workflows/build.yml)
[![Kotlin](https://img.shields.io/badge/Kotlin-2.0-purple?logo=kotlin&logoColor=white)](https://kotlinlang.org/)
[![Java](https://img.shields.io/badge/Java-21-orange?logo=openjdk&logoColor=white)](https://adoptium.net/)
[![Paper](https://img.shields.io/badge/Paper-1.21-blue?logo=minecraft&logoColor=white)](https://papermc.io/)

Lightweight Paper plugin for Minecraft 1.21 written in Kotlin. Covers the basic admin commands and a settings GUI, nothing more.

## Stack

| Area     | Tech                        |
|----------|-----------------------------|
| API      | Paper 1.21                  |
| Language | Kotlin 2.0                  |
| Commands | CommandAPI                  |
| Text     | Adventure / MiniMessage     |
| Config   | kaml (typed YAML)           |
| Testing  | MockBukkit                  |
| CI       | GitHub Actions              |

## Commands

| Command | Description | Permission |
|---------|-------------|------------|
| `/heal [player]` | Restore health | `octane.heal` / `octane.heal.others` |
| `/feed [player]` | Restore hunger | `octane.feed` / `octane.feed.others` |
| `/gmc [player]` | Gamemode creative | `octane.gmc` / `octane.gmc.others` |
| `/gms [player]` | Gamemode survival | `octane.gms` / `octane.gms.others` |
| `/gmsp [player]` | Gamemode spectator | `octane.gmsp` / `octane.gmsp.others` |
| `/gma [player]` | Gamemode adventure | `octane.gma` / `octane.gma.others` |
| `/fly [player]` | Toggle flight | `octane.fly` / `octane.fly.others` |
| `/flyspeed <1-10> [player]` | Set fly speed | `octane.flyspeed` / `octane.flyspeed.others` |
| `/settings` | Open server settings GUI | `octane.admin` |
| `/octane reload` | Reload config files | `octane.admin` |

## Building

Requires Java 21.

```bash
./gradlew build
```

Jar is output to `build/libs/`. Kotlin stdlib and CommandAPI are shaded in automatically.

## Configuration

Two files are generated in `plugins/octane/` on first run:

- `config.yml` - GUI layout (slot positions, materials, MiniMessage names/lore)
- `messages.yml` - all player-facing strings in MiniMessage format, supports `%player%` and `%sender%` placeholders
