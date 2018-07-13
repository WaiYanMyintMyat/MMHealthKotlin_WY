package com.example.waiyan.mmhealthkotlin.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.example.waiyan.mmhealthkotlin.R
import com.example.waiyan.mmhealthkotlin.adapters.HealthCareInfoAdapter

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    private var adapter: HealthCareInfoAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        rv.layoutManager = LinearLayoutManager(applicationContext
                , LinearLayoutManager.VERTICAL
                , false)
        adapter = HealthCareInfoAdapter()
        rv.adapter = adapter
    }
}
