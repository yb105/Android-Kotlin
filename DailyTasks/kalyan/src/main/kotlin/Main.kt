import java.util.Scanner

fun main(){
     var s = Scanner(System.`in`)
    var firstName: String
    var lastName: String
    var age: Int
    var gender: String
    var product: String
    var quantity: Int
    val todayP = 4783
    var makingCharge: Int
    var price:Int
    var discount = 0
    var dis: Int
    var finalPrice: Int

    println("------------Kalyan Jewellers-------------")
    print("Enter Your First Name : ")
    firstName = s.next()
    print("Enter Your Last Name : ")
    lastName = s.next()
    print("Enter Your Age : ")
    age = s.nextInt()
    print("Enter Your Gender(m/f): ")
    gender = s.next()
    print("Enter Your Product Name : ")
    product = s.next()
    print("Enter Product Quantity : ")
    quantity = s.nextInt()

    makingCharge = (4783*quantity)*8/100

    price = makingCharge + (4783*quantity)
    println("---------------------")
    println("Todays Price : 4783(1GRM 22k)")
    println("Making Charges : 8%")
    println("Amount : $price")


    if (age>65 && gender == "m"){

       val p = when{

           price>300000 -> discount = 30
           price>200000 && price<300000 -> discount = 20
           price>100000 && price<200000 -> discount = 10
           else -> discount = 0
       }
    }else{

        val p = when{

            price>300000 -> discount = 40
            price>200000 && price<300000 -> discount = 30
            price>100000 && price<200000 -> discount = 20
            else -> discount = 0
        }
    }

    if (age<65 && gender == "m"){

        val p = when{

            price>300000 -> discount = 25
            price>200000 && price<300000 -> discount = 15
            price>100000 && price<200000 -> discount = 5
            else -> discount = 0
        }
    }else if (age<65 && gender == "f"){

        val p = when{

            price>300000 -> discount = 35
            price>200000 && price<300000 -> discount = 25
            price>100000 && price<200000 -> discount = 15
            else -> discount = 0
        }
    }

    dis  = price*discount/100
    finalPrice = price-dis
    println("-----Purchase Amount-------")
    println("Discount : $discount%")
    println("Discount Price : $dis")
    println("-----------------")
    println("Net Amount : $finalPrice")



}