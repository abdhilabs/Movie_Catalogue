package com.abdhilabs.favoriteprovider.ui;


import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.abdhilabs.favoriteprovider.R;
import com.abdhilabs.favoriteprovider.adapter.MovieFavAdapter;

public class MovieFragment extends Fragment {
    public static final String AUTHORITY = "com.abdhilabs.submission4.provider";

    public static final Uri URI_MOVIE = Uri.parse(
            "content://" + AUTHORITY + "/" + "tMovie");

    private static final int LOADER_FAV = 1;
    private MovieFavAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_movie, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView rvMovie = view.findViewById(R.id.rvMovie);
        rvMovie.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new MovieFavAdapter(getActivity());
        rvMovie.setAdapter(adapter);

        getActivity().getSupportLoaderManager().initLoader(LOADER_FAV, null, mLoaderCallbacks);
    }

    LoaderManager.LoaderCallbacks<Cursor> mLoaderCallbacks = new LoaderManager.LoaderCallbacks<Cursor>() {
        @NonNull
        @Override
        public Loader<Cursor> onCreateLoader(int id, @Nullable Bundle bundle) {
            switch (id) {
                case LOADER_FAV:
                    return new CursorLoader(getContext(),
                            URI_MOVIE,
                            null,
                            null, null, null);
                default:
                    throw new IllegalArgumentException();
            }
        }

        @Override
        public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor cursor) {
            switch (loader.getId()) {
                case LOADER_FAV:
                    adapter.setMovie(cursor);
                    break;
            }
        }

        @Override
        public void onLoaderReset(@NonNull Loader<Cursor> loader) {
            switch (loader.getId()) {
                case LOADER_FAV:
                    adapter.setMovie(null);
                    break;
            }
        }
    };
}