package fall2020.mobileapp.lab11_krimal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.Bundle;
import android.widget.ImageView;

public class newActivity extends AppCompatActivity {

    ImageView image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);
        image = findViewById(R.id.imageView);

        Intent i = getIntent();
        Bitmap bp = (Bitmap) i.getExtras().get("data");
        image.setImageBitmap(bp);



    }
}
