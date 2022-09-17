package com.example.currencyconverter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import kotlin.math.log

class MainActivity : AppCompatActivity() {
    val TAG="MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val convertBtn:Button=findViewById(R.id.convert_button)
        val amountEt:TextInputEditText=findViewById(R.id.amount_edit_text)
        val resultEt:TextInputEditText=findViewById(R.id.result_edit_text)



        convertBtn.setOnClickListener(){
            val amount=amountEt.text.toString().toDouble()
            val result=amount.times(15.73)
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
}