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

- Get the first word in a string.
- Count the most common words in a string.
- Output the longest line in a string.
  - Bonus: Output all of the longest lines if there's a tie.
- Output groups of characters as they appear in a string. So "hello old wool" would be output as "h, e, ll, o, , o, l, d, , w, oo, l".
- Get the substring of a string.
- Replace all occurrences of a character in a string with another.
  - Bonus: Allow the replacement to be a string rather than a character. The replacement string can be of any length.
- Match a string against a regex pattern (Usage of libraries is allowed!)
- Substitute part of a string using a regex pattern (Usage of libraries is allowed!)

Hard mode (these may not apply to all of the above problems, and may not apply to your chosen language!):

- Be wary of the encoding of the strings in the above problems (ie support unicode). Try with UTF-8 and UTF-16 encodings.
- Do not use numerical comparisons in the above problems. Using equality is okay, but do not compare the length of a string.
- Do not use lists in the above problems.
- Do not use arithmetic operators in the above problems. (So no ++ or i += 1!).

"Just for Jim" (a separate problem to choose from if you want something bigger):

- Implement one or more edit distance algorithms as described here: https://en.wikipedia.org/wiki/Edit_distance

We like this one because it covers:

- How to use strings and text.
- Calling functions.
- Using maps/dicts.

# 5 - Input and/or synchronous concurrency

We've split this one into parts, the idea being they scale depending on how much time you have/what language you've chosen.

## Part One

Given a file path from the command line, read the file and count the number of lines it contains.

You could:

- Pick one of the string problems from last week to run on the lines.
- Add a command line flags to select the string processing to be run, by default the program will print the number of lines.

This covers:

- Arguments.
- Reading in files.

## Part Two - Concurrency!

Part one but with concurrency!

https://rosettacode.org/wiki/Synchronous_concurrency

Create two concurrent activities ("Threads" or "Tasks", not processes.) that share data synchronously.
One of the concurrent units (the reading unit) will read from a given file and send the contents of that file, one line at a time, to the other concurrent unit (the printing unit), which will print the line it receives to standard output.
The printing unit must count the number of lines it prints, after the reading unit sends its last line to the printing unit, the reading unit will request the number of lines printed by the printing unit, which it will then print (gasp, even though it is a reading unit).

This covers:

- Learning how to create threads.
- Communication between threads (put/get).
- Cleanly terminating the threads.
- Threads.

## "Just for Jim" - More Concurrency

Expand on part two, you could:

- Add a third unit that queries the reading and printing unit to calculate the current progress of the file processing.
- Implement a system such that there is a main thread that organises the two (or more) workers.

# 6 - Data Science with CSV files

## CSV Files

The data/ folder contains some csv and json files for the problem this week.

### Rugby

rugby.csv contains some statistics from the English Rubgy Union premiership
and championship leagues in the 19/20 season.
The columns "Total For" and "Total Against" contain the number of tries scored
for a team and against them respectively.
Write a program to read the file and print the teams with the smallest
and biggest differences in tries scored for and against them.

### Weather

weather.csv contains the weather data for Whistler Roundhouse in 2020.
Each row contains the weather data for a day of the year,
The columns "Max Temp (°C)" and "Min Temp (°C)" contain the lowest
and highest temperature for that day.
Write a program to read the file and print the days with the smallest
and biggest differences in temperature on a day.

Tips:

- If you have trouble with the unicode in the column headers then feel free to edit that out.
- If you have trouble with the quoting in the file then feel free to edit that out.

Bonus:

- Try to write the code such that you can use the same functions for the rugby and weather data.

### Detecting Accounting Errors

You've opened a successful bakery in Oregon (no sales tax!)
(but this is at a point in time where the US Dollar is weak
and matches the Canadian Dollar, so someone living in Canada wouldn't find your prices strange...).
An important part of any business is accounting.
transactions.csv contains bank transactions for all the sales you've made in the past month,
and sales.csv contains the receipt data for all of those transactions.
Your task is to write a program that will output the IDs of transactions
with accounting errors (ie. where the transaction amount does not match the amount on the receipt).

errors.csv contains the correct list of erroneous transactions
for you to validate against.

Bonus:

- Calculate the percentage of incorrect transactions.
- Calculate the amount of money gained or lost to errors.
- Calculate which staff member makes the most mistakes.
  - Calculate which staff member makes the most mistakes as
    a percentage of their total sales and fire them!
- Try to solve the problem without using the transaction ID from the bank transactions.
- If your chosen language has such a type, use a "decimal" type to calculate
  the amount of money lost or gained to errors,
  so that you do not accumulate floating point errors!
  For example, Python has https://docs.python.org/3/library/decimal.html.

### Bonuses

- Read the data out of the associated json files for each problem instead of the csv files.
