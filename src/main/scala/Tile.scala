class Tile(val coords: (Int, Int), val num: Int) {
  assert(num < 10, "A tile cannot contain a value greater than 9!")
  def update(x: Int): Tile = Tile(coords, x)
}
