import java.util.Scanner
import javax.print.DocFlavor.STRING

var arrayList = ArrayList<String>()
var answers = mutableMapOf<String, String>("q1" to "prabhas","q2" to "10", "q3" to "4", "q4" to "110", "q5" to "110",)
var score = 0
var wrongAns = mutableMapOf<String, String>()
var sc = Scanner(System.`in`)


fun main(){
    var choice = ""


    var q1 = """----------KBC-----------
Q1) Name the actor who played lead role in Bahubali?
a) Prabhas
b) Akshay
c) Karthik
b) Ajay
     
    """.trimMargin()
    var q2 = """ 
        Q2) What is 5 + 5 ?
        a) 11
        b) 12
        c) 10
        d) 9
    """.trimIndent()

    var q3 = """ 
        Q3) What is 2*2  ?
        a) 2
        b) 4
        c) 8
        d) 6
    """.trimIndent()

    var q4 = """ 
        Q4) What is 55 + 55  ?
        a) 100
        b) 120
        c) 130
        d) 110
    """.trimIndent()
    var q5 = """ 
        Q5) What is 77 + 33  ?
        a) 110
        b) 100
        c) 105
        d) 108
    """.trimIndent()


   for (i in 1..5){

       when(i){
           1 -> println(q1)
           2 -> println(q2)
           3 -> println(q3)
           4 -> println(q4)
           5 -> println(q5)
       }
       display("q$i")
   }





        println("Your Score is : $score ")

    while (choice != "exit") {
        print(
            """ ---------------------------------
            1) For Seeing All Correct Answers Enter 'correct'
            2) For Seeing Your Wrong Answer Enter 'wrong'
            3) For Exiting game Enter 'exit'
            Enter Your Choice : 
        """.trimIndent()
        )
        choice = sc.next()

        if (choice == "correct") {

            for (i in answers.keys) {
                println("$i : ${answers.get(i)}")

            }
        } else if (choice == "wrong") {
            for (i in wrongAns.keys) {
                println("$i : ${wrongAns.get(i)}")

            }
        } else if (choice == "exit") {
            println("------Thank You For Playing-------")

        }
    }
    }

fun display(q: String){
    var ans: String

    print("Enter Your Answer : ")
    ans = sc.next()
    arrayList.add(ans)
    if (answers.containsValue(ans)){
        score = score + 50


    }else{
        wrongAns.put(q,ans)
    }

}