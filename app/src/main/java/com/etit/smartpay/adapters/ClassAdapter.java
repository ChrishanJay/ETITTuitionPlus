package com.etit.smartpay.adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.etit.smartpay.R;
import com.etit.smartpay.model.TuitionClass;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class ClassAdapter extends
        RecyclerView.Adapter<ClassAdapter.ViewHolder> {

    private List<TuitionClass> mClasses;
    private Context context;

    public ClassAdapter(List<TuitionClass> classes) {
        this.mClasses = classes;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View contactView = inflater.inflate(R.layout.item_tuition, parent, false);
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TuitionClass tuitionClass = mClasses.get(position);

        holder.className.setText(tuitionClass.getClassName());
        holder.date.setText(tuitionClass.getTime());
        holder.location.setText(tuitionClass.getLocation());

        try {
            // get input stream
            InputStream ims = context.getAssets().open("images/"+tuitionClass.getImage());
            // load image as Drawable
            Drawable d = Drawable.createFromStream(ims, null);
            // set image to ImageView
            holder.classImage.setImageDrawable(d);
        }
        catch(IOException ex) {
            Log.e("ETIT", ex.toString());
            return;
        }
    }

    @Override
    public int getItemCount() {
        return mClasses.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView className;
        public TextView date;
        public TextView location;
        public ImageView classImage;

        public ViewHolder(View itemView) {
            super(itemView);
            classImage = (ImageView) itemView.findViewById(R.id.classImage);
            className = (TextView) itemView.findViewById(R.id.className);
            date = (TextView) itemView.findViewById(R.id.classDate);
            location = (TextView) itemView.findViewById(R.id.classLocation);
        }

    }
}
