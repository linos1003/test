import models.{Lawn, Mower}
import models.Orientation.N
import org.apache.log4j.Logger
import utils.PositionsManger._

/**
  * Created by bsmida on 19/11/17.
  */
object AppMain extends App {

  val LOGGER = Logger.getLogger(this.getClass)

  if (args.length < 2)
    LOGGER.error(
      """
        |Wrong number of parameters
        |Usage: AppMain <input-file-path> <output-file-path>
      """.stripMargin
    )

  lawn = new Lawn(5, 5)
  printPosition(new Mower(0, 0, N))


  def printPosition(mower: Mower) = {
    val rowSeparator = "+ - "
    var string = "\n"
    for (i <- 0 to lawn.y) {
      for (j <- 0 to lawn.x) string = string.concat(rowSeparator)
      string = string.concat("+\n|")
      for (j <- 0 to lawn.x) string = string.concat(if (mower.x == (j) && mower.y == (lawn.x - i)) " X |" else "   |")
      string = string.concat("\n")
    }
    for (i <- 0 to lawn.x) string = string.concat(rowSeparator)

    string = string.concat("+")
    LOGGER.info(string)
  }
}
