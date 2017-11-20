package models

import models.Orientation.Ornt

/**
  * Created by bsmida on 19/11/17.
  */
case class Mower(x: Int, y: Int, orientation: Ornt) {
  def hasSamePosition(mower: Mower): Boolean = this.y == mower.y && this.x == mower.x

  def equals(obj: Mower): Boolean = hasSamePosition(obj) && obj.orientation == this.orientation
}


