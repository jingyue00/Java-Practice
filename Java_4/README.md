## Homework 4 - Week 4

### Motivation
* Demonstrate your ability to program using inheritance in the Java programming language.
* Demonstrate your knowledge of proper `equals` and `hashCode` implementations in the Java programming language.
* Demonstrate your knowledge of variable arguments and enum types in the Java programming language.

### Instructions
* There are four tasks to complete
    - Create an object hierarchy which mimics the classification for players of [winter sports](https://en.wikipedia.org/wiki/Winter_sport).
        - The hierarchy should be contained within package [edu.nyu.cs9053.homework4.hierarchy](src/main/java/edu/nyu/cs9053/homework4/hierarchy)
        - There should be a base class called [WinterSportPlayer](src/main/java/edu/nyu/cs9053/homework4/hierarchy/WinterSportPlayer.java)
        - There should be the following subtypes:
            - [Luger](src/main/java/edu/nyu/cs9053/homework4/hierarchy/Luger.java)
            - [IceSkater](src/main/java/edu/nyu/cs9053/homework4/hierarchy/IceSkater.java)
            - [Snowboarder](src/main/java/edu/nyu/cs9053/homework4/hierarchy/Snowboarder.java)
            - [Skier](src/main/java/edu/nyu/cs9053/homework4/hierarchy/Skier.java)
            - [SpeedSkater](src/main/java/edu/nyu/cs9053/homework4/hierarchy/SpeedSkater.java)
            - [Curler](src/main/java/edu/nyu/cs9053/homework4/hierarchy/Curler.java)
            - [Sledder](src/main/java/edu/nyu/cs9053/homework4/hierarchy/Sledder.java)
            - [Bobsledder](src/main/java/edu/nyu/cs9053/homework4/hierarchy/Bobsledder.java)
            - [CrossCountrySkier](src/main/java/edu/nyu/cs9053/homework4/hierarchy/CrossCountrySkier.java)
            - [SkeletonPlayer](src/main/java/edu/nyu/cs9053/homework4/hierarchy/SkeletonPlayer.java)
            - [MogulSkier](src/main/java/edu/nyu/cs9053/homework4/hierarchy/MogulSkier.java)
            - [FigureSkater](src/main/java/edu/nyu/cs9053/homework4/hierarchy/FigureSkater.java)
        - Each of the subtypes should have the following methods:
            - `getName` returns a name for the player as a `String`. This value is per object and not defined by the class it should be used for equality and hashCode.
            - `getAge` returns the age of the player as an `int`. This value is per object and not defined by the class it should be used for equality and hashCode.
        - If appropriate, the subtypes should have the following methods:
            - `getSkateSize` returns the size of the player's skate as an `int`.
            - `getSledColor` returns a `String` representing the color of the sled the player users.
            - `getSkiLength` returns the length in centimeters of the player's skis as an `int`.
        - For each class which has no sub-types itself, add an instance field to the class particular to the type which is also used in the `equals` and `hashCode` methods.
    - Provide implementations of the `equals` and `hashCode` methods for each concrete class within package [edu.nyu.cs9053.homework4.hierarchy](src/main/java/edu/nyu/cs9053/homework4/hierarchy)
        - Note, these methods are testing equality and returning hashes for the objects and so should only include checks on type information and object specific values.
        - Note, do not use Objects helper class, write your own implementations.
        - Note, do not share code with super classes.
    - Create an enum type representing the 3 medals awarded in the Olympic games named [OlympicMedal](src/main/java/edu/nyu/cs9053/homework4/OlympicMedal.java).    
    - Create an enum type representing the 23 Olympic games [ever played](https://en.wikipedia.org/wiki/Winter_Olympic_Games#List_of_Winter_Olympic_Games);
        - Each Olympic game must also have a method returning the host country of the Olympics as a `String`, name it `getHostCountry`.
        - The enum should be called [OlympicGame](src/main/java/edu/nyu/cs9053/homework4/OlympicGame.java) with package `edu.nyu.cs9053.homework4`
    - Create a static varargs method within [OlympicGame](src/main/java/edu/nyu/cs9053/homework4/OlympicGame.java) which takes a variable number of OlympicGame objects and prints each object's host country.

### Implementation
* Ensure your code is correct by compiling and testing it
* A portion of your grade will be based upon readability and organization of your code.
    - Follow the naming guidelines of lecture
    - Break large functions into multiple functions based on logical organizations
    

    