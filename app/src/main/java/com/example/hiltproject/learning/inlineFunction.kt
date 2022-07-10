package com.example.hiltproject

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log

var n1 = 0
var n2:kotlin.Int = 1
var n3:kotlin.Int = 0

val count = 20
/*Log.d("???????????>>>>>>>....","$n1 $n2") //printing 0 and 1

printFibonacci(count-2)*/


private fun printFibonacci(count: Int) {
    if (count > 0) {
        n3 = n1 + n2
        n1 = n2
        n2 = n3
        Log.d("ghj,."," $n3")
        printFibonacci(count - 1)
    }
}
/*
fun main(){
    inlineFunction({ println("calling inline functions")})
}*/
inline fun <reified T : Any>  Activity.startActivityExt(className : Class<T>,bundle : Bundle? ) {

    val intent = Intent(this , className)
    bundle?.let {
       intent.putExtras(bundle)
    }
    startActivity(intent)

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