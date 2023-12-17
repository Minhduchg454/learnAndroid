package eu.tutorials.bankaccountprogram

class BankAccountTest (var accountHolder : String,var balance : Double){
    private val transactionHistory = mutableListOf<String>()



    fun acctBanlance () : Double{
        return balance
    }
}