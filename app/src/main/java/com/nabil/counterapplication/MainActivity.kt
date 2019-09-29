package com.nabil.counterapplication

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        var count: Int = 0;
        counterText.text = count.toString()

        incrementButton.setOnClickListener {
            vibratePhone(40)

//          Try to see if the code block below works
            try{
                count += incrementationInput.text.toString().toInt()
                counterText.text = count.toString()

            }catch(e: Exception) {

//                If anything goes wrong, it most likely is not a number
                Toast.makeText(this, "Please enter a number!", Toast.LENGTH_SHORT).show()
            }
        }

        resetButton.setOnClickListener {

            vibratePhone(500)

            count = 0
            counterText.text = count.toString()
            }

        shareButton.setOnClickListener {
            val i = Intent()
            i.action = Intent.ACTION_SEND
            i.putExtra(Intent.EXTRA_TEXT, "I am counting... I am currently at $count counts in increment(s) of ${incrementationInput.text}")
            i.type = "text/plain"

            startActivity(Intent.createChooser(i, "Share to:"))
        }
    }

    private fun vibratePhone(ms: Long){

//        Initialise vibratorService
        val vibratorService = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator

        if(Build.VERSION.SDK_INT < 26){
//            VibratePhone is not deprecated, so we will use that
            vibratorService.vibrate(ms)
        }else{
            vibratorService.vibrate(VibrationEffect.createOneShot(ms, VibrationEffect.DEFAULT_AMPLITUDE))
        }


    }
}
