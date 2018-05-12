## Homework 11 - Week 11

### Motivation
* Demonstrate your ability to program using Java IO mechanisms.

### Instructions
* Your task is to create a networked game.
    - There are two classes to implement.  You should not modify any other file to complete this homework.
        - [GameClient](src/main/java/edu/nyu/cs9053/homework11/network/GameClient.java) which should use blocking IO
        - [GameServer](src/main/java/edu/nyu/cs9053/homework11/network/GameServer.java) which should use non-blocking IO
    - To test your solution works, in two separate terminal tabs/windows run the following:
        - Run [GameServer](src/main/java/edu/nyu/cs9053/homework11/network/GameServer.java)
        - Run [Game](src/main/java/edu/nyu/cs9053/homework11/game/Game.java).
            - You can pass in a [Difficulty](src/main/java/edu/nyu/cs9053/homework11/game/Difficulty.java) to this program; the default is Easy. If you can beat the game on Easy try Medium and Hard

### Implementation
* Ensure your code is correct by compiling and testing it
* A portion of your grade will be based upon readability and organization of your code.
    - Follow the naming guidelines of lecture
    - Break large functions into multiple functions based on logical organizations
* Your code may correctly count the words within the files but may still be incorrect. It must be thread safe as well.
    
## NOTE - will not run correctly on Windows OS
* This homework can be developed on Windows but you'll be unable to run the program (and see Emoji) on a Windows machine.  You'll need to do this on a Unix based machine to have the emoji/ASCII-coloring work properly.
* If you do not have access to a Unix based machine (Mac, Linux, etc) then let me know ASAP and I can create a VM for you to use.