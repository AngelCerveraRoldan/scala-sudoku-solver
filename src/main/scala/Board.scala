type Row = List[Tile]

class Board(rows: List[Row]) {
  assert(rows.length == 9, "The board must be 9x9")
  assert(rows.length == rows.head.length, "The board must be square")

  override def toString: String =
    rows.flatMap(row =>
      row.flatMap(tile => f"${tile.num}") ++ "\n"
    ).toString()

  def getCol(x: Int): List[Tile] = rows.indices.map(i => rows(i)(x)).toList
  def getRow(y: Int): List[Tile] = rows(y)
  def getBlock(x: Int, y: Int): List[Tile] =
    val xBlock: Int = x / 3
    val yBlock: Int = y / 3

    // Get the rows that are in the block
    val inBlockRows: List[Row] = rows.slice(3 * yBlock, 3 * (yBlock + 1))
    val inBlockAll: List[Row] = inBlockRows.map(row => row.slice(3 * xBlock, 3 * (xBlock + 1)))

    inBlockAll.flatten

  def possibleValues(tile: Tile): List[Int] =
    val (x, y) = tile.coords
    val cannotBe = (getCol(x) ++ getRow(y) ++ getBlock(x, y)).map(tile => tile.num)

    if tile.num == 0 then (1 to 9).filterNot(n => cannotBe.contains(n)).toList else List()

  // Check if the board has been solved
  // TODO: Improve this function, as an uncompleted row could also sum to 45 if a tile contains an invalid number such as 12
  def solved: Boolean =
    rows
      .map(row => row.map(tile => tile.num).sum)
      .forall(_ == 45)

  def updateTile(x: Int, y: Int, v: Int): Board =
    Board(rows.updated(
      y,
      rows(y).updated(x, rows(y)(x).update(v))
    ))

  def simplifyBoard(): Board =
    def updateRow(row: Row) =
      row.map(tile => if possibleValues(tile).length == 1 then tile.update(possibleValues(tile).head) else tile)

    // new board with updated values
    val newBoardVal = rows.map(row => updateRow(row))
    val newBoard = Board(newBoardVal)

    // This will make it so that the simplify function will keep calling itself until it can no longer improve the board
    if newBoardVal == rows then newBoard
    else newBoard.simplifyBoard()

  // Get all possible solutions to a sudoku
  def solutions: List[Board] = this.simplifyBoard() match {
    case x: Board if x.solved => List(x)
    case x: Board =>
      // x is the board, simplified but not solved
      // find first empty tile
      val emptyTile = rows.map(row => row.filter(tile => tile.num == 0)).filter(row => row.nonEmpty).head.head
      val replacedTiles =
        this.possibleValues(emptyTile)
          .map(possibleValue => {
            this.updateTile(emptyTile.coords._1, emptyTile.coords._2, possibleValue)
          })

      replacedTiles.flatMap(board => board.solutions)
  }
}