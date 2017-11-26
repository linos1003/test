package utils

import models.Commandes.{A, Cmnd, D, G}
import models.Orientation._
import models.{Lawn, Mower}
import utils.Parser.{parseCommands, parseMower, readFile}


/**
  * Created by bsmida on 20/11/17.
  */
object PositionsManger {

  /**
    * Function used to extract mowers from the input text.
    * Assuming tha the first line of the String Seq is referenced to the Lawn length.
    *
    * @param inputs
    * @return
    */

  def loadMowersAndCommands(inputs: Seq[String]) = inputs.drop(1).sliding(2, 2).toList.map(x => (parseMower(x(0)), parseCommands(x(1))))

  /**
    * Function used to Calculate the final position of a given Mower in terms of a given commands list
    * Using the nextPostion function , we loop the list of commands and  calculate  the  new  position
    * If the next postion is off the lawn area , then we conserve the last position.
    *
    * @param mower
    * @param commands
    * @return
    */

  def computeNewPosition(mower: Mower, commands: Seq[Cmnd], lawn: Lawn) = {
    var newMower = mower

    def nxt(x: Cmnd) = {
      val next = nextPosition(newMower, x)
      if (isInLawnArea(next, lawn))
        newMower = next
    }

    commands.map { x => nxt(x) }

    newMower
  }

  /**
    * Function used to calculate the next position of a given Mower in terms of a given Command
    * Assuming that the next north position of (x, y) is  (x, y+1)
    *
    * @param mower
    * @param cmnd
    * @return
    */

  def nextPosition(mower: Mower, cmnd: Cmnd): Mower = {
    (cmnd, mower.orientation) match {
      case (G, W) | (D, E) => Mower(mower.x, mower.y, S)
      case (G, S) | (D, N) => Mower(mower.x, mower.y, E)
      case (G, E) | (D, W) => Mower(mower.x, mower.y, N)
      case (G, N) | (D, S) => Mower(mower.x, mower.y, W)
      //case (D, W) => Mower(mower.x, mower.y, N)
      //case (D, S) => Mower(mower.x, mower.y, E)
      //case (D, E) => Mower(mower.x, mower.y, S)
      //case (D, N) => Mower(mower.x, mower.y, W)
      case (A, W) => Mower(mower.x - 1, mower.y, W)
      case (A, S) => Mower(mower.x, mower.y - 1, S)
      case (A, E) => Mower(mower.x + 1, mower.y, E)
      case (A, N) => Mower(mower.x, mower.y + 1, N)
      case (_, _) => Mower(mower.x, mower.y, X)
    }

  }

  /**
    * Function used to know if a given Mower is in/out the Lawn Area.
    * A Mower is inside  the   Lawn   only if its coordinates  (x, y)
    * are both between 0 and the lawn lengths
    *
    * @param x : Mower
    * @return true if the Mower is in the Lawn Area
    */

  def isInLawnArea(x: Mower, lawn: Lawn) = {
    x.x <= lawn.x && x.y <= lawn.y && x.x >= 0 && x.y >= 0
  }

  /**
    *
    * @param path
    * @return
    */

  def loadPositions(path: String) = readFile(path).map(parseMower)


  def isPositionEmpty(mower: Mower, seq: Seq[Mower]): Boolean = seq.filter(p => p.hasSamePosition(mower)).isEmpty

  def getFinalPositions(inputs: Seq[String], lawn: Lawn) = {
    val mowers = loadMowersAndCommands(inputs)
    val l = mowers.map { case (mower, cmnds) => computeNewPosition(mower.asInstanceOf[Mower], cmnds.asInstanceOf[List[Cmnd]], lawn) }
    l
  }


}
