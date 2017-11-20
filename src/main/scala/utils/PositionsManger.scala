package utils

import models.Lawn

/**
  * Created by bsmida on 20/11/17.
  */
object PositionsManger {

  def initLawn(input: String): Lawn = {
    val values = input.split(" ").asInstanceOf[Array[Int]]
    new Lawn(values(0), values(1))
  }
}
