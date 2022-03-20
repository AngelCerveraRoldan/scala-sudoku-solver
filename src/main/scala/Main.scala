object Main extends App {
  def makeBoard(rows: List[List[Int]]): Board =
    def makeRow(nums: List[Int], y: Int): Row = (0 until 9).map(x => Tile((x, y), nums(x))).toList

    Board((0 until 9).map(y => makeRow(rows(y), y)).toList)

  val sampleBoard = makeBoard(List(
    //   0  1  2  3  4  5  6  7  8
    List(0, 0, 0, 0, 8, 6, 0, 0, 1), // 0
    List(0, 0, 2, 0, 0, 0, 6, 0, 0), // 1
    List(0, 7, 0, 0, 0, 0, 2, 5, 0), // 2
    List(9, 1, 0, 0, 0, 0, 0, 7, 0), // 3
    List(3, 0, 0, 1, 4, 5, 0, 0, 6), // 4
    List(0, 6, 0, 0, 9, 0, 0, 2, 4), // 5
    List(0, 5, 0, 0, 0, 0, 0, 6, 0), // 6
    List(0, 0, 0, 9, 0, 3, 0, 0, 0), // 7
    List(2, 0, 0, 5, 1, 0, 0, 0, 0)  // 8
  ))

  val nBoard = sampleBoard.solutions
  println(nBoard)
}