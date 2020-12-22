package fall2020.mobileapp.project2_krimal;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {


    Button Workout_menu, EditMode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Workout_menu = findViewById(R.id.Do_a_workout);
        EditMode= findViewById(R.id.EditMode);

        Workout_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                function_workoutMenu();
            }
        });

        EditMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                function_EditMode();
            }
        });

    }

    public void function_workoutMenu(){
        Intent intent = new Intent(this, WORKOUT_MENU.class);
        startActivity(intent);
    }

    public void function_EditMode(){
        Intent intent = new Intent(this, EDIT_MODE.class);
        startActivity(intent);
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
