open class RBI{
    var roi = 7.5
    fun rules(){
        println("Pay Debt on time")
    }
}
class SBI: RBI(){

    fun display(){
        println("ROI = $roi")
        rules()
    }
}

fun main(){
    var obj = SBI()
    obj.display()
}