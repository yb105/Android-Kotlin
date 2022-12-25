import java.util.Scanner

fun main(){
     val read = Scanner(System.`in`)
    val c: Char

    print("Enter any character : ")
    c = read.next().single()
    val ascii = c.toInt()

    println("The ASCII value of $c is: $ascii")
}