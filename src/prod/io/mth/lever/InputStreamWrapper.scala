package io.mth.lever

import java.io.InputStream

class InputStreamWrapper(delegate: InputStream) {
  import Lever._

  def toByteArray = using(delegate) { r =>
    Iterator.continually(r.read()).takeWhile(_ != -1).map(_.toByte).toArray
  }
}