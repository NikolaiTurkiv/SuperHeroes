package com.test_kode.superheroes.ui.adapters.superheroes_adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.test_kode.data.database.models.SuperHero
import com.test_kode.superheroes.R
import com.test_kode.utils.Constants.Companion.DC_COMICS
import com.test_kode.utils.Constants.Companion.MARVEL

class SuperHeroesAdapter(private val context: Context) :
    RecyclerView.Adapter<SuperHeroesAdapter.SuperHeroesViewHolder>() {

    var superHeroList: List<SuperHero> = listOf()

    fun updateHeroes(superHeroList: List<SuperHero>) {
        this.superHeroList = superHeroList
        notifyDataSetChanged()
    }

    var onHeroClickListener: OnHeroClickListener? = null

    inner class SuperHeroesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageSuperHero = itemView.findViewById<ImageView>(R.id.card_image)
        val superHeroName = itemView.findViewById<TextView>(R.id.hero_name)
        val realName = itemView.findViewById<TextView>(R.id.real_name)
        val studioImage = itemView.findViewById<ImageView>(R.id.studio_image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SuperHeroesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.hero_card, parent, false)
        return SuperHeroesViewHolder(view)
    }

    override fun onBindViewHolder(holder: SuperHeroesViewHolder, position: Int) {
        val superHero = superHeroList[position]
        with(holder) {
            with(superHero) {
                Picasso.get().load(imagesSm.toString()).into(imageSuperHero)
                superHeroName.text = name.toString()
                realName.text = fullName.toString()
                if (publisher == MARVEL) {
                    studioImage.setImageResource(R.drawable.marvel_logo)
                } else if (publisher == DC_COMICS) {
                    studioImage.setImageResource(R.drawable.dc_logo)
                } else
                    studioImage.setImageResource(R.drawable.all_comix)

                itemView.setOnClickListener {
                    onHeroClickListener?.onHeroClick(this)
                }
            }
        }
    }

    override fun getItemCount(): Int = superHeroList.size

    interface OnHeroClickListener {
        fun onHeroClick(superHero: SuperHero)
    }

}