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

  var lastPositions: Seq[Mower] = Seq()
  var lawn: Lawn = new Lawn(0, 0)

  def initLastPostions(inputs: Seq[(Mower, List[Cmnd])]) = lastPositions = inputs.map { x =>
    if (isInLawnArea(x._1)) x._1
    else throw new PositionOffTheLawnException(x._1)
  }


  def loadMowersAndCommandes(inputs: Seq[String]): Seq[(Mower, List[Cmnd])] = inputs.drop(1).sliding(2, 2).toList.map(x => (parseMower(x(0)), parseCommandes(x(1))))

  def computeNewPosition(mower: Mower, commandes: Seq[Cmnd]) = {
    commandes.foreach {
      x =>
        val next = nextPosition(mower, x)
        if (isPositionEmpty(next) && isInLawnArea(next)) next
        else x
    }
  }

  def isPositionEmpty(mower: Mower): Boolean = lastPositions.filter(p => p.hasSamePosition(mower)).isEmpty

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

}
