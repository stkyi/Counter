package com.example.stanislav.counter

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.content.Intent
import android.os.Handler


class screen_saver : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.screen_saver)

        Handler().postDelayed({
            val myIntent = Intent(this@screen_saver, MainActivity::class.java)
            startActivity(myIntent)
            finish()
        },2000)



    }
}