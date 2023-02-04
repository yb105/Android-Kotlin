import java.util.Scanner

fun main(){

    var i: Int
    var a: Int
    var add: Int = 0
    var sc = Scanner(System.`in`)

    for(i in 1..5 ){

        println("Enter input : ")
        a=  sc.nextInt()
        add = add + a
    }
    print("Addition : $add")
}