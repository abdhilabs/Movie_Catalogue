package com.abdhilabs.submission4;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.abdhilabs.submission4.ui.FavoriteFragment;
import com.abdhilabs.submission4.ui.SettingFragment;
import com.abdhilabs.submission4.ui.movie.MovieFragment;
import com.abdhilabs.submission4.ui.tvshow.TvShowFragment;
import com.gauravk.bubblenavigation.BubbleNavigationConstraintView;
import com.gauravk.bubblenavigation.listener.BubbleNavigationChangeListener;

public class MainActivity extends AppCompatActivity {
    BubbleNavigationConstraintView buble;
    Fragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // kita set default nya Home Fragment
        if (savedInstanceState == null) {
            loadFragment(new MovieFragment());
        }

        buble = findViewById(R.id.floating_top_bar_navigation);
        buble.setNavigationChangeListener(new BubbleNavigationChangeListener() {
            @Override
            public void onNavigationChanged(View view, int position) {
                switch (view.getId()) {
                    case R.id.movie:
                        fragment = new MovieFragment();
                        break;
                    case R.id.tvShow:
                        fragment = new TvShowFragment();
                        break;
                    case R.id.favorite:
                        fragment = new FavoriteFragment();
                        break;
                    case R.id.setting:
                        fragment = new SettingFragment();
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.fl_container, fragment).commit();
            }
        });
    }

    // method untuk load fragment yang sesuai
    private void loadFragment(Fragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fl_container, fragment)
                    .commit();
        }
    }
}
