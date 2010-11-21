package io.mth.lever

import java.io._


object LeverUsing {
  def using[T <: Closeable, U](r: => T)(block: T => U) = {
    var suppress = false
    try {
      block(r)
    } catch {
      case t: Throwable => {
        suppress = true
        throw t
      }
    } finally {
      close(r, suppress)
    }
  }

  def using2[T <: Closeable, U <: Closeable, V](r1: => T, r2: => U)(block: T => U => V) =
    using(r1) { m1 =>
      using(r2) { m2 =>
        block(m1)(m2)
      }
    }

  def close(closeable: Closeable, suppress: Boolean):Unit =
    if (suppress) {
      try {
        closeable.close
      } catch {
        case suppressed: Throwable => ()
      }
    } else {
      closeable.close
    }
}