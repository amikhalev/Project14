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