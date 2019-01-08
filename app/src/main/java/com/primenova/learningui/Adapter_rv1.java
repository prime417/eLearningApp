package com.primenova.learningui;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;

import java.util.List;

public class Adapter_rv1 extends RecyclerView.Adapter<Adapter_rv1.MyViewHolder> {

    private List<Model_rv1> movieList;
    private Context context;

    public Adapter_rv1(Context context) {
        this.context = context;
    }

    public void setMovieList(List<Model_rv1> movieList) {
        this.movieList = movieList;
        notifyDataSetChanged();
    }

    @Override
    public Adapter_rv1.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_adapter, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final Adapter_rv1.MyViewHolder holder, int position) {

        holder.title.setText(movieList.get(position).getTitle());

//        ImageLoader imageLoader = AppController.getInstance().getImageLoader();
        ImageLoader imageLoader = MySingleton.getInstance(context).getImageLoader();
        imageLoader.get(movieList.get(position).getImageUrl(), new ImageLoader.ImageListener() {

            @Override
            public void onErrorResponse(VolleyError error) {

            }

            @Override
            public void onResponse(ImageLoader.ImageContainer response, boolean isImmediate) {
                holder.image.setImageBitmap(response.getBitmap());
            }
        });

    }

    @Override
    public int getItemCount() {
        if (movieList != null) {
            return movieList.size();
        } else {
            return 0;
        }

    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView title;
        private ImageView image;

        public MyViewHolder(View view) {
            super(view);
            title = view.findViewById(R.id.title);
            image = view.findViewById(R.id.image);
        }
    }
}
