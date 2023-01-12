package com.example.myguessgame

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*

class MainActivity : AppCompatActivity() {
    lateinit var btnStart: Button
    lateinit var rgN:RadioGroup
    lateinit var rbEasy:RadioButton
    lateinit var rbHard:RadioButton

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rgN=findViewById(R.id.rgN)
        rbEasy= findViewById(R.id.rbEasy)
        rbHard= findViewById(R.id.rbHard)
        btnStart= findViewById(R.id.btnStartGame)
        btnStart.setOnClickListener {

            val rbId= rgN.checkedRadioButtonId
            val rbcheked= findViewById<RadioButton>(rbId)
            if(rbcheked.equals(rbEasy)){
                Toast.makeText(applicationContext,rbcheked.text.toString(), Toast.LENGTH_LONG).show()
                val intentGameActivity: Intent = Intent(this,GameActivity::class.java)
                startActivity(intentGameActivity)
            }else if(rbcheked.equals(rbHard)){
                Toast.makeText(applicationContext,rbcheked.text.toString(), Toast.LENGTH_LONG).show()
                val intentGameActivity: Intent = Intent(this,GameHardActivity::class.java)
                startActivity(intentGameActivity)
            }





        }

        // var int : Intent= new Intent()
    }




}
