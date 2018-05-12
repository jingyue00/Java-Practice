## Homework 8 - Week 8

### Motivation
* Demonstrate your ability to design algorithms using Java Collections

### Instructions
There is one task to complete.
* Using Java Collections, create a solution to the following problem.
    - You have just graduated NYU!  Congratulations (w00t w00t)!  Even better, you have landed a job.  You're now a software engineer at a newly created startup, `Hipster.Phone`.  The start-up aims to bring to market a "reimagining of the flip-phone."  As crazy as it sounds the company has been gaining hype and has been able to raise a round of capital and build out an engineering team.  There is a lot of work to do to bring modern smartphone convenience to the packaging of an old-school flip-phone.  Your task is to ease the speed of texting on a 0-9 numerical keypad.  To assist in your task your manager has told you, "Don't reinvent the wheel, they did this back in the day with [T9](https://en.wikipedia.org/wiki/T9_(predictive_text))"  Using T9 as a rough guide, build the following subset of functionality; from an inputted sequence of key presses (2-9, 1 and 0 are reserved for special actions) return a `List<String>` possible words (potentially empty but never `null`).

    - Place this within [TextingDictionary](src/main/java/edu/nyu/cs9053/homework8/TextingDictionary.java)

    - To assist your implementation, create an `enum` named [ValidTextKeyPress](src/main/java/edu/nyu/cs9053/homework8/ValidTextKeyPress.java) with values; `Two`, `Three`, `Four`, `Five`, `Six`, `Seven`, `Eight` and `Nine`.

    - Your `TextingDictionary` should have the following two public methods: `void insert(String word)` (to populate into the dictionary of possible results), `List<String> search(List<ValidTextKeyPress> prefixes)` (given an ordered list of valid key-press values returns a list of words or empty if none found)

    - Your implementation should be case insensitive (i.e. `Brian` is treated the same as `brian`).  Your implementation must handle non a-Z or A-Z characters by throwing an `IllegalArgumentException`. Your implementation need not care about frequency or return order of the words.

    - The following are some example inputs and outputs (in short-hand, i.e. non-Java, for brevity):
```
dictionary.insert("Brian")
dictionary.insert("brain")
dictionary.insert("braid")
dictionary.search({Two, Seven}) -> {"Brian","brain","braid"}
dictionary.search({Two, Seven, Two}) -> {"brain", "braid"}
dictionary.search({Two, Seven, Four}) -> {"Brian"}
dictionary.search({Two, Eight}) -> {}
dictionary.insert("butte")
dictionary.search({Two, Eight}) -> {"butte"}
```

### Implementation
* Ensure your code is correct by compiling and testing it
* A portion of your grade will be based upon readability and organization of your code.
    - Follow the naming guidelines of lectures
    - Break large functions into multiple functions based on logical organizations