package de.ricoklimpel.geschichte;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.NestedScrollView;
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
    private int mShortAnimationDuration = 500;
    private String Startwert;

    final Techniques AnimIn = Techniques.FadeIn;
    final Techniques AnimOut = Techniques.FadeOut;
    final Integer AnimDuration = 300;

    Toolbar toolbar;
    DrawerLayout drawer;
    NavigationView navigationView;

    LinearLayout Layout_About;
    LinearLayout Layout_Ubersicht;
    LinearLayout layout_now;
    LinearLayout Layout_planwirtschaft_kommentare;
    LinearLayout Layout_planwirtschaft_howto;
    LinearLayout Layout_planwirtschaft_definition;
    LinearLayout Layout_planwirtschaft_procon;
    LinearLayout Layout_planwirtschaft_ddr;
    LinearLayout Layout_planwirtschaft_important;
    LinearLayout Layout_marktwirtschaft_grundlage;
    LinearLayout Layout_marktwirtschaft_entwicklung;
    NestedScrollView Nestedt_Scroll;

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
        Nestedt_Scroll = (NestedScrollView)findViewById(R.id.Nested_Scroll);

        CTL = (CollapsingToolbarLayout)findViewById(R.id.collapsing_toolbar_layout);

        Layout_About = (LinearLayout)findViewById(R.id.Layout_About);
        Layout_Ubersicht = (LinearLayout)findViewById(R.id.Layout_Ubersicht);
        Layout_planwirtschaft_kommentare = (LinearLayout)findViewById(R.id.Layout_Planwirtschaft_kommentare);
        Layout_planwirtschaft_howto = (LinearLayout)findViewById(R.id.Layout_Planwirtschaft_howto);
        Layout_planwirtschaft_definition = (LinearLayout)findViewById(R.id.Layout_Planwirtschaft_definition);
        Layout_planwirtschaft_procon = (LinearLayout)findViewById(R.id.Layout_Planwirtschaft_procon);
        Layout_planwirtschaft_ddr = (LinearLayout)findViewById(R.id.Layout_Planwirtschaft_ddr);
        Layout_planwirtschaft_important = (LinearLayout)findViewById(R.id.Layout_Planwirtschaft_important);

        Layout_marktwirtschaft_entwicklung = (LinearLayout)findViewById(R.id.Layout_Marktwirtschaft_entwicklung);
        Layout_marktwirtschaft_grundlage = (LinearLayout)findViewById(R.id.Layout_Marktwirtschaft_grundlage);




        Layout_About.setVisibility(View.VISIBLE);

        Layout_planwirtschaft_kommentare.setVisibility(View.GONE);
        Layout_Ubersicht.setVisibility(View.GONE);
        Layout_planwirtschaft_howto.setVisibility(View.GONE);
        Layout_planwirtschaft_definition.setVisibility(View.GONE);
        Layout_planwirtschaft_procon.setVisibility(View.GONE);
        Layout_planwirtschaft_ddr.setVisibility(View.GONE);
        Layout_planwirtschaft_important.setVisibility(View.GONE);
        Layout_marktwirtschaft_grundlage.setVisibility(View.GONE);
        Layout_marktwirtschaft_entwicklung.setVisibility(View.GONE);




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
            if (layout_now == Layout_About){
                super.onBackPressed();
            }else if(layout_now == Layout_Ubersicht){
                set_layout_about();
            }else{
                set_layout_übersicht();
            }
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
        } else if (id == R.id.nav_menu_planwirtschaft_ddr){
            set_layout_planwirtschaft_ddr();
        } else if (id == R.id.nav_menu_planwirtschaft_howto){
            set_layout_planwirtschaft_howto();
        } else if (id == R.id.nav_menu_planwirtschaft_definition){
            set_layout_planwirtschaft_definition();
        } else if (id == R.id.nav_menu_planwirtschaft_procontra){
            set_layout_planwirtschaft_procon();
        } else if (id == R.id.nav_menu_planwirtschaft_important){
            set_layout_planwirtschaft_important();
        } else if (id == R.id.nav_menu_marktwirtschaft_entwicklung){
            set_layout_marktwirtschaft_entwicklung();
        } else if (id == R.id.nav_menu_marktwirtschaft_grundlagen){
            set_layout_marktwirtschaft_grundlage();
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

            Nestedt_Scroll.scrollTo(0,0);



            //Init Buttons
            übersicht_menu_click();

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

            Nestedt_Scroll.scrollTo(0,0);

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

        if(layout_now != Layout_planwirtschaft_kommentare){


            Layout_Transition(layout_now, Layout_planwirtschaft_kommentare, AnimIn,AnimOut,AnimDuration);
            layout_now = Layout_planwirtschaft_kommentare;
            CTL.setTitle("Kommentare");

            Nestedt_Scroll.scrollTo(0,-10);

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

    private void set_layout_planwirtschaft_howto(){

        if(layout_now != Layout_planwirtschaft_howto){


            Layout_Transition(layout_now, Layout_planwirtschaft_howto, AnimIn,AnimOut,AnimDuration);
            layout_now = Layout_planwirtschaft_howto;
            CTL.setTitle("Wie Planen die?");

            Nestedt_Scroll.scrollTo(0,-10);

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


    private void set_layout_planwirtschaft_definition(){

        if(layout_now != Layout_planwirtschaft_definition){


            Layout_Transition(layout_now, Layout_planwirtschaft_definition, AnimIn,AnimOut,AnimDuration);
            layout_now = Layout_planwirtschaft_definition;
            CTL.setTitle("Definition");

            Nestedt_Scroll.scrollTo(0,-10);

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

    private void set_layout_planwirtschaft_procon(){

        if(layout_now != Layout_planwirtschaft_procon){


            Layout_Transition(layout_now, Layout_planwirtschaft_procon, AnimIn,AnimOut,AnimDuration);
            layout_now = Layout_planwirtschaft_procon;
            CTL.setTitle("Vor-und Nachteile");

            Nestedt_Scroll.scrollTo(0,-10);

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

    private void set_layout_planwirtschaft_ddr(){

        if(layout_now != Layout_planwirtschaft_ddr){


            Layout_Transition(layout_now, Layout_planwirtschaft_ddr, AnimIn,AnimOut,AnimDuration);
            layout_now = Layout_planwirtschaft_ddr;
            CTL.setTitle("In der DDR und Heute");

            Nestedt_Scroll.scrollTo(0,-10);

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


    private void set_layout_planwirtschaft_important(){

        if(layout_now != Layout_planwirtschaft_important){


            Layout_Transition(layout_now, Layout_planwirtschaft_important, AnimIn,AnimOut,AnimDuration);
            layout_now = Layout_planwirtschaft_important;
            CTL.setTitle("Wichtiges / Zusammenfassung");

            Nestedt_Scroll.scrollTo(0,-10);

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


    private void set_layout_marktwirtschaft_entwicklung(){

        if(layout_now != Layout_marktwirtschaft_entwicklung){


            Layout_Transition(layout_now, Layout_marktwirtschaft_entwicklung, AnimIn,AnimOut,AnimDuration);
            layout_now = Layout_marktwirtschaft_entwicklung;
            CTL.setTitle("BRD - Marktwirtschaft Entwicklung");

            Nestedt_Scroll.scrollTo(0,-10);

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



    private void set_layout_marktwirtschaft_grundlage(){

        if(layout_now != Layout_marktwirtschaft_grundlage){


            Layout_Transition(layout_now, Layout_marktwirtschaft_grundlage, AnimIn,AnimOut,AnimDuration);
            layout_now = Layout_marktwirtschaft_grundlage;
            CTL.setTitle("Grundlagen");

            Nestedt_Scroll.scrollTo(0,-10);

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

    private void Layout_Transition(final LinearLayout layout_from , final LinearLayout layout_to,
                                   final Techniques Tech_in, final Techniques Tech_Out , final Integer Duration){

        YoYo.with(Tech_Out).duration(Duration).withListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }
            @Override
            public void onAnimationEnd(Animator animation) {

                layout_from.setVisibility(View.GONE);

                YoYo.with(Tech_in).duration(Duration).withListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {
                        layout_to.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {

                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                }).playOn(layout_to);

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

        Button btn_plan_kommentare = (Button)findViewById(R.id.btn_plan_kommentare);
        Button btn_plan_definition = (Button)findViewById(R.id.btn_plan_definition);
        Button btn_plan_howto = (Button)findViewById(R.id.btn_plan_howto);
        Button btn_plan_procon = (Button)findViewById(R.id.btn_plan_procon);
        Button btn_plan_ddr = (Button)findViewById(R.id.btn_plan_ddr);
        Button btn_plan_important = (Button)findViewById(R.id.btn_plan_important);
        Button btn_markt_grundlagen = (Button)findViewById(R.id.btn_markt_grundsätze);
        Button btn_markt_entwicklung = (Button)findViewById(R.id.btn_markt_entwicklung);

        btn_markt_entwicklung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                set_layout_marktwirtschaft_entwicklung();
            }
        });
        btn_markt_grundlagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                set_layout_marktwirtschaft_grundlage();
            }
        });
        btn_plan_kommentare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                set_layout_planwirtschaft_allgemein();
            }
        });
        btn_plan_definition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                set_layout_planwirtschaft_definition();
            }
        });
        btn_plan_howto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                set_layout_planwirtschaft_howto();
            }
        });
        btn_plan_procon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                set_layout_planwirtschaft_procon();
            }
        });
        btn_plan_ddr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                set_layout_planwirtschaft_ddr();
            }
        });
        btn_plan_important.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                set_layout_planwirtschaft_important();
            }
        });

    }

}
