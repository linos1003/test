package models

/**
  * Created by bsmida on 19/11/17.
  */
object Orientation {

  sealed trait Ornt

  case object N extends Ornt

  case object E extends Ornt

  case object W extends Ornt

  case object S extends Ornt

  case object X extends Ornt

  val orientations = Seq(N, E, W, S, X)

  def getOrientationfromSting(str: String): Ornt = {

    str match {
      case "N" => N
      case "E" => E
      case "W" => W
      case "S" => S
      case _ => X
    }
  }
}

