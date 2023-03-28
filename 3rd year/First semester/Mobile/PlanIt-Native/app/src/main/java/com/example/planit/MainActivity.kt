package com.example.planit

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.MyRecyclerViewAdapter
import com.example.planit.Repository.ActivityRepo
import com.example.planit.Repository.TaskRepo
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.tabs.TabLayout
import java.text.SimpleDateFormat
import java.util.*


class MainActivity() : AppCompatActivity(), MyRecyclerViewAdapter.ItemClickListener{
    var adapter: MyRecyclerViewAdapter? = null
    lateinit var recyclerView: RecyclerView
    var tabPosition = 0

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setDate()

        //Set recycle view
        recyclerView = findViewById<RecyclerView>(R.id.elementsRecycleView)
        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        val dividerItemDecoration = DividerItemDecoration(
            recyclerView.context,
            layoutManager.orientation
        )
        recyclerView.addItemDecoration(dividerItemDecoration)
        adapter = MyRecyclerViewAdapter(this, TaskRepo.elementsName)
        recyclerView.adapter = adapter
        adapter!!.setClickListener(this@MainActivity)


        val tabLayout = findViewById<TabLayout>(R.id.tabLayout)

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                val tab_position = tab?.position
                if (tab_position != null) {
                    if (tab_position.compareTo(0) == 0) {
                        tabPosition = 0
                        adapter = MyRecyclerViewAdapter(this@MainActivity, TaskRepo.elementsName)
                        adapter!!.setClickListener(this@MainActivity)
                    } else{
                        tabPosition = 1
                        adapter = MyRecyclerViewAdapter(this@MainActivity, ActivityRepo.elementsName)
                        adapter!!.setClickListener(this@MainActivity)

                    }
                    recyclerView.adapter = adapter
                }
            }
            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })

        val addBtn = findViewById<FloatingActionButton>(R.id.addButton)
        val intent = Intent(this@MainActivity, AddTask::class.java)
        addBtn.setOnClickListener {
            if (tabPosition == 0){
                val intent = Intent(this@MainActivity, AddTask::class.java)
                startActivity(intent)
            }
            else{
                val intent = Intent(this@MainActivity, AddActivity::class.java)
                startActivity(intent)
            }
        }
        val bundle :Bundle ?=intent.extras
        if (bundle != null){
            val message = bundle.getString("msg") // 1
            if (message != null) {
                recyclerView.adapter?.notifyItemChanged(message.toInt())
            }
        }

    }

    @SuppressLint("SimpleDateFormat")
    fun setDate(){
        val datePicker = findViewById<Button>(R.id.datePicker)
        val formatter = SimpleDateFormat("dd/MM/yyyy")
        val date = Date()
        val current = formatter.format(date)
        datePicker.text = current
    }


    override fun onItemClick(view: View?, position: Int) {
        if (tabPosition == 0){
            val intent = Intent(this@MainActivity, ModifyTask::class.java).apply {
                action = Intent.ACTION_SEND
                type = "text/plain"
                putExtra("id", adapter!!.getItem(position))
            }
            startActivity(intent)

        }
        else{
            val intent = Intent(this@MainActivity, ModifyActivity::class.java).apply {
                action = Intent.ACTION_SEND
                type = "text/plain"
                putExtra("id", adapter!!.getItem(position))
            }
            startActivity(intent)
        }
    }
}