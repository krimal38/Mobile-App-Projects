package fall2020.mobileapp.lab7_krimal

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class NewActivity : AppCompatActivity() {


    var Excellent_editText: EditText? = null
    var Average_editText: EditText? = null
    var Lacking_editText: EditText? = null
    var UpdateButton: Button? = null
    var excellent = 0
    var average = 0
    var bad = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new)


        Excellent_editText = findViewById(R.id.Excellent)
        Average_editText = findViewById(R.id.Average)
        Lacking_editText = findViewById(R.id.Lacking)
        UpdateButton = findViewById(R.id.UpdateButton)

        val intent = intent


        excellent = intent.getIntExtra("NUMBER1", 0)
        average = intent.getIntExtra("NUMBER2", 0)
        bad = intent.getIntExtra("NUMBER3", 0)
        UpdateButton!!.setOnClickListener(View.OnClickListener { openActivityForUpdate() })
    }


    fun openActivityForUpdate() {
        val intent = Intent(this@NewActivity, MainActivity::class.java)
        if (!TextUtils.isEmpty(Excellent_editText!!.text.toString())) {
            intent.putExtra("NUMBER1", Excellent_editText!!.text.toString().toInt())
            excellent = Excellent_editText!!.text.toString().toInt()
        } else {
            intent.putExtra("NUMBER1", excellent)
        }
        if (!TextUtils.isEmpty(Average_editText!!.text.toString())) {
            intent.putExtra("NUMBER2", Average_editText!!.text.toString().toInt())
            average = Average_editText!!.text.toString().toInt()
        } else {
            intent.putExtra("NUMBER2", average)
        }
        if (!TextUtils.isEmpty(Lacking_editText!!.text.toString())) {
            intent.putExtra("NUMBER3", Lacking_editText!!.text.toString().toInt())
            bad = Lacking_editText!!.text.toString().toInt()
        } else {
            intent.putExtra("NUMBER3", bad)
        }
        setResult(Activity.RESULT_OK, intent)
        finish()
    }
}
