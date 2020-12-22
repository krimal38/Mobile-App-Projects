package fall2020.mobileapp.lab2_krimal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class NewActivity extends AppCompatActivity {


    EditText Excellent_editText;
    EditText Average_editText;
    EditText Lacking_editText;
    Button UpdateButton;
    public int excellent, average, bad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);


        Excellent_editText =  findViewById(R.id.Excellent);
        Average_editText = findViewById(R.id.Average);
        Lacking_editText = findViewById(R.id.Lacking);
        UpdateButton =findViewById(R.id.UpdateButton);

        Intent intent = getIntent();


        excellent = intent.getIntExtra("NUMBER1", 0);
        average = intent.getIntExtra("NUMBER2", 0);
        bad = intent.getIntExtra("NUMBER3", 0);
        UpdateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openActivityForUpdate();

            }
        });
    }

    public void openActivityForUpdate() {


        Intent intent = new Intent(NewActivity.this, MainActivity.class);

        if(!TextUtils.isEmpty(Excellent_editText.getText().toString())) {
            intent.putExtra("NUMBER1", Integer.parseInt(Excellent_editText.getText().toString()));
            excellent = Integer.parseInt(Excellent_editText.getText().toString());
        } else{
            intent.putExtra("NUMBER1", excellent);
        }

        if(!TextUtils.isEmpty(Average_editText.getText().toString())) {
            intent.putExtra("NUMBER2", Integer.parseInt(Average_editText.getText().toString()));
            average = Integer.parseInt(Average_editText.getText().toString());
        } else{
            intent.putExtra("NUMBER2", average);
        }

        if(!TextUtils.isEmpty(Lacking_editText.getText().toString())) {
            intent.putExtra("NUMBER3", Integer.parseInt(Lacking_editText.getText().toString()));
            bad = Integer.parseInt(Lacking_editText.getText().toString());

        } else{
            intent.putExtra("NUMBER3", bad);
        }

        setResult(RESULT_OK,intent);
        finish();


    }





}
