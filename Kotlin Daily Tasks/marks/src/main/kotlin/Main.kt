import java.util.*

fun main() {
    var a: String
    print("Enter A char : ")
    a = readLine().toString()

    when(a){

        in "a","e","i","o","u" -> print("vowel")
        else -> print("Consonant")
    }
}