# Project 14

This is a repo to hold the code for the final project for Herr Schreiber's AP Comp
Sci class. This is a text based adventure game.

## Compiling

There are two ways to compile

1. Bluej
    Just add all of the .java files to a bluej project and hit compile. Then
    you can start the main method in the Main class to run the game.

2. Terminal
    ```sh
    javac *.java
    java Main
    ```
## Playing

This game takes many commands. Here they are:

| Command                                 | Description                     |
|-----------------------------------------|---------------------------------|
| `quit`/`exit`                           | Exits the game                  |
| `cheat [cheatcode]`                     | Inputs a cheat code             |
| `go [direction]`/`navigate [direction]` | Makes the player go a direction |
| `attack [character]`                    | Attacks a character             |
| `stats`                                 | Prints out your stats           |
| `say [something]`                       | Says something                  |
| `inventory`/`items`                     | Prints out your inventory       |
| `look`/`examine`                        | Examines your current room      |
| `look [thing]`/`examine [thing]`        | Examines a thing                |
| `grab [thing]`/`take [thing]`           | Grabs an item                   |
| `equip [item]`                          | Equips an item                  |
| `use [item]`                            | Uses an item                    |
| `use [item] on [item]`                  | Uses an item on an item         |
| `help`                                  | Doesn't help you                |
| `restart`/`reload`                      | Restarts the game               |
| `save`                                  | Saves the game                  |
| `load`                                  | Loads a game                    |

Nothing is case sensitive

## Walkthrough (!!! SPOILERS !!!)

