package com.example.hiltproject


/*
fun main(){
    inlineFunction({ println("calling inline functions")})
}*/
inline fun inlineFunction(myFun: () -> Unit ) {
myFun()
    print("code inside inline function")
}

fun higherfunc( lmbd: () -> Unit ) {     // accepting lambda as parameter
    lmbd()                               //invokes lambda expression
}
fun higherfunc1( str : String, myfunc: (String) -> Unit){
    // invoke regular function using local name
    myfunc(str)
}

fun higherfunc2( lmbd: (Int, Int) -> Int) {      // accepting lambda as parameter

    var result = lmbd(2,4)    // invokes the lambda expression by passing parameters
    println("The sum of two numbers is: $result")
}
/*

        // regular function definition
        fun printMe(s:String): Unit{
            println(s)
        }
        var lambda = {println("GeeksforGeeks: A Computer Science portal for Geeks") }
        higherfunc(lambda)                 // passing lambda as parameter
        higherfunc1("GeeksforGeeks: A Computer Science portal for Geeks",::printMe)
        var lambda1 = {a: Int , b: Int -> a + b }

        higherfunc2(lambda1)           //passing lambda as parameter

        // inlineFunction({ println("calling inline functions")})


        println( "??????????"+ myFun("HELLO"))
*/