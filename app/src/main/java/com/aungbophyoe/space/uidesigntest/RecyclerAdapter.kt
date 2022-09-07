package com.aungbophyoe.space.uidesigntest

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.github.hariprasanths.bounceview.BounceView

class RecyclerAdapter:RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {
    companion object {
        var isRoom = true
    }
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val tvDeal = view.findViewById<TextView>(R.id.tvDeals)
        val tvViewRate = view.findViewById<TextView>(R.id.tvViewRate)
        val tvRoomViewRate = view.findViewById<TextView>(R.id.tvRoomViewRate)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflatedView = if (isRoom) {
            LayoutInflater.from(parent.context).inflate(R.layout.rv_by_room_item, parent, false)
        } else {
            LayoutInflater.from(parent.context).inflate(R.layout.rv_by_rate_item, parent, false)
        }
        val holder = ViewHolder(inflatedView)
        if (isRoom){
            BounceView.addAnimTo(holder.tvRoomViewRate)
        }else{
            BounceView.addAnimTo(holder.tvDeal)
            BounceView.addAnimTo(holder.tvViewRate)
        }
        return holder
    }

    fun setUpType(_isRoom: Boolean) {
        isRoom = _isRoom
        this.notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return 10
    }
}