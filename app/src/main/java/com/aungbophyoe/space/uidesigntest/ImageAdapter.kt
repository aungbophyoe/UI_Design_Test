package com.aungbophyoe.space.uidesigntest

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.aungbophyoe.space.uidesigntest.databinding.ImageContainerBinding

class ImageAdapter(private val imageList: ArrayList<Int>, private val view : ViewPager2)
    :RecyclerView.Adapter<ImageAdapter.ViewHolder>() {
        class ViewHolder(private val binding : ImageContainerBinding) : RecyclerView.ViewHolder(binding.root){
            fun bind (image:Int){
                binding.apply {
                    ivImage.setImageResource(image)
                }
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageAdapter.ViewHolder {
        val viewHolder = ViewHolder(ImageContainerBinding.inflate(LayoutInflater.from(parent.context),parent,false))
        return viewHolder
    }

    override fun onBindViewHolder(holder: ImageAdapter.ViewHolder, position: Int) {
        val image = imageList[position]
        if(image!=null){
            holder.bind(image)
        }
        if(position == imageList.size-1){
            view.post(runnable)
        }
    }

    override fun getItemCount(): Int {
        return imageList.size
    }

    private val runnable = Runnable {
        imageList.addAll(imageList)
        notifyDataSetChanged()
    }
}