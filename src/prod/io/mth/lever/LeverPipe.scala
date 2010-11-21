package io.mth.lever

import java.io.InputStream

object LeverPipe {
  def pipe(in: InputStream, writable: {def write(b: Array[Byte], offset: Int, length: Int): Int}, buffersize: Int) = {
    val buffer = new Array[Byte](buffersize)
    Iterator.continually(in.read(buffer, 0, buffer.length)).
                takeWhile(_ != -1).
                foreach(writable.write(buffer, 0, _))

  }
}