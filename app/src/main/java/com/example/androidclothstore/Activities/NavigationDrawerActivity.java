package com.example.androidclothstore.Activities;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.androidclothstore.Fragments.ContactFragment;
import com.example.androidclothstore.Fragments.ProductsFragment;
import com.example.androidclothstore.Fragments.ProfileFragment;
import com.example.androidclothstore.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

public class NavigationDrawerActivity extends AppCompatActivity {

    private NavigationView navigationView;
    private DrawerLayout drawer;
    private View navHeader;
    private ImageView imgNavHeaderBg, imgProfile;
    private TextView txtName, txtWebsite;
    private Toolbar toolbar;
    private FloatingActionButton fab;
    private Context context;

    String notificationNo="";

    public static int navItemIndex = 0;


    private static final String TAG_Profile = "profile";
    private static final String TAG_Products= "products";
    private static final String TAG_Cart = "cart";
    private static final String TAG_Orders = "orders";
    private static final String TAG_Contact = "contact us";
    public static String CURRENT_TAG = TAG_Products;


    private String[] activityTitles;

    SharedPreferences shared;
    private boolean shouldLoadHomeFragOnBackPress = true;
    private Handler mHandler;
    String email;

    AlertDialog alertdialog;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_drawer);

        toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        mHandler = new Handler();

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        fab = (FloatingActionButton) findViewById(R.id.fab);

        navHeader = navigationView.getHeaderView(0);

        activityTitles = getResources().getStringArray(R.array.nav_item_activity_titles);

        setUpNavigationView();

        if (savedInstanceState == null)
        {
            navItemIndex = 0;
            CURRENT_TAG = TAG_Products;
            loadUserFragment();
        }

        shared= getSharedPreferences("Mypref", Context.MODE_PRIVATE);
        email = shared.getString("email", "");
    }

    private void loadUserFragment()
    {
        selectNavMenu();

//        setToolbarTitle();

        if (getSupportFragmentManager().findFragmentByTag(CURRENT_TAG) != null)
        {
            drawer.closeDrawers();


            toggleFab();
            return;
        }

        Runnable mPendingRunnable = new Runnable()
        {
            @Override
            public void run() {

                Fragment fragment = getuserFragment();
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
                fragmentTransaction.replace(R.id.frame, fragment, CURRENT_TAG);
                fragmentTransaction.commitAllowingStateLoss();
            }
        };

        if (mPendingRunnable != null)
        {
            mHandler.post(mPendingRunnable);
        }
        toggleFab();
        drawer.closeDrawers();
        invalidateOptionsMenu();
    }

    private Fragment getuserFragment()
    {
        switch (navItemIndex) {
            case 0:
                ProfileFragment profileFragment = new  ProfileFragment();
                return profileFragment;
            case 1:
                ProductsFragment productsFragment = new ProductsFragment();
                return productsFragment;

            case 2:
                return null;
            case 3:
                return null;
            case 4:
                ContactFragment contactFragment = new ContactFragment();
                return contactFragment;
            case 5:

            default:
                productsFragment = new ProductsFragment();
                return productsFragment;

        }
    }

    private void setToolbarTitle()
    {

        //   getSupportActionBar().setTitle(activityTitles[navItemIndex]);
    }

    private void selectNavMenu()
    {
        navigationView.getMenu().getItem(navItemIndex).setChecked(true);
    }

    private void setUpNavigationView() {

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {


            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {


                switch (menuItem.getItemId()) {

                    case R.id.nav_profile:
                        navItemIndex = 0;
                        CURRENT_TAG = TAG_Profile;
                        break;

                    case R.id.nav_products:
                        navItemIndex = 1;
                        CURRENT_TAG = TAG_Products;
                        break;

                    case R.id.nav_cart:
                        navItemIndex = 2;
                        CURRENT_TAG = TAG_Cart;
                        break;

                    case R.id.nav_order:
                        navItemIndex = 3;
                        CURRENT_TAG = TAG_Orders;
                        break;
                    case R.id.nav_contact:
                        navItemIndex = 4;
                        CURRENT_TAG = TAG_Contact;
                        break;
                    default:
                        navItemIndex = 0;
                        CURRENT_TAG = TAG_Products;
                        break;
                }


                if (menuItem.isChecked()) {
                    menuItem.setChecked(false);
                }
                else
                {
                    menuItem.setChecked(true);
                }
                menuItem.setChecked(true);

                loadUserFragment();

                return true;
            }
        });


        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.openDrawer, R.string.closeDrawer)
        {
            @Override
            public void onDrawerClosed(View drawerView)
            {
                super.onDrawerClosed(drawerView);
            }
            @Override
            public void onDrawerOpened(View drawerView) {
            }
        };
        drawer.setDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
    }




    @Override
    public void onBackPressed()
    {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        if (navItemIndex == 0)
        {
            getMenuInflater().inflate(R.menu.main, menu);
        }


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {

        int id = item.getItemId();


        if (id == R.id.action_logout)
        {


            alertdialog = new AlertDialog.Builder(NavigationDrawerActivity.this,R.style.alert).create();

            alertdialog.setTitle("Logout");
            alertdialog.setMessage("Are you sure ! logout ?");
            alertdialog.setCancelable(false);
            alertdialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener()
            {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    alertdialog.dismiss();

                }
            });

            alertdialog.setButton(DialogInterface.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener()
            {
                @Override
                public void onClick(DialogInterface dialog, int which)
                {

                    shared= getSharedPreferences("Mypref", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = shared.edit();
                    editor.clear();
                    editor.apply();
                    finish();
                    Intent intent = new Intent(NavigationDrawerActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            });
            alertdialog.show();

            // Toast.makeText(getApplicationContext(), "Logout user!", Toast.LENGTH_LONG).show();
            return true;
        }




        return super.onOptionsItemSelected(item);
    }


    private void toggleFab() {
        if (navItemIndex == 0)
            fab.show();
        else
            fab.hide();
    }
}