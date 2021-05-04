package io.giodude.englishpremierleague;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import dagger.hilt.android.AndroidEntryPoint;
import io.giodude.englishpremierleague.View.HomeView;
import io.giodude.englishpremierleague.View.LiveView;
import io.giodude.englishpremierleague.View.MatchesView;
import io.giodude.englishpremierleague.View.TeamView;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    boolean doubleBackToExitPressedOnce = false;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBar;
    androidx.appcompat.widget.Toolbar toolbar;
    NavigationView navigationView;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    public String tag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MainActivity.this.setTitle("");
        fragmentManager = getSupportFragmentManager();
        init();


    }

    public void init() {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.navMain);
        navigationView = findViewById(R.id.drawer);
        navigationView.setNavigationItemSelectedListener(this);

        actionBar = new ActionBarDrawerToggle(this, drawerLayout, toolbar, (R.string.Open), (R.string.Close));
        drawerLayout.addDrawerListener(actionBar);
        actionBar.setDrawerIndicatorEnabled(true);
        actionBar.syncState();

        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fLayout, new HomeView());
        fragmentTransaction.commit();
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        switch (item.getItemId()) {
            case R.id.homes: {
                fragmentTransaction.replace(R.id.fLayout, new HomeView());
                fragmentTransaction.commit();
                drawerLayout.closeDrawer(GravityCompat.START);
                break;

            }
            case R.id.teams: {

                fragmentTransaction.replace(R.id.fLayout, new TeamView());
                fragmentTransaction.commit();
                drawerLayout.closeDrawer(GravityCompat.START);
                break;

            }
            case R.id.match: {

                fragmentTransaction.replace(R.id.fLayout, new MatchesView());
                fragmentTransaction.commit();
                drawerLayout.closeDrawer(GravityCompat.START);
                break;

            }
            case R.id.live: {

                fragmentTransaction.replace(R.id.fLayout, new LiveView());
                fragmentTransaction.commit();
                drawerLayout.closeDrawer(GravityCompat.START);
                break;

            }
        }


        fragmentManager.popBackStack();
        return true;
    }

    @Override
    public void onBackPressed() {

        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Double Click the Back Button to Exit!", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(() -> doubleBackToExitPressedOnce = false, 2000);

    }
}