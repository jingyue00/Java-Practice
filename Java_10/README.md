## Homework 10 - Week 10

### Motivation
* Demonstrate your ability to program using Java concurrency mechanisms (i.e., Threads / ServiceExecutors / latch objects).

### Instructions
* Your task is to create a fortification which can weather an invasion
    - There are two classes to implement.  Two implementations of [Fortification](src/main/java/edu/nyu/cs9053/homework10/Fortification.java)
        - I have stubbed out the two implementations with TODOs for you to implement.
            - Although I have provided stubs please think about encapsulation and code-reuse and create abstract classes/etc if warranted.
        - [MiddleAgesFortification](src/main/java/edu/nyu/cs9053/homework10/MiddleAgesFortification.java)
        - [ModernFortification](src/main/java/edu/nyu/cs9053/homework10/ModernFortification.java)
    - Besides the two implementations you must also address the TODOs in [FortificationFactory](src/main/java/edu/nyu/cs9053/homework10/FortificationFactory.java)
        - This is similar to previous homework assignments in that you must construct your concrete implementations of interface, [Fortification](src/main/java/edu/nyu/cs9053/homework10/Fortification.java)
    - You should not modify any of [Battler](src/main/java/edu/nyu/cs9053/homework10/Battler.java), [AttackHandler](src/main/java/edu/nyu/cs9053/homework10/AttackHandler.java), [Fortification](src/main/java/edu/nyu/cs9053/homework10/Fortification.java), [ConcurrencyFactorProvider](src/main/java/edu/nyu/cs9053/homework10/ConcurrencyFactorProvider.java), [Invasion](src/main/java/edu/nyu/cs9053/homework10/Invasion.java)
        - If you find yourself wanting to do so, you are doing something wrong
    - To test your solution works (at least functionally, there is no automated test for thread-safety that will be me and the TA reviewing your code manually):
        - Run [Battler](src/main/java/edu/nyu/cs9053/homework10/Battler.java) passing in values as detailed by it.
        - It should be able to run for at least 5 minutes without failing

### Implementation
* Ensure your code is correct by compiling and testing it
* A portion of your grade will be based upon readability and organization of your code.
    - Follow the naming guidelines of lecture
    - Break large functions into multiple functions based on logical organizations
    - Make abstract classes where necessary
* Your code may correctly withstand the invasion yet still be incorrect. It must be guaranteed to be thread safe.
    

    