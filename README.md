Battleship_Coding_Challenge
===========================

Was given 1 hour to try to create a game of Battleship with AI.  Did not fully finish so I went home and decided to try to complete the challenge during the rest of the day.  Will update AI.

How to play:
- First player must type in where they want their ships to be place (coordinates in the form of A-J and 1-10 then they will type the direction the ship should go (right or down)
- The AI will then place their ships randomly
- The player then has a chance to type in the coordinate of where they want to attack
- First one to kill all the other's ships wins

Done:
- Grid System
- Object Oriented Design for whole game
- Pretty fast run time for checking if ships alive (I believe linear but I'll update soon to make sure).  Due to each player storing Ships classes, the player does not have to go through their whole grid to see if their ships are alive
- Attacking should be done in linear time
- Player Can place where their ships go
- AI randomly places ships
- AI randomly chooses where to attack

To Do:
- Make it so that player nor AI can overlap previously placed ships (easy)
- Make it so that AI is more smart about where it attacks (not so easy)
- Change the printed view of the player's board so that it's easier to view (right now it displays both enemies and players view for debugging purposes) (easy)
- Add online with 2 players (I think this actually won't be hard)
