package fall2020.mobileapp.project1_krimal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.Random;

public class SignedToHex extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

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

    String[] ansArray = new String[4];

    RadioGroup radioGroup;
    RadioButton radioButton;
    Button checkbtn, nextQuestion;
    String bitValue = "";
    String userInput = "";
    TextView questionText;
    Spinner Spinner_for_FirstVal, Spinner_for_SecondVal, Spinner_for_ThirdVal, Spinner_for_ForthVal;
    int totalNumberOfQuestions = 0, score =0;
    TextView ScoreText, resultText;

    ArrayAdapter<CharSequence> adapter_for_SpinnerFirst;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signed_to_hex);

        radioGroup = findViewById(R.id.RadioGroup);
        checkbtn = findViewById(R.id.checkButton);
        questionText = findViewById(R.id.InputText);
        Spinner_for_FirstVal = findViewById(R.id.SpinnerFirst);
        Spinner_for_SecondVal = findViewById(R.id.SpinnerSecond);
        Spinner_for_ThirdVal = findViewById(R.id.SpinnerThird);
        Spinner_for_ForthVal = findViewById(R.id.SpinnerFour);
        nextQuestion = findViewById(R.id.NextButton);
        ScoreText = findViewById(R.id.ScoreTracker);
        resultText = findViewById(R.id.Result);

        bitValue = getIntent().getStringExtra("BIT");



        adapter_for_SpinnerFirst = ArrayAdapter.createFromResource(this, R.array.First_spinner_SignedToHex, android.R.layout.simple_spinner_item);
        adapter_for_SpinnerFirst.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner_for_FirstVal.setAdapter(adapter_for_SpinnerFirst);
        Spinner_for_FirstVal.setOnItemSelectedListener(this);

        ArrayAdapter<CharSequence> adapter_for_bits_SpinnerSecond = ArrayAdapter.createFromResource(this, R.array.Second_spinner_SignedToHex, android.R.layout.simple_spinner_item);
        adapter_for_bits_SpinnerSecond.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner_for_SecondVal.setAdapter(adapter_for_bits_SpinnerSecond);
        Spinner_for_SecondVal.setOnItemSelectedListener(this);

        ArrayAdapter<CharSequence> adapter_for_bits_SpinnerThird = ArrayAdapter.createFromResource(this, R.array.Third_spinner_SignedToHex, android.R.layout.simple_spinner_item);
        adapter_for_bits_SpinnerThird.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner_for_ThirdVal.setAdapter(adapter_for_bits_SpinnerThird);
        Spinner_for_ThirdVal.setOnItemSelectedListener(this);

        ArrayAdapter<CharSequence> adapter_for_bits_SpinnerForth = ArrayAdapter.createFromResource(this, R.array.Forth_spinner_SignedToHex, android.R.layout.simple_spinner_item);
        adapter_for_bits_SpinnerForth.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner_for_ForthVal.setAdapter(adapter_for_bits_SpinnerForth);
        Spinner_for_ForthVal.setOnItemSelectedListener(this);




        ShowNextQuestion();
        checkbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  Toast.makeText(SignedToHex.this, "UserInput: " + string, Toast.LENGTH_LONG).show();
                nextQuestion.setEnabled(true);
                answer();

            }
        });

        nextQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkbtn.setEnabled(true);
                ShowNextQuestion();
            }
        });


    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString().toLowerCase();

        int idForSpinner = parent.getId();

        if(idForSpinner == R.id.SpinnerFirst){
            ansArray[0] = text;

        } else if(idForSpinner == R.id.SpinnerSecond){
            ansArray[1] = text;
            //Toast.makeText(this, "Value2: " + secondSpinnerVal, Toast.LENGTH_LONG).show();

        } else if(idForSpinner == R.id.SpinnerThird){
            ansArray[2] = text;
            //Toast.makeText(this, "Value3: " + thirdSpinnerVal, Toast.LENGTH_LONG).show();

        } else if(idForSpinner == R.id.SpinnerFour){
                ansArray[3] = text;
            //Toast.makeText(this, "Value4: " + forthSpinnerVal, Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


    public void answer(){
        String UserInputThatWasGenerated = questionText.getText().toString();
        int UserInputInInteger = Integer.parseInt(UserInputThatWasGenerated);
        String result = TakeUserInput(UserInputInInteger);

        int radioId = radioGroup.getCheckedRadioButtonId();
        String UserClick = CheckUserClick(radioId);
       // Toast.makeText(this, "Value: " + UserClick, Toast.LENGTH_LONG).show();
       // Toast.makeText(this, "Computed Result: " + result, Toast.LENGTH_LONG).show();


        if(result.contains(UserClick)){
            score++;
            totalNumberOfQuestions++;
            resultText.setText("Result: " + result);
            ScoreText.setText("Score: " + score + "/" + totalNumberOfQuestions);
        } else{
            totalNumberOfQuestions ++;
            ScoreText.setText("Score: " + score + "/" + totalNumberOfQuestions);
            resultText.setText("Result: " + result);
            checkbtn.setEnabled(false);
        }
      //  Toast.makeText(this, "Computed Result: " + result, Toast.LENGTH_LONG).show();


    }

    public String TakeUserInput(int value){
        String Hexstring = "";

        if(bitValue.equals("8 bit")){
            Hexstring = compute8(value);
            if(!(ansArray[0].equals("0"))) {
                userInput = ansArray[0] + ansArray[1];
            } else{
                userInput = ansArray[1];
            }

        } else if(bitValue.equals( "10 bit")){
            Hexstring = compute10(value);
            if(!(ansArray[0].equals("0") && ansArray[1].equals("0"))) {
                userInput = ansArray[0] + ansArray[1] + ansArray[2];
            } else{
                userInput = ansArray[2];
            }

        } else if(bitValue.equals("12 bit")){
            Hexstring = compute12(value);
            userInput = ansArray[0] + ansArray[1] + ansArray[2] + ansArray[3];
        }

        return Hexstring;

    }




    public int randomVal_8bit(){
        int randNum = 0;
        randNum = rand.nextInt(274)-137;

        return randNum;
       // return Integer.toHexString(randNum);

    }

    public int randomVal_10bit(){
        int randNum = 0;
        randNum = rand.nextInt(1100)-550;

        return randNum;
        //return  Integer.toHexString(randNum);

    }

    public int randomVal_12bit(){
        int randNum = 0;
        randNum = rand.nextInt(4400)-2200;

        return randNum;
       // return Integer.toHexString(randNum);


    }

    public void ShowNextQuestion(){
        int randomVal = 0;
        String val = "0";

        nextQuestion.setEnabled(false);
        if(bitValue.equals("8 bit")){
            randomVal = randomVal_8bit();
            val = Integer.toString(randomVal);
            questionText.setText(val);
            Spinner_for_ForthVal.setEnabled(false);
            Spinner_for_ThirdVal.setEnabled(false);

        } else if(bitValue.equals( "10 bit")){
            randomVal = randomVal_10bit();
            val = Integer.toString(randomVal);
            questionText.setText( val);
            Spinner_for_ForthVal.setEnabled(false);

        } else if(bitValue.equals("12 bit")){
            randomVal = randomVal_12bit();
            Spinner_for_ForthVal.setEnabled(false);
            val = Integer.toString(randomVal);
            questionText.setText(val);


        }

    }

    private String compute8(int value) {
        String s;
        if (value > 0) {
            if (value > Umax8) {
                s = "8 bit: too large signed and unsigned";
            } else {
                if (value > Tmax8) {
                    s = "8 bit:  unsigned= 0x" + Integer.toHexString(value) +
                            "  signed - too large";
                } else {
                    s = "8 bit:  unsigned, signed = 0x" + Integer.toHexString(value);
                }
            }
        } else { // negative
            if (value < Tmin8) {
                s = "8 bit: too small signed and unsigned";
            } else {
                s = "8 bit:  unsigned too small, signed= 0x" + Integer.toHexString(value).substring(6) ;
            }
        }
        return s;
    }


    private String compute10(int value) {
        String s;
        if (value > 0) {
            if (value > Umax10) {
                s = "10 bit: too large signed and unsigned";
            } else {
                if (value > Tmax10) {
                    s = "10 bit:  unsigned= 0x" + Integer.toHexString(value) +
                            " signed - too large";
                } else {
                    s = "10 bit:  unsigned, signed = 0x" + Integer.toHexString(value);
                }
            }
        } else {  //negative #
            if (value < Tmin10) {
                s = "10 bit: too small signed and unsigned";
            } else {
                String fs = Integer.toHexString(value).substring(5);
                if (fs.startsWith("f"))
                    fs = "3" + Integer.toHexString(value).substring(6);
                else if (fs.startsWith("e"))
                    fs = "2" + Integer.toHexString(value).substring(6);

                s = "10 bit:  unsigned too small, signed= 0x" + fs ;
            }
        }
        return s;
    }

    private String compute12(int value) {
        String s;
        if (value > 0) {
            if (value > Umax12) {
                s = "12 bit: too large signed and unsigned";
            } else {
                if (value > Tmax12) {
                    s = "12 bit:  unsigned= 0x" + Integer.toHexString(value) +
                            "  signed - too large";
                } else {
                    s = "12 bit:  unsigned, signed = 0x" + Integer.toHexString(value);
                }
            }
        } else {  //negative #
            if (value < Tmin12) {
                s = "12 bit: too small signed and unsigned";
            } else {
                s = "12 bit:  unsigned too small, signed= 0x" + Integer.toHexString(value).substring(5) ;
            }
        }
        return s;
    }

    public String CheckUserClick(int ID){
        String USERINPUT = "";

       switch(ID){
           case R.id.TooLarge:
               USERINPUT = "too Large";
               break;
           case R.id.TooSmall:
               USERINPUT = "too small signed";
               break;
           case R.id.value:
               USERINPUT = userInput;
               break;

       }

        return USERINPUT;
    }

    public void onBackPressed(){
        Intent intent = new Intent(this, MainActivity.class);

        intent.putExtra("SCORE", ScoreText.getText().toString());

        if(!ScoreText.getText().toString().equals("Score: 0/0"))
            setResult(RESULT_OK, intent);
        finish();
    }



}
