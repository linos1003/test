package utils

import exceptions.{InvalidLawnInputFormatException, InvalidMowerInputFormatException}
import models.Commands._
import models.Orientation.{X, getOrientationfromSting}
import models.{Lawn, Mower}
import org.apache.log4j.Logger

import scala.util.{Failure, Success, Try}

/**
  * Created by bsmida on 20/11/17.
  */
object Parser {
  val LOGGER = Logger.getLogger(this.getClass)

  /**
    * Function used to parse the fist line
    * (with format: [int] [int] ) into a Lawn object
    *
    * @param input
    * @return Lawn object
    */
  def initLawn(input: String) = {
    val values = Try(input.split(" ").map(_.toInt))
    values match {
      case Success(s) =>
        val x = values.get(0)
        val y = values.get(1)
        (x > 0 && y > 0) match {
          case true => new Lawn(x, y)
          case false => LOGGER.error(new InvalidLawnInputFormatException(input).getMessage)
        }

      case Failure(f) =>
        LOGGER.error(new InvalidLawnInputFormatException(input).getMessage)
    }
  }

  /**
    * Function used to read the input file and transport it into
    * a Seq of Sting to be used later
    *
    * @param filename
    * @return Seq of String
    */
  def readFile(filename: String): Seq[String] = {
    val bufferedSource = io.Source.fromFile(filename)
    val lines = (for (line <- bufferedSource.getLines()) yield line).toList
    bufferedSource.close
    lines
  }

  /**
    * Function used to parse the fist line
    * (with format: [int] [int] [Ornt] ) into a Mower object
    *
    * @param str
    * @return mower object
    */
  def parseMower(str: String) = {

    val values = Try(str.split(" "))
    values match {
      case Success(s) =>
        val x = values.get(0).toInt
        val y = values.get(1).toInt
        val z = getOrientationfromSting(values.get(2))
        (x >= 0 && y >= 0 && !z.equals(X)) match {
          case true => new Mower(x, y, z)
          case false => LOGGER.error(new InvalidMowerInputFormatException(str).getMessage)
        }

      case Failure(f) =>
        LOGGER.error(new InvalidMowerInputFormatException(str).getMessage)
    }

  }

  /**
    * Function used to parse an input String (with format A,G,D) into a Command object
    *
    * @param str
    * @return Cmnd object
    */
  def parseCommands(str: String) = {
    str.map(x => getCommandefromSting(x)).toList
  }
}
