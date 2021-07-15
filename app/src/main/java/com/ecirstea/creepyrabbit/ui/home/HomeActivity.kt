package com.ecirstea.creepyrabbit.ui.home


import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.ecirstea.creepyrabbit.R
import com.ecirstea.creepyrabbit.databinding.ActivityHomeBinding

private const val TAG = "HomeActivity"

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_home)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.textTEST.text = resources.getString(R.string.myName)
        Log.d(TAG, "onCreate: +")


    }
}