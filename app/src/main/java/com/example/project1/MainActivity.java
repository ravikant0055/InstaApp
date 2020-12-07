package com.example.project1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button photo,dashboard,cidea;
    ImageView btnabout, btnshare;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        photo = findViewById(R.id.preview);
        dashboard = findViewById(R.id.templatess);
        cidea = findViewById(R.id.creativedesing);
        btnabout = findViewById(R.id.aboutbtn);
        btnshare = findViewById(R.id.sharebtn);

        btnabout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent idea = new Intent(MainActivity.this, AboutActivity.class);
                startActivity(idea);
            }
        });
        photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent dash = new Intent(MainActivity.this, Preview.class);
                startActivity(dash);
            }
        });
        dashboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent dash = new Intent(MainActivity.this, Dashboard.class);
                startActivity(dash);
            }
        });
        cidea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent idea = new Intent(MainActivity.this, CreativeIdeas.class);
                startActivity(idea);
            }
        });
    }
}
