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

  val orientations=Seq(N,E,W,S)
}

