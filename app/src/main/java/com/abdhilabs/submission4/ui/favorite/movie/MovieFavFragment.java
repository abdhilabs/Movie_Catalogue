package com.abdhilabs.submission4.ui.favorite.movie;


import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.abdhilabs.submission4.R;
import com.abdhilabs.submission4.adapter.MovieAdapter;
import com.abdhilabs.submission4.model.MovieItem;

import java.util.ArrayList;
import java.util.List;

public class MovieFavFragment extends Fragment implements MovieFavView.View {
    private RecyclerView recyclerView;
    private ArrayList<MovieItem> dataFav = new ArrayList<>();

    private final MovieFavPresenter presenter = new MovieFavPresenter(this);
    SwipeRefreshLayout swipeFav;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_movie_fav, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.rvFavorite);
        swipeFav = view.findViewById(R.id.swipeFavoriteMovie);
        presenter.getDataListMovie(getContext());
        swipeFav.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.getDataListMovie(getContext());
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        MovieAdapter adapter = new MovieAdapter(getActivity(), dataFav);
        recyclerView.setAdapter(adapter);

    }

    @Override
    public void hideRefresh() {
        swipeFav.setRefreshing(false);
    }

    @Override
    public void showDataList(List<MovieItem> movieItems) {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(new MovieAdapter(getContext(), movieItems));
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.getDataListMovie(getContext());
    }
}