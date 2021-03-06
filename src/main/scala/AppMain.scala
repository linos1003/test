import java.io.{BufferedWriter, File, FileWriter}

import models.Commands.Cmnd
import models.Orientation.X
import models.{Lawn, Mower}
import org.apache.log4j.Logger
import utils.Parser.{initLawn, _}
import utils.PositionsManger._

import scala.util.{Failure, Success, Try}

/**
  * Main class
  *
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

  // read input file
  val inputs = Try(readFile(args(0)))
  inputs match {
    case Success(inputs) =>

      // parse dispay param to Boolean to active or not  printing matrix details
      val display = if (args.length == 3 && Array("true", "false").contains(args(2))) args(2).toBoolean else false

      // init lawn
      val lawn = initLawn(inputs(0)).asInstanceOf[Lawn]

      // load mowers with its commands
      val mowers = loadMowersAndCommands(inputs)

      // compute the final position for each mower
      val l = mowers.map { case (mower, cmnds) =>
        computeNewPosition(mower.asInstanceOf[Mower], cmnds.asInstanceOf[List[Cmnd]], lawn)
      }

      // logging results
      LOGGER.info("Initial mowers positions: " + mowers.map { case (mower, _) => mower }
        .map(_.toString).mkString(", ") + matrixDisplay(mowers.map { case (mower, _) => mower.asInstanceOf[Mower] }, display, lawn))
      LOGGER.info("Final mowers positions  : " + l.map(_.toString).mkString(", ") + matrixDisplay(l, display, lawn))

      LOGGER.info("Saving output into file: " + args(1))
      writeOuputToFile(args(1), l)
      LOGGER.info("Saving Done! ")

    case Failure(e) =>
      LOGGER.error(e)
  }

  /**
    * Method used to display  lawn and mowers positions in a matrix
    *
    * @param mowers
    * @param display
    * @param lawn
    * @return
    */
  def matrixDisplay(mowers: Seq[Mower], display: Boolean, lawn: Lawn) = {

    display match {
      case true =>
        val rowSeparator = "+ - "

        def stringRow(i: Int): String = {
          val header = (0 to lawn.x).toList.flatMap(_ => rowSeparator).mkString("")
          val content = (0 to lawn.x).toList.flatMap(x => if (!isPositionEmpty(new Mower(x, lawn.y - i, X), mowers)) " X |" else "   |").mkString("")
          header.concat("+\n|").concat(content).concat("\n")
        }

        val str = "\n" + (0 to lawn.y).toList.flatMap(i => stringRow(i)).mkString("")
        val strFinal = str + (0 to lawn.x).toList.flatMap(_ => rowSeparator).mkString("").concat("+")
        strFinal

      case false =>
        ""
    }

  }

  /**
    * Function used to store the results into a new file
    *
    * @param path
    * @param mowers
    */
  def writeOuputToFile(path: String, mowers: Seq[Mower]): Unit = {
    val file = new File(path)
    val bw = new BufferedWriter(new FileWriter(file))
    bw.write(mowers.map(_.toFileString).mkString("\n"))
    bw.close()
  }
}
