package fall2020.mobileapp.project1_krimal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class HexToDecimal extends AppCompatActivity {

    Random rand = new Random();
    static final int Umax8 = 255;
    static final int Umax10 = 1023;
    static final int Umax12 = 4095;
    static final int Tmax8 = 127;
    static final int Tmax10 = 511;
    static final int Tmax12 = 2047;
    static final int Tmin8 = -128;
    static final int Tmin10 = -512;
    static final int Tmin12 = -4096;



    TextView QuestionText, score, ansForUnsigned, ansForSigned;
    EditText unsigned_val, signed_val;
    Button checkAnswer;
    Button next_button;
    String bit_value ="";
    //private static  String RandomTracker = "";
    private int score_track =0;
    private int numOfQuestions =0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hex_to_decimal);
        QuestionText = findViewById(R.id.InsertText);

        score = findViewById(R.id.ScoreTracker);
        unsigned_val = findViewById(R.id.Unsigned_enter);
        signed_val = findViewById(R.id.Signed_enter);
        checkAnswer = findViewById(R.id.CheckButton);
        ansForUnsigned = findViewById(R.id.textView_forUnsignedAnswer);
        ansForSigned = findViewById(R.id.textView_forSignedAnswer);
        next_button = findViewById(R.id.button4);

        bit_value = getIntent().getStringExtra("BIT");


        showNextQuestion();
        checkAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(TextUtils.isEmpty(unsigned_val.getText().toString()) || TextUtils.isEmpty(signed_val.getText().toString())){

                    Toast.makeText(HexToDecimal.this, "Please enter something", Toast.LENGTH_LONG).show();
                }else{

                    CheckAnswer();

                }
            }
        });

        next_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showNextQuestion();
            }
        });


    }


    public void showNextQuestion(){

        if(bit_value.equals("8 bit")){
           // numOfQuestions++;
            QuestionText.setText(randomVal_8bit());
            score.setText("Score: " + score_track + "/" + numOfQuestions);
            unsigned_val.getText().clear();
            signed_val.getText().clear();
            checkAnswer.setEnabled(true);
            ansForUnsigned.setText("Unsigned value: " + "");
            ansForSigned.setText("Signed value: " + "");


        } else if(bit_value.equals("10 bit")){
            QuestionText.setText(randomVal_10bit());
           // numOfQuestions++;
            score.setText("Score: " + score_track + "/" + numOfQuestions);
            unsigned_val.getText().clear();
            signed_val.getText().clear();
            checkAnswer.setEnabled(true);
            ansForUnsigned.setText("Unsigned value: " + "");
            ansForSigned.setText("Signed value: " + "");

        }
        else if(bit_value.equals("12 bit")) {
            QuestionText.setText(randomVal_12bit());
           // numOfQuestions++;
            score.setText("Score: " + score_track + "/" + numOfQuestions);
            unsigned_val.getText().clear();
            signed_val.getText().clear();
            checkAnswer.setEnabled(true);
            ansForUnsigned.setText("Unsigned value: " + "");
            ansForSigned.setText("Signed value: " + "");
        }

    }

    public void CheckAnswer(){
        numOfQuestions++;
       // String value_bit8 = randomVal_8bit();
        String qt = QuestionText.getText().toString();

        int rightAnswer_unsigned = UnSignedConversion_bit(qt);
        //int rightAnswer_signed = SignedConversion_bit(qt);
        String rightSigned_Answer = Signed_conversion_locate(qt);
        int rightAnswer_signed = Integer.parseInt(rightSigned_Answer);


       int getEditText_unsignedVal = Integer.parseInt(unsigned_val.getText().toString());
      int getEditText_signedVal = Integer.parseInt(signed_val.getText().toString());


      if(getEditText_unsignedVal == rightAnswer_unsigned && getEditText_signedVal == rightAnswer_signed){
            score_track ++;
          ansForUnsigned.setText("Unsigned value: " + rightAnswer_unsigned);
          ansForSigned.setText("Signed value: " + rightAnswer_signed);
          score.setText("Score: " + score_track + "/" + numOfQuestions);
          signed_val.getText().clear();
          unsigned_val.getText().clear();

        } else{
          ansForUnsigned.setText("Unsigned value: " + rightAnswer_unsigned);
          ansForSigned.setText("Signed value: " + rightAnswer_signed);
          score.setText("Score: " + score_track + "/" + numOfQuestions);
          signed_val.getText().clear();
          unsigned_val.getText().clear();


        }
        checkAnswer.setEnabled(false);


    }



    public String randomVal_8bit(){
        int randNum = 0;
        randNum = rand.nextInt(Umax8)+1;

        return Integer.toHexString(randNum);

    }

    public String randomVal_10bit(){
        int randNum = 0;
        randNum = rand.nextInt(Umax10)+1;

        return  Integer.toHexString(randNum);

    }

    public String randomVal_12bit(){
        int randNum = 0;
        randNum = rand.nextInt(Umax12)+1;

        return Integer.toHexString(randNum);


    }

    public int UnSignedConversion_bit(String string){

        int value = Integer.parseInt(string,16);
        return value;
    }



    private String Signed_conversion_locate(String string){
        String hexValue = "";
        int convertToInt = Integer.parseInt(string, 16);
        switch (bit_value){
            case "8 bit":
              hexValue =  hex8_Signed(convertToInt);
                break;
            case "10 bit":
              hexValue =  hex10_Signed(convertToInt);
                break;
            case "12 bit":
               hexValue = hex12_Signed(convertToInt);
            default:

        }

        return hexValue;
    }

   public void onBackPressed(){
        Intent intent = new Intent(this, MainActivity.class);

            intent.putExtra("SCORE", score.getText().toString());

        if(!score.getText().toString().equals("Score: 0/0"))
            setResult(RESULT_OK, intent);
        finish();
    }


    private String hex8_Signed(int value) {
        String s;
        if (value > Umax8) s = "8 bit: unsigned, signed too large";
        else if (value > Tmax8) s =  "" + (value - (Umax8+1));
        else s = "" + value;

        return s;
    }

    private String hex10_Signed(int value) {
        String s;
        if (value > Umax10) s = "10 bit: unsigned, signed too large";
        else if (value > Tmax10) s = "" + (value - (Umax10+1));
        else s = "" + value;

        return s;
    }

    private String hex12_Signed(int value) {
        String s;
        if (value > Umax12) s = "12 bit: unsigned, signed too large";
        else if (value > Tmax12) s = "" + (value - (Umax12+1));
        else s = "" + value;

        return s;
    }





}
