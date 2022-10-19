fun main()
{
    var mycart = mutableMapOf<String,Int>("vadapav" to 30,"dabeli" to 20,"bhel" to 60)
    var mystock = mutableMapOf<String,Int>()
    for(i in mycart.keys)
    {
        println("$i  Rs. ${mycart.get(i)}")
    }

    var productName:String
    var price :Int
    var qty :Int
    var total_price :Int
    println("Enter product name : ")
    productName = readLine()!!

    if(mycart.containsKey(productName))
    {
        println("Enter product qty : ")
        qty = readLine()!!.toInt()

        price = mycart.get(productName)!!
        println("price = $price")

        total_price = qty * price
        println("Total price = $total_price")

        if (mystock.containsKey(productName))
        {
            var old_qty :Int
            old_qty = mystock.get(productName)!!
            mystock.put(productName,old_qty+qty)
        }
        else
        {
            mystock.put(productName,qty)
        }


    }

    for(i in mystock.keys)
    {
        var total = mycart.get(i)!! * mystock.get(i)!!
        println("$i  Qty. ${mystock.get(i)}  total price Rs. $total")

    }

}
