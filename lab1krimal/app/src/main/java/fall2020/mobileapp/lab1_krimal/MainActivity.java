package fall2020.mobileapp.lab1_krimal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    RadioGroup radioGroup;
    RadioButton radioButton;
    EditText userInput;
    TextView BinaryTextView, text;
    TextView OctalTextView;
    TextView decimalTextView;
    TextView HexTextView;
    Button convert, clear;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userInput = findViewById(R.id.editText2); // Instantiate userInput
        radioGroup = findViewById(R.id.RadioLayout); // Instantiate radioGroup
        convert = findViewById(R.id.button); // Instantiate convert Button
        clear = findViewById(R.id.clear); //Instantiate clear button
        BinaryTextView = findViewById(R.id.Binary_output);
        decimalTextView = findViewById(R.id.Decimal_output);
        OctalTextView = findViewById(R.id.Octal_output);
        HexTextView = findViewById(R.id.Hex_output);





        convert.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                 int check = radioGroup.getCheckedRadioButtonId();
              String  userInp = userInput.getText().toString();
                radioButton = findViewById(check);

                    Find_the_button(check, userInp);

            }
        });



        clear.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                userInput.getText().clear();
                BinaryTextView.setText("Binary      0");
                decimalTextView.setText("Decimal     0 ");
                HexTextView.setText("Hexadecimal     0 ");
                OctalTextView.setText("Octal     0 ");
            }

        });

    }


    public void checkButton(View v){
        int radioId = radioGroup.getCheckedRadioButtonId();
        if(radioId == R.id.Hex){
            userInput.setInputType(InputType.TYPE_CLASS_NUMBER & (InputType.TYPE_CLASS_TEXT));

        } else{
            userInput.setInputType(InputType.TYPE_CLASS_NUMBER);

        }

        radioButton = findViewById(radioId);
        userInput.setText(radioButton.getText());

    }

   public void Find_the_button(int check, String userInp){
       switch (check){

               case R.id.Binary:

                   if (userInp.matches("[0-1]+")) {
                       int Binary_decimal_value = Integer.parseInt(userInp, 2);
                       String Binary_to_hex = Integer.toString(Binary_decimal_value, 16);
                       String Binary_to_octal = Integer.toString(Binary_decimal_value, 8);

                       BinaryTextView.setText("Binary  " + userInp);
                       decimalTextView.setText("Decimal  " + Binary_decimal_value);
                       HexTextView.setText("Hexadecimal  " + Binary_to_hex);
                       OctalTextView.setText("Octal  " + Binary_to_octal);

                   } else
                       Toast.makeText(getApplicationContext(), "Illegal Number (" + userInp + ") for base 2", Toast.LENGTH_SHORT).show();

                   break;

           case R.id.Octal:

               if(userInp.matches("[0-7]+")) {
                   int Octal_to_Decimal = Integer.parseInt(userInp, 8);
                   String Octal_to_Hex = Integer.toString(Octal_to_Decimal, 16);
                   String Octal_to_binary = Integer.toString(Octal_to_Decimal, 2);

                   OctalTextView.setText("Octal  " + userInp);
                   decimalTextView.setText("Decimal  " + Octal_to_Decimal);
                   BinaryTextView.setText("Binary " + Octal_to_binary);
                   HexTextView.setText("Hexadecimal  " + Octal_to_Hex);
               } else
               Toast.makeText(getApplicationContext(), "Illegal Number (" + userInp + ") for base 8", Toast.LENGTH_SHORT).show();

               break;

           case R.id.Decimal:

               if(userInp.matches("[0-9]+")) {
                   int decimal_value = Integer.parseInt(userInp);
                   String binary_value = Integer.toBinaryString(decimal_value);
                   String hexadecimal_value = Integer.toHexString(decimal_value);
                   String octal_value = Integer.toOctalString(decimal_value);

                   BinaryTextView.setText("Binary  " + binary_value);
                   decimalTextView.setText("Decimal  " + decimal_value);
                   OctalTextView.setText("Octal  " + octal_value);
                   HexTextView.setText("Hexadecimal  " + hexadecimal_value);
               }else
                   Toast.makeText(getApplicationContext(), "Illegal Number (" + userInp + ") for base 10", Toast.LENGTH_SHORT).show();

               break;

           case R.id.Hex:

               if(userInp.matches("[0-9A-Fa-f]+")) {
                   int hex_to_Decimal = Integer.parseInt(userInp, 16);
                   String hex_to_binary = Integer.toString(hex_to_Decimal, 2);
                   String hex_to_octal = Integer.toString(hex_to_Decimal, 8);

                   decimalTextView.setText("Decimal  " + hex_to_Decimal);
                   HexTextView.setText("Hexadecimal  " + userInp);
                   OctalTextView.setText("Octal  " + hex_to_octal);
                   BinaryTextView.setText("Binary  " + hex_to_binary);
               }else
               Toast.makeText(getApplicationContext(), "Illegal Number (" + userInp + ") for base 16", Toast.LENGTH_SHORT).show();

               break;

       }
    }



}
