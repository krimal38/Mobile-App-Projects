package fall2020.mobileapp.finalproject_krimal_radhika2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    ImageButton galleryBtn, camera,uploadBtn;
    Button passwordBtn;
    Database db;
    private int requestCapture = 100;
    private int requestName = 99;
    Bitmap bp;
    private static final int PICK_IMAGE = 1;
    Uri imageUri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new Database(this);
        galleryBtn =  findViewById(R.id.gallery);
        camera = findViewById(R.id.cameraButton);
        uploadBtn = findViewById(R.id.Upload);
        passwordBtn = findViewById(R.id.passwordBtn);


        passwordBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = db.getRow("pin", "IMAGE");
                if(str.equals("")){
                    passwordCreateIntent();
                } else{
                    if(!str.equals("")){
                        Intent intent = new Intent(MainActivity.this, CheckBeforeUpdating_4digit.class);
                        startActivityForResult(intent, 003);
                    } else {
                        passwordUpdateIntent();
                    }
                }
            }
        });

        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takePicture(v);
            }
        });

        galleryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = db.getRow("pin", "IMAGE");
                if(str.equals("")){
                    Intent intent = new Intent(MainActivity.this, CreateNewPassword4Digit.class);
                    startActivityForResult(intent, 001);
                } else {
                    Intent intent = new Intent(MainActivity.this, EnterPin_4.class);
                    startActivityForResult(intent, 002);
                }
            }
        });


        uploadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gallery = new Intent();
                gallery.setType("image/*");
                gallery.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(gallery, "Select Picture"), PICK_IMAGE);

            }
        });

    }

    public void passwordCreateIntent(){
        Intent intent = new Intent(MainActivity.this, CreateNewPassword4Digit.class);
        startActivityForResult(intent, 1);
    }

    public void passwordUpdateIntent(){
        Intent intent = new Intent(MainActivity.this, UpdatePasswordDigit4.class);
        startActivityForResult(intent, 2);
    }

    public void takePicture(View view){
        Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);

        if(intent.resolveActivity(getPackageManager()) != null){
            startActivityForResult(intent, requestCapture);

        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if(requestCode == requestCapture && resultCode == RESULT_OK) {
            bp = (Bitmap) data.getExtras().get("data");
            Intent intent = new Intent(this, PictureNameActivity.class);
            startActivityForResult(intent, requestName);
        }
        if(requestCode == requestName && resultCode == RESULT_OK){
            String str = (String) data.getExtras().get("data");
            db.addData( str, BitMapToString(bp));
        }


        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK && data != null) {
            imageUri = data.getData();

            try {
                bp = MediaStore.Images.Media.getBitmap(getContentResolver(),  imageUri);
                Intent intent = new Intent(this, PictureNameActivity.class);
                startActivityForResult(intent, requestName);
                //ProfileImage.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public String BitMapToString(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] b = baos.toByteArray();
        String temp = Base64.encodeToString(b, Base64.DEFAULT);
        return temp;
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
