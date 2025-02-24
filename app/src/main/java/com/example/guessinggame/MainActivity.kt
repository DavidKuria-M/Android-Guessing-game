package com.example.guessinggame

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.guessinggame.databinding.ActivityMainBinding
import kotlin.random.Random
import kotlin.random.nextInt

class MainActivity : AppCompatActivity() {

    private var groupNumber = 0
    private var randomNumbers = mutableSetOf<Int>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val level:TextView = findViewById(R.id.tv_level)
        val guessedNumber:EditText = findViewById(R.id.et_guessed)
        val gameQuestion:TextView = findViewById(R.id.textview_question)
        val guessedButton:Button = findViewById(R.id.btn_guessing)
        val scored:TextView = findViewById(R.id.tv_score)


        guessedButton.setOnClickListener {

            fun generate(start:Int,stop:Int):Int{

                return Random.nextInt(start,stop)
            }

            fun guessing(){

                val lev = level.text.toString()

                var question: String
                val score = scored.text.toString()
                var scores:Int = score.toInt()
                var progres = lev.toInt()
                var start = 0
                var stop = 10
                val random = generate(start,stop)
                var chance = 0

                val num = guessedNumber.text.toString().trim()

                do {
                    chance ++
                    if(num.isNotEmpty()){
                        guessedNumber.text.clear()
                        if (num.toInt() == random){
                            Toast.makeText(this,"Congratulations you got the answer correct",Toast.LENGTH_LONG).show()
                            progres += 1
                            scores += 1
                            start += 10
                            stop += 10
                            question = "Guess the generated number between {$start} to {$stop}"
                            gameQuestion.text = question
                            continue

                        }else{
                            Toast.makeText(this,"Wrong!!,Try again",Toast.LENGTH_LONG).show()
                            continue
                        }

                    }else{
                        Toast.makeText(this,"Please enter a number",Toast.LENGTH_LONG).show()
                    }
                }while (chance <= 5)

            }
            guessing()
        }

    }

}

