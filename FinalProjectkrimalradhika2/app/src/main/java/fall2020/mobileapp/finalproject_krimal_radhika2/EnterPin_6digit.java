package fall2020.mobileapp.finalproject_krimal_radhika2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EnterPin_6digit extends AppCompatActivity {

    Button btn;
    EditText UserText;
    Database db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_pin_6digit);

        btn = findViewById(R.id.button);
        UserText = findViewById(R.id.editText);
        db = new Database(this);



        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = db.getRow("passwords", "IMAGE");
                String userinput = UserText.getText().toString();
                if(!TextUtils.isEmpty(userinput)) {
                    if (userinput.equals(str)) {
                       // Toast.makeText(EnterPin_6digit.this, "Correct!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(EnterPin_6digit.this, FinalLayout.class);
                        Intent intentgetData = getIntent();
                        String getData = (String) intentgetData.getExtras().get("data");
                        intent.putExtra("sendData", getData);
                        startActivityForResult(intent, 9);
                        UserText.getText().clear();
                    } else{
                        Toast.makeText(EnterPin_6digit.this, "Incorrect pin. Please try again! ", Toast.LENGTH_SHORT).show();
                        UserText.getText().clear();
                    }
                }else{
                    Toast.makeText(EnterPin_6digit.this, "Pin cannot be empty!", Toast.LENGTH_SHORT).show();

                }
            }
        });


    }


}
