

object HelloWorld extends App {

  def main (args: Arry[String] ):
  Unit = {

    val message = if (args.length >= 1) {
      "Hello, World! have params: " + args
    } else {
      "Hello, World! without params"
    }

    println(message)
  }

}