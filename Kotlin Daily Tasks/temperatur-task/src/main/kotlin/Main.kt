fun main(){

    var temp: Int

    print("enter temperature : ")
    temp = Integer.valueOf(readLine())

    if(temp<0){
        print("Ice")
    }else if(temp>=0 && temp<100){
        print("Water")
    }else{
        print("Air")
    }
}