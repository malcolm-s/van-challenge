# 1 - Number game

Write a game (computer program) that follows the following rules:

- The computer chooses a number between given set limits.
- The player is asked for repeated guesses until the the target number is guessed correctly

At each guess, the computer responds with whether the guess is:

- higher than the target,
- equal to the target,
- less than the target, or
- the input was inappropriate.

# 2 - Bitmap/Bresenham's line algorithm

Write a basic line renderer that outputs to the terminal:

- You can store the points and lines in memory (maybe even in a read only constant!)
- Do the intermediate calculation of the final image in a simple frame buffer.
- You can assume a terminal (and buffer) of constant size and that the points fit in it.

Bonus points:

- Use 3D lines and do a basic orthographic projection to 2D space.
- Scale the points to fit the size of the fixed frame size.

# 3 - Noughts and Crosses

The goal is to write the perfect AI for a noughts and crosses (tic tac toe) game.
Write a function that, when given a series of moves by each player, will use a decision tree to decide on the next best move to make (as pictured on https://en.wikipedia.org/wiki/Tic-tac-toe#Strategy).

Hard mode:

- Render the output of the function on a grid
- Build the AI into a fully functioning game.
  - Randomise who goes first, you or the computer.
  - Reject invalid input.
  - Notify when there is a winner.

We like this one because it covers:

- Basic classes to store the decision nodes
- Recursion or stacks

You'll also be building on the concepts that you learned this week:

- Defining lists, changing their contents

A player can play a perfect game of tic-tac-toe (to win or at least draw) if, each time it is their turn to play, they choose the first available move from the following list, as used in Newell and Simon's 1972 tic-tac-toe program.

1. Win: If the player has two in a row, they can place a third to get three in a row.
2. Block: If the opponent has two in a row, the player must play the third themselves to block the opponent.
3. Fork: Create an opportunity where the player has two ways to win (two non-blocked lines of 2).
4. Blocking an opponent's fork: If there is only one possible fork for the opponent, the player should block it. Otherwise, the player should block all forks in any way that simultaneously allows them to create two in a row. Otherwise, the player should create a two in a row to force the opponent into defending, as long as it doesn't result in them creating a fork. For example, if "X" has two opposite corners and "O" has the center, "O" must not play a corner move in order to win. (Playing a corner move in this scenario creates a fork for "X" to win.)
5. Center: A player marks the center. (If it is the first move of the game, playing a corner move gives the second player more opportunities to make a mistake and may therefore be the better choice; however, it makes no difference between perfect players.)
6. Opposite corner: If the opponent is in the corner, the player plays the opposite corner.
7. Empty corner: The player plays in a corner square.
8. Empty side: The player plays in a middle square on any of the 4 sides.

# 4 - String Shenanigans

This week is a set of small string processing challenges to get us used to using string and text in our chosen languages. Thanks to Jim for this idea!
* Get the first word in a string.
* Count the most common words in a string.
* Output the longest line in a string.
  * Bonus: Output all of the longest lines if there's a tie.
* Output groups of characters as they appear in a string. So "hello old wool" would be output as "h, e, ll, o, , o, l, d, , w, oo, l".
* Get the substring of a string.
* Replace all occurrences of a character in a string with another.
  * Bonus: Allow the replacement to be a string rather than a character. The replacement string can be of any length.
* Match a string against a regex pattern (Usage of libraries is allowed!)
* Substitute part of a string using a regex pattern (Usage of libraries is allowed!)

Hard mode (these may not apply to all of the above problems, and may not apply to your chosen language!):
* Be wary of the encoding of the strings in the above problems (ie support unicode). Try with UTF-8 and UTF-16 encodings.
* Do not use numerical comparisons in the above problems. Using equality is okay, but do not compare the length of a string.
* Do not use lists in the above problems.
* Do not use arithmetic operators in the above problems. (So no ++ or i += 1!).

"Just for Jim" (a separate problem to choose from if you want something bigger): 
* Implement one or more edit distance algorithms as described here: https://en.wikipedia.org/wiki/Edit_distance

We like this one because it covers:
* How to use strings and text.
* Calling functions.
* Using maps/dicts.

# 5 - Input and/or synchronous concurrency #

We've split this one into parts, the idea being they scale depending on how much time you have/what language you've chosen.

## Part One

Given a file path from the command line, read the file and count the number of lines it contains.

You could:
* Pick one of the string problems from last week to run on the lines.
* Add a command line flags to select the string processing to be run, by default the program will print the number of lines.

This covers:
* Arguments.
* Reading in files.

## Part Two - Concurrency!
Part one but with concurrency!

https://rosettacode.org/wiki/Synchronous_concurrency

Create two concurrent activities ("Threads" or "Tasks", not processes.) that share data synchronously.
One of the concurrent units (the reading unit) will read from a given file and send the contents of that file, one line at a time, to the other concurrent unit (the printing unit), which will print the line it receives to standard output.
The printing unit must count the number of lines it prints, after the reading unit sends its last line to the printing unit, the reading unit will request the number of lines printed by the printing unit, which it will then print (gasp, even though it is a reading unit).

This covers:
* Learning how to create threads.
* Communication between threads (put/get).
* Cleanly terminating the threads.
* Threads.

## "Just for Jim" - More Concurrency

Expand on part two, you could:
* Add a third unit that queries the reading and printing unit to calculate the current progress of the file processing.
* Implement a system such that there is a main thread that organises the two (or more) workers.
