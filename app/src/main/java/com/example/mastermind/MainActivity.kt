package com.example.mastermind

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

    var random: Int? = null
    var sRandom: String? = null
    var mTitle: TextView? = null
    var mSub: TextView? = null
    var mResponse: TextView? = null
    var mButt: Button? = null
    var mGuess: EditText? = null
    var numberOfGuesses: Int = 0
    var numOfInc: Int = 0
    var numOfCor: Int = 0

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        this.window?.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_main)

        //Assigning variables to appropriate xml object
        mTitle = findViewById(R.id.titletext)
        mSub = findViewById(R.id.subtext)
        mResponse = findViewById(R.id.responsetext)
        mButt = findViewById(R.id.secret)
        mGuess = findViewById(R.id.guess)
        random = (Math.random()*1000).toInt()
        sRandom = random.toString()
        sRandom = configureStringZeros(sRandom)
        mButt!!.setOnClickListener {
            println("in the button event handler")
            var guess: String? = mGuess!!.text.toString()
            guess = configureStringZeros(guess)
            if (guess == null) {
                mResponse!!.text = "Please enter a valid number between 0000 and 9999!"
            } else {
                println("about to run checkValue")
                checkValue(sRandom!!, guess!!)
            }

        }

    }
    private fun configureStringZeros(s: String?): String? {
        when (s?.length) {
            1 -> return "000$s"
            2 -> return "00$s"
            3 -> return "0$s"
            4 -> return s
            else -> {
                return null
            }
        }
    }

    private fun checkValue(r: String, i: String) {
        if(r == i){
            mResponse!!.text = ("Congratulations!! You won in $numberOfGuesses guesses!")
        } else {
            numOfCor = 0
            numOfInc = 0
            val iMap = mapOf(Pair(0, i[0]), Pair(1, i[1]), Pair(2, i[2]), Pair(3, i[3]))
            val rMap = mapOf(Pair(0, r[0]), Pair(1, r[1]), Pair(2, r[2]), Pair(3, r[3]))
            for(i in 0..3) {
                if(iMap[i] == rMap[i])
                    numOfCor++
                if(rMap.containsValue(iMap[i]))
                    numOfInc++
            }
            numOfInc -= numOfCor
            mResponse!!.text = ("$numOfInc digits in the wrong place. \n$numOfCor digits in the correct place.")
            numberOfGuesses++
        }
    }


}