# RiskGame

----------------------------------------------------------------------------------------------------------------------------------------
                                                 WELCOME TO RISK GAME 
----------------------------------------------------------------------------------------------------------------------------------------
Steps for playing the Game:

1. Start the Game
2. Select the players
3. Load the map (mapinput.txt)
4. Game screen appears showing the neighbours matrix and the list of players, initial allocation of player territories and troops are done
5. The turn of the current player is visible. Initially click on the start turn which takes the player to reinforcement phase.
6. After reinforcement the player gets back to the game screen where he has 3 options:
   1. ATTACK
   2. FORITFY
   3. STATISTICS
   
7. ATTACK takes the player to attack phase, where he can select the territory from where he can attack and to which territory he wants to attack. Click on the dice roll, to see who won, attacker or defender.
8. The current player can attack again, once done click on quit attack to move to fortification phase.
9. In fortification, player can move troops from one territory to other.
10. The territory and troops owned by the player are visible to the him, accordingly he can fortify.
11. Once fortification is done, the turn moves to next player and the process repeats.
12. A player can check his/her details like territories owned, total troops owned, by selecting the STATISTICS.

-----------------------------------------------------------------------------------------------------------------------------

Correct Map File : 
mapinput.txt

Incorrect Map File : 
incorrectMap.txt

-----------------------------------------------------------------------------------------------------------------------------

For creating new map:
1. select create a new map option
2. The game will ask the player to enter the no of total continents.
3. For each continent, enter the continent name and the number of countries it consists of.
4. Enter total number of countries
5. For every country :
   1. Enter the country name
   2. X and Y co-ordinates
   3. The continent it resides in
   4. Enter list of adjoining countries separated by "," and no space.
6. Once done, the map will be stored in Map.txt file on the system and map matrix will be loaded and game will commence. 
