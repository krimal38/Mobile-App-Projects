package fall2020.mobileapp.project2_krimal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class WORKOUT_MENU extends AppCompatActivity {

    ArrayList<String> items;
    ArrayAdapter<String> itemsAdapter;
    ListView listView;
    DatabaseForUpdating db;
    RelativeLayout layout;
    String getName, getReps, getSets, getNotes, getWeight;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_w_o_r_k_o_u_t__m_e_n_u);

        db = new DatabaseForUpdating(this);
        listView = findViewById(R.id.list_item);
        layout = findViewById(R.id.layoutID);
        layout.getLayoutParams().height = 1480;
        layout.getLayoutParams().width = 5000;


        items = new ArrayList<>();
        items = db.getData();
        itemsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, items);
        listView.setAdapter(itemsAdapter);

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                //db.delete(itemsAdapter.getItem(position).toString());
                items.remove(position);
                itemsAdapter.notifyDataSetChanged();
                Toast.makeText(WORKOUT_MENU.this, "Removed", Toast.LENGTH_SHORT).show();
                return true;
            }
        });


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item =  (String) ((TextView) view).getText();
                getName = db.findval(item, "NAME");
                getReps = db.findval(item, "REPS");
                getSets = db.findval(item, "SETS");
                getWeight = db.findval(item, "WEIGHT");
                getNotes = db.findval(item, "NOTES");
                 //String print = "Name: " + getName + "\n Reps: " + getReps + "\n Sets: " + getSets + "\n Weight: " + getWeight + "\n Note:" + getNotes;
                String print = "Name: " + getName +"," +  " Reps: " + getReps +"," + " Sets: " + getSets +"," + " Weight: " + getWeight +"," + " Note: " + getNotes;

                showSnackBar(print);
            }
        });

    }

    public void showSnackBar(String print){

        Snackbar snackbar = Snackbar.make(layout, print, Snackbar.LENGTH_INDEFINITE);
        snackbar.show();
    }

    public void onBackPressed(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
