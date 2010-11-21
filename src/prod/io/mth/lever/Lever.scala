package io.mth.lever

import java.io.{Closeable, InputStream}

object Lever {
  implicit def inputstream2wrapper(i: InputStream) = new InputStreamWrapper(i)
  def using[T <: Closeable, U](r: => T)(block: T => U) = LeverUsing.using(r)(block)
  def using2[T <: Closeable, U <: Closeable, V](r1: => T, r2: => U)(block: T => U => V) = LeverUsing.using2(r1, r2)(block)
  def pipe(in: InputStream, writable: {def write(b: Array[Byte], offset: Int, length: Int): Int}, buffersize: Int) = LeverPipe.pipe(in, writable, buffersize)
}