package section3

object PartialFunctions  {
  def main(args: Array[String]) {

    val iHandleSomes: PartialFunction[Option[String], String] = {
      case Some(value) => value
    }

    val some = Some("hallo")
    val none = None

    println(iHandleSomes.isDefinedAt(none))
    println(iHandleSomes.isDefinedAt(some))
    println(iHandleSomes(some))



    val iHandleNones: PartialFunction[Option[String], String] = {
      case None => "Fallback"
    }

    // Combined partial function
    val optionHandler = iHandleSomes orElse iHandleNones
    println(optionHandler.isDefinedAt(none))
    println(optionHandler.isDefinedAt(some))
    println(optionHandler(some))

    val f1: String => String = (x: String) => x + "!"
    val f2: String => String = (y: String) => "Hallo " + y

    // Our composition is equals to:
    // f2(f1(optionHandler(x)))
    val f3 = f2 compose f1 compose optionHandler
    println(f3(Some("Welt")))

    // Even if a pattern match looks like a partial function, it is not!
    /* Does not work:  Some("Welt") match (f3) */

    // But we can use them as exception mapper in a catch block
    val exceptionMapper: PartialFunction[Throwable, String] = {
      case e: IllegalArgumentException => "Fallback"
    }

    try {
      throw new IllegalArgumentException("Wah!")
    } catch exceptionMapper

  }
}
