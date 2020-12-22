package fall2020.mobileapp.lab10_krimal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    Button btn;
    EditText text;
    String source = "", value = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = findViewById(R.id.button);
        text = findViewById(R.id.editText);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                source = text.getText().toString();
                if(!TextUtils.isEmpty(source)) {
                    INTENT(source);
                } else{
                    Toast.makeText(MainActivity.this, "Field Empty!", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
    public void INTENT(String item){
        Intent intent = new Intent(MainActivity.this, MapsActivity.class);
        intent.putExtra("SOURCE", item);
        startActivityForResult(intent, 999);
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        exitFromApp();
    }

    private void exitFromApp() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        startActivity(intent);
    }
}
