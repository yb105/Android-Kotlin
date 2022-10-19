class Sbi{

    var balance = 100

    fun deposit(amount:Int){


        balance += amount
    }
    fun withdraw(amount:Int){

        if (amount<balance){

            balance -= amount

        }else
        {
            println("Insufficient Balance")
        }
    }

    fun display(){

        return println("Your Balance $balance")
    }
}

fun main(){

    var obj = Sbi()
    var bal: Int
    var choice: Int = 1
    var menu = """
        1) Deposit 
        2) Withdraw
        3) Display Balance
        4) Exit
    """.trimIndent()



    while (choice != 4)
    {
        println(menu)
        println("Enter Your Choice : ")
        choice = readLine()!!.toInt()

        if (choice == 1){
        println("Enter Amount :")
        bal = readLine()!!.toInt()
        obj.deposit(bal)
    }else if (choice == 2){

        println("Enter Amount :")
        bal = readLine()!!.toInt()
        obj.withdraw(bal)
    }else if(choice == 3){
        obj.display()
    }else{
        println("Thank you for using sbi")
    }}


}