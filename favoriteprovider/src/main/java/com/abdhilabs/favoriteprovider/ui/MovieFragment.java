package com.abdhilabs.favoriteprovider.ui;


import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.CursorLoader;
import androidx.loader.content.Loader;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.abdhilabs.favoriteprovider.R;
import com.abdhilabs.favoriteprovider.adapter.MovieFavAdapter;

public class MovieFragment extends Fragment {
    private static final String AUTHORITY = "com.abdhilabs.submission4.provider";

    private static final Uri URI_MOVIE = Uri.parse(
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

        requireActivity().getSupportLoaderManager().initLoader(LOADER_FAV, null, mLoaderCallbacks);
    }

    private LoaderManager.LoaderCallbacks<Cursor> mLoaderCallbacks = new LoaderManager.LoaderCallbacks<Cursor>() {
        @NonNull
        @Override
        public Loader<Cursor> onCreateLoader(int id, @Nullable Bundle bundle) {
            if (id == LOADER_FAV) {
                return new CursorLoader(requireContext(),
                        URI_MOVIE,
                        null,
                        null, null, null);
            }
            throw new IllegalArgumentException();
        }

        @Override
        public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor cursor) {
            if (loader.getId() == LOADER_FAV) {
                adapter.setMovie(cursor);
            }
        }

        @Override
        public void onLoaderReset(@NonNull Loader<Cursor> loader) {
            if (loader.getId() == LOADER_FAV) {
                adapter.setMovie(null);
            }
        }
    };
}