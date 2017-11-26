import models.Commandes.Cmnd
import models.{Lawn, Mower}
import models.Orientation.X
import org.apache.log4j.Logger
import utils.Parser.initLawn
import utils.PositionsManger._
import utils.Parser._

import scala.util.{Failure, Success, Try}

/**
  * Created by bsmida on 19/11/17.
  */
object AppMain extends App {

  val LOGGER = Logger.getLogger(this.getClass)

  if (args.length < 2) {
    LOGGER.error(
      """
        |Wrong number of parameters
        |Usage: AppMain <input-file-path> <output-file-path> <matrix-dislay>
        |      <input-file-path> the path of the input file
        |      <output-file-path> the path of the output file
        |      <matrix-display> Optional boolean (true or false) which indicate
        |            if you want to show results as a matrix
      """.stripMargin
    )
    System.exit(1)
  }


  val inputs = Try(readFile(args(0)))
  inputs match {
    case Success(inputs) =>
      val display = if (args.length == 3 && Array("true", "false").contains(args(2))) args(2).toBoolean else false
      lawn = initLawn(inputs(0)).asInstanceOf[Lawn]
      val mowers = loadMowersAndCommands(inputs)
      val l = mowers.map(x => computeNewPosition(x._1.asInstanceOf[Mower], x._2.asInstanceOf[List[Cmnd]]))
      LOGGER.info("Initial mowers positions: " + mowers.map(_._1).map(_.toString).mkString(", ") + matrixDisplay(mowers.map(_._1.asInstanceOf[Mower]), display))
      LOGGER.info("Final mowers positions  : " + l.map(_.toString).mkString(", ") + matrixDisplay(l, display))
    case Failure(e) =>
      LOGGER.error(e)
  }


  def matrixDisplay(mowers: Seq[Mower], display: Boolean) = {

    val rowSeparator = "+ - "
    var string = "\n"
    for (i <- 0 to lawn.y) {
      for (j <- 0 to lawn.x) string = string.concat(rowSeparator)
      string = string.concat("+\n|")
      for (j <- 0 to lawn.x) string = string.concat(if (!isPositionEmpty(new Mower(j, lawn.y - i, X), mowers)) " X |" else "   |")
      string = string.concat("\n")
    }
    for (i <- 0 to lawn.x) string = string.concat(rowSeparator)
    string = string.concat("+")
    if (display) string else ""
  }

  // todo develop function to write output file
}
