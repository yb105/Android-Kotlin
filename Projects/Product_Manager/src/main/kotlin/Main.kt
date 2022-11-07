import java.util.Scanner

class Product{

      var role:String = ""
    var choiceInt: Int = 0


    fun menu(){
        var menu = """MENU
        |1) Product Manager
        |2) Customer
    """.trimMargin()

        println(menu)
        print("Enter Your Role : ")
        role = readLine()!!




    }

    fun manager(){
        var managerMenu = """
            1) Add Product
            2) View Product
            3) Exist
        """.trimIndent()

        println(managerMenu)
        print("Enter Your Choice : ")
        choiceInt = readLine()!!.toInt()
        }


    }



fun main() {
    var obj = Product()
    var product: String = ""
    var cproduct : String
    var qty: Int = 0
    var onePrice = 0
    var cqty : Int
    var cTot : Int
    var yn = "y"
    var cChoice = "y"
    var finalPrice = 0
    var totP = 0
    var productMap = mutableMapOf<String,Int>()
    var map = mutableMapOf<String,Int>()
    obj.menu()


    if (obj.role == "product manager") {
        obj.manager()
        if (obj.choiceInt == 1) {

            while (yn == "y") {
                print("Enter Your Product Name : ")
                product = readLine()!!
                print("Enter Your Product Quantity : ")
                qty = readLine()!!.toInt()
                print("Enter Your Product price for 1 piece : ")
                onePrice = readLine()!!.toInt()
                productMap.put(product, qty)
                map.put(product,onePrice)
                println("do you want to add mor product enter (y/n) : ")
                yn = readLine()!!

            }
            obj.manager()
        }


            if (obj.choiceInt == 2) {

                for (i in productMap.keys) {

                    println(" product name : $i  qty : ${productMap.get(i)}")
                }
                obj.manager()
            }


            if (obj.choiceInt == 3){
                obj.menu()
            }

        }

    if(obj.role == "customer"){

        for (i in map.keys){
            println("Product Name : $i     Product Price : ${map.get(i)}")
        }

        while (cChoice == "y"){
            print("enter your product : ")
            cproduct = readLine()!!
            print("Enter your qty : ")
            cqty = readLine()!!.toInt()
            if (map.contains(cproduct)){

                println("$cproduct per piece price is : ${map.get(cproduct)}")
                cTot = map.get(cproduct)!! * cqty
                println("Your Price to pay is : $cTot  ")
                println("do you want to purchase more items (y/n) : ")
                cChoice = readLine()!!
                finalPrice = cTot + finalPrice

            }

            println("Your final price is : $finalPrice ")


        }


    }


    }
