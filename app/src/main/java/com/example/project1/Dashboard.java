package com.example.project1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import Adapterss.TemplatesAdapters;

public class Dashboard extends AppCompatActivity {
    private static final String TAG = "ravi";
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        recyclerView = findViewById(R.id.recyclerview);

        GridLayoutManager gridLayoutManager =new GridLayoutManager(this, 3);
        recyclerView.setLayoutManager(gridLayoutManager);

        final ArrayList<String> list = new ArrayList<>();

       /* list.add(new TemplatesModel(R.drawable.bbb));
        list.add(new TemplatesModel(R.drawable.drummer));
        list.add(new TemplatesModel(R.drawable.guitar));
        list.add(new TemplatesModel(R.drawable.kabir));
        list.add(new TemplatesModel(R.drawable.kabira));
        list.add(new TemplatesModel(R.drawable.kahawat));
        list.add(new TemplatesModel(R.drawable.sfbs));
        list.add(new TemplatesModel(R.drawable.bbb));
        list.add(new TemplatesModel(R.drawable.bbb));
        list.add(new TemplatesModel(R.drawable.bbb));
        list.add(new TemplatesModel(R.drawable.bbb));
        list.add(new TemplatesModel(R.drawable.bbb));
        list.add(new TemplatesModel(R.drawable.bbb));
        list.add(new TemplatesModel(R.drawable.bbb));
        list.add(new TemplatesModel(R.drawable.bbb));
        list.add(new TemplatesModel(R.drawable.bbb));
        list.add(new TemplatesModel(R.drawable.bbb));*/

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.child("Data").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot: snapshot.getChildren()){
                    Log.d(TAG, "onDataChange: ravi1"+dataSnapshot.getValue().toString());
                    Log.d(TAG, "onDataChange: ravi3"+dataSnapshot.getKey());
                    list.add(dataSnapshot.getValue().toString());
                }
                TemplatesAdapters adapter = new TemplatesAdapters(list,getApplicationContext());
                recyclerView.setAdapter(adapter);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}
