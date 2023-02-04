fun main(){
    val a: Int
    val b:Int
    val quo:Int
    val rem: Int
    print("Enter Number 1 : ")
    a = Integer.valueOf(readLine())
    print("Enter Number 2 : ")
    b = Integer.valueOf(readLine())

    quo = a/b
    rem = a%b

    println("Quotient = $quo")
    print("Remainder = $rem")
}