import models.Mower
import org.scalatest.FlatSpec
import utils.Parser._
import utils.PositionsManger._

/**
  * Created by bsmida on 19/11/17.
  */
class TestCase extends FlatSpec {
  /**
    * Use case test  from the exam
    */
  "USE CASE 1: Mowers positions" should " be calculated as expected " in {
    val inputs = readFile("src/test/resources/in/mower_use_case_1.in")
    val expectedPositions = loadPositions("src/test/resources/out/mower_use_case_1.out")
    lawn = initLawn(inputs(0))

    val l = getFinalPositions(inputs)
    assert(l.toSet == expectedPositions.toSet)
  }

  /**
    * A custom test case contains mowers with off lawn area movements.
    * example :   the   next  position   of   a  mower  having  (0,0)
    * coordinates west orientation  and  A  command can't be (-1 , 0).
    * So we keep the last position.
    *
    */


  "USE CASE 2: Mowers positions" should " be calculated as expected " in {
    val inputs = readFile("src/test/resources/in/mower_use_case_2.in")
    val expectedPositions = loadPositions("src/test/resources/out/mower_use_case_2.out")
    lawn = initLawn(inputs(0))

    val l = getFinalPositions(inputs)

    assert(l.toSet == expectedPositions.toSet)
  }


  /**
    * A custom test case contains random mowers and commands.
    *
    */
  "USE CASE 3: Mowers positions" should " be calculated as expected " in {
    val inputs = readFile("src/test/resources/in/mower_use_case_3.in")
    val expectedPositions = loadPositions("src/test/resources/out/mower_use_case_3.out")
    lawn = initLawn(inputs(0))

    val l = getFinalPositions(inputs)

    assert(l.toList == expectedPositions.toList)
  }

}
