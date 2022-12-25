fun main(){

    var capital: Map<String,String> = mapOf("Rajasthan" to "Jaipur", "Gujrat" to "Gandhinagar", "Maharasthra" to "Mumbai")

    for (i in capital.keys){

        println("$i   ${capital.get(i)}")
    }
}