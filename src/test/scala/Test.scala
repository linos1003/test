import org.scalatest.FlatSpec
import utils.Parser._
import utils.PositionsManger._

/**
  * Created by bsmida on 19/11/17.
  */
class Test extends FlatSpec {
  "Mowers positions" should " be calculated as expected " in {
    val inputs = readFile("src/test/resources/in/mower.in")
    val expectedPositions = loadPositions("src/test/resources/out/mower.out")
    lawn = initLawn(inputs(0))

    val mowers = loadMowersAndCommandes(inputs)
    val l = mowers.map(x => computeNewPosition(x._1, x._2))
    assert(l.toSet == expectedPositions.toSet)
  }

  def loadPositions(path: String) = readFile(path).map(parseMower)
}
