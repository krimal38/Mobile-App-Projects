package fall2020.mobileapp.project1_krimal;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Spinner Spinner_for_text, Spinner_for_conversion;
    Button button;
    String choice_bit = "";
    String score = "";
    TextView score_tracker, QuizScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Spinner_for_text = findViewById(R.id.SpinnerText);
        Spinner_for_conversion = findViewById(R.id.SpinnerBitValue);
        button = findViewById(R.id.button);
        score_tracker = findViewById(R.id.ScoreTracker);
        QuizScore = findViewById(R.id.QuizScore);

        ArrayAdapter<CharSequence> adapter_for_text = ArrayAdapter.createFromResource(this, R.array.conversion_list, android.R.layout.simple_spinner_item);
        adapter_for_text.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner_for_text.setAdapter(adapter_for_text);
        Spinner_for_text.setOnItemSelectedListener(this);

        ArrayAdapter<CharSequence> adapter_for_bits_conversion = ArrayAdapter.createFromResource(this, R.array.bit_conversion, android.R.layout.simple_spinner_item);
        adapter_for_bits_conversion.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner_for_conversion.setAdapter(adapter_for_bits_conversion);
        Spinner_for_conversion.setOnItemSelectedListener(this);


    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();


        if (text.equals("8 bit") || text.equals("10 bit") || text.equals("12 bit")) {
            choice_bit = text;
        }

        //   Toast.makeText(parent.getContext(), choice_bit, Toast.LENGTH_LONG).show();

        if (text.equals("Practise Hex to Decimal (Signed and Unsigned)")) {
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    function_to_go_HexToDec();

                }
            });

        } else if (text.equals("Quiz me!")) {
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    function_to_quizActivity();
                }
            });
        } else if (text.equals("Practise Signed Decimal to Hex")) {
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    function_to_SignedToHex();
                }
            });
        } else if (text.equals("Practise Unsigned Decimal to Hex")) {
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    function_to_UnsignedHexToDec();
                }
            });

        }
    }
    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    // Intent for HexToDecimal activity
    public void function_to_go_HexToDec() {
        Intent intent = new Intent(this, HexToDecimal.class);
        intent.putExtra("BIT", choice_bit);
       // startActivity(intent);
        startActivityForResult(intent, 999);
    }

    // Intent for quiz activity
    public void function_to_quizActivity() {
        Intent intent = new Intent(this, QuizActivity.class);
        intent.putExtra("BIT", choice_bit);
        startActivityForResult(intent, 99);
    }

    public void function_to_UnsignedHexToDec(){
        Intent intent = new Intent(this, Unsigned_DecimalToHex.class);
        intent.putExtra("BIT", choice_bit);
        startActivityForResult(intent, 999);
    }

    //Intent for signedToHex activity
    public void function_to_SignedToHex(){
        Intent intent = new Intent(this, SignedToHex.class);
        intent.putExtra("BIT", choice_bit);
        startActivityForResult(intent, 999);
    }

    @SuppressLint("MissingSuperCall")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 999 && resultCode == RESULT_OK) {
            score_tracker.setText(data.getStringExtra("SCORE"));
        } else if(requestCode == 99 && resultCode == RESULT_OK){
            QuizScore.setText(data.getStringExtra("QUIZ_SCORE"));
        }
    }



}
