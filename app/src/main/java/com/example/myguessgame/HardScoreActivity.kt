package com.example.myguessgame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.bumptech.glide.Glide

class HardScoreActivity : AppCompatActivity() {
    lateinit var imgG: ImageView;
    internal  var helper= DatabaseHoelperHard(this)
    lateinit var btnInsert: ImageButton
    lateinit var firstName : EditText
    lateinit var imgBtnMain: ImageButton
    lateinit var lvScore:ListView
    var list = mutableListOf<Gamer>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hard_score)
        showGif()


        lvScore= findViewById(R.id.lvMS)

        viewAll()

        val adapter= MyListAdapter(this, R.layout.gamer, list)
        lvScore.adapter = adapter



        imgBtnMain= findViewById<ImageButton>(R.id.btnAccueil)
        imgBtnMain.setOnClickListener {
            var i = Intent(this@HardScoreActivity, MainActivity::class.java )
            startActivity(i)
            finish()

        }


        val score = intent.getIntExtra("SCORE",0)
        Toast.makeText(this, "score :"+score.toString(), Toast.LENGTH_SHORT).show()
        firstName=findViewById(R.id.editTextFirstName)
        btnInsert= findViewById<ImageButton>(R.id.btnimageInsert)
        btnInsert.setOnClickListener{
            helper.insertData(firstName.text.toString().trim(),score.toString().trim())
            Toast.makeText(this,"Inserted "+firstName.text.toString()+" :"+score, Toast.LENGTH_LONG).show()

            firstName.setFocusable(false)
            viewAll()
            btnInsert.setClickable(false)
        }


    }

    private fun showGif(){
        imgG = findViewById(R.id.imgCong)
        Glide.with(this).load(R.drawable.hardimage).into(imgG)
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
}