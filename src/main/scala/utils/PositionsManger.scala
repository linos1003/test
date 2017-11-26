package utils

import models.Commandes.{A, Cmnd, D, G}
import models.Orientation._
import models.{Lawn, Mower}
import utils.Parser.{parseCommandes, parseMower, readFile}


/**
  * Created by bsmida on 20/11/17.
  */
object PositionsManger {

  var lawn: Lawn = new Lawn(0, 0)

  /**
    * Function used to extract mowers from the input text.
    * Assuming tha the first line of the String Seq is referenced to the Lawn length.
    *
    * @param inputs
    * @return
    */

  def loadMowersAndCommands(inputs: Seq[String]): Seq[(Mower, List[Cmnd])] = inputs.drop(1).sliding(2, 2).toList.map(x => (parseMower(x(0)), parseCommandes(x(1))))

  /**
    * Function used to Calculate the final position of a given Mower in terms of a given commands list
    * Using the nextPostion function , we loop the list of commands and  calculate  the  new  position
    * If the next postion is off the lawn area , then we conserve the last position.
    *
    * @param mower
    * @param commandes
    * @return
    */

  def computeNewPosition(mower: Mower, commandes: Seq[Cmnd]) = {
    var newMower = mower
    commandes.foreach {
      x =>
        val next = nextPosition(newMower, x)
        if (isInLawnArea(next))
          newMower = next
    }
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
    var newMower = null
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
      case (_,X) =>  Mower(mower.x,mower.y,X)
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

  def isInLawnArea(x: Mower) = {
    x.x <= lawn.x && x.y <= lawn.y && x.x >= 0 && x.y >= 0
  }

  /**
    *
    * @param path
    * @return
    */

  def loadPositions(path: String) = readFile(path).map(parseMower)



  def isPositionEmpty(mower: Mower,seq: Seq[Mower]): Boolean = seq.filter(p => p.hasSamePosition(mower)).isEmpty

}
