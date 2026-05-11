# Octane

A lightweight, custom Minecraft Bukkit plugin designed with performance and clean code architecture in mind. Built for Minecraft 1.21.

## Features
* **Modular Command System:** Clean, abstract-based command architecture (`PlayerCommand`).
* **Target Support:** Execute commands on yourself or target other players safely.
* **Custom Configurations:** Easily edit settings and plugin messages via auto-generating `config.yml` and `messages.yml` files.
* **Color Code Support:** Full support for legacy `&` color codes in all configuration messages.

## Commands & Permissions

| Command          | Description                                             | Permission           |
|:-----------------|:--------------------------------------------------------|:---------------------|
| `/heal`          | Restores your health to full.                           | `octane.heal`        |
| `/heal <player>` | Restores the target player's health to full.            | `octane.heal.others` |
| `/feed`          | Restores your hunger and saturation to full.            | `octane.feed`        |
| `/feed <player>` | Restores the target player's saturation to full.        | `octane.feed.others` |


## Installation
1. Compile the project using Gradle or download the latest `octane-1.0-SNAPSHOT.jar`.
2. Place the `.jar` file into your Minecraft server's `plugins/` folder.
3. Start or restart your server.
4. Edit the generated `config.yml` and `messages.yml` files in the `plugins/octane` directory to customize the plugin.
5. Restart the server to apply configuration changes.

## Development & Building
This project is built using **Gradle** (Kotlin DSL) and targets the Bukkit/Paper 1.21 API using Java 21.

### Project Structure
* `commands/` - Contains all command executors extending the base `PlayerCommand`.
* `managers/` - Contains core logic handlers like `ConfigManager`.
* `utils/` - Contains helper tools like `ChatUtil`.

### Build Instructions
To build the plugin into a usable `.jar` file:
1. Open the project in IntelliJ IDEA (or your preferred IDE).
2. Run the Gradle `clean` and `build` tasks.
3. The compiled jar will be generated and located in `build/libs/`.

## Author
* **twenty1989** - made the codebase he sigma
