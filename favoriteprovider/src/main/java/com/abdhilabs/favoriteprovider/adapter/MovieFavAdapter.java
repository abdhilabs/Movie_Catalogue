package com.abdhilabs.favoriteprovider.adapter;

import android.content.Context;
import android.database.Cursor;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.abdhilabs.favoriteprovider.R;
import com.bumptech.glide.Glide;

public class MovieFavAdapter extends RecyclerView.Adapter<MovieFavAdapter.FavViewHolder> {
    private Cursor mCursor;
    private Context context;

    public MovieFavAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public FavViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new FavViewHolder(viewGroup);
    }

    @Override
    public void onBindViewHolder(@NonNull FavViewHolder holder, int i) {
        if (mCursor.moveToPosition(i)) {
            holder.tvTitle.setText(mCursor.getString(
                    mCursor.getColumnIndexOrThrow("title")
            ));
            holder.tvReleaseDate.setText(mCursor.getString(
                    mCursor.getColumnIndexOrThrow("release_date")
            ));
            String pathGambar = mCursor.getString(
                    mCursor.getColumnIndexOrThrow("backdrop_path")
            );
            Glide.with(context)
                    .load("https://image.tmdb.org/t/p/w500" + pathGambar)
                    .into(holder.imgPoster);
        }
    }

    @Override
    public int getItemCount() {
        return mCursor == null ? 0 : mCursor.getCount();
    }

    public void setMovie(Cursor cursor) {
        mCursor = cursor;
        notifyDataSetChanged();
    }

    static class FavViewHolder extends RecyclerView.ViewHolder {
        ImageView imgPoster;
        TextView tvTitle, tvReleaseDate;

        FavViewHolder(ViewGroup parent) {
            super(LayoutInflater.from(parent.getContext()).inflate(
                    R.layout.item_movie, parent, false));
            imgPoster = itemView.findViewById(R.id.imgBannerMovie);
            tvTitle = itemView.findViewById(R.id.tvTitleMovie);
            tvReleaseDate = itemView.findViewById(R.id.tvReleaseDateMovie);
        }
    }
}
