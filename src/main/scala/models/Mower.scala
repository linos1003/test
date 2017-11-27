package models

import models.Orientation.Ornt

/**
  * Created by bsmida on 19/11/17.
  */
case class Mower(x: Int, y: Int, orientation: Ornt) {
  //TODO adding id
  def hasSamePosition(mower: Mower): Boolean = this.y == mower.y && this.x == mower.x

  override def toString = "(" + x + "," + y + "," + orientation + ")"

  def toFileString = x + " " + y + " " + orientation
}


