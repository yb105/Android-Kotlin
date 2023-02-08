import java.util.Scanner

fun main(){

    var i: Int = 1
    var a: Int
    var  sc = Scanner(System.`in`)

    while(i<= 5){

        print("Enter Number : ")
        a = sc.nextInt()
        if (a%2 == 0){
            println("Number is even ")
        }else{
            println("Number is odd")
        }
        i++
    }
}