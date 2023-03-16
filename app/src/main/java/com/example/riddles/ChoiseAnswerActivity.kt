package com.example.riddles

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import com.example.riddles.databinding.ActivityChoiseAnswerBinding
import com.example.riddles.databinding.ActivityMainBinding

class ChoiseAnswerActivity : AppCompatActivity() {
    lateinit var binding: ActivityChoiseAnswerBinding
    var ListAnswer: ArrayList<String>? = null
    lateinit var intentResult: Intent
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChoiseAnswerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ListAnswer = intent.getStringArrayListExtra("ListAnswer")

        FillRadio()
        EnabledValidate(false)
        intentResult = Intent(this, MainActivity::class.java)
    }
    fun FillRadio(){
        binding.radioOne.text = ListAnswer?.get(0)
        binding.radioTwo.text = ListAnswer?.get(1)
        binding.radioThree.text = ListAnswer?.get(2)
        binding.radioFour.text = ListAnswer?.get(3)
        binding.radioFive.text = ListAnswer?.get(4)
        binding.radioSix.text = ListAnswer?.get(5)
        binding.radioSeven.text = ListAnswer?.get(6)
        binding.radioEight.text = ListAnswer?.get(7)
        binding.radioNine.text = ListAnswer?.get(8)
        binding.radioTen.text = ListAnswer?.get(9)

    }
    fun EnabledValidate(x: Boolean){
        binding.butValidate.isEnabled = x
    }
    fun butRadioOne(view: View){
        intentResult.putExtra("Result", binding.radioOne.text)
        EnabledValidate(true)

    }
    fun butRadioTwo(view: View){
        intentResult.putExtra("Result", binding.radioTwo.text)
        EnabledValidate(true)
    }
    fun butRadioThree(view: View){
        intentResult.putExtra("Result", binding.radioThree.text)
        EnabledValidate(true)
    }
    fun butRadioFour(view: View){
        intentResult.putExtra("Result", binding.radioFour.text)
        EnabledValidate(true)
    }
    fun butRadioFive(view: View){
        intentResult.putExtra("Result", binding.radioFive.text)
        EnabledValidate(true)
    }
    fun butRadioSix(view: View){
        intentResult.putExtra("Result", binding.radioSix.text)
        EnabledValidate(true)
    }
    fun butRadioSeven(view: View){
        intentResult.putExtra("Result", binding.radioSeven.text)
        EnabledValidate(true)
    }
    fun butRadioEight(view: View){
        intentResult.putExtra("Result", binding.radioEight.text)
        EnabledValidate(true)
    }
    fun butRadioNine(view: View){
        intentResult.putExtra("Result", binding.radioNine.text)
        EnabledValidate(true)
    }
    fun butRadioTen(view: View){
        intentResult.putExtra("Result", binding.radioTen.text)
        EnabledValidate(true)
    }

    fun butValidate(view: View){
        setResult(RESULT_OK,intentResult)
        finish()
    }



}