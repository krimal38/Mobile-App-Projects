package fall2020.mobileapp.finalproject_krimal_radhika2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


public class FinalLayout extends AppCompatActivity {

    ImageView image;
    Button downloadBtn;
    Database db;
    Context context;
    Bitmap bp;
    String TAG;
    String item;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_layout);
        image = findViewById(R.id.imageView);
        db = new Database(this);
        downloadBtn = findViewById(R.id.DownloadBtn);
        context= getApplicationContext();
        Intent intent = getIntent();
        item = (String) intent.getExtras().get("sendData");
        String databaseString = db.getRow(item, "IMAGE");

        bp = StringToBitMap(databaseString);
        image.setImageBitmap(bp);
        downloadBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                BitmapDrawable drawable = (BitmapDrawable) image.getDrawable();
                Bitmap bitmap = drawable.getBitmap();

                saveReceivedImage(bitmap, item);
                downloadBtn.setEnabled(false);
            }
        });


    }


    public Bitmap StringToBitMap(String encodedString){
        try {
            byte [] encodeByte=Base64.decode(encodedString,Base64.DEFAULT);
            Bitmap bitmap=BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
            return bitmap;
        } catch(Exception e) {
            e.getMessage();
            return null;
        }
    }
    public void onBackPressed(){
        Intent intent = new Intent(this, GalleryClass.class);
        startActivity(intent);
        finish();
    }




    private void saveReceivedImage(Bitmap bitmap, String imageName){
        try {
            File path = new File(context.getFilesDir(), "MyAppName" + File.separator + "Images");


            if(!path.exists()){
                path.mkdirs();
            }
            File outFile = new File(path, imageName + ".jpeg");
            FileOutputStream outputStream = new FileOutputStream(outFile);

            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
            outputStream.close();
            Toast.makeText(context, "Image Saved!", Toast.LENGTH_SHORT).show();

        } catch (FileNotFoundException e) {
            Log.e(TAG, "Saving received message failed with", e);
        } catch (IOException e) {
            Log.e(TAG, "Saving received message failed with", e);
        }

    }

}