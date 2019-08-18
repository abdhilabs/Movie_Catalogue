package com.abdhilabs.submission4.ui.movie;

import com.abdhilabs.submission4.model.MovieItem;

import java.util.ArrayList;

public interface MovieView {
    void showLoad();

    void finishLoad();

    void showList(ArrayList<MovieItem> listMovie);

    void noData();
}
