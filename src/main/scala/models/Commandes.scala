package models

/**
  * Created by bsmida on 19/11/17.
  */
object Commandes {

  sealed trait Cmnd

  case object G extends Cmnd

  case object D extends Cmnd

  case object A extends Cmnd

  val commandes = Seq(G, D, A)


}
