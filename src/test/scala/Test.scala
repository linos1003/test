import org.scalatest.FlatSpec
import utils.Parser._
import utils.PositionsManger._

/**
  * Created by bsmida on 19/11/17.
  */
class Test extends FlatSpec {
  "Mowers positions" should " be calculated as expected " in {
    val inputs = readFile("src/test/resources/in/mower.in")
    val expected = readFile("src/test/resources/out/mower.out")
    val lawn = initLawn(inputs(0))
    val mowers=loadMowersAndCommandes(inputs)

    assert(1 == 1)
  }
}
