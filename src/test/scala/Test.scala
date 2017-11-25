import org.scalatest.FlatSpec
import utils.Parser._
import utils.PositionsManger._

/**
  * Created by bsmida on 19/11/17.
  */
class Test extends FlatSpec {
  /**
    * Use case test  from the exam
    */
  "Mowers positions" should " be calculated as expected " in {
    val inputs = readFile("src/test/resources/in/mower.in")
    val expectedPositions = loadPositions("src/test/resources/out/mower.out")
    lawn = initLawn(inputs(0))

    val mowers = loadMowersAndCommands(inputs)
    val l = mowers.map(x => computeNewPosition(x._1, x._2))
    assert(l.toSet == expectedPositions.toSet)
  }

  /**
    * Another custom tes use case
    */
  "USE CASE 2: Mowers positions" should " be calculated as expected " in {
    val inputs = readFile("src/test/resources/in/mower_use_case_2.in")
    val expectedPositions = loadPositions("src/test/resources/out/mower_use_case_2.out")
    lawn = initLawn(inputs(0))

    val mowers = loadMowersAndCommands(inputs)
    val l = mowers.map(x => computeNewPosition(x._1, x._2))
    assert(l.toSet == expectedPositions.toSet)
  }


}
