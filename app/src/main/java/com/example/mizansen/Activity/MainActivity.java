package com.example.mizansen.Activity;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.example.mizansen.CustomView.NonSwipeableViewPager;
import com.example.mizansen.Fragment.BottomBar.CategoryFragment;
import com.example.mizansen.Fragment.BottomBar.MyVideosFragment;
import com.example.mizansen.Fragment.BottomBar.ShowcaseFragment;
import com.example.mizansen.Helper.LanguageHelper;
import com.example.mizansen.Helper.LocaleHelper;
import com.example.mizansen.R;
import java.util.ArrayList;
import java.util.List;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;

public class MainActivity extends FragmentActivity {

    BottomBarAdapter adapter;
    public NonSwipeableViewPager viewPager;
    ImageView imageViewSerarch;
    String TAG = "TAG_MainActivity";
    LanguageHelper languageHelper = new LanguageHelper();
    MeowBottomNavigation bottomNavigation;


    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(LocaleHelper.onAttach(newBase, "fa"));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        imageViewSerarch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


        bottomNavigation.setOnClickMenuListener(new Function1<MeowBottomNavigation.Model, Unit>() {
            @Override
            public Unit invoke(MeowBottomNavigation.Model p1) {
                Log.i(TAG, "model " + p1.getId());
                MenuAction(p1.getId());
                return Unit.INSTANCE;
            }
        });


    }

    void MenuAction(int select) {

        if (select == 3){
            startActivity(new Intent(MainActivity.this, ProfileActivity.class));
            finish();
        }

        viewPager.setCurrentItem(select);

    }

    @SuppressLint({"ResourceType", "WrongViewCast"})
    void initView() {

        imageViewSerarch = findViewById(R.id.main_search);


        bottomNavigation = findViewById(R.id.bottomNavigationView);
        bottomNavigation.add(new MeowBottomNavigation.Model(0, R.drawable.ic_home));

        bottomNavigation.add(new MeowBottomNavigation.Model(1, R.drawable.ic_category));

        bottomNavigation.add(new MeowBottomNavigation.Model(2, R.drawable.ic_mywishlist));

        bottomNavigation.add(new MeowBottomNavigation.Model(3, R.drawable.ic_user));

        bottomNavigation.show(0, true);


        languageHelper.GetLanguage(MainActivity.this);


        viewPager = findViewById(R.id.viewPager);
        setupViewPager(viewPager);

    }

    void setupViewPager(NonSwipeableViewPager viewPager) {

        adapter = new BottomBarAdapter(getSupportFragmentManager());

        ShowcaseFragment showcaseFragment = new ShowcaseFragment();
        CategoryFragment categoryFragment = new CategoryFragment();
        MyVideosFragment myVideosFragment = new MyVideosFragment();

        adapter.addFragments(showcaseFragment);
        adapter.addFragments(categoryFragment);
        adapter.addFragments(myVideosFragment);

        viewPager.setAdapter(adapter);

    }

    public class BottomBarAdapter extends FragmentPagerAdapter {
        private final List<Fragment> fragments = new ArrayList<>();

        public BottomBarAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        // Our custom method that populates this Adapter with Fragments
        public void addFragments(Fragment fragment) {
            fragments.add(fragment);
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

    }

}
