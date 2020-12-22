package fall2020.mobileapp.project2_krimal;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class EDIT_MODE extends AppCompatActivity {
    ArrayList<String> items;
    ArrayAdapter<String> itemsAdapter;
    ListView listView;
    DatabaseForUpdating db;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_e_d_i_t__m_o_d_e);

        db = new DatabaseForUpdating(this);
        btn = findViewById(R.id.button);
        listView = findViewById(R.id.listView);


        items = new ArrayList<>();
        items = db.getData();
        itemsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, items);
        listView.setAdapter(itemsAdapter);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EDIT_MODE.this, UpdateWorkout.class);
               startActivity(intent);
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(EDIT_MODE.this, EditTheWorkoutMode.class);
                String item =  (String) ((TextView) view).getText();
                intent.putExtra("SELECTITEM", item);
                startActivityForResult(intent, 999);

            }
        });


        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                db.delete(itemsAdapter.getItem(position).toString());
                items.remove(position);
                itemsAdapter.notifyDataSetChanged();
                Toast.makeText(EDIT_MODE.this, "Removed", Toast.LENGTH_LONG).show();
                return true;
            }
        });

    }



    public void onBackPressed(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }


}
