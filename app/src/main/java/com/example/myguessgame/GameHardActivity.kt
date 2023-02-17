package com.example.myguessgame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import kotlin.random.Random

class GameHardActivity : AppCompatActivity() {
    internal  var helper= DatabaseHoelperHard(this)

    var list = mutableListOf<Gamer>()
    private  lateinit var tVScore: TextView

     var nbEssais : Int=0
     var nbSecondesPassees:Int=0
     var score:Int =100
     val START_TIME_IN_MILI: Long = 80 * 1000
     var remainingTime : Long = START_TIME_IN_MILI
     var isTimerRunning : Boolean=false
     var timer: CountDownTimer? =null
     lateinit var  textViewEssais: TextView
     lateinit var textViewMessage: TextView
     lateinit var textViewTimer: TextView
     lateinit var editText: EditText

     lateinit var imageButtonReset: ImageButton
     lateinit var  imageButtonCheck: ImageButton
     var random: Int = Random.nextInt(1, 1000)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_hard)
        tVScore= findViewById<TextView>(R.id.textViewScoreH)
        textViewEssais=findViewById(R.id.tvEssaisH)



        textViewTimer= findViewById(R.id.tvTimerH)

        if(!isTimerRunning){
            startTimer()
        }
        textViewMessage= findViewById(R.id.tvMessageH)




        editText= findViewById(R.id.editTextNumberH)

        imageButtonCheck= findViewById(R.id.imageButtonCheckH)
        imageButtonCheck.setOnClickListener {
            if(nbEssais<20){
                try {

                    val  number: Int=editText.text.toString().toInt()


                    if(number> 1000 || number< 1){
                        Toast.makeText(applicationContext,"Enter an integer between 1 and 1000", Toast.LENGTH_LONG).show()
                    }else{

                        nbEssais++
                        var nbE= (20 - nbEssais)
                        textViewEssais.text= nbE.toString()


                       // Toast.makeText(applicationContext,nbEssais.toString(), Toast.LENGTH_LONG).show()

                        if(number < random){
                            textViewMessage.text="Wrong, your number is to low!"

                            editText.text.clear();


                        }else if(number > random){
                            textViewMessage.text="Wrong, your number is to high!"
                            editText.text.clear()

                        }else{
                            textViewMessage.text="GOOD, your number is right "
                            gameGood()

                        }



                    }

                }catch (e: java.lang.Exception){
                    e.message
                }


            }else {
                gameOver()

            }



        }
        imageButtonReset= findViewById(R.id.imageButtonResetH)
        imageButtonReset.setOnClickListener {
            reset()
            startTimer()
        }

    }

    private fun gameGood() {

        editText.setFocusable(false)

        imageButtonCheck.setClickable(false)


        timer?.cancel()

        affScore()
        viewAll()
        if(list.size >= 0 && list.size < 10){
            intentScore()

        }else{
            if(gamerbetween(score)){
                intentScore()

            }

        }

    }

    private fun gameOver() {
        editText.setFocusable(false)
        imageButtonCheck.setClickable(false)
        timer?.cancel()
        affScore()

    }

    private fun startTimer() {
        timer = object : CountDownTimer(remainingTime, 1000) {
            override fun onTick(timeleft: Long) {
                remainingTime=timeleft
                updateTimerText()
                nbSecondesPassees++

            }

            override fun onFinish() {
                textViewTimer.text = "Done!"

                editText.setFocusable(false)
                imageButtonCheck.setClickable(false)
                isTimerRunning= false
                affScore()


            }


        }.start()

        isTimerRunning=true
    }



    private fun updateTimerText(){
        val minute = remainingTime.div(1000).div(60)
        val second = remainingTime.div(1000) % 60

        val formatTime = String.format("%02d:%02d",minute,second)
        textViewTimer.text= formatTime


    }
    private fun reset(){

        random= Random.nextInt(1, 1000)
        editText.text.clear()
        resetTimer()
        imageButtonCheck.setClickable(true)
        editText.setFocusable(true)

        tVScore.text="00"


    }

    private fun resetTimer() {
        timer?.cancel()
        remainingTime = START_TIME_IN_MILI
        updateTimerText()
        isTimerRunning=false
    }

    override fun onStart() {
        super.onStart()

    }

    override fun onStop() {
        super.onStop()
        timer?.cancel()
        isTimerRunning=false

    }
    override fun onPause() {
        super.onPause()
        timer?.cancel()
        isTimerRunning=false

    }

    override fun onRestart() {
        super.onRestart()
        if(!isTimerRunning){
            startTimer()
        }
    }
    override fun onResume() {
        super.onResume()
        if(!isTimerRunning){
            startTimer()

        }


    }

    private  fun affScore(){
        score= score - (nbEssais + nbSecondesPassees)
        tVScore.text = score.toString()



    }
    fun viewAll(){
        list.clear()
        val res=helper.allData
        if(res.count == 0){
            Toast.makeText(this, "No records", Toast.LENGTH_SHORT).show()

        }
        while (res.moveToNext()){
            list.add(Gamer(res.getString(0),
                res.getString(1),
                res.getString(2)))

        }
    }

    private fun intentScore() {
        val d = Intent(this@GameHardActivity, HardScoreActivity::class.java)
        d.putExtra("SCORE", score)
        startActivity(d)
        finish()
    }

    fun  gamerbetween(s : Int): Boolean{
        var p : Boolean = false
        for ( gamer in list ){
            if(Integer.parseInt(gamer.score) <= s){
                p= true
            }
        }

        return p
    }
}