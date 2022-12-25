import java.util.Scanner

fun main(){
    val read = Scanner(System.`in`)
    var a: Float
    var b: Float

    print("Enter number 1 : ")
    a = read.nextFloat()
    print("Enter number 2 : ")
    b = read.nextFloat()

    print("Multipliction = "+ (a*b))
}