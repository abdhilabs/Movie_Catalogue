package com.abdhilabs.submission4.receiver;

import android.content.Context;

import com.abdhilabs.submission4.model.MovieItem;
import com.abdhilabs.submission4.model.TvShowItem;

import java.util.ArrayList;

public interface ReminderView {
    interface View {
        void setMovies(Context context, ArrayList<MovieItem> movies, int notifId);

        void setTvShow(Context context, ArrayList<TvShowItem> tvShow, int notifId);
    }

    interface Presenter {
        void requestMovies(String date);

        void requestTvShows(String date);
    }
}
