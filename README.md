# ![AllMining](./assets/AllMining.png)

It is a Spigot Plugin which extracts all adjacent ore at once.
Since this plugin leaves the mining processing of each ore to the server side,
processing of other plugins (Jobs, McMMO etc) can be maintained as it is.

### YouTube

[![](http://img.youtube.com/vi/TZzIPO7CANE/0.jpg)](http://www.youtube.com/watch?v=TZzIPO7CANE)

## Features

- Mine all the same kind of ore adjacent to the mined ore
- Drop experience values ​​and items at the time of mining at the feet of players
- Stop mining if the tool breaks during ore mining
- Every ore is a judgment that each player mined
- It corresponds to enchantment such as silk touch, durability, Fortune
- You can add tools to apply in settings
- You can add ores to apply in setting
- You can limit the number of ores mined at once by setting

## Download

In order to use this plugin, MSCore must also be installed.

| Spigot | MSCore | AllMining |
| :----: | :----: | :-------: |
| 1.10.2 | [ ![Download](https://api.bintray.com/packages/masahirosaito-repo/Spigot-Plugin/MSCore/images/download.svg) ](https://bintray.com/masahirosaito-repo/Spigot-Plugin/download_file?file_path=com%2FMasahiroSaito%2FSpigot%2FMSCore%2F0.5%2FMSCore-0.5.jar) | [ ![Download](https://api.bintray.com/packages/masahirosaito-repo/Spigot-Plugin/AllMining/images/download.svg?version=1.1) ](https://www.spigotmc.org/resources/allmining.36443/download?version=143087) |
| 1.11.2 | [ ![Download](https://api.bintray.com/packages/masahirosaito-repo/Spigot-Plugin/MSCore/images/download.svg) ](https://bintray.com/masahirosaito-repo/Spigot-Plugin/download_file?file_path=com%2FMasahiroSaito%2FSpigot%2FMSCore%2F0.5%2FMSCore-0.5.jar) | [ ![Download](https://api.bintray.com/packages/masahirosaito-repo/Spigot-Plugin/AllMining/images/download.svg?version=1.1) ](https://www.spigotmc.org/resources/allmining.36443/download?version=143087) |

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