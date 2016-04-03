package bankomat

import org.scalatest.{FunSuite, MustMatchers}

class AccountTest extends FunSuite with MustMatchers {

  test("An account can deposit an amount of money") {
    val account = Account("7411", 100.45)
    account.deposit(4.0).balance mustBe BigDecimal(104.45)
  }

  test("An account can withdraw an amount of money") {
    val account = Account("7411", 100.45)
    account.withdraw(20.45).balance mustBe 80.00
  }
}
