package com.example.myguessgame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager.LayoutParams.*
import android.widget.ImageView
import com.bumptech.glide.Glide
import java.util.*

class SplashScreen : AppCompatActivity() {
    lateinit var imgLoader: ImageView;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        showGif()
        Timer().schedule(object : TimerTask(){
            override fun run() {
                val i = Intent(this@SplashScreen,MainActivity::class.java);
                startActivity(i);
                finish();
            }


        },1500L)

    }

    fun showGif(){
        imgLoader= findViewById(R.id.imageLoader)
        Glide.with(this).load(R.drawable.loader).into(imgLoader)
    }
}