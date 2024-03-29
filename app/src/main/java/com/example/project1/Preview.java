package com.example.project1;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

import Adapterss.PreviewAdapter;

public class Preview extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<String> list;
    PreviewAdapter adapter;
// shared preferences
    SharedPreferences sp;
    Gson gson;
    private final int IMG_REQUEST_ID = 10;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview);

        //        initialize shared preferences variables
        sp = getSharedPreferences("shared preferences", Context.MODE_PRIVATE);
        gson=new Gson();


        Toolbar toolbar = findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        recyclerView = findViewById(R.id.recyclerview);
        BottomNavigationView bottomNavigationView = findViewById(R.id.navbar);
        bottomNavigationView.setOnNavigationItemSelectedListener(navlistner);

        // It will Load the data the Array list of string from Shared preferences which is stored as "list" key.
        String json =sp.getString("list",null);
        Type type= new TypeToken<ArrayList<String>>() {}.getType();
        list=gson.fromJson(json,type);

        //if there is no list in shared preference,initialize the list
        if(list==null){
            list = new ArrayList<>();
        }


        adapter = new PreviewAdapter(list, this);
        recyclerView.setAdapter(adapter);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        recyclerView.setLayoutManager(gridLayoutManager);

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }


    //code for drag and drop images
    ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP
            | ItemTouchHelper.DOWN
            | ItemTouchHelper.START | ItemTouchHelper.END, 0) {
        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            int fromPosition = viewHolder.getAdapterPosition();
            int toPosition = target.getAdapterPosition();

            if (recyclerView.getAdapter() != null) {
                (recyclerView.getAdapter()).notifyItemMoved(fromPosition, toPosition);
            }
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
        }
    };

    //code for toolbar menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {

            case R.id.creative_idea:
                Intent dash = new Intent(Preview.this, CreativeIdeas.class);
                startActivity(dash);
                break;
            case R.id.templates:
                Intent dash1 = new Intent(Preview.this, Dashboard.class);
                startActivity(dash1);
                break;
            default:
                break;
        }
        return true;
    }

    //code for BottomNavigation items
    private BottomNavigationView.OnNavigationItemSelectedListener navlistner =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuitem) {
                    int id = menuitem.getItemId();
                    switch (id) {
                        case R.id.btnlight:
                            Toast.makeText(Preview.this, "Light Mode", Toast.LENGTH_SHORT).show();
                            break;
                        case R.id.btnadd:
                            requestimage();
                            break;
                        case R.id.btndark:
                            Toast.makeText(Preview.this, "Dark Mode", Toast.LENGTH_SHORT).show();
                            break;
                        default:
                            break;
                    }
                    return true;
                }
            };


    //code for enter in phone storage
    private void requestimage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_OPEN_DOCUMENT);
        startActivityForResult(Intent.createChooser(intent, "Select pictue"), IMG_REQUEST_ID);
    }

    //code for getting images from phone storage
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == IMG_REQUEST_ID && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri imguri = data.getData();
            list.add(imguri.toString());

//            update the list with new uri of image that is selected
//            it will save the string uri to the list
            SharedPreferences.Editor editor=sp.edit();
            String json =gson.toJson(list);
            editor.putString("list",json);
            editor.apply();

            adapter.notifyDataSetChanged();

        }
    }
}