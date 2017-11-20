package utils

import exceptions.{InvalidCommandFormatException, InvalidLawnInputFormatException, InvalidMowerInputFormatException}
import models.Commandes._
import models.Orientation.getOrientationfromSting
import models.{Lawn, Mower}

/**
  * Created by bsmida on 20/11/17.
  */
object Parser {
  def initLawn(input: String): Lawn = {
    try {
      val values = input.split(" ").map(_.toInt)
      new Lawn(values(0), values(1))
    }
    catch {
      case e: Exception => throw new InvalidLawnInputFormatException
    }
  }

  def readFile(filename: String): Seq[String] = {
    val bufferedSource = io.Source.fromFile(filename)
    val lines = (for (line <- bufferedSource.getLines()) yield line).toList
    bufferedSource.close
    lines
  }

  def parseMower(str: String): Mower = {
    try {
      val values = str.split(" ")
      new Mower(values(0).toInt, values(1).toInt, getOrientationfromSting(values(2)))
    }
    catch {
      case e: Exception => throw new InvalidMowerInputFormatException
    }
  }

  def parseCommandes(str: String): List[Cmnd] = {
    try {
      str.map(x => getCommandefromSting(x)).toList
    }
    catch {
      case e: Exception => throw new InvalidCommandFormatException
    }
  }
}
