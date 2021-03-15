package com.example.kotlintest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var a:Int = 123
        println(a)

        val b:Int = 456
        println(b)

        val testText = "test message"
        println(testText)

        val testText2 = """test
            |messages
            |123
        """.trimMargin()
        println(testText2)

        var s: String?

        s = null
        println("$s.length is ${s?.length}") // abc.length is 3
    }
}
