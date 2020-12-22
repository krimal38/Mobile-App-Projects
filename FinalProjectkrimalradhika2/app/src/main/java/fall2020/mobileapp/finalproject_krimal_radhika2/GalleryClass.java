package fall2020.mobileapp.finalproject_krimal_radhika2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Base64;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import javax.microedition.khronos.opengles.GL;

public class GalleryClass extends AppCompatActivity {

    ArrayList<String> items;
    ListView listView;
    ArrayAdapter<String> itemsAdapter;
    Database db;
    Button UpdateButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery_class);
        listView = findViewById(R.id.ListView);
        db = new Database(this);


        items = new ArrayList<>();
        items = db.getDataBase();
        itemsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, items);
        listView.setAdapter(itemsAdapter);
        UpdateButton = findViewById(R.id.passwordBtn);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item =  (String) ((TextView) view).getText();
                String str = db.getRow("passwords", "IMAGE");

                if(str.equals("")){
                    Intent intent = new Intent(GalleryClass.this, CreatePassword_6digit.class);
                    startActivityForResult(intent, 1955);

                } else{
                    Intent intent = new Intent(GalleryClass.this, EnterPin_6digit.class);
                    intent.putExtra("data", item);
                    startActivityForResult(intent, 9999);
                }


            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                db.delete(itemsAdapter.getItem(position).toString());
                items.remove(position);
                itemsAdapter.notifyDataSetChanged();
                Toast.makeText(GalleryClass.this, "Removed", Toast.LENGTH_SHORT).show();
                return true;
            }
        });

        UpdateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updatePassword();
            }
        });


    }



    public void onBackPressed(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }


    public void updatePassword(){
        Intent intent = new Intent(this, Upadatepassword_6digit.class);
        startActivityForResult(intent, 1988);
    }




}
