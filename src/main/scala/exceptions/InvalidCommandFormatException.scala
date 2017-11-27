package exceptions


/**
  * Exception class thrown when the program read
  * bad command format (other input then A,G,D)
  *
  * Created by bsmida on 20/11/17.
  */
class InvalidCommandFormatException(private val cmnd: Char) extends Exception {
  override def getMessage: String = cmnd + " is not a valid command "
}
