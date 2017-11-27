package scala.models

import models.Commands.Cmnd
import models.Mower

case class MowerWithCommands(mower: Mower, cmds: Seq[Cmnd])
