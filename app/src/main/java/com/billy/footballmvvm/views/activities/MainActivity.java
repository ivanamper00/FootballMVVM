package com.billy.footballmvvm.views.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.widget.Toast;

import com.billy.footballmvvm.R;
import com.billy.footballmvvm.views.fragments.AboutFragment;
import com.billy.footballmvvm.views.fragments.HomeFragment;
import com.billy.footballmvvm.views.fragments.LiveFragment;
import com.billy.footballmvvm.views.fragments.MatchesFragment;
import com.billy.footballmvvm.views.fragments.TeamsFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    int flag;
    private FragmentManager fragmentManager;
    private BottomNavigationView bottomNavigationView;
    private BottomNavigationView.OnNavigationItemSelectedListener selector = (item)->{
        switch (item.getItemId()){
            case R.id.home:
                flag = 0;
                fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.main_frame, new HomeFragment()).commit();
                return true;
            case R.id.live:
                flag = 0;
                fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.main_frame, new LiveFragment()).commit();
                return true;
            case R.id.matches:
                flag = 0;
                fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.main_frame, new MatchesFragment()).commit();
                return true;
            case R.id.teams:
                flag = 0;
                fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.main_frame, new TeamsFragment()).commit();
                return true;
            case R.id.about:
                flag = 0;
                fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.main_frame, new AboutFragment()).commit();
                return true;
        }
        return false;
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
//                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
//        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        bottomNavigationView = findViewById(R.id.main_navigation_menu);
        bottomNavigationView.setOnNavigationItemSelectedListener(selector);
        bottomNavigationView.setSelectedItemId(R.id.home);
        flag = 0;

    }

    @Override
    public void onBackPressed() {
        if(flag == 0){
            bottomNavigationView.setSelectedItemId(R.id.home);
            flag++;
        }else if(flag == 1){
            Toast.makeText(this, "Press Again to Exit", Toast.LENGTH_SHORT).show();
        }else{
            System.exit(0);
        }
    }
}