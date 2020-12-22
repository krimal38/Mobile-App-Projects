package fall2020.mobileapp.lab7_krimal

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    var excellent_tip = 20
    var average_tip = 18
    var bad_tip = 14
    var button: Button? = null
    var Lab1Button: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button =findViewById<View>(R.id.secondActivityButton) as Button
        Lab1Button = findViewById<View>(R.id.Implicit_call) as Button


        button!!.setOnClickListener { openNewActivity() }

        Lab1Button!!.setOnClickListener { Lab1Activity() }
    }


    fun Lab1Activity() {
        val intent = Intent()
        intent.action = "android.intent.action.VIEW"
        intent.addCategory("android.intent.category.DEFAULT")
        startActivity(intent)
    }

    fun openNewActivity() {
        val intent = Intent(this, NewActivity::class.java)
        intent.putExtra("NUMBER1", excellent_tip)
        intent.putExtra("NUMBER2", average_tip)
        intent.putExtra("NUMBER3", bad_tip)
        startActivityForResult(intent, 999)
    }

    fun onRadioButtonClicked(view: View) {
        val checked = (view as RadioButton).isChecked
        val bill: Float
        when (view.getId()) {
            R.id.excellent_button -> if (checked) {
                val b = findViewById<View>(R.id.bill) as EditText
                bill = if (b.text.toString() == "") 0f else b.text.toString().toFloat()
                compute_tip(bill, excellent_tip)
            }
            R.id.average_button -> if (checked) {
                val b = findViewById<View>(R.id.bill) as EditText
                bill = if (b.text.toString() == "") 0f else b.text.toString().toFloat()
                compute_tip(bill, average_tip)
            }
            R.id.lacking_button -> if (checked) {
                val b = findViewById<View>(R.id.bill) as EditText
                bill = if (b.text.toString() == "") 0f else b.text.toString().toFloat()
                compute_tip(bill, bad_tip)
            }
        }
    }
    fun roundToTwoDigit(paramFloat: Float): String? {
        return String.format("%.2f%n", paramFloat)
    }

    fun compute_tip(bill: Float, percent: Int) {
        val pct = percent.toFloat() / 100
        val tip = bill * pct
        val total = bill + tip
        var t = findViewById<View>(R.id.computed_tip) as TextView
        var s = roundToTwoDigit(tip)
        t.text = s
        t = findViewById<View>(R.id.bill_total) as TextView
        s = roundToTwoDigit(total)
        t.text = s
    }

    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?
    ) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 999 && resultCode == Activity.RESULT_OK) {
            if(data != null) {
                excellent_tip = data.getIntExtra("NUMBER1", excellent_tip)
                average_tip = data.getIntExtra("NUMBER2", average_tip)
                bad_tip = data.getIntExtra("NUMBER3", bad_tip)
            }
        }
    }

}
