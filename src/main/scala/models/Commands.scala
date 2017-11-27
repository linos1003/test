package models

import exceptions.InvalidCommandFormatException
import org.apache.log4j.Logger

/**
  * Created by bsmida on 19/11/17.
  */
object Commands {

  val LOGGER = Logger.getLogger(this.getClass)

  sealed trait Cmnd

  case object G extends Cmnd

  case object D extends Cmnd

  case object A extends Cmnd

  case object X extends Cmnd

  val commands = Seq(G, D, A, X)

  /**
    * Function used to parse a char into a Cmnd object
    *
    * @param str
    * @return Cmnd object
    */
  def getCommandefromSting(str: Char) = {

    str match {
      case 'A' => A
      case 'D' => D
      case 'G' => G
      case _ => LOGGER.error(new InvalidCommandFormatException(str))
    }
  }
}
