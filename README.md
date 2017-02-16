# AllMining

Spigot plugin to mine all the same ore at once.
Since this plugin leaves the mining processing of each ore to the server side,
processing of other plugins (Jobs, McMMO etc) can be maintained as it is.

## Download

In order to use this plugin, MSCore must also be installed.

| Spigot | MSCore | AllMining |
| :----: | :----: | :-------: |
| 1.10.2 | [ ![Download](https://api.bintray.com/packages/masahirosaito-repo/Spigot-Plugin/MSCore/images/download.svg) ](https://bintray.com/masahirosaito-repo/Spigot-Plugin/download_file?file_path=com%2FMasahiroSaito%2FSpigot%2FMSCore%2F0.5%2FMSCore-0.5.jar) | [ ![Download](https://api.bintray.com/packages/masahirosaito-repo/Spigot-Plugin/AllMining/images/download.svg?version=1.0) ](https://bintray.com/masahirosaito-repo/Spigot-Plugin/download_file?file_path=com%2FMasahiroSaito%2FSpigot%2FAllMining%2F1.0%2FAllMining-1.0.jar) |
| 1.11.2 | [ ![Download](https://api.bintray.com/packages/masahirosaito-repo/Spigot-Plugin/MSCore/images/download.svg) ](https://bintray.com/masahirosaito-repo/Spigot-Plugin/download_file?file_path=com%2FMasahiroSaito%2FSpigot%2FMSCore%2F0.5%2FMSCore-0.5.jar) | [ ![Download](https://api.bintray.com/packages/masahirosaito-repo/Spigot-Plugin/AllMining/images/download.svg?version=1.0) ](https://bintray.com/masahirosaito-repo/Spigot-Plugin/download_file?file_path=com%2FMasahiroSaito%2FSpigot%2FAllMining%2F1.0%2FAllMining-1.0.jar) |

## Configuration

The configuration file is created in json format.
You can change the setting by editing with a text editor or the like.

```json
{
  "Display a mining data when mined ores": true,
  "Mine ore only during sneaking": false,
  "Mine ore also in creative mode": false,
  "Number of ores that can be mined at one time": 64,
  "Types of tools for mining": [
    "DIAMOND_PICKAXE",
    "GOLD_PICKAXE",
    "IRON_PICKAXE",
    "STONE_PICKAXE",
    "WOOD_PICKAXE"
  ],
  "Types of ores that can be mined": [
    {
      "material": "COAL_ORE",
      "damage": 0
    },
    {
      "material": "IRON_ORE",
      "damage": 0
    },
    {
      "material": "GOLD_ORE",
      "damage": 0
    },
    {
      "material": "REDSTONE_ORE",
      "damage": 0
    },
    {
      "material": "GLOWING_REDSTONE_ORE",
      "damage": 0
    },
    {
      "material": "LAPIS_ORE",
      "damage": 0
    },
    {
      "material": "EMERALD_ORE",
      "damage": 0
    },
    {
      "material": "DIAMOND_ORE",
      "damage": 0
    },
    {
      "material": "QUARTZ_ORE",
      "damage": 0
    },
    {
      "material": "GLOWSTONE",
      "damage": 0
    },
    {
      "material": "OBSIDIAN",
      "damage": 0
    }
  ]
}
```

## Repository

<a href='https://bintray.com/masahirosaito-repo/Spigot-Plugin/AllMining?source=watch' alt='Get automatic notifications about new "AllMining" versions'><img src='https://www.bintray.com/docs/images/bintray_badge_color.png'></a>