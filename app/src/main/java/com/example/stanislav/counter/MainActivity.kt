package com.example.stanislav.counter

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import kotlinx.android.synthetic.main.activity_main.*
import com.github.moneytostr.MoneyToStr

class MainActivity : AppCompatActivity() {
    private var isCancelled = true
    var moneyToStr = MoneyToStr(MoneyToStr.Currency.RUR, MoneyToStr.Language.RUS, MoneyToStr.Pennies.NUMBER)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val millisInFuture: Long = 1000*1000
        val countDownInterval: Long = 1000

        button_start.setOnClickListener{
            if(isCancelled) {

                isCancelled = false
                // Start the timer
                stopwatch(millisInFuture, countDownInterval).start()
                button_start.text = "stop"
                textView.text = isCancelled.toString()

            }
            else if(!isCancelled) {
                isCancelled = true
                button_start.text = "start"
                //textView.text = isCancelled.toString()

            }

        }
    }

    private fun stopwatch(millisInFuture:Long, countDownInterval: Long):CountDownTimer{
        return object: CountDownTimer(millisInFuture,countDownInterval){
            override fun onTick(millisUntilFinished: Long){
                val timeRemaining = timeString(millisUntilFinished)
                if (isCancelled){
                    cancel()
                }else{
                    textView.text = timeRemaining
                }
            }
            override fun onFinish() {
                button_start.text = "Start"
                isCancelled = true

            }
        }
    }
    // Method to get days hours minutes seconds from milliseconds
    private fun timeString(millisUntilFinished:Long):String{
        val time_now = 1000-(millisUntilFinished/1000)
        val afterLib = moneyToStr.convert(time_now.toDouble())
        val ar = afterLib.split(" ").toMutableList()
        ar.removeAt(ar.size-1)
        ar.removeAt(ar.size-1)
        ar.removeAt(ar.size-1)
        return ar.joinToString(" ")
    }

}
