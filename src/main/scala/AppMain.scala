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
      val lawn = initLawn(inputs(0)).asInstanceOf[Lawn]
      val mowers = loadMowersAndCommands(inputs)
      val l = mowers.map { case (mower, cmnds) =>
        computeNewPosition(mower.asInstanceOf[Mower], cmnds.asInstanceOf[List[Cmnd]], lawn)
      }
      LOGGER.info("Initial mowers positions: " + mowers.map { case (mower, _) => mower }
        .map(_.toString).mkString(", ") + matrixDisplay(mowers.map { case (mower, _) => mower.asInstanceOf[Mower] }, display, lawn))
      LOGGER.info("Final mowers positions  : " + l.map(_.toString).mkString(", ") + matrixDisplay(l, display, lawn))
    case Failure(e) =>
      LOGGER.error(e)
  }


  def matrixDisplay(mowers: Seq[Mower], display: Boolean, lawn: Lawn) = {

    val rowSeparator = "+ - "

    def stringRow(i: Int): String = {
      val header = (0 to lawn.x).toList.flatMap(_ => rowSeparator).mkString("")
      val content = (0 to lawn.x).toList.flatMap(x => if (!isPositionEmpty(new Mower(x, lawn.y - i, X), mowers)) " X |" else "   |").mkString("")
      header.concat("+\n|").concat(content).concat("\n")
    }

    val str = "\n" + (0 to lawn.y).toList.flatMap(i => stringRow(i)).mkString("")

    val strFinal = str + (0 to lawn.x).toList.flatMap(_ => rowSeparator).mkString("").concat("+")

    if (display) strFinal else ""

  }


  // todo develop function to write output file
}
