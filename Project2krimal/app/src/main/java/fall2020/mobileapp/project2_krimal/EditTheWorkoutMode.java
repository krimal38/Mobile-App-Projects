package fall2020.mobileapp.project2_krimal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Set;

public class EditTheWorkoutMode extends AppCompatActivity {

    Button addWorkoutBtn, CancelBtn;
    EditText Name, Reps, Sets, Notes, Weight;
    DatabaseForUpdating db;
    public String getName, getReps, getSets, getNotes, getWeight;
    String getValue = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_the_workout_mode);



        getValue = getIntent().getStringExtra("SELECTITEM");
        db = new DatabaseForUpdating(this);
        addWorkoutBtn = findViewById(R.id.AddWorkout);
        CancelBtn = findViewById(R.id.Cancel);
        Name = findViewById(R.id.name);
        Reps = findViewById(R.id.Reps);
        Sets = findViewById(R.id.Sets);
        Notes = findViewById(R.id.Notes);
        Weight = findViewById(R.id.Weight);


        Name.setHint(db.findval(getValue, "NAME"));
        Reps.setHint(db.findval(getValue, "REPS"));
        Sets.setHint(db.findval(getValue, "SETS"));
        Weight.setHint(db.findval(getValue, "WEIGHT"));
        Notes.setHint(db.findval(getValue, "NOTES"));



        addWorkoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UpdateFunction();
            }
        });

        CancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EditTheWorkoutMode.this, EDIT_MODE.class);
                startActivity(intent);
                finish();
            }
        });

    }


    private void UpdateFunction() {
       // getName = Name.getText().toString().toLowerCase();
        getReps = Reps.getText().toString();
        getSets = Sets.getText().toString();
        getNotes = Notes.getText().toString();
        getWeight = Weight.getText().toString();

        if(!TextUtils.isEmpty(Name.getText().toString())){
            Toast.makeText(this, "Name field cannot be updated!", Toast.LENGTH_LONG).show();
            Name.getText().clear();
        }

        if(TextUtils.isEmpty(getReps) && TextUtils.isEmpty(getSets) && TextUtils.isEmpty(getNotes) && TextUtils.isEmpty(getWeight)){
            Toast.makeText(this, "Seems like you didn't add anything!", Toast.LENGTH_LONG).show();
        } else {
            db.updateDB(getValue, getReps, getSets, getWeight, getNotes );
            Toast.makeText(this, "Updated!", Toast.LENGTH_LONG).show();
        }
        Reps.getText().clear();
        Sets.getText().clear();
        Weight.getText().clear();
        Notes.getText().clear();
        Name.setHint(db.findval(getValue, "NAME"));
        Reps.setHint(db.findval(getValue, "REPS"));
        Sets.setHint(db.findval(getValue, "SETS"));
        Weight.setHint(db.findval(getValue, "WEIGHT"));
        Notes.setHint(db.findval(getValue, "NOTES"));


    }

    public void onBackPressed(){
        Intent intent = new Intent(this, EDIT_MODE.class);
        startActivity(intent);
        finish();
    }




}
