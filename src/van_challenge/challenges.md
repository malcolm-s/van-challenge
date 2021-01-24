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
