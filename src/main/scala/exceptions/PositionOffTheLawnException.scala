package exceptions

import models.{Lawn, Mower}

/**
  * Created by bsmida on 19/11/17.
  */
class PositionOffTheLawnException (private val mower: Mower ,lawn:Lawn) extends Exception{
  override def getMessage: String = "Mower["+mower.x+","+mower.y+"] is out of the lawn area ["+lawn.x+","+lawn.y+"]"
}