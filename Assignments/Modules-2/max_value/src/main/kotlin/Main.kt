import java.util.Scanner

fun max(a:Int, b:Int){

    if (a>b){
        println("$a is Max")
    }else{ println("$b is Max")}
}


fun main(){
    var a: Int
    var b: Int
    var sc = Scanner(System.`in`)

    println("Enter frist Number : ")
    a = sc.nextInt()
    println("Enter Second Number : ")
    b = sc.nextInt()

    max(a,b)

}