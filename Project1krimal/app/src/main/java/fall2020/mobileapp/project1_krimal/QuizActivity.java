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
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;


public class QuizActivity extends AppCompatActivity {

    static final int Umax8 = 255;
    static final int Umax10 = 1023;
    static final int Umax12 = 4095;
    static final int Tmax8 = 127;
    static final int Tmax10 = 511;
    static final int Tmax12 = 2047;
    static final int Tmin8 = -128;
    static final int Tmin10 = -512;
    static final int Tmin12 = -4096;


    Random rand = new Random();

    EditText UserAnswer;
    Button NextButton;
    TextView Question, ScoreText;
    List<String> listOfQuestions = new ArrayList<>();
    List<String> QuestionIncludingDescription = new ArrayList<>();
    List<String> AnswerList = new ArrayList<>();
    List<Integer> randomOrder = new ArrayList<>(Arrays.asList(0,1,2,3,4,5,6,7,8,9));
    List<String> lastAnswerList = new ArrayList<>();
    String bitValue = "";
    int count =1, nextQuestion =0, correctAns =0;
    int nextCounter =0, QuestionCount =1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        UserAnswer = findViewById(R.id.InputText);
        Question = findViewById(R.id.TEXT);
        NextButton = findViewById(R.id.NextButton);
        ScoreText = findViewById(R.id.ScoreTracker);


        bitValue = getIntent().getStringExtra("BIT");


