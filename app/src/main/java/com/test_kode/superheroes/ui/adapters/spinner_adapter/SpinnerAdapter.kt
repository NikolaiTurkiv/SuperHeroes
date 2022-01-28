package com.test_kode.superheroes.ui.adapters.spinner_adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.test_kode.superheroes.R
import com.test_kode.superheroes.ui.studios.Studios

class SpinnerAdapter(val context: Context, val listOfStudio: List<Studios>) : BaseAdapter() {

    override fun getCount(): Int = listOfStudio.size ?: 0


    override fun getItem(p0: Int): Any {
        return listOfStudio[p0]
    }

    override fun getItemId(p0: Int): Long = p0.toLong()

    @SuppressLint("ViewHolder")
    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        val rootView = LayoutInflater.from(context).inflate(R.layout.spinner_card, p2, false)

        val image = rootView.findViewById<ImageView>(R.id.studio_logo)
        val label = rootView.findViewById<TextView>(R.id.studio_name)

        image.setImageResource(listOfStudio[p0].image)
        label.text = listOfStudio[p0].name

        return rootView

    }
}