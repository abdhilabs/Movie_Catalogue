package com.abdhilabs.submission4.ui.tvshow;

import com.abdhilabs.submission4.model.TvShowItem;

import java.util.ArrayList;

public interface TvShowView {
    void showLoad();

    void finishLoad();

    void showList(ArrayList<TvShowItem> listTvShow);

    void noData();
}
