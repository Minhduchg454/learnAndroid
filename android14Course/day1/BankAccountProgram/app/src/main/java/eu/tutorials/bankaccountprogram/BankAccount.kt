package eu.tutorials.bankaccountprogram

class BankAccount (
    var accountHolder: String,
    var balance: Double)
{
    //Bien chua lich su giao dich
    private val tranactionHistoy = mutableListOf<String>()



    //Nap tien
    fun deposit (amount: Double){
        balance += amount
        tranactionHistoy.add("$accountHolder deposited $$amount")
    }

    //Rut tien
    fun withdraw(amount: Double){
        if(amount <= balance){
            balance -= amount
            tranactionHistoy.add("$accountHolder withdrew $$amount")

        }else{
            println("You don't have the funds to withdraw $$amount")
        }
    }

    //Lich su giao dich
    fun displayTransactionHistory(){
        println("Transaction history for $accountHolder")
        for (index in 0 until tranactionHistoy.size){
            println(tranactionHistoy[index])
        }
    }

    fun acctBanlance () :Double{
        return balance
    }

}