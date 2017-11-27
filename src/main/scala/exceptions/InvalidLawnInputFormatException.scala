package exceptions

/**
  * Exception class thrown when the program read
  * bad Lawn format (only strict  positive   integers)
  *
  * Created by bsmida on 20/11/17.
  */
class InvalidLawnInputFormatException(private val lawn: String) extends Exception {
  override def getMessage: String = lawn + " is is not a valid Lawn"
}