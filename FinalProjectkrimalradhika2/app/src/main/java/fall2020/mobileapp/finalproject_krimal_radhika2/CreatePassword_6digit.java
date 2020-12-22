package fall2020.mobileapp.finalproject_krimal_radhika2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CreatePassword_6digit extends AppCompatActivity {

    Button btn;
    EditText text;
    String takeTextVal = "";
    Database db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_password_6digit);
        db = new Database(this);
        btn = findViewById(R.id.button);
        text = findViewById(R.id.editText);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!TextUtils.isEmpty(text.getText().toString())) {
                    if(text.getText().toString().length() < 6) {
                        Toast.makeText(CreatePassword_6digit.this, "Invalid length", Toast.LENGTH_SHORT).show();
                    } else{
                        takeTextVal = text.getText().toString();
                        db.updateData("passwords", takeTextVal);
                        Toast.makeText(CreatePassword_6digit.this, "pin has been created successfully", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                } else{
                    Toast.makeText(CreatePassword_6digit.this, "Pin cannot be empty!", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}
