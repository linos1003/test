package utils

import utils.Parser.{parseCommandes, parseMower}


/**
  * Created by bsmida on 20/11/17.
  */
object PositionsManger {

  def loadMowersAndCommandes(inputs: Seq[String]) = inputs.drop(1).sliding(2, 2).toList.map(x => (parseMower(x(0)), parseCommandes(x(1))))
}
