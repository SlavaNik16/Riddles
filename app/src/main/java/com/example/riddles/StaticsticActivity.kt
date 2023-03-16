package com.example.riddles

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.riddles.databinding.ActivityStaticsticBinding

class StaticsticActivity : AppCompatActivity() {
    lateinit var binding: ActivityStaticsticBinding
    lateinit var intentMain: Intent
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStaticsticBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var CountTrue: Int =  intent.getIntExtra("CountTrue", 0)
        var CountFalse: Int = intent.getIntExtra("CountFalse", 0)

        binding.txtAllCount.text = (CountTrue + CountFalse).toString()
        binding.txtTrue.text = CountTrue.toString()
        binding.txtFalse.text = CountFalse.toString()

        intentMain = Intent(this, MainActivity::class.java)
    }

    fun butMain(view: View){
        setResult(RESULT_CANCELED,intentMain)
        finish()
    }
}