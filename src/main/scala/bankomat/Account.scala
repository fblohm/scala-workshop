package bankomat

case class Account(number: String, balance: Double) {
  def deposit(amount: Double): Account = {
    copy(balance = balance + amount)
  }

  def withdraw(amount: Double): Account = {
    copy(balance = balance - amount)
  }
}
