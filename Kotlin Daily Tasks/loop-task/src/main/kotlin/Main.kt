import java.util.Scanner

fun main(){

    var i: Int
    var a: Int
    var b: String = "y"
    var sc =  Scanner(System.`in`)
        while(b == "y")
        {
            println("Enter Number : ")
            a = sc.nextInt()
            println("do you want to continue press y for yes and press n for no : ")
            b = sc.next()

        }

}