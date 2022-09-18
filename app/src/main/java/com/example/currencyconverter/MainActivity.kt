package com.example.currencyconverter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import com.google.android.material.textfield.TextInputEditText
import java.math.BigDecimal
import java.math.RoundingMode

class MainActivity : AppCompatActivity() {
    val TAG="MainActivity"
    private val egyptionPound="Egyptian Pound"
    private val americanDollar="American Dollar"
    private val AED="AED"
    private val GBP="GBP"
    lateinit var fromDropDownMenu:AutoCompleteTextView
    lateinit var toDropDownMenu: AutoCompleteTextView
    lateinit var convertBtn:Button
    lateinit var amountEt:TextInputEditText
    lateinit var resultEt:TextInputEditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initializeView()
        populateDropDownMenue()




        convertBtn.setOnClickListener(){
            val amount=amountEt.text.toString().toDouble()
            val currencyToField=toDropDownMenu.text.toString()
            Log.d(TAG,"TO $currencyToField")

            var result:Double=0.0
           result= when(currencyToField){
                AED->BigDecimal.valueOf(amount.times(3.67)).setScale(2,RoundingMode.DOWN).toDouble()
                egyptionPound->BigDecimal.valueOf(amount.times(19.26)).setScale(2,RoundingMode.DOWN).toDouble()
                GBP->BigDecimal.valueOf(amount.times(0.87)).setScale(2,RoundingMode.DOWN).toDouble()
               else->BigDecimal.valueOf(amount.times(1)).setScale(2,RoundingMode.DOWN).toDouble()
            }
            resultEt.setText(result.toString())
            Log.d(TAG,"result = $result")


        //   Toast.makeText(this,"Button clicked",Toast.LENGTH_SHORT).show()
        /*  val x=12
            if(x>5){
                val comment="x is more than 5"
                Log.d(TAG, "Result : $comment")
            }else if(x<5){
                val comment="x is less than 5"
                Log.d(TAG,"Result: $comment")
            }*/
        }
    }
    private fun initializeView(){

        convertBtn=findViewById(R.id.convert_button)
        fromDropDownMenu=findViewById(R.id.from_auto_text_view)
        toDropDownMenu=findViewById(R.id.to_edit_text)
        amountEt=findViewById(R.id.amount_edit_text)
        resultEt=findViewById(R.id.result_edit_text)
    }
    private  fun populateDropDownMenue(){
        val listOfCountries= listOf(egyptionPound,americanDollar,AED,GBP)
        //R resource folder
        val adapter=ArrayAdapter(this,R.layout.drop_down_list,listOfCountries)
        toDropDownMenu.setAdapter(adapter)
        fromDropDownMenu.setAdapter(adapter)
    }
}