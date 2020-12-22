package fall2020.mobileapp.lab2_krimal;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int excellent_tip = 20;
    int average_tip = 18;
    int bad_tip = 14;
    Button button;
    Button Lab1Button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*Intent intent = getIntent();

        excellent_tip = intent.getIntExtra("NUMBER1", excellent_tip);
        average_tip= intent.getIntExtra("NUMBER2", average_tip);
        bad_tip = intent.getIntExtra("NUMBER3", bad_tip);*/


        button = (Button) findViewById(R.id.secondActivityButton);
        Lab1Button = (Button) findViewById(R.id.Implicit_call);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openNewActivity();

            }
        });

        Lab1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Lab1Activity();
            }
        });

    }

    public void Lab1Activity(){
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        startActivity(intent);
    }

    public void openNewActivity(){
        Intent intent = new Intent(this, NewActivity.class);
        intent.putExtra("NUMBER1", excellent_tip);
        intent.putExtra("NUMBER2", average_tip);
        intent.putExtra("NUMBER3", bad_tip );
        startActivityForResult(intent, 999);
    }


    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        float bill;

        // Check which radio button was clicked
        switch (view.getId()) {
            case R.id.excellent_button:
                if (checked) {
                    EditText b = (EditText)findViewById(R.id.bill);
                    if (b.getText().toString().equals(""))
                        bill = 0;
                    else bill = Float.parseFloat(b.getText().toString());
                    compute_tip(bill, excellent_tip);
                }
                break;
            case R.id.average_button:
                if (checked) {
                    EditText b = (EditText)findViewById(R.id.bill);
                    if (b.getText().toString().equals(""))
                        bill = 0;
                    else bill = Float.parseFloat(b.getText().toString());
                    compute_tip(bill, average_tip);
                }
                break;
            case R.id.lacking_button:
                if (checked) {
                    EditText b = (EditText)findViewById(R.id.bill);
                    if (b.getText().toString().equals(""))
                        bill = 0;
                    else bill = Float.parseFloat(b.getText().toString());
                    compute_tip(bill, bad_tip);
                }

                break;
        }
    }
    public static String roundToTwoDigit(float paramFloat) {
        return String.format("%.2f%n", paramFloat);
    }
    void compute_tip(float bill, int percent) {
        float pct= (float)percent/100;
        float tip = bill * pct;
        float total = bill + tip;
        TextView t = (TextView)findViewById(R.id.computed_tip);
        String s = roundToTwoDigit(tip);
        t.setText(s);
        t = (TextView)findViewById(R.id.bill_total);
        s = roundToTwoDigit(total);
        t.setText(s);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 999 && resultCode == RESULT_OK){
            excellent_tip = data.getIntExtra("NUMBER1", excellent_tip);
            average_tip= data.getIntExtra("NUMBER2", average_tip);
            bad_tip = data.getIntExtra("NUMBER3", bad_tip);
        }

    }
}
