package com.example.guessinggame

import android.annotation.SuppressLint
import android.content.Intent
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
import kotlin.properties.Delegates
import kotlin.random.Random
import kotlin.random.nextInt

class MainActivity : AppCompatActivity() {

    private var currentLevel = 1
    private var currentScore = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val level: TextView = findViewById(R.id.tv_level)
        val guessedNumber: EditText = findViewById(R.id.et_guessed)
        val gameQuestion: TextView = findViewById(R.id.textview_question)
        val guessedButton: Button = findViewById(R.id.btn_guessing)
        val scored: TextView = findViewById(R.id.tv_score)
        val levels = level.text.toString()
        //val score = scored.text.toString()

        var currentLevel = levels.toInt()
        var randomNumber:Int


        fun generate(first:Int,last:Int):Int{

            val random = Random.nextInt(first,last)
            return random
        }
       fun modify(){
            /* val point = score.toInt() + 1
            scored.text = point.toString()
            val lev = levels.toInt() + 1
            level.text = lev.toString()
            currentLevel = lev
             */

           level.text = currentLevel.toString()
           scored.text = currentScore.toString()
        }



        @SuppressLint("SetTextI18n")
        fun questioned(){
            when(currentLevel){
                1 ->{
                    "Guess the number between 1 and 10".also { gameQuestion.text = it }
                }
                2 -> {
                    "Guess the generate number between 11 to 20".also { gameQuestion.text = it }
                }
                3 -> {
                    "Guess the generate number between 21 to 30".also { gameQuestion.text = it }
                }
                4 -> {
                    "Guess the generate number between 31 to 50".also { gameQuestion.text = it }
                }
                5 -> {
                    "Guess the generate number between 51 to 100".also { gameQuestion.text = it }
                }

                6 -> {
                    val intent = Intent(this,Winning::class.java)
                    intent.putExtra("totalScore",scored.text)
                    startActivity(intent)
                    finish()
                }
                else -> {
                    "Game Over!".also { gameQuestion.text = it }
                }
            }
        }

        fun validate(inputNumber:Int,randomGenerated:Int){
                if (inputNumber == randomGenerated){
                    Toast.makeText(this,"Congratulations you got it correct",Toast.LENGTH_LONG).show()
                    currentLevel += 1
                    currentScore += 1
                    modify()
                    questioned()
                    guessedNumber.text.clear()

                }else {
                    Toast.makeText(this,"Try again!",Toast.LENGTH_LONG).show()
                    guessedNumber.text.clear()
                }

            }

        guessedButton.setOnClickListener {


            fun guessing(){
                val number = guessedNumber.text.toString()



                if (number.trim().isNotEmpty()){
                    //Toast.makeText(this,"You have entered a number",Toast.LENGTH_LONG).show()
                    when(currentLevel){
                        1 -> {
                            randomNumber = generate(1,10)
                            val num = number.toInt()
                            validate(num,randomNumber)


                        }
                        2 -> {
                            randomNumber = generate(11,20)
                            val num = number.toInt()
                            validate(num,randomNumber)

                        }
                        3 -> {
                            randomNumber = generate(21, 30)
                            val num = number.toInt()
                            validate(num, randomNumber)


                        }
                        4 -> {
                            randomNumber = generate(31, 50)
                            val num = number.toInt()
                            validate(num, randomNumber)

                        }
                        5 -> {
                            randomNumber = generate(51, 100)
                            val num = number.toInt()
                            validate(num, randomNumber)

                        }
                        6 -> {
                            currentScore += 1

                        }
                        else -> {
                            Toast.makeText(this,"Invalid Level",Toast.LENGTH_LONG).show()
                        }


                    }

                }else {
                   Toast.makeText(this,"Guess a number!",Toast.LENGTH_LONG).show()
                }

            }
            guessing()

        }

    }
}


