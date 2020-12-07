package com.example.project1;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.IOException;

public class PlusActivity extends AppCompatActivity {
    Button btnarw;
    private Button btnup, btnsave;
    private EditText txtdata ;
    private ImageView images;
    private final int IMG_REQUEST_ID = 10;
    private Uri imgUri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plus);

        btnarw = findViewById(R.id.arrow);
        btnsave = findViewById(R.id.btnreq);
        btnup = findViewById(R.id.uploadbtn);
        images = findViewById(R.id.imageView);
        txtdata = findViewById(R.id.instaid);

        btnarw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PlusActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
        btnup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestImage();
            }
        });
    }

    //code for enter in phone storage
    private void requestImage(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"),IMG_REQUEST_ID);
    }

    //Enter phone storage..............
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == IMG_REQUEST_ID && resultCode == RESULT_OK && data != null && data.getData() != null){
            imgUri = data.getData();
            try {
                Bitmap bitmapref = MediaStore.Images.Media.getBitmap(getContentResolver(),imgUri);
                images.setImageBitmap(bitmapref);
                btnup.setEnabled(false);
                btnsave.setEnabled(true);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}