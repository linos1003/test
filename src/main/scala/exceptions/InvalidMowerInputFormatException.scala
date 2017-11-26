package exceptions

/**
  * Created by bsmida on 20/11/17.
  */
class InvalidMowerInputFormatException (private val mower: String) extends Exception{
  override def getMessage: String = mower+" is not a valid Mower"
}