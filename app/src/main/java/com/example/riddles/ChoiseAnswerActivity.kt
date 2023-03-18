package com.example.riddles

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.core.view.isVisible
import com.example.riddles.databinding.ActivityChoiseAnswerBinding
import com.example.riddles.databinding.ActivityMainBinding

class ChoiseAnswerActivity : AppCompatActivity() {
    lateinit var binding: ActivityChoiseAnswerBinding
    val listRadioName:ArrayList<String> = arrayListOf<String>("radioOne", "radioTwo","radioThree","radioFour","radioFive",
        "radioSix", "radioSeven","radioEight","radioNine","radioTen")
    var ListAnswer: ArrayList<String>? = null
    lateinit var intentResult: Intent
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChoiseAnswerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.butValidate.isEnabled = false
        ListAnswer = intent.getStringArrayListExtra("ListAnswer")
        intentResult = Intent(this, MainActivity::class.java)
        val rg = RadioGroup(this)
        rg.orientation = RadioGroup.VERTICAL
        for(i in ListAnswer!!.indices){
            var rb = RadioButton(this)
            rb.text = ListAnswer?.get(i)
            rb.id = View.generateViewId()
            rb.textSize = 28f
            rb.setOnClickListener{
                intentResult.putExtra("Result", rb.text);
                binding.butValidate.isEnabled = true
            }
            rg.addView(rb)
        }
        binding.linearLayout2.addView(rg)
    }
    fun butValidate(view: View){
        setResult(RESULT_OK,intentResult)
        finish()
    }



}