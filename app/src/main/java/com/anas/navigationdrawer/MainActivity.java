package com.anas.navigationdrawer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    DrawerLayout DL;
    NavigationView vNV;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DL = findViewById(R.id.DL);
        vNV = findViewById(R.id.vNV);
        toolbar = findViewById(R.id.toolbar);

        //toolbar link
        setSupportActionBar(toolbar);

        //if Title is set using this then Titlein toolbar_layout will not show
        if (getSupportActionBar()!=null){
            getSupportActionBar().setTitle("ASUS");
        }

        //to allow functionality of Home button
        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle
                (this,DL,toolbar,R.string.OpenDrawer,R.string.CloseDrawer);

        DL.addDrawerListener(toggle);

        //showing hamburger icon
        toggle.syncState();

        //default frag
        loadFrag(new HomeFragment(),true);

        vNV.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();

                if (itemId==R.id.home_item) {
                    loadFrag(new HomeFragment(), false);
                }
                else if (itemId==R.id.search_item) {
                    Toast.makeText(MainActivity.this, "Search", Toast.LENGTH_SHORT).show();
                }
                else if (itemId==R.id.utilites_item) {
                    Toast.makeText(MainActivity.this, "Utitlities", Toast.LENGTH_SHORT).show();
                }
                else if (itemId==R.id.help_item) {
                    Toast.makeText(MainActivity.this, "Help", Toast.LENGTH_SHORT).show();
                }
                else if (itemId==R.id.profile_item) {
                    Toast.makeText(MainActivity.this, "Profile", Toast.LENGTH_SHORT).show();
                }

                //close drawer on click of item
                DL.closeDrawer(GravityCompat.START);
                return true;
            }


        });

    }

    private void loadFrag(Fragment fragment_name, boolean flag) {

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();

        if (flag==true)
            ft.add(R.id.FL, fragment_name);

        else
            ft.replace(R.id.FL, fragment_name);

        ft.commit();
    }

    @Override
    public void onBackPressed() {
        if (DL.isDrawerOpen(GravityCompat.START)){
            DL.closeDrawer(GravityCompat.START);
        }
        else{
            super.onBackPressed();
        }
    }
}