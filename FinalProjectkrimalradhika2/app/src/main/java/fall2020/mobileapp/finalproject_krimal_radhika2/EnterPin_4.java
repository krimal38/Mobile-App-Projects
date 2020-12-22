package fall2020.mobileapp.finalproject_krimal_radhika2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EnterPin_4 extends AppCompatActivity {

    Button btn;
    EditText UserText;
    Database db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_pin_4);

        btn = findViewById(R.id.button);
        UserText = findViewById(R.id.editText);
        db = new Database(this);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = db.getRow("pin", "IMAGE");
                String userinput = UserText.getText().toString();
                if(!TextUtils.isEmpty(userinput)) {

                        if (userinput.equals(str)) {
                            //Toast.makeText(EnterPin_4.this, "Correct!", Toast.LENGTH_SHORT).show();
                            intentMethod();
                            UserText.getText().clear();
                        } else {
                            Toast.makeText(EnterPin_4.this, "Incorrect pin. Please try again! ", Toast.LENGTH_SHORT).show();
                            UserText.getText().clear();
                        }

                }else{
                    Toast.makeText(EnterPin_4.this, "Pin cannot be empty!", Toast.LENGTH_SHORT).show();

                }
            }
        });


    }

    public void intentMethod(){
        Intent intent = new Intent(EnterPin_4.this, GalleryClass.class);
        startActivity(intent);
        finish();
    }
}
