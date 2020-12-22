package fall2020.mobileapp.finalproject_krimal_radhika2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.w3c.dom.Text;

public class PictureNameActivity extends AppCompatActivity {

    EditText text;
    Button btn;
    Database db;
    String str = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture_name);

        btn = findViewById(R.id.button);
        text = findViewById(R.id.editText);
        db = new Database(this);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!TextUtils.isEmpty(text.getText().toString())) {

                    if(db.findTask(text.getText().toString()).moveToFirst()){
                        Toast.makeText(PictureNameActivity.this, " Name already exists", Toast.LENGTH_SHORT).show();
                    } else{
                        str = text.getText().toString();
                        Intent intent = new Intent();
                        intent.putExtra("data", str);
                        setResult(RESULT_OK, intent);
                        finish();
                    }

                } else{
                    Toast.makeText(PictureNameActivity.this, "Name cannot be empty!", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}
