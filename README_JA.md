# ![AllMining](./assets/AllMining.png)

隣接する鉱石を一度に全て採掘する Spigot Plugin です。

このプラグインは、それぞれの鉱石の採掘処理をサーバー側に任せているため、

他のプラグイン(Jobs、McMMOなど)の処理をそのまま保つことができます。

### YouTube

[![](http://img.youtube.com/vi/TZzIPO7CANE/0.jpg)](http://www.youtube.com/watch?v=TZzIPO7CANE)

## 機能

- 採掘した鉱石に隣接する同じ種類の鉱石を全て採掘する
- 採掘時の経験値とアイテムは全てプレイヤーの足元にドロップする
- 鉱石の採掘中に道具が壊れた場合は採掘をやめる
- 全ての鉱石はそれぞれプレイヤーが採掘した判定になる
- シルクタッチ、耐久、幸運などのエンチャントに対応する
- 設定で適用する道具を追加できる
- 設定で適用する鉱石を追加できる
- 設定で一度に採掘する鉱石の数を制限できる
- コマンドでON/OFFの切り替えができる

## ダウンロード

| Spigot | AllMining |
| :----: | :-------: |
| v 1.10 ~ | [v 1.3](https://github.com/MasahiroSaito/AllMining/releases/tag/1.3) |

## コマンド

| コマンド | 説明                          |
| :------: | :---------------------------: |
| /am on   | AllMining の機能を ON にする  |
| /am off  | AllMining の機能を OFF にする |

## 設定構成

設定ファイルが Json 形式で作成されます。

テキストエディターなどで編集することで設定を変更できます。

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
