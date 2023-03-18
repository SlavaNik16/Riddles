package com.example.riddles

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Debug
import android.util.Log
import android.view.View
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.graphics.alpha
import androidx.core.graphics.green
import androidx.core.view.isVisible
import com.example.riddles.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var Riddle: RiddlesAnswer
    lateinit var txtAnswer: String
    lateinit var txtRiddle: String
    var listAnswer: MutableSet<String> = mutableSetOf()
    var listRiddle: ArrayList<String> = arrayListOf()
    var count = 0
    var maxCount:Int = 10

    var ResultTrueCount = 0
    var ResultFalseCount = 0

    private var launcher: ActivityResultLauncher<Intent>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        Riddle = RiddlesAnswer()
        setContentView(binding.root)
        maxCount = binding.txtQuestionsCountMax.text.toString().toInt()
        binding.butStatus.isEnabled = false
        binding.butAnswer.isEnabled = false

        binding.butEnd.isVisible = false
        binding.butRestore.isVisible = false

        launcher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
                result:ActivityResult ->
                if(result.resultCode == RESULT_OK){
                    var text = result.data?.getStringExtra("Result")
                    binding.ANSWER.text = text
                    if(txtAnswer == text){
                        binding.ANSWER.setBackgroundColor(getColor(R.color.DarkGreen))
                        ResultTrueCount++
                    }else{
                        binding.ANSWER.setBackgroundColor(getColor(R.color.DarkRed))
                        ResultFalseCount++
                    }
                    if(count < maxCount){
                        buttonRiddles(true)
                    }else{
                        СonditioTwo()
                    }
                }
                if(result.resultCode == RESULT_CANCELED){
                    СonditioTwo()
                }
            }
    }

    fun butRiddle(view: View){

        binding.ANSWER.setBackgroundColor(Color.WHITE)
        binding.ANSWER.text = ""
        count = binding.txtQuestionsCount.text.toString().toInt()
        var rnd = 0
        while(true){
            rnd = Riddle.RandomFunc()
            if(Riddle.Riddles[rnd] !in listRiddle){
                break
            }
        }

        txtAnswer = Riddle.Answer[rnd]
        txtRiddle = Riddle.Riddles[rnd]

        listRiddle.add(txtRiddle)
        listAnswer.clear()

        listAnswer.add(txtAnswer)

        while(listAnswer.size < 10){
            listAnswer.add(Riddle.Answer[Riddle.Answer.indices.random()])
        }

        binding.RIDDLES.text = txtRiddle
        count++
        binding.txtQuestionsCount.text = count.toString()
        buttonRiddles(false)

    }

    fun butAnswer(view: View){
        var list = listAnswer.shuffled()
        var answerList:ArrayList<String> = arrayListOf();
        answerList.addAll(list)
        var intent = Intent(this,ChoiseAnswerActivity::class.java)
        intent.putStringArrayListExtra("ListAnswer", answerList)
        launcher?.launch(intent)
    }

    fun buttonRiddles(Truth:Boolean){
        binding.butAnswer.isEnabled = !Truth
        binding.butRiddles.isEnabled = Truth
    }
    fun textViewVisibleOff(){
        binding.txtQuestionsCount.isVisible = false
        binding.txtQuestionsCountMax.isVisible = false
        binding.txtSeparator.isVisible = false
        binding.RIDDLES.isVisible = false
    }

    fun butRestore(view: View){
        finish();
        startActivity(getIntent());
    }
    fun butEnd(view: View){
        finishAndRemoveTask()
    }
    fun butStatistic(view: View){
        var intent = Intent(this,StaticsticActivity::class.java)
        intent.putExtra("CountTrue", ResultTrueCount)
        intent.putExtra("CountFalse", ResultFalseCount)
        startActivity(intent)
    }

    fun СonditioTwo(){
        binding.butRiddles.isEnabled =false
        binding.butAnswer.isEnabled = false
        binding.butStatus.isEnabled = true

        binding.butEnd.isVisible = true
        binding.butRestore.isVisible = true
        textViewVisibleOff()
    }
}