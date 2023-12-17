package eu.tutorials.bankaccountprogram

fun main (){
    val denisBankAccount = BankAccount("Denis Panjuta", 1338.20)
    //Nap tien vao tai khoan
    denisBankAccount.deposit(200.0)

    //Rut tien ra khoi tai khoan
    denisBankAccount.withdraw(1200.00)

    denisBankAccount.deposit(3000.0)
    denisBankAccount.deposit(2000.0)
    denisBankAccount.withdraw(3333.15)

    //In lich su giao dich
    denisBankAccount.displayTransactionHistory()

    //Tong tien hien tai
    println("Total money ${denisBankAccount.accountHolder} is " + denisBankAccount.acctBanlance())


    println("\n\n")
    val sarahBankAccount = BankAccount("Sarah", 0.0)
    sarahBankAccount.deposit(100.0)
    sarahBankAccount.withdraw(10.0)
    sarahBankAccount.deposit(300.0)

    sarahBankAccount.displayTransactionHistory()
    println("Total money ${sarahBankAccount.accountHolder} is ${sarahBankAccount.acctBanlance()} ")

}