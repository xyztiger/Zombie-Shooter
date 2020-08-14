# BoxHead

## A top-down zombie shooter game

This is a top-down zombie shooter game inspired by the popular web game Box Head.
The user will control a soldier and try to survive as long as they can by shooting
zombies that spawn.

Users of this project are looking for a lax and fun way to pass their time.

This project is interesting to me because:

- I was a big fan of the original Box Head game
- I believe that coding this project will be appropriately challenging
- I hope that I will create an application that I myself will enjoy using

## User Stories

- As a user, I want to be able to move my character
- As a user, I want to be able to shoot my weapon
- As a user, I want to be able to have zombies spawn
- As a user, I want to have new zombies spawn when I kill a zombie
- As a user, I want to be able to shoot zombies to kill them
- As a user, I want to be able to see the stage in which I am confined in
- As a user, I want to be able to buy new guns and add them to my arsenal
- As a user, I want to be able to keep track of my ammo
- As a user, I want to be able to buy and add ammo to my ammo reservoir
- As a user, I want to be able to see my score increase when I kill zombies
- As a user, I want to be able to spend my score points to buy new guns/ammo
- As a user, I want to be able to choose a weapon from my current arsenal

- As a user, I want to be able to save my current game state to file.
- As a user, I want to be able to optionally load my previous game state when I run the program.

- PHASE 3:
- press B for the user story "- As a user, I want to be able to buy new guns and add them to my arsenal"
    and "- As a user, I want to be able to buy and add ammo to my ammo reservoir"
- press C for the user story "- As a user, I want to be able to choose a weapon from my current arsenal"

- PHASE 4: TASK 2
- My Player class is robust in that the move() method will throw a BorderException if the player attempts to move
  outside the stage. This exception is handled by the Game class which will print out a message to the system and
  not allow the player to move out the stage.
- The tests for the move() method and exception handling is included in the PlayerTest class.

- PHASE 4: TASK 3
- One problem is that my panels contained too much duplicate code. I fixed this by creating an abstract class called
  ButtonPanel which implements methods common to all my panels that have buttons. All my panels which have buttons now 
  extend the ButtonPanel class, which drastically reduced duplicate code. This was partially done in Phase 3.
- Another problem is that there was poor cohesion in my Game class. This class had too many functionalities. I fixed
  this by creating two separate classes called the LoadPanel and QuitPanel, which implement the load game and save/quit
  game features that were both originally methods within the Game class. Now the Game class has less logic and focuses
  more on taking in user input.