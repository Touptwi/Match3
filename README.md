# Jewels Falls
(by MONSINJON Samuel and )

The Goal is to make the biggest score by making match3
which mean align 3 tiles (or more) with the same color.



# To run the game you to:

 - Download "Jewels Falls.exe"
 - Make sure your java is up to date (min version: JRE 1.8.0_341)
 - Execute "Jewels Falls.exe"


# Features

 Menu:
 - 2 Gamemodes
 - Music's volume controler
 - Exit Button

 Game:
 - A grid
 - Spells
 - Score

 grid:
 - Falling animation
 - Drag and Drop
 - Match3 sound as feedback
 - No waiting time for next move
 - Hovered tile feedback

 Spells:
 - Loading bar progression
 - Different colors
 - Clickable bar to activate spell effect


# Fancy functions

 - checkMatch3 is call only if needed to avoid stackOverFlow
 - checkFlyingTiles need to be call each time you "break" tiles (to manage hole in grid and generate animations)
 - create3FirstTiles take care of having no match3 at the grid's generation
 
 
 
 (The project only use Java and Java Swing)
