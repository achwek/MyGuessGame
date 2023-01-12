package com.example.myguessgame

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class MyListAdapter( var mCtx: Context, var resource: Int, var items: List<Gamer>)
    :ArrayAdapter<Gamer>(mCtx,resource,items) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val layoutInflater:  LayoutInflater= LayoutInflater.from(mCtx)
        val view : View = layoutInflater.inflate(resource,null)
        val firstName: TextView= view.findViewById(R.id.tvFirstName)
        val score: TextView= view.findViewById(R.id.tvScoreGamer)
        val gamer: Gamer = items[position]
        firstName.text=gamer.firstName
        score.text= gamer.score

        return view
    }




}