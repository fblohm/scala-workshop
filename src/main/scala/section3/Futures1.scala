package section3

import scala.concurrent.duration.DurationInt
import scala.concurrent.{Await, Future}
import scala.util.Random

// Node, that we import this implicit execution context!
import scala.concurrent.ExecutionContext.Implicits.global

object Futures1 extends App {
  /**
   * A Future is an object, which halts a code block, which
   * then runs sometimes in the future in its own thread and computes the result of type T
   */
  val f: Future[String] = Future {
    val wait = Random.nextInt(2000)
    Thread.sleep(wait)
    s"I waited ${wait}ms in thread ${Thread.currentThread().getId} to compute this result"
  }

  /**
   * Now we block our method and await the result of our future.
   */
  println(s"I wait for the future result in thread ${Thread.currentThread().getId}")
  val result: String = Await.result(f, 4.seconds) // <- With DurationInt we have a nice implicit conversion
  println(result)
}
