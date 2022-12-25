import java.util.Scanner

fun main(){

    var arrayList = ArrayList<String>()
    var sc = Scanner(System.`in`)

    for (i in 0..5 - 1){

        print("Enter Name : ")
        arrayList.add(sc.next())

    }
    for (i in arrayList){
        println(i)
    }


}