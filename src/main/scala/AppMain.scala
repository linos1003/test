
import models.Mower
import models.Orientation.X
import org.apache.log4j.Logger
import utils.Parser.initLawn
import utils.PositionsManger._
import utils.Parser._

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
  val inputs = readFile(args(0))
  lawn = initLawn(inputs(0))
  val mowers = loadMowersAndCommands(inputs)
  val l = mowers.map(x => computeNewPosition(x._1, x._2))
  LOGGER.info("Initial mowers positions: " + mowers.map(_._1).map(_.toString).mkString(", ")+  matrixDisplay(mowers.map(_._1)))
  LOGGER.info("Final mowers positions  : " + l.map(_.toString).mkString(", ")+matrixDisplay(l))



  def matrixDisplay(mowers: Seq[Mower]) = {
    val rowSeparator = "+ - "
    var string = "\n"
    for (i <- 0 to lawn.y) {
      for (j <- 0 to lawn.x) string = string.concat(rowSeparator)
      string = string.concat("+\n|")
      for (j <- 0 to lawn.x) string = string.concat(if (!isPositionEmpty(new Mower(j, i, X), mowers)) " X |" else "   |")
      string = string.concat("\n")
    }
    for (i <- 0 to lawn.x) string = string.concat(rowSeparator)
    string = string.concat("+")
    string
  }
}
