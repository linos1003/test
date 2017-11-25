package utils

import exceptions.PositionOffTheLawnException
import models.Commandes.{A, Cmnd, D, G}
import models.Orientation._
import models.{Lawn, Mower}
import utils.Parser.{parseCommandes, parseMower}


/**
  * Created by bsmida on 20/11/17.
  */
object PositionsManger {

  var lawn: Lawn = new Lawn(0, 0)

  def loadMowersAndCommandes(inputs: Seq[String]): Seq[(Mower, List[Cmnd])] = inputs.drop(1).sliding(2, 2).toList.map(x => (parseMower(x(0)), parseCommandes(x(1))))

  def computeNewPosition(mower: Mower, commandes: Seq[Cmnd]) = {
    var newMower=mower
    commandes.foreach {
      x =>
        val next = nextPosition(newMower, x)
        if (isInLawnArea(next))
          newMower=next
    }
    newMower
  }




  //TODO add the _ case

  def nextPosition(mower: Mower, cmnd: Cmnd): Mower = {
    var newMower = null
    (cmnd, mower.orientation) match {
      case (G, W) | (D, E) => Mower(mower.x, mower.y, S)
      case (G, S) | (D, S) => Mower(mower.x, mower.y, E)
      case (G, E) | (D, W) => Mower(mower.x, mower.y, N)
      case (G, N) | (D, N) => Mower(mower.x, mower.y, W)
      //case (D, W) => Mower(mower.x, mower.y, N)
      //case (D, S) => Mower(mower.x, mower.y, E)
      //case (D, E) => Mower(mower.x, mower.y, S)
      //case (D, N) => Mower(mower.x, mower.y, W)
      case (A, W) => Mower(mower.x - 1, mower.y, W)
      case (A, S) => Mower(mower.x, mower.y - 1, S)
      case (A, E) => Mower(mower.x + 1, mower.y, E)
      case (A, N) => Mower(mower.x, mower.y + 1, N)
    }
  }

  def isInLawnArea(x: Mower) = {
    x.x <= lawn.x && x.y <= lawn.y
  }


  //TODO print lawn for each step


}
