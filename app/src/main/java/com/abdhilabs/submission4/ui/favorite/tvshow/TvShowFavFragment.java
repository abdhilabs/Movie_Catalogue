package com.abdhilabs.submission4.ui.favorite.tvshow;

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
import com.abdhilabs.submission4.adapter.TvShowAdapter;
import com.abdhilabs.submission4.model.TvShowItem;

import java.util.ArrayList;
import java.util.List;

public class TvShowFavFragment extends Fragment implements TvShowFavView.View {
    private RecyclerView recyclerView;
    private List<TvShowItem> dataFav = new ArrayList<>();

    private final TvShowFavPresenter presenter = new TvShowFavPresenter(this);
    SwipeRefreshLayout swipeFav;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tv_show_fav, container, false);
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
        TvShowAdapter adapter = new TvShowAdapter(getActivity(), dataFav);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void hideRefresh() {
        swipeFav.setRefreshing(false);
    }

    @Override
    public void showDataList(List<TvShowItem> tvShowItems) {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(new TvShowAdapter(getContext(), tvShowItems));
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.getDataListMovie(getContext());
    }
}
