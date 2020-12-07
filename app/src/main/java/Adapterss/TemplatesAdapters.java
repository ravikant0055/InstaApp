package Adapterss;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;


import com.example.project1.R;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;


public class TemplatesAdapters extends RecyclerView.Adapter<TemplatesAdapters.viewHolder> {

    ArrayList<String> list;
    Context context;

    public TemplatesAdapters(ArrayList<String> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.sample_recyclerview,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        String model = list.get(position);
        Log.d(TAG, "onDataChange: ravi2"+model);
        holder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"Image clicked",Toast.LENGTH_LONG).show();
                BottomSheetDialog bottomSheetDialog =new BottomSheetDialog(context,R.style.Theme_Design_BottomSheetDialog);
                View bottomSheetView =((FragmentActivity)context).getLayoutInflater()
                        .inflate(R.layout.layout_bottom_sheet,(LinearLayout)bottomSheetDialog.findViewById(R.id.layout_bottom_sheet_container));
                bottomSheetDialog.setContentView(bottomSheetView);
                bottomSheetDialog.show();
            }
        });
        Picasso.get().load(model).into(holder.img);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.imageviews);

        }
    }
}
