package com.abdhilabs.favoriteprovider;

import android.os.Bundle;
import com.google.android.material.tabs.TabLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.abdhilabs.favoriteprovider.adapter.ViewPageAdapter;
import com.abdhilabs.favoriteprovider.ui.MovieFragment;
import com.abdhilabs.favoriteprovider.ui.TvShowFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TabLayout tabLayout = findViewById(R.id.tabLayout);
        ViewPager viewPager = findViewById(R.id.viewPager);

        ViewPageAdapter adapter = new ViewPageAdapter(getSupportFragmentManager());
        adapter.AddFragment(new MovieFragment(), getString(R.string.tab1));
        adapter.AddFragment(new TvShowFragment(), getString(R.string.tab2));
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }
}
