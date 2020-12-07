package com.example.project1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import Adapterss.SlideAdapter;
import Modelss.SlideModel;

public class CreativeIdeas extends AppCompatActivity {
    private ViewPager2 viewPager2;
    Button btnpluss;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creative_ideas);

        viewPager2 = findViewById(R.id.viewImageSlider);
        btnpluss = findViewById(R.id.btnplus);

        List<SlideModel> slideModels = new ArrayList<>();
        slideModels.add(new SlideModel(R.drawable.a));
        slideModels.add(new SlideModel(R.drawable.b));
        slideModels.add(new SlideModel(R.drawable.c));
        slideModels.add(new SlideModel(R.drawable.d));
        slideModels.add(new SlideModel(R.drawable.e));
        slideModels.add(new SlideModel(R.drawable.f));
        slideModels.add(new SlideModel(R.drawable.g));
        slideModels.add(new SlideModel(R.drawable.h));

        viewPager2.setAdapter(new SlideAdapter(slideModels, viewPager2));

        viewPager2.setClipToPadding(false);
        viewPager2.setClipChildren(false);
        viewPager2.setOffscreenPageLimit(3);
        viewPager2.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);

        CompositePageTransformer compositePageTransformer = new CompositePageTransformer();
        compositePageTransformer.addTransformer(new MarginPageTransformer(40));
        compositePageTransformer.addTransformer(new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {
                float r = 1 - Math.abs(position);
                page.setScaleY(0.85f + r * 0.15f);
            }
        });

        viewPager2.setPageTransformer(compositePageTransformer);

        btnpluss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CreativeIdeas.this,PlusActivity.class);
                startActivity(intent);
            }
        });

    }
}
