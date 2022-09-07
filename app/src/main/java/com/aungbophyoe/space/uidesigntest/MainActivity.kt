package com.aungbophyoe.space.uidesigntest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.aungbophyoe.space.uidesigntest.databinding.ActivityMainBinding
import com.github.hariprasanths.bounceview.BounceView
import kotlin.math.abs

class MainActivity : AppCompatActivity() {
    private var _binding : ActivityMainBinding? = null
    private val binding get() = _binding
    private val imageList = arrayListOf(
        R.drawable.sample1,R.drawable.sample3,R.drawable.sample1,R.drawable.sample4
    )
    private val handler : Handler by lazy {
        Handler(Looper.myLooper()!!)
    }
    private var isRoom = true
    private val recyclerAdapter : RecyclerAdapter by lazy {
        RecyclerAdapter()
    }
    private lateinit var adapter: ImageAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        val view = _binding!!.root
        setContentView(view)
        binding!!.apply {
            adapter = ImageAdapter(imageList,viewPager)
            viewPager.adapter = adapter
            viewPager.offscreenPageLimit = 3
            viewPager.clipToPadding = false
            viewPager.clipChildren = false
            viewPager.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
            setUpTransformer()
            viewPager.registerOnPageChangeCallback(object  : ViewPager2.OnPageChangeCallback(){
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    handler.removeCallbacks(runnable)
                    handler.postDelayed(runnable,4000)
                }
            })
            recyclerAdapter.setUpType(isRoom)
            recyclerView.apply {
                setHasFixedSize(true)
                layoutManager = LinearLayoutManager(this@MainActivity)
                adapter = recyclerAdapter
                isNestedScrollingEnabled = false
            }
            recyclerView.adapter!!.notifyDataSetChanged()
            ivFlag.setOnClickListener {
                if(isRoom){
                    isRoom = false
                    recyclerAdapter.setUpType(isRoom)
                    recyclerView.adapter = recyclerAdapter
                    recyclerView.adapter!!.notifyDataSetChanged()
                    ivFlag.background = ContextCompat.getDrawable(this@MainActivity,R.drawable.byrate_background)
                }else{
                    isRoom = true
                    recyclerAdapter.setUpType(isRoom)
                    recyclerView.adapter = recyclerAdapter
                    recyclerView.adapter!!.notifyDataSetChanged()
                    ivFlag.background = ContextCompat.getDrawable(this@MainActivity,R.drawable.byroom_background)
                }
            }
            BounceView.addAnimTo(ivFlag)
            BounceView.addAnimTo(tvSeeAll)
            BounceView.addAnimTo(ivCurrency)
            BounceView.addAnimTo(ivChatActive)
        }
    }

    private val runnable = Runnable {
        binding!!.viewPager.currentItem = binding!!.viewPager.currentItem + 1
    }

    override fun onPause() {
        super.onPause()
        handler.removeCallbacks(runnable)
    }

    override fun onResume() {
        super.onResume()
        handler.postDelayed(runnable,5000)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun setUpTransformer(){
        binding!!.apply {
            val transformer = CompositePageTransformer()
            transformer.addTransformer(MarginPageTransformer(40))
            transformer.addTransformer{page,position ->
                val r =1 - abs(position)
                page.scaleY = 0.85f + r + 0.14f
            }
            viewPager.setPageTransformer(transformer)
        }
    }
}