# Zombies Addon
A mod that helps playing hypixel zombies.

[한국어 내용](https://namu.wiki/w/%ED%95%98%EC%9D%B4%ED%94%BD%EC%85%80/Zombies/%ED%8C%81#s-4.1.1), [한국어 업데이트 로그](https://blog.naver.com/hahaha98757/223012598464)

## Features
### Cornering
Toggles the visibility of players nearby.
Use /cornering <number> command to set the range of cornering.
Used the config.

### Block Alarm
If you survived alone and there are players within 1.5m that can revive, show "BLOCK" on display.
Display player's health.
Used the config.
It works only if set the language of Hypixel in English or Korean.

### DPS Counter
Counts your party's DPS.

### NOTLAST
Shows who is kill the last in every round.
It works only if set the language of Hypixel in English or Korean.

### Auto Splits
Run LiveSplits or internal timer at start of round.
Displays the wave delay.
If color of wave delay is yellow: Start wave soon.
If it is green: Started wave.
Displays the current wave with a purple arrow.
Used the config.

Displays the wave which the boss spawns.
| Color | Boss name |
|----|----|
| Gold | Bombie |
| Red | Inferno |
| Dark Purple | The Broodmother, Wither, or Herobrine |
| Red and Apua | Lily and Ellie |
| Green | King Slime |
| Dark Apua | (Rainbow) Giant |
| Dark Red | The Old One |
| Dark Apua and Dark Red | (Rainbow) Giant and The Old One |
| Black | World Ender |

Displays the grow time of Blob. It works only if set the language of Hypixel in English or Korean.
| Round | Time |
|----|----|
| r18, r23 | 38s |
| r26 | 40s |
| r29 | 36s[or earlier] |
| r31, r33, r34 | 42s |
| r39 | 22s |
| r43 | 13s |
| r47 | 41s[or earlier] |
| r52 | 27s |

### Zombies Strat Viewer
Displays the text in [https://pastebin.com/](https://pastebin.com/) on the in-game screen.
Use /ZSV <URL|de|bb|aa|off> command to load or close text.
URL: It must start with "https://pastebin.com/raw/".
de: Load [the text](https://pastebin.com/raw/sb65mNA9).
bb: Load [the text](https://pastebin.com/raw/NrQEhqQy).
aa: Load [the text](https://pastebin.com/raw/4KE9jkXY).
off: Close text.
Use /ZSVLines <number> command to set lines of view.

### Spawn Limit Action
Display number of windows which zombie can spawn in.
Use /SLA <de|bb|aa|off> to sets the map or off SLA.
Used the config.

#### Advanced SLA
Activate Advanced SLA in config to use it.
SLA is displayed in more detail.
Others is the same as SLA.

### Auto Rejoin
Automatically rejoin the Zombies game.
It works only if set the language of Hypixel in English or Korean.

### Powerup Alarm
You will receive an alarm when a powerup is spawned.
You will know when powerup spawn and despawn.
Use /powerupAlarm <insta|max|ss> [number|on|off] command to reset or set the pattern.
Used the config.
It works only if set the language of Hypixel in English or Korean.

### Zombies Overlay in Korean
[Zombies Overlay](https://github.com/TheExploration/zombies-overlay) works even if language of Hypixel in Korean.

### Boss Alarm
You will know which area the boss spawned in.
Used the config.
It works only if set the language of Hypixel in English or Korean.

### Lrod Order
Shows the order of use of Lrod.
Used the config.
It works only if set the language of Hypixel in English or Korean.

****

## Update Log

### 1.12.3
- Fixed bugs.
- Fixed update checker

### 1.12.2
- Fixed update checker.
- Fixed /info command.

### 1.12.1
- Removed Hologram Remover.
- Added update checker.

### 1.12.0
- Added Grow Guide for Auto Splits.
- Fixed bugs.
- Fixed config.
- Added mod version on the display.
- Config reset when mod update.
- Added option which default value of mod be set.


### 1.11.0
- Added Health Indicator for Block Alarm.
- Rename Hologram Bug Generator to Hologram Remover.
- Removed rev and dead for Block Alarm.


### 1.10.3
- Fixed bugs.

### 1.10.2
- Added Hologram Bug Generator.

### 1.10.1
- Fixed bugs.

### 1.10.0
- Changed package.
- Code Reorganization.
- Added Lrod Order.
- Added Advanced SLA.
- Fixed commands.


### 1.9.2
- Fixed bugs.

### 1.9.1
- Fixed bugs.

### 1.9.0
- Fixed bugs.
- Added Boss Alarm.
- Fixed forge command bug.


### 1.8.9
- Fixed bugs.

### 1.8.8
- Fixed bugs.
- Added Boss Wave Alarm for Auto Splits.

### 1.8.7
- Fixed bugs.

### 1.8.6
- Fixed bugs.
- Removed /zombies command.
- Added config for Zombies Overlay.
- Fixed Powerup Alarm.

### 1.8.5
- Fixed bugs.
- Fixed CommandInfo.
- Added /zombies Command for Zombies Overlay.

### 1.8.4
- Fixed bugs.
- Fixed Auto Splits.

### 1.8.3
- Fixed bugs.

### 1.8.2
- Fixed bugs.

### 1.8.1
- Fixed Powerup Alarm.
- Fixed bugs.
- Added config for SLA.

### 1.8.0
- Added Powerup Alarm.
- Fixed bugs.
- Added config for Zombies Addon.
- Fixed Auto Splits.


### 1.7.6
- Fixed bug.

### 1.7.5
- Fixed bug.

### 1.7.4
- Fixed bug.

### 1.7.3
- Fixed bug.

### 1.7.2
- Fixed bug.

### 1.7.1
- Fixed bug.

### 1.7.0
- Fixed Auto Splits.
- Fixed /ZSV, /SLA command.
- Fixed Auto Rejoin.
- Added config for Auto Splits.

### 1.6.5
- Fixed bug.

### 1.6.4
- Fixed bug.
 
### 1.6.3
- Fixed bug.

### 1.6.2
- Fixed NOTLAST and Auto Rejoin.
- Fixed bug.

### 1.6.1
- Fixed Block Alarm.

### 1.6.0
- Remake /setMap, /setstrat, and /setlines commands name to /SLA, /ZSV, and /ZSVLines.
- Remove /blockAlarm command.
- Added /cornering command.
- Added config for Cornering and Block Alarm.


### 1.5.2
- Fixed bug.

### 1.5.1
- Fixed bug.

### 1.5.0
- Added Auto Rejoin mod.
- Fixed bug.
- Fixed Auto Splits.


### 1.4.5
- Fixed Block Alarm.

### 1.4.4
- Fixed Block Alarm.

### 1.4.3
- Fixed bug.
- You can use Block Alarm in Korean.

### 1.4.2
- Fixed bug.
- Block Alarm fixed.
- Added /blockAlarm command.

### 1.4.1
- Fixed mod's message.

### 1.4.0
- Added ZSV and SLA mod.


### 1.3.0
- Added Auto Splits mod.


### 1.2.1
- Added /updatelog command.
- /info command fixed.

### 1.2.0
- Added NOTLAST


### 1.1.0
- Removed ZHF


### 1.0.0
- Added ZHF, Cornering, Block Alarm, and DPS Counter.
- Fixed Block Alarm
