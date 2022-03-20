# Sudoku Solver Scala

# Algorithms
There are two main algorithms, one of them simplifies the board,
the other returns a List of possible Boards

## Simplify Board
To simplify the board, we will check the possible values each empty 
tile can have, if an empty tile can only possibly be filled with one
number, then we will update the board such that the tile contains said
value.

If an empty tile could contain multiple values, then we will leave it 
empty.

We will run this simplifies function recursively, until the sudoku is 
solved, or until there are no empty tiles that can only hold one value.

If the board isn't finished, and we cannot further simplify it, we will then 
run the "guessing" algorithm.

## Guessing
This algorithm is quite simple, if there is a tile that could contain multiple 
values, return a list containing boards. Each board will hold one of the 
possible values for said tile.

We then run the simplify algorithm on each one of the boards.

If we cannot solve a board by simplifying it, we will keep guessing.

After a few iterations of guessing and simplofying, we will get a list 
containing all the possible answers for a sudoku.

## Todo
- [ ] Add some tests
- [ ] Improve toString function