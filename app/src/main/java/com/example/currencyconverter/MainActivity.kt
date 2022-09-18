package com.example.currencyconverter

import android.content.Intent
import android.content.IntentSender
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.*
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.viewmodel.CreationExtras
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import java.math.BigDecimal
import java.math.RoundingMode

class MainActivity : AppCompatActivity() {
    val TAG="MainActivity"
    private val egyptionPound="Egyptian Pound"
    private val americanDollar="American Dollar"
    private val AED="AED"
    private val GBP="GBP"
    val values= mapOf(
        americanDollar to 1.0,
        egyptionPound  to 19.26,
        AED            to 3.67,
        GBP            to 0.87,
    )


    lateinit var fromDropDownMenu:AutoCompleteTextView
    private lateinit var toDropDownMenu: AutoCompleteTextView
    lateinit var convertBtn:Button
    lateinit var amountEt:TextInputEditText
    lateinit var resultEt:TextInputEditText
    lateinit var toolBar:Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initializeView()
        populateDropDownMenue()
        toolBar.inflateMenu(R.menu.options_menu)
        toolBar.setOnMenuItemClickListener(){
                menuItem->when(menuItem.itemId) {
            R.id.share_action ->
            {
                val message="${amountEt.text.toString()} ${fromDropDownMenu.text.toString()} is equal to ${resultEt.text.toString()} ${toDropDownMenu.text.toString()}"
                //Toast.makeText(this, "sharing......", Toast.LENGTH_SHORT).show()
                val shareIntent=Intent(Intent.ACTION_SEND)
                shareIntent.type="text/plain"
                shareIntent.putExtra(Intent.EXTRA_TEXT,"$message")
                if (shareIntent.resolveActivity(packageManager)!=null){
                    startActivity(shareIntent)
                }else{
                    Toast.makeText(this, "$amountEt ", Toast.LENGTH_SHORT).show()
                }



            }
            R.id.setting_action -> Toast.makeText(this, "setting......", Toast.LENGTH_SHORT).show()
            R.id.contact_action -> Toast.makeText(this, "contact......", Toast.LENGTH_SHORT).show()

        }
            true


        }
        amountEt.addTextChangedListener{
            getResult()
        }
        fromDropDownMenu.setOnItemClickListener { adapter, view, position, id ->
            getResult()
        }
        toDropDownMenu.setOnItemClickListener { adapter, view, position, id ->
            getResult()
        }


        convertBtn.setOnClickListener(){
            getResult()

        }
    }
    private fun getResult(){

        if (amountEt.text.toString().isNotEmpty()){
            val amount=amountEt.text.toString().toDouble()
            val currencyToField=values[toDropDownMenu.text.toString()]
            val currencyFromField=values[fromDropDownMenu.text.toString()]
            Log.d(TAG,"TO $currencyToField")

            val converted=amount.times(currencyToField!!.div(currencyFromField!!))
            var result:Double= String.format("%.2f",converted).toDouble()
            resultEt.setText(result.toString())

        }else{
            amountEt.setError("amount can't be empty")
        }

    }
    private fun initializeView(){

        convertBtn=findViewById(R.id.convert_button)
        fromDropDownMenu=findViewById(R.id.from_auto_text_view)
        toDropDownMenu=findViewById(R.id.to_edit_text)
        amountEt=findViewById(R.id.amount_edit_text)
        resultEt=findViewById(R.id.result_edit_text)
        toolBar=findViewById(R.id.tool_bar)
    }
    private  fun populateDropDownMenue(){
        val listOfCountries= listOf(egyptionPound,americanDollar,AED,GBP)
        //R resource folder
        val adapter=ArrayAdapter(this,R.layout.drop_down_list,listOfCountries)
        toDropDownMenu.setAdapter(adapter)
        fromDropDownMenu.setAdapter(adapter)
    }

    /*
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.options_menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.share_action -> Toast.makeText(this, "sharing......", Toast.LENGTH_SHORT).show()
            R.id.setting_action -> Toast.makeText(this, "setting......", Toast.LENGTH_SHORT).show()
            R.id.contact_action -> Toast.makeText(this, "contact......", Toast.LENGTH_SHORT).show()

        }
        return super.onOptionsItemSelected(item)
    }
    */




}


// val someVla=values.get(egyptionPound)


/*Toast.makeText(this,"amount can't be empty",Toast.LENGTH_SHORT).show()
val snackbar=Snackbar.make(fromDropDownMenu,"amount can't be empty",Toast.LENGTH_SHORT)
snackbar.show()
snackbar.setAction("OK"){
    Toast.makeText(this,"action pressed",Toast.LENGTH_SHORT).show()

}
*/




/*    result= when(currencyToField){
         AED->BigDecimal.valueOf(amount.times(3.67)).setScale(2,RoundingMode.DOWN).toDouble()
         egyptionPound->BigDecimal.valueOf(amount.times(19.26)).setScale(2,RoundingMode.DOWN).toDouble()
         GBP->BigDecimal.valueOf(amount.times(0.87)).setScale(2,RoundingMode.DOWN).toDouble()
        else->BigDecimal.valueOf(amount.times(1)).setScale(2,RoundingMode.DOWN).toDouble()
     } */
//   Toast.makeText(this,"Button clicked",Toast.LENGTH_SHORT).show()
/*  val x=12
    if(x>5){
        val comment="x is more than 5"
        Log.d(TAG, "Result : $comment")
    }else if(x<5){
        val comment="x is less than 5"
        Log.d(TAG,"Result: $comment")
    }*/