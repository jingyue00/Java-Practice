## Homework 5 - Week 5

### Motivation
* Demonstrate your ability to program using interfaces and inner classes in the Java programming language.

### Instructions
* There are two tasks to complete
    - Become a five-star restaurant chef!
        - Finish [Chef](src/main/java/edu/nyu/cs9053/homework5/Chef.java) as detailed by its comments
        - Use anonymous classes to implement [Timer](src/main/java/edu/nyu/cs9053/homework5/Timer.java) when set by the [Chef](src/main/java/edu/nyu/cs9053/homework5/Chef.java) while using her [Oven](src/main/java/edu/nyu/cs9053/homework5/Oven.java)
        - All implementations of [Recipe](src/main/java/edu/nyu/cs9053/homework5/Recipe.java) need the `Oven` to be made.
            - Make an implementation of [Recipe](src/main/java/edu/nyu/cs9053/homework5/Recipe.java) for [RoastedSweetPotato](src/main/java/edu/nyu/cs9053/homework5/RoastedSweetPotato.java) which takes 1/10th the `Oven` temperature in minutes (e.g. if the `Oven` temperature is 300 then it takes 30 minutes to make the `RoastedSweetPotato`)
                - It's food's volume is 6,000 cubic inches
            - Make an implementation of [Recipe](src/main/java/edu/nyu/cs9053/homework5/Recipe.java) for [PotRoast](src/main/java/edu/nyu/cs9053/homework5/PotRoast.java) which takes 1/5th the `Oven` temperature in minutes (e.g. if the `Oven` temperature is 300 then it takes 60 minutes to make the `PotRoast`)
                - It's food's volume is 10,000 cubic inches
            - Make an implementation of [Recipe](src/main/java/edu/nyu/cs9053/homework5/Recipe.java) for [Baguette](src/main/java/edu/nyu/cs9053/homework5/Baguette.java) which takes time exponentially proportional (i.e. exponential decay) to the `Oven` temperature in minutes.  The rate is passed into the recipe's constructor. (e.g. if the `Oven` temperature is 300, the constant is 0.5 then it takes ~20 minutes to make the `Baguette`); see [wiki](http://en.wikipedia.org/wiki/Exponential_decay).  Note, making a perfect French baguette is tricky as there's not an exact time but something close enough to done.
                - It's food's volume is 2,000 cubic inches
        - The `Chef` should make one `PotRoast`, one `Baguette`, one `RoastedSweetPotato` and second `Baguette` in that order.
        - The `Oven`'s initial temperature should be `300`
        - Note, to become a five-star chef takes high precision when cooking.  Unfortunately the `Oven` isn't perfectly calibrated and so the temperature set often fluctuates.
        - Note, every great chef has a great sous-chef.  Use your [SousChef](src/main/java/edu/nyu/cs9053/homework5/SousChef.java) wisely!
        - Note, you need not (and must not) use `Thread`/`synchronized` or any other concurrency pattern for this assignment. They exist within the provided code but you will not need to program them yourself (this assignment can be done 100% with things we've already learned in lecture).     
    - Fix the leaky kitchen [Faucet](src/main/java/edu/nyu/cs9053/homework5/memory/Faucet.java) such that you eliminate the inner class leak.

### Implementation
* Ensure your code is correct by compiling and testing it
* A portion of your grade will be based upon readability and organization of your code.
    - Follow the naming guidelines of lecture
    - Break large functions into multiple functions based on logical organizations
    

    