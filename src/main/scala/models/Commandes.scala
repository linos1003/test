package models

/**
  * Created by bsmida on 19/11/17.
  */
object Commandes {

  sealed trait Cmnd

  case object G extends Cmnd

  case object D extends Cmnd

  case object A extends Cmnd

  case object X extends Cmnd

  val commandes = Seq(G, D, A, X)

  def getCommandefromSting(str: Char): Cmnd = {

    str match {
      case 'A' => A
      case 'D' => D
      case 'G' => G
      case _ => X
    }
  }
}
