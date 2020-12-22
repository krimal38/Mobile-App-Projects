package fall2020.mobileapp.finalproject_krimal_radhika2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Upadatepassword_6digit extends AppCompatActivity {
    Button btn;
    EditText text, UpdateEditText;
    String takeTextVal = "";
    Database db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upadatepassword_6digit);

        db = new Database(this);
        btn = findViewById(R.id.button);
        text = findViewById(R.id.editText);
        UpdateEditText = findViewById(R.id.CheckUpdate);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = db.getRow("passwords", "IMAGE");
                if(!TextUtils.isEmpty(text.getText().toString()) || !TextUtils.isEmpty(UpdateEditText.getText().toString())) {

                    if(str.equals("")){
                        Intent intent = new Intent(Upadatepassword_6digit.this, CreatePassword_6digit.class);
                        Toast.makeText(Upadatepassword_6digit.this, "Password has not been created, yet", Toast.LENGTH_SHORT).show();
                        startActivityForResult(intent, 005);
                        finish();
                    }
                     else if(!(UpdateEditText.getText().toString().equals(str))){
                        Toast.makeText(Upadatepassword_6digit.this, "Previous password did not match", Toast.LENGTH_SHORT).show();
                        UpdateEditText.getText().clear();
                        text.getText().clear();

                    } else {
                        if (text.getText().toString().length() < 6) {
                            Toast.makeText(Upadatepassword_6digit.this, "Invalid length", Toast.LENGTH_SHORT).show();
                        } else {
                            takeTextVal = text.getText().toString();
                            db.updateData("passwords", takeTextVal);
                            Toast.makeText(Upadatepassword_6digit.this, "pin has been updated successfully", Toast.LENGTH_SHORT).show();

                            finish();
                        }

                    }
                } else{
                    Toast.makeText(Upadatepassword_6digit.this, "Pin cannot be empty!", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }


}