```
Project 14
Made by Alex Mikhalev and Tavi Kohn

Please enter your name:
Screiber
-- Grate Room --
A rectangular room with old-looking stone walls. There is a small puddle of water on the floor, and exits to the north, east, and south. I don't know about you, but I think this room is great!
There is a(n) Grate in the room
 > go south
-- Storeroom --
A musty old storeroom. It smells like old grandpa feet in here, so you want to get out pretty quickly.
There is a(n) Coal in the room
There is a(n) Backpack in the room
There is a(n) Moss in the room
 > take backpack
You pick up the Backpack
 > take coal
You shove the Coal in your bag
 > go north
-- Grate Room --
A rectangular room with old-looking stone walls. There is a small puddle of water on the floor, and exits to the north, east, and south. I don't know about you, but I think this room is great!
There is a(n) Grate in the room
 > go east
-- Vault --
A large ancient vault. There is a large vault door that you are pretty sure you can't open.
There is a(n) Warp Ring in the room
There is a(n) Rock in the room
 > take rock
You shove the Rock in your bag
 > equip rock
You equip the Rock, which boosts your stats Attack +1
 > go west
-- Grate Room --
A rectangular room with old-looking stone walls. There is a small puddle of water on the floor, and exits to the north, east, and south. I don't know about you, but I think this room is great!
There is a(n) Grate in the room
 > attack grate
You attacked the Grate for 1 damage!
You kill the Grate
 > go down
Luckily, the coal weighs you down past the magical force!
-- Rising Room --
A mystic force tries to push you upward towards a 3 foot square grate. There is a small exit to the South
 > go south
-- Winding Tunnel --
A tunnel that twists and turns. You can't see very well in here, so good luck finding how to get out!
 > go east
-- Crystal Cavern --
A cavern with giant glowing crystals protruding from the wall. There are exits to the east and west.
There is a(n) Unlit Lantern in the room
There is a(n) Flint in the room
There is a(n) Shard in the room
 > take unlit lantern
You shove the Unlit Lantern in your bag
 > take flint
You shove the Flint in your bag
 > take shard
You shove the Shard in your bag
 > use flint on unlit lantern
You light the lantern using the flint. Hooray!
 > go east
-- Crystal Hall --
A large hall with the walls carved from some crystal. There are exits to the North and West
 > go north
-- Throne Room --
A great room with a slightly undersized crystal throne. There is an exit to the South
There is a(n) Hammer in the room
There is a(n) Rusty Pickaxe in the room
 > take hammer
You shove the Hammer in your bag
 > equip hammer
You equip the Hammer, which boosts your stats Attack +8
 > take rusty pickaxe
You shove the Rusty Pickaxe in your bag
 > use rusty pickaxe on shard
You smash the crystal into crystal shards!
 > go south
-- Crystal Hall --
A large hall with the walls carved from some crystal. There are exits to the North and West
 > go west
-- Crystal Cavern --
A cavern with giant glowing crystals protruding from the wall. There are exits to the east and west.
 > go west
-- Winding Tunnel --
A tunnel that twists and turns. You can't see very well in here, so good luck finding how to get out!
 > go north
-- Rising Room --
A mystic force tries to push you upward towards a 3 foot square grate. There is a small exit to the South
 > drop coal
You drop the Coal on the ground
 > go up
-- Grate Room --
A rectangular room with old-looking stone walls. There is a small puddle of water on the floor, and exits to the north, east, and south. I don't know about you, but I think this room is great!
 > go north
-- Wizard's Grotto --
A small room, with a skylight above. There is a door to the west and an archway to the south.
There is a(n) Spellbook in the room
There is a(n) Staff in the room
 > take spellbook
You shove the Spellbook in your bag
 > take staff
You shove the Staff in your bag
 > go west
-- Wizard's Wardrobe --
A small wardrobe with a door to the east
There is a(n) Hat in the room
There is a(n) Robe in the room
 > take hat
You shove the Hat in your bag
 > take robe
You shove the Robe in your bag
 > go east
-- Wizard's Grotto --
A small room, with a skylight above. There is a door to the west and an archway to the south.
 > go south
-- Grate Room --
A rectangular room with old-looking stone walls. There is a small puddle of water on the floor, and exits to the north, east, and south. I don't know about you, but I think this room is great!
 > go east
-- Vault --
A large ancient vault. There is a large vault door that you are pretty sure you can't open.
There is a(n) Warp Ring in the room
 > look spellbook
Spellbook - An ancient tome with letters that seem to shift across the page. Some you can translate:
	Transmutation:
	Using a ... it is possible to ... into...
	 for example chocolate (by saying agherm)...

 The rest of the book is stained, burned, and unreadable
 > say agherm
Awesome! The spell worked! The vault door turns into pure chocolate!
 > look
-- Vault Room --
A large ancient vault. There is a large vault door that you are pretty sure you can't open.
There is a(n) Warp Ring in the room
There is a(n) Chocolate Door in the room
 > look chocolate door
Chocolate Door - A vault door made of pure chocolate!
 > equip hat
You equip the Hat, which boosts your stats Defense +1
 > attack chocolate door
You attacked the Chocolate Door for 9 damage!
 > attack chocolate door
You attacked the Chocolate Door for 9 damage!
 > attack chocolate door
You attacked the Chocolate Door for 9 damage!
You kill the Chocolate Door
 > look
-- Vault Room --
A large ancient vault. There is a large vault door that you are pretty sure you can't open.
There is a(n) Warp Ring in the room
There is a(n) Chocolate Slab in the room
 > take chocolate slab
You shove the Chocolate Slab in your bag
 > take warp ring
You shove the Warp Ring in your bag
 > look chocolate slab
Chocolate Slab - A large slab of pure chocolate!
 > use chocolate slab
It seems a bit early to do this. Why don't you explore a bit more.
 > use warp ring
You go through time. It is pretty cool when you do that, because it is all trippey and stuff. You would talk about it but there is no time, because you end up in the same room but in a different time!
-- Final Room --
This is the final room. You have one more thing to do before you win...
 > use chocolate slab
You sink your teeth into the delicious chocolate.You spread it through your mouth, attempting to describe the flavor...





You realize that this chocolate sucks. Well, that was a waste of time. That's life for you, ain't it? Bye, and go do something better with your time, Screiber.
```