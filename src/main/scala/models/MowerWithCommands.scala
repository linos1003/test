package scala.models

import models.Commandes.Cmnd
import models.Mower

case class MowerWithCommands(mower: Mower, cmds: Seq[Cmnd])
