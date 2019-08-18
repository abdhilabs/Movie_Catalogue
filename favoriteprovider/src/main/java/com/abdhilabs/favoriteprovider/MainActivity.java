package com.abdhilabs.favoriteprovider;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.abdhilabs.favoriteprovider.adapter.ViewPageAdapter;
import com.abdhilabs.favoriteprovider.ui.MovieFragment;
import com.abdhilabs.favoriteprovider.ui.TvShowFragment;

public class MainActivity extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);

        ViewPageAdapter adapter = new ViewPageAdapter(getSupportFragmentManager());
        adapter.AddFragment(new MovieFragment(), getString(R.string.tab1));
        adapter.AddFragment(new TvShowFragment(), getString(R.string.tab2));
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }
}
