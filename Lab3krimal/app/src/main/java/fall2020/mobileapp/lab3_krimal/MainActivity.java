package fall2020.mobileapp.lab3_krimal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> items;
    ArrayAdapter<String> itemsAdapter;
    ListView listView;
    Button AddBtn, removeBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);
        AddBtn = findViewById(R.id.AddButton);
        removeBtn = findViewById(R.id.removeButton);

        AddBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addItems();
            }
        });

        items = new ArrayList<>();
        itemsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, items);
        listView.setAdapter(itemsAdapter);



        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            public boolean onItemLongClick(AdapterView<?> parent, View view,
                                           int position, long id) {
                Toast.makeText(getApplicationContext(), "Removing " + ((TextView)
                        view).getText(), Toast.LENGTH_SHORT).show();
                items.remove(position);
                itemsAdapter.notifyDataSetChanged();
                return true;
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Toast.makeText(getApplicationContext(), ((TextView) view).getText(),
                        Toast.LENGTH_SHORT).show();
                String currentItem =  (String) ((TextView) view).getText();
                if(!currentItem.contains("Done")) {
                    String newItem = "Done: " + ((TextView) view).getText();
                    itemsAdapter.remove(currentItem);

                    itemsAdapter.add(newItem);
                }

            } });
        removeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeItems();
            }
        });

    }

    private void removeItems() {
        for(int i= itemsAdapter.getCount()-1; i>=0; i--){
            if(itemsAdapter.getItem(i).contains("Done:"))
                itemsAdapter.remove(itemsAdapter.getItem(i));
        }


    }

    private void addItems() {
        EditText input = findViewById(R.id.editText);
        String itemText = input.getText().toString();


        // adding value
        if(!(itemText.equals(""))){
            itemsAdapter.add(itemText);
            input.setText("");
        }else{
            Toast.makeText(getApplicationContext(), "Text required!", Toast.LENGTH_LONG).show();
        }

    }
}
