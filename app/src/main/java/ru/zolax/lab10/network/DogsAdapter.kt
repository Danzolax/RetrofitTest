package ru.zolax.lab10.network

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.zolax.lab10.R

class DogsAdapter(
    private val dogs: MutableList<Dog> = mutableListOf()
) : RecyclerView.Adapter<DogsAdapter.DogsVH>() {
    var onAttach = true
    val DURATION: Long = 500



    class DogsVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(dog: Dog) {
            Glide.with(itemView).load(dog.imageUrl).into(itemView.findViewById(R.id.image))

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogsVH {
        val inflater: LayoutInflater = LayoutInflater.from(parent.context)
        return DogsVH(inflater.inflate(R.layout.rv_item, parent, false))
    }

    override fun onBindViewHolder(holder: DogsVH, position: Int) {
        holder.bind(dogs[position])
    }

    override fun getItemCount(): Int {
        return dogs.size
    }

    fun addDog(newDog: Dog) {
        dogs.add(newDog)
        notifyDataSetChanged()
    }

    fun clearDogs() {
        dogs.clear()
        notifyDataSetChanged()
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                onAttach = false
            }
        })
        super.onAttachedToRecyclerView(recyclerView)
    }




}