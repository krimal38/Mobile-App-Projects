package fall2020.mobileapp.project2_krimal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Set;

public class UpdateWorkout extends AppCompatActivity {

    Button addWorkoutBtn, CancelBtn;
    EditText Name, Reps, Sets, Notes, Weight;
    DatabaseForUpdating updatingWorkoutObj;
    public String getName, getReps, getSets, getNotes, getWeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_workout);

        updatingWorkoutObj = new DatabaseForUpdating(this);
        addWorkoutBtn = findViewById(R.id.AddWorkout);
        CancelBtn = findViewById(R.id.Cancel);
        Name = findViewById(R.id.name);
        Reps = findViewById(R.id.Reps);
        Sets = findViewById(R.id.Sets);
        Notes = findViewById(R.id.Notes);
        Weight = findViewById(R.id.Weight);

        addWorkoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addItems();
            }
        });



        CancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UpdateWorkout.this, EDIT_MODE.class);
                startActivity(intent);
                finish();
            }
        });

    }

    private void addItems() {
       getName = Name.getText().toString().toLowerCase();
       getReps = Reps.getText().toString();
       getSets = Sets.getText().toString();
       getNotes = Notes.getText().toString();
       getWeight = Weight.getText().toString();


       if(!(TextUtils.isEmpty(getName))){

        if(updatingWorkoutObj.findTask(getName).moveToFirst()){
            Toast.makeText(this, getName + " already exists", Toast.LENGTH_SHORT).show();

        } else {

            boolean value = updatingWorkoutObj.addData(getName, getReps, getSets, getWeight, getNotes);
            Name.getText().clear();
            Reps.getText().clear();
            Sets.getText().clear();
            Notes.getText().clear();
            Weight.getText().clear();
        }

       } else{
           Toast.makeText(this, "Name field should be entered!", Toast.LENGTH_SHORT).show();
       }


    }
    public void onBackPressed(){
        Intent intent = new Intent(this, EDIT_MODE.class);
        startActivity(intent);
        finish();
    }

}
