package com.primenova.learningui;

import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView tvToolbar;
    private AppBarLayout appBarLayout;
    private NestedScrollView scrollView;
    private boolean scrollFlag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Toolbar toolbar = findViewById(R.id.toolbar);
        final FrameLayout frameLayout = findViewById(R.id.floatingLayout);
        CardView floatingbtn = findViewById(R.id.floatingbtn);
        TextView headingmain = findViewById(R.id.headingmain);

        setSupportActionBar(toolbar);
        tvToolbar = findViewById(R.id.txt_toolbar);
        final LinearLayout linearLayout = findViewById(R.id.secondTxt);
        appBarLayout = findViewById(R.id.app_bar);
        appBarLayout.bringToFront();
        appBarLayout.setBackgroundResource(R.drawable.hero);

        frameLayout.bringToFront();
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp_oval);

        Shader textShader = new LinearGradient(0, 0, 150, 20,
                new int[]{Color.parseColor("#ff6968"), Color.parseColor("#ff9742")},
                new float[]{0, 1}, Shader.TileMode.CLAMP);
        tvToolbar.getPaint().setShader(textShader);
        headingmain.getPaint().setShader(textShader);

        floatingbtn.setBackgroundResource(R.drawable.gradientbg);

        Inner_rv1 fragment_rv1 = new Inner_rv1();
        RV2 rv2 = new RV2();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.placeholder1, fragment_rv1);
        fragmentTransaction.add(R.id.placeholder2, rv2);
        fragmentTransaction.commit();

        scrollView = findViewById(R.id.scrollViewmain);
        scrollView.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
            @Override
            public void onScrollChanged() {
                int scrollY = scrollView.getScrollY();
                if (scrollY > 200) {
                    if (!scrollFlag) {
                        toolbar.setBackgroundColor(Color.WHITE);
                        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);
                        tvToolbar.setVisibility(View.VISIBLE);
                        linearLayout.setVisibility(View.INVISIBLE);
                        frameLayout.setVisibility(View.GONE);
                        appBarLayout.setBackgroundResource(R.color.colorPrimary);
                        scrollFlag = true;
                    }
                } else {
                    if (scrollFlag) {
                        toolbar.setBackgroundColor(Color.TRANSPARENT);
                        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp_oval);
                        linearLayout.setVisibility(View.VISIBLE);
                        tvToolbar.setVisibility(View.INVISIBLE);
                        appBarLayout.setBackgroundResource(R.drawable.hero);
                        frameLayout.setVisibility(View.VISIBLE);
                        scrollFlag = false;
                    }
                }
            }
        });
    }


    // create an action bar button
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        return super.onCreateOptionsMenu(menu);
    }

    // handle button activities
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.mybutton) {
            // do something here
        }
        return super.onOptionsItemSelected(item);
    }
}