        Collections.shuffle(randomOrder);
        generatingQuestions();
        for(int i=0;i<10;i++) {
            UploadQuestion(randomOrder.get(i));
        }
        //nextQuestion();
        lastAnswerList.remove(lastAnswerList.size()-1);
        Question.setText(QuestionIncludingDescription.get(count-1));
        NextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextQuestion();
            }
        });

        System.out.println("MODIFICATION " + lastAnswerList);

    }

    private void nextQuestion(){

        String UserInput = UserAnswer.getText().toString().toLowerCase();
        String correctResult = lastAnswerList.get(nextCounter);
        if(count <= 10){

                if (!(TextUtils.isEmpty(UserAnswer.getText().toString()))) {
                    if (UserInput.equals(correctResult)) {
                        nextCounter++;
                        correctAns++;
                        nextQuestion++;
                        ScoreText.setText("Quiz Score: " + correctAns + "/" + nextQuestion);
                        UserAnswer.getText().clear();
                    } else {
                        nextCounter++;
                        nextQuestion++;
                        ScoreText.setText("Quiz Score: " + correctAns + "/" + nextQuestion);
                        UserAnswer.getText().clear();
                    }
                    count ++;




                    if(count<=10) {
                        Question.setText(QuestionIncludingDescription.get(count-1));
                        if(count==10) {
                            NextButton.setText("Submit");
                            UserAnswer.getText().clear();

                        }
                    }
                    else {
                            NextButton.setEnabled(false);
                        }



                } else {
                    Toast.makeText(QuizActivity.this, "Please enter something!", Toast.LENGTH_LONG).show();
                }


            }

           /* if(count == 10){

                if(UserInput.equals(correctResult)) {
                    correctAns++;
                    NextButton.setText("Submit");
                    ScoreText.setText("Score: " + correctAns + "/" + nextQuestion);
                }else {
                    NextButton.setText("Submit");

                    ScoreText.setText("Score: " + correctAns + "/" + nextQuestion);
                }
                NextButton.setEnabled(false);
            }*/

        }




    public String randomVal_8bit_HexToDecimal(){
        int randNum = 0;
        randNum = rand.nextInt(Umax8);

        return Integer.toHexString(randNum);

    }

    public String randomVal_10bit_HexToDecimal(){
        int randNum = 0;
        randNum = rand.nextInt(Umax10);

        return  Integer.toHexString(randNum);

    }

    public String randomVal_12bit_HexToDecimal(){
        int randNum = 0;
        randNum = rand.nextInt(Umax12);

        return Integer.toHexString(randNum);

    }

    public String randomVal_8bit_signed(){
        int randNum = 0;
        randNum = rand.nextInt(254)-127;

        return Integer.toString(randNum);
        // return Integer.toHexString(randNum);

    }

    public String randomVal_10bit_signed(){
        int randNum = 0;
        randNum = rand.nextInt(1022)-511;

        return Integer.toString(randNum);

        //return  Integer.toHexString(randNum);

    }

    public String randomVal_12bit_signed(){
        int randNum = 0;
        randNum = rand.nextInt(4094)-2047;

        return Integer.toString(randNum);
        // return Integer.toHexString(randNum);
    }

    public String randomVal_8_unsigned(){
        int randNum = 0;
        randNum = rand.nextInt(Tmax8);

        return Integer.toString(randNum);
    }
    public String randomVal_10_unsigned(){
        int randNum = 0;
        randNum = rand.nextInt(Tmax8);

        return Integer.toString(randNum);
    }

    public String randomVal_12_unsigned(){
        int randNum = 0;
        randNum = rand.nextInt(Tmax8);

        return Integer.toString(randNum);
    }

    private void generatingQuestions(){
        int first=0, second=0, third =0;

        // have to modify string add functions with signed and unsigned
        if(first < 4){ // this is for HexToDecimal
            if(bitValue.equals( "8 bit")) {

                String str = randomVal_8bit_HexToDecimal();
                int value1 = Integer.parseInt(str, 16);
                String add =  hex8_Signed(value1);
                listOfQuestions.add(str);
                AnswerList.add(add);
                first++;

                String str1 = randomVal_8bit_HexToDecimal();
                int value2 = Integer.parseInt(str1, 16);
                String add1 = hex8_Unsigned(value2);
                listOfQuestions.add(str1);
                AnswerList.add(add1);
                first++;

                String str2 = randomVal_8bit_HexToDecimal();
                int value3 = Integer.parseInt(str2, 16);
                String add2 = hex8_Signed(value3);
                listOfQuestions.add(str2);
                AnswerList.add(add2);
                first++;

                String str3 = randomVal_8bit_HexToDecimal();
                int value4 = Integer.parseInt(str3, 16);
                String add3 = hex8_Unsigned(value4);
                listOfQuestions.add(str3);
                AnswerList.add(add3);
                first++;

            } else if(bitValue.equals("10 bit")){

                String str1 = randomVal_10bit_HexToDecimal();
                int value = Integer.parseInt(str1, 16);
                String add = hex10_Signed(value);
                listOfQuestions.add(str1);
                AnswerList.add(add);
                first++;

                String str2 = randomVal_10bit_HexToDecimal();
                int value1 = Integer.parseInt(str2, 16);
                String add1 = hex10_Unsigned(value1);
                listOfQuestions.add(str2);
                AnswerList.add(add1);
                first++;

                String str3 = randomVal_10bit_HexToDecimal();
                int value2 = Integer.parseInt(str3, 16);
                String add2 = hex10_Signed(value2);
                listOfQuestions.add(str3);
                AnswerList.add(add2);
                first++;

                String str4 = randomVal_10bit_HexToDecimal();
                int value3 = Integer.parseInt(str4, 16);
                String add3 = hex10_Unsigned(value3);
                listOfQuestions.add(str4);
                AnswerList.add(add3);
                first++;

            } else if (bitValue.equals("12 bit")){

                String str =randomVal_12bit_HexToDecimal();
                int value = Integer.parseInt(str, 16);
                String add = hex12_Signed(value);
                listOfQuestions.add(str);
                AnswerList.add(add);
                first++;

                String str1 = randomVal_12bit_HexToDecimal();
                int value1 = Integer.parseInt(str1, 16);
                String add1 = hex12_Unsigned(value1);
                listOfQuestions.add(str1);
                AnswerList.add(add1);
                first++;

                String str2 = randomVal_12bit_HexToDecimal();
                int value2 = Integer.parseInt(str2, 16);
                String add2 = hex12_Signed(value2);
                listOfQuestions.add(str2);
                AnswerList.add(add2);
                first++;

                String str3 = randomVal_12bit_HexToDecimal();
                int value3 = Integer.parseInt(str3, 16);
                String add3 = hex12_Unsigned(value3);
                listOfQuestions.add(str3);
                AnswerList.add(add3);
                first ++;
            }
        }
        if(second < 3){ // this is for SignedToHex
            if(bitValue.equals("8 bit")) {

                String str =randomVal_8bit_signed();
                int value = Integer.parseInt(str);
                String add = compute8(value);
                listOfQuestions.add(str);
                AnswerList.add(add);
                second++;

                String str1 = randomVal_8bit_signed();
                int value1 = Integer.parseInt(str1);
                String add1 = compute8(value1);
                listOfQuestions.add(str1);
                AnswerList.add(add1);
                second++;

                String str2 = randomVal_8bit_signed();
                int value2 = Integer.parseInt(str2);
                String add2 = compute8(value2);
                listOfQuestions.add(str2);
                AnswerList.add(add2);
                second++;

            } else if(bitValue.equals("10 bit")){

                String str = randomVal_10bit_signed();
                int value = Integer.parseInt(str);
                String add = compute10(value);
                listOfQuestions.add(str);
                AnswerList.add(add);
                second ++;

                String str1 = randomVal_10bit_signed();
                int value1 = Integer.parseInt(str1);
                String add1 = compute10(value1);
                listOfQuestions.add(str1);
                AnswerList.add(add1);
                second ++;

                String str2 = randomVal_10bit_signed();
                int value2 = Integer.parseInt(str1);
                String add2 = compute10(value2);
                listOfQuestions.add(str2);
                AnswerList.add(add2);
                second ++;

            } else if(bitValue.equals("12 bit")){

                String str = randomVal_12bit_signed();
                int value = Integer.parseInt(str);
                String add = compute12(value);
                listOfQuestions.add(str);
                AnswerList.add(add);
                second ++;

                String str1 = randomVal_12bit_signed();
                int value1 = Integer.parseInt(str1);
                String add1 = compute12(value1);
                listOfQuestions.add(str1);
                AnswerList.add(add1);
                second ++;

                String str2 = randomVal_12bit_signed();
                int value2 = Integer.parseInt(str2);
                String add2 = compute12(value2);
                listOfQuestions.add(str2);
                AnswerList.add(add2);
                second ++;
            }
        }

        if(third < 3){ // this is for UnsignedDecimalToHex
            if(bitValue.equals("8 bit")) {

                String str = randomVal_8_unsigned();
                int value = Integer.parseInt(str);
                String add = compute8(value);
                listOfQuestions.add(str);
                AnswerList.add(add);
                third++;

                String str1 = randomVal_8_unsigned();
                int value1 = Integer.parseInt(str1);
                String add1 = compute8(value1);
                listOfQuestions.add(str1);
                AnswerList.add(add1);
                third++;

                String str2 = randomVal_8_unsigned();
                int value2 = Integer.parseInt(str2);
                String add2 = compute8(value2);
                listOfQuestions.add(str2);
                AnswerList.add(add2);
                third++;

            } else if(bitValue.equals("10 bit")){

                String str = randomVal_10_unsigned();
                int value = Integer.parseInt(str);
                String add = compute10(value);
                listOfQuestions.add(str);
                AnswerList.add(add);
                third ++;

                String str1 = randomVal_10_unsigned();
                int value1 = Integer.parseInt(str1);
                String add1 = compute10(value1);
                listOfQuestions.add(str1);
                AnswerList.add(add1);
                third ++;

                String str2 = randomVal_10_unsigned();
                int value2 = Integer.parseInt(str2);
                String add2 = compute10(value2);
                listOfQuestions.add(str2);
                AnswerList.add(add2);
                third++;

            } else if(bitValue.equals( "12 bit")){

                String str = randomVal_12_unsigned();
                int value = Integer.parseInt(str);
                String add = compute12(value);
                listOfQuestions.add(str);
                AnswerList.add(add);
                third ++;

                String str1 = randomVal_12_unsigned();
                int value1 = Integer.parseInt(str1);
                String add1 = compute12(value1);
                listOfQuestions.add(str1);
                AnswerList.add(add1);
                third++;

                String str2 = randomVal_12_unsigned();
                int value2 = Integer.parseInt(str2);
                String add2 = compute12(value2);
                listOfQuestions.add(str2);
                AnswerList.add(add2);
                third ++;
            }
        }

        //Collections.shuffle(listOfQuestions);

    }

    public void UploadQuestion(int key){

        switch (key){
            case 0:
                QuestionIncludingDescription.add(QuestionCount + ": What is the decimal signed value for Hexadecimal 0x" + listOfQuestions.get(0) + "?");
                QuestionCount ++;
                lastAnswerList.add(AnswerList.get(0));

                break;
            case 1:
                QuestionIncludingDescription.add(QuestionCount + ": What is the decimal unsigned value for Hexadecimal 0x" + listOfQuestions.get(1) + "?");
                QuestionCount ++;
                lastAnswerList.add(AnswerList.get(1));

                break;
            case 2:
                QuestionIncludingDescription.add(QuestionCount + ": What is the decimal signed value for Hexadecimal 0x" + listOfQuestions.get(2) + "?");
                QuestionCount++;
                lastAnswerList.add( AnswerList.get(2));
                break;
            case 3:
                QuestionIncludingDescription.add(QuestionCount + ": What is the decimal unsigned value for Hexadecimal 0x" + listOfQuestions.get(3) + "?");
                QuestionCount ++;
                lastAnswerList.add(AnswerList.get(3));

                break;
            case 4:
                QuestionIncludingDescription.add(QuestionCount + ": What is the hexadecimal value for signed 2's complement decimal of " + listOfQuestions.get(4) + "?");
                QuestionCount++;
                lastAnswerList.add(AnswerList.get(4));

                break;
            case 5:
                QuestionIncludingDescription.add(QuestionCount + ": What is the hexadecimal value for signed 2's complement decimal of " + listOfQuestions.get(5) + "?");
                QuestionCount++;
                lastAnswerList.add(AnswerList.get(5));

                break;
            case 6:
                QuestionIncludingDescription.add(QuestionCount + ": What is the hexadecimal value for signed 2's complement decimal of " + listOfQuestions.get(6) + "?");
                QuestionCount ++;
                lastAnswerList.add(AnswerList.get(6));

            case 7:
                QuestionIncludingDescription.add(QuestionCount + ": What is the hexadecimal value for unsigned decimal " + listOfQuestions.get(7) + "?");
                QuestionCount++;
               lastAnswerList.add(AnswerList.get(7));

                break;
            case 8:
                QuestionIncludingDescription.add(QuestionCount + ": What is the hexadecimal value for unsigned decimal " + listOfQuestions.get(8) + "?");
                QuestionCount++;
               lastAnswerList.add(AnswerList.get(8));

                break;
            case 9:
                QuestionIncludingDescription.add(QuestionCount + ": What is the hexadecimal value for unsigned decimal " + listOfQuestions.get(9) + "?");
                QuestionCount ++;
                lastAnswerList.add(AnswerList.get(9));
                break;


        }


    }

    private String compute8(int value) {
        String s;
        if (value >=0) {
            if (value > Umax8) {
                s = "8 bit: too large signed and unsigned";
            } else {
                if (value > Tmax8) {
                    s = Integer.toHexString(value) ;

                } else {
                    s =  Integer.toHexString(value);
                }
            }
        } else { // negative
            if (value < Tmin8) {
                s = "8 bit: too small signed and unsigned";
            } else {
                s = Integer.toHexString(value).substring(6) ;
            }
        }
        return s;
    }


    private String compute10(int value) {
        String s;
        if (value >= 0) {
            if (value > Umax10) {
                s = "10 bit: too large signed and unsigned";
            } else {
                if (value > Tmax10) {
                    s =  Integer.toHexString(value) ;

                } else {
                    s =  Integer.toHexString(value);
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

                s =  fs ;
            }
        }
        return s;
    }

    private String compute12(int value) {
        String s;
        if (value >=0) {
            if (value > Umax12) {
                s = "12 bit: too large signed and unsigned";
            } else {
                if (value > Tmax12) {
                    s = "" + Integer.toHexString(value);

                } else {
                    s = "" + Integer.toHexString(value);
                }
            }
        } else {  //negative #
            if (value < Tmin12) {
                s = "12 bit: too small signed and unsigned";
            } else {
                s = "" + Integer.toHexString(value).substring(5) ;
            }
        }
        return s;
    }

    private String hex8_Unsigned(int value) {
        String s;
        if (value > Umax8) s = "8 bit: unsigned, signed too large";
        else if (value > Tmax8) s = ""+value;
        else s = "" + value;

        return s;
    }
    private String hex8_Signed(int value) {
        String s;
        if (value > Umax8) s = "8 bit: unsigned, signed too large";
        else if (value > Tmax8) s =  "" + (value - (Umax8+1));
        else s = "" + value;

        return s;
    }
    private String hex10_Unsigned(int value) {
        String s;
        if (value > Umax10) s = "10 bit: unsigned, signed too large";
        else if (value > Tmax10) s = ""+value;
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

    private String hex12_Unsigned(int value) {
        String s;
        if (value > Umax12) s = "12 bit: unsigned, signed too large";
        else if (value > Tmax12) s = ""+value;
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

    public void onBackPressed(){
        Intent intent = new Intent(this, MainActivity.class);

        intent.putExtra("QUIZ_SCORE", ScoreText.getText().toString());

        if(!ScoreText.getText().toString().equals("Quiz Score: 0/0"))
            setResult(RESULT_OK, intent);
        finish();
    }
}
