package de.ricoklimpel.geschichte;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
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
import android.widget.Button;
import android.widget.LinearLayout;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.nineoldandroids.animation.Animator;

public class Activity_Content extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{


    private View mAboutView;
    private View mÜbersichtView;
    private int mShortAnimationDuration = 600;
    private String Startwert;

    Techniques AnimIn = Techniques.FadeInDown;
    Techniques AnimOut = Techniques.FadeOut;
    Integer AnimDuration = 600;

    Toolbar toolbar;
    DrawerLayout drawer;
    NavigationView navigationView;

    LinearLayout Layout_About;
    LinearLayout Layout_Ubersicht;
    LinearLayout layout_now;
    LinearLayout Layout_planwirtschaft_allgmein;

    CollapsingToolbarLayout CTL;

    FloatingActionButton fab;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_content_standard);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        fab = (FloatingActionButton)findViewById(R.id.fab);

        CTL = (CollapsingToolbarLayout)findViewById(R.id.collapsing_toolbar_layout);

        Layout_About = (LinearLayout)findViewById(R.id.Layout_About);
        Layout_Ubersicht = (LinearLayout)findViewById(R.id.Layout_Ubersicht);
        Layout_planwirtschaft_allgmein = (LinearLayout)findViewById(R.id.Layout_Planwirtschaft_allgemein);

        Layout_About.setVisibility(View.VISIBLE);
        Layout_planwirtschaft_allgmein.setVisibility(View.GONE);
        Layout_Ubersicht.setVisibility(View.GONE);

        layout_now = Layout_About;

        Activity_Content.this.fab.setImageResource(R.drawable.ic_filter_list_white_24dp);
        Activity_Content.this.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                set_layout_übersicht();

            }
        });

        set_layout_about();
        init_toolbar_drawer();

        CTL.setTitle("Über die App");



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

        if (id == R.id.nav_menu_planwirtschaft_allgmein) {
            set_layout_planwirtschaft_allgemein();

        } else if (id == R.id.nav_menu_übersicht) {
            set_layout_übersicht();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;

    }

    private void set_layout_übersicht() {

        if(layout_now != Layout_Ubersicht){

            Layout_Transition(layout_now,Layout_Ubersicht, AnimIn,AnimOut,AnimDuration);
            layout_now = Layout_Ubersicht;
            CTL.setTitle("Übersicht");


            fab.setImageResource(R.drawable.ic_info_outline_white_24dp);
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    set_layout_about();
                    FAB_press_animation();

                }
            });

        }
    }

    private void set_layout_about() {

        if(layout_now != Layout_About){


            Layout_Transition(layout_now,Layout_About, AnimIn,AnimOut,AnimDuration);
            layout_now = Layout_About;
            CTL.setTitle("Über die App");



            fab.setImageResource(R.drawable.ic_filter_list_white_24dp);
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    set_layout_übersicht();
                    FAB_press_animation();

                }
            });

        }

    }

    private void set_layout_planwirtschaft_allgemein(){

        if(layout_now != Layout_planwirtschaft_allgmein){


            Layout_Transition(layout_now,Layout_planwirtschaft_allgmein, AnimIn,AnimOut,AnimDuration);
            layout_now = Layout_planwirtschaft_allgmein;
            CTL.setTitle("Allgmein");
            CTL.setCollapsedTitleTextAppearance(R.style.TextAppereance);



            fab.setImageResource(R.drawable.ic_filter_list_white_24dp);
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    set_layout_übersicht();
                    FAB_press_animation();

                }
            });

        }
    }


    private void FAB_press_animation(){

        YoYo.with(Techniques.Bounce).duration(500).playOn(fab);

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


        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();


        navigationView.setNavigationItemSelectedListener(this);

    }

    private void übersicht_menu_click(){

        Button Übersicht_Planwirtschaft_Allgmein = (Button)findViewById(R.id.übersicht_planwirtschaft_allgmein);


        Übersicht_Planwirtschaft_Allgmein.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                set_layout_planwirtschaft_allgemein();
            }
        });

    }

}
