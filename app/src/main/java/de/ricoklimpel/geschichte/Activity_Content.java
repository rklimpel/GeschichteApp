package de.ricoklimpel.geschichte;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.nineoldandroids.animation.Animator;

public class Activity_Content extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    private View mAboutView;
    private View mÜbersichtView;
    private int mShortAnimationDuration = 1000;
    private String Startwert;

    Toolbar toolbar;

    LinearLayout Layout_About;
    LinearLayout Layout_Ubersicht;

    LinearLayout layout_now;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_content_standard);

        Layout_About = (LinearLayout)findViewById(R.id.Layout_About);
        Layout_Ubersicht = (LinearLayout)findViewById(R.id.Layout_Ubersicht);

        Layout_About.setVisibility(View.VISIBLE);
        Layout_Ubersicht.setVisibility(View.GONE);

        layout_now = Layout_About;

        init_toolbar_drawer();

    }



    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.activity__menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }




    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_share) {


        } else if (id == R.id.nav_menu_übersicht) {

            set_layout_übersicht();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;

    }

    private void set_layout_übersicht() {

        if(layout_now != Layout_Ubersicht){

            Layout_Transition(layout_now,Layout_Ubersicht, Techniques.FadeInDown,Techniques.FadeOutDown,800);
            layout_now = Layout_Ubersicht;
            toolbar.setTitle("Übersicht");



            FloatingActionButton fab = (FloatingActionButton)findViewById(R.id.fab);
            fab.setBackgroundDrawable(getResources().getDrawable(R.drawable.ic_filter_list_white_24dp));
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    set_layout_about();

                }
            });

        }
    }

    private void set_layout_about() {

        if(layout_now != Layout_About){


            Layout_Transition(layout_now,Layout_About, Techniques.FadeInDown,Techniques.FadeOutDown,800);
            layout_now = Layout_About;
            toolbar.setTitle("Über die App");


            FloatingActionButton fab = (FloatingActionButton)findViewById(R.id.fab);
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    set_layout_übersicht();

                }
            });

        }

    }

    private void Layout_Transition(final LinearLayout layout_from , LinearLayout layout_to,
                                   Techniques Tech_in, Techniques Tech_Out , Integer Duration){

        layout_to.setVisibility(View.VISIBLE);

        YoYo.with(Tech_in).duration(Duration).playOn(layout_to);
        YoYo.with(Tech_Out).duration(Duration).withListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                layout_from.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        }).playOn(layout_from);

    }



    private void init_toolbar_drawer() {


        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }

}
