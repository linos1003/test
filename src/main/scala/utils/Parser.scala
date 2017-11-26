package utils

import exceptions.{InvalidCommandFormatException, InvalidLawnInputFormatException, InvalidMowerInputFormatException}
import models.Commandes._
import models.Orientation.getOrientationfromSting
import models.{Lawn, Mower}

import scala.util.{Failure, Success, Try}
import org.apache.log4j.Logger

/**
  * Created by bsmida on 20/11/17.
  */
object Parser {
  val LOGGER = Logger.getLogger(this.getClass)

  def initLawn(input: String) = {
    val values = Try(input.split(" ").map(_.toInt))
    values match {
      case Success(s) =>
        new Lawn(values.get(0), values.get(1))
      case Failure(f) =>
        LOGGER.error(new InvalidLawnInputFormatException(input).getMessage)
    }
  }

  def readFile(filename: String): Seq[String] = {
    val bufferedSource = io.Source.fromFile(filename)
    val lines = (for (line <- bufferedSource.getLines()) yield line).toList
    bufferedSource.close
    lines
  }

  def parseMower(str: String) = {

    val values = Try(str.split(" "))
    values match {
      case Success(s) =>
        new Mower(values.get(0).toInt, values.get(1).toInt, getOrientationfromSting(values.get(2)))
      case Failure(f) =>
        LOGGER.error(new InvalidMowerInputFormatException(str).getMessage)
    }

  }

  def parseCommands(str: String)= {
    str.map(x => getCommandefromSting(x)).toList
  }
}
