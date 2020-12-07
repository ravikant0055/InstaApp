package Adapterss;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.example.project1.R;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.List;

import Modelss.SlideModel;

public class SlideAdapter extends RecyclerView.Adapter<SlideAdapter.SliderViewHolder> {
    private List<SlideModel> slideModels;
    private ViewPager2 viewPager2;

    public SlideAdapter(List<SlideModel> slideModels, ViewPager2 viewPager2) {
        this.slideModels = slideModels;
        this.viewPager2 = viewPager2;
    }

    @NonNull
    @Override
    public SliderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SliderViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.slide_item_container,
                        parent,
                        false
                )
        );
    }

    @Override
    public void onBindViewHolder(@NonNull SliderViewHolder holder, int position) {
        holder.setimage(slideModels.get(position));
        if (position == slideModels.size() - 2){
            viewPager2.post(runnable);
        }
    }

    @Override
    public int getItemCount() {
        return slideModels.size();
    }

    class SliderViewHolder extends RecyclerView.ViewHolder{
        private RoundedImageView imageView;

        SliderViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.imageSlider);
        }
        void setimage(SlideModel slideModel)
        //if you want to image from yhe internet
        //you can put code here using glider or picasso
        {
            imageView.setImageResource(slideModel.getImage());
        }
    }
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            slideModels.addAll(slideModels);
            notifyDataSetChanged();
        }
    };
}
