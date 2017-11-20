package exceptions
import utils.PositionsManger._
import models.Mower

/**
  * Created by bsmida on 19/11/17.
  */
class PositionOffTheLawnException (private val mower: Mower , private val cause: Throwable = None.orNull) extends Exception{
  override def getMessage: String = "Mower["+mower.x+","+mower.y+"] is out of the lawn area ["+lawn.x+","+lawn.y+"]"
}