package com.wallforfry.budget_etudiant;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.text.format.DateFormat;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;




public class MainActivity extends ActionBarActivity /*implements View.OnClickListener */ {

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle drawerToggle;
    Toolbar toolbar;
    View button;
    LinearLayout depenses_drawer_layout;
    LinearLayout revenus_drawer_layout;
    LinearLayout operations_drawer_layout;
    LinearLayout parametres_drawer_layout;
    private ListView drawerList;
    private CustomAdapter mAdapter;


    private Integer[] tab_images_pour_la_liste = {
            R.drawable.ic_home_black_24dp,
            R.drawable.ic_file_download_black_24dp,
            R.drawable.ic_add_black_24dp,
            R.drawable.ic_file_upload_black_24dp,
            R.drawable.ic_settings_black_24dp,
            R.drawable.ic_help_black_24dp,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }

        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close);
        drawerLayout.setDrawerListener(drawerToggle);
        drawerToggle.setDrawerIndicatorEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


      /*  depenses_drawer_layout = (LinearLayout) findViewById(R.id.depenses_parent);
        depenses_drawer_layout.setOnClickListener(this);
        revenus_drawer_layout = (LinearLayout) findViewById (R.id.revenus_parent);
        revenus_drawer_layout.setOnClickListener(this);
        operations_drawer_layout = (LinearLayout) findViewById (R.id.operations_parent);
        operations_drawer_layout.setOnClickListener(this);
        parametres_drawer_layout = (LinearLayout) findViewById (R.id.parametres_parent);
        parametres_drawer_layout.setOnClickListener(this);*/

        drawerList = (ListView) findViewById(R.id.listView2);
        /*drawerList.setAdapter(new ArrayAdapter<String>(
                this,
                R.layout.rowlayout,
                getResources().getStringArray(R.array.menu_items)));*/
       /* drawerList.setAdapter(new MonAdaptateurDeListe(
                this, getResources().getStringArray(R.array.menu_items)));*/


        drawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                // Start your Activity according to the item just clicked.
                switch (position) {
                    case (0):
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.container, new PlaceholderFragment()).addToBackStack("")
                                .commit();
                        break;

                    case (3):
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.container, new RevenusFragment()).addToBackStack("")
                                .commit();

                        break;

                    case (4):
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.container, new DepensesFragment()).addToBackStack("")
                                .commit();
                        break;

                    case (5):
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.container, new OperationsFragment()).addToBackStack("")
                                .commit();
                        break;

                    case (7):

                        setTitle(R.string.Parametres);
                        popUp("Paramètres");
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.container, new PlaceholderFragment())
                                .commit();
                        drawerLayout.closeDrawers();
                        break;
                    case (8):
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.container, new AideFragment()).addToBackStack("")
                                .commit();

                        break;
                }

            }
        });

        ArrayList<Item> listP = Item.getAListOfItem();
        mAdapter = new CustomAdapter(this, listP);
        //mAdapter.addItem("Row Item 1");
        //mAdapter.addSectionHeaderItem("Section 1");
        /*for (int i = 1; i < 30; i++) {
            mAdapter.addItem("Row Item #" + i);
            if (i % 4 == 0) {
                mAdapter.addSectionHeaderItem("Section #" + i);
            }
        }*/
        mAdapter.addItem("Dashboard");
        mAdapter.addSectionHeaderItem("Mon Budget");
        mAdapter.addItem("Revenus");
        mAdapter.addItem("Dépenses");
        mAdapter.addItem("Opérations");
        mAdapter.addItem("Paramètres");
        mAdapter.addItem("Aides & Commentairs");


        drawerList.setAdapter(mAdapter);

    }




    public void onClick (View v){

    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // synchroniser le drawerToggle après la restauration via onRestoreInstanceState
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        String hist = null;

        switch (id) {
            case R.id.action_revenus:
                //hist = getResources().getString(R.string.toast_new);
                setTitle(R.string.Revenus);
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, new RevenusFragment())
                        .commit();
                break;
            case R.id.action_depenses:
                //hist = getResources().getString(R.string.toast_edit);
                setTitle(R.string.Depenses);
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, new DepensesFragment())
                        .commit();
                break;
            case R.id.action_operations:
                hist = getResources().getString(R.string.toast_delete);
                break;
            case R.id.action_home:
                //hist = getResources().getString(R.string.toast_home);
                setTitle(R.string.app_name);
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, new PlaceholderFragment())
                        .commit();
                break;
            case R.id.home:
                if (drawerLayout.isDrawerOpen(Gravity.LEFT) == false) {
                    drawerLayout.openDrawer(Gravity.LEFT);
                } else {
                    drawerLayout.closeDrawers();
                }
                break;
            default:
        }

        Context context = getApplicationContext();
        Toast msg = Toast.makeText(context, hist, Toast.LENGTH_LONG);
        msg.setGravity(Gravity.CENTER, msg.getXOffset() / 2, msg.getYOffset() / 2);
        if (hist != null) {
            msg.show();
        }

        return super.onOptionsItemSelected(item);
    }


   /* public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            setTitle(R.string.app_name);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, new PlaceholderFragment())
                    .commit();
        }
        return true;
    }*/

  /*  @Override
    public void onClick(View v) {
        String hist;
        Context context;
        Toast msg;
        //switch(v.getId()) {
        switch(v.getTag()) {
            case (R.id.revenus_parent):

                setTitle(R.string.Revenus);
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, new RevenusFragment())
                        .commit();
                drawerLayout.closeDrawers();
            break;
            case (R.id.depenses_parent):

                setTitle(R.string.Depenses);
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, new DepensesFragment())
                        .commit();
                drawerLayout.closeDrawers();
            break;
            case (R.id.operations_parent):


                hist = "Operations";
                context = getApplicationContext();
                msg = Toast.makeText(context, hist, Toast.LENGTH_LONG);
                msg.setGravity(Gravity.BOTTOM, msg.getXOffset() / 2, msg.getYOffset() / 2);
                if (hist != null) {
                    msg.show();
                }
                drawerLayout.closeDrawers();
            break;
            case (R.id.parametres_parent):


                hist = "Paramètres";
                context = getApplicationContext();
                msg = Toast.makeText(context, hist, Toast.LENGTH_LONG);
                msg.setGravity(Gravity.BOTTOM, msg.getXOffset() / 2, msg.getYOffset() / 2);
                if (hist != null) {
                    msg.show();
                }
                drawerLayout.closeDrawers();
                break;
        }
    }*/


    /**
     * A placeholder fragment containing a simple view.
     */
    public class PlaceholderFragment extends Fragment {

        ListView mListView;

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {

            super.onCreate(savedInstanceState);
            // keep the fragment and all its data across screen rotation
            setRetainInstance(true);

            setTitle(R.string.Dashboard);
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);

            mListView = (ListView) rootView.findViewById(R.id.listView);
            List<Tweet> tweets = genererTweets();
            TweetAdapter adapter = new TweetAdapter(MainActivity.this, tweets);
            mListView.setAdapter(adapter);
            drawerLayout.closeDrawers();
            return rootView;
        }

        private List<Tweet> genererTweets() {
            List<Tweet> tweets = new ArrayList<Tweet>();
            tweets.add(new Tweet(Color.BLACK, "- xxx €", "Téléphone"));
            tweets.add(new Tweet(Color.BLUE, "- xxx €", "Course 19/07"));
            tweets.add(new Tweet(Color.GREEN, "- xxx €", "Coiffeur"));
            tweets.add(new Tweet(Color.RED, "+ xxx €", "Cours de guitare"));
            tweets.add(new Tweet(Color.GRAY, "- xxx €", "Bus"));
            return tweets;
        }
    }

    public class RevenusFragment extends Fragment {

        ListView mListView;


        public RevenusFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {

            super.onCreate(savedInstanceState);
            // keep the fragment and all its data across screen rotation
            setRetainInstance(true);


            setTitle(R.string.Revenus);
            View rootView = inflater.inflate(R.layout.fragment_revenus, container, false);

            mListView = (ListView) rootView.findViewById(R.id.listView);
            List<Tweet> tweets = genererTweets();
            TweetAdapter adapter = new TweetAdapter(MainActivity.this, tweets);
            mListView.setAdapter(adapter);
            drawerLayout.closeDrawers();
            return rootView;
        }

        private List<Tweet> genererTweets() {
            List<Tweet> tweets = new ArrayList<Tweet>();
            tweets.add(new Tweet(Color.BLACK, "xxx €", "Premier revenu"));
            tweets.add(new Tweet(Color.BLUE, "xxx €", "Deuxième revenu"));
            tweets.add(new Tweet(Color.GREEN, "xxx €", "Bourses"));
            tweets.add(new Tweet(Color.RED, "xxx €", "Aides"));
            tweets.add(new Tweet(Color.GRAY, "xxx €", "Parents"));
            return tweets;
        }

    }

    public class DepensesFragment extends Fragment {

        ListView mListView;


        public DepensesFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {

            super.onCreate(savedInstanceState);
            // keep the fragment and all its data across screen rotation
            setRetainInstance(true);


            setTitle(R.string.Depenses);
            View rootView = inflater.inflate(R.layout.fragment_depenses, container, false);

            mListView = (ListView) rootView.findViewById(R.id.listView);
            List<Tweet> tweets = genererTweets();
            TweetAdapter adapter = new TweetAdapter(MainActivity.this, tweets);
            mListView.setAdapter(adapter);
            drawerLayout.closeDrawers();
            return rootView;
        }

        private List<Tweet> genererTweets() {
            List<Tweet> tweets = new ArrayList<Tweet>();
            tweets.add(new Tweet(Color.BLACK, "xxx €", "Première dépense"));
            tweets.add(new Tweet(Color.BLUE, "xxx €", "Deuxième dépense"));
            tweets.add(new Tweet(Color.GREEN, "xxx €", "école"));
            tweets.add(new Tweet(Color.RED, "xxx €", "Logement"));
            tweets.add(new Tweet(Color.GRAY, "xxx €", "Transports"));
            return tweets;
        }

    }

    public class AideFragment extends Fragment {


        public AideFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {

            super.onCreate(savedInstanceState);
            // keep the fragment and all its data across screen rotation
            setRetainInstance(true);


            setTitle(R.string.Aide);
            View rootView = inflater.inflate(R.layout.fragment_aide, container, false);
            drawerLayout.closeDrawers();
            return rootView;
        }

    }

    public class OperationsFragment extends Fragment {

        public OperationsFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {

            super.onCreate(savedInstanceState);
            // keep the fragment and all its data across screen rotation
            setRetainInstance(true);

            setTitle(R.string.Operations);
            View rootView = inflater.inflate(R.layout.fragment_operations, container, false);

            ImageButton fab_operations = (ImageButton) rootView.findViewById(R.id.fab_operations);
            fab_operations.setOnClickListener(new View.OnClickListener(){
                                                  @Override public void onClick(View v){
                                                      getSupportFragmentManager().beginTransaction()
                                                              .replace(R.id.container, new AddOperationsFragment()).addToBackStack("")
                                                              .commit();
                                                  }
                                              }
            );

            drawerLayout.closeDrawers();
            return rootView;
        }


    }

    public class AddOperationsFragment extends Fragment {

        public AddOperationsFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {

            super.onCreate(savedInstanceState);
            // keep the fragment and all its data across screen rotation
            setRetainInstance(true);

            setTitle(R.string.Operations);
            View rootView = inflater.inflate(R.layout.fragment_addoperations, container, false);

            final EditText txtDate = (EditText) rootView.findViewById(R.id.txtDate);

            ImageButton fab_operations = (ImageButton) rootView.findViewById(R.id.fab_addoperations);
            fab_operations.setOnClickListener(new View.OnClickListener(){
                                                  @Override public void onClick(View v){
                                                      Calendar c = Calendar.getInstance();
                                                      int mYear = c.get(Calendar.YEAR);
                                                      int mMonth = c.get(Calendar.MONTH);
                                                      int mDay = c.get(Calendar.DAY_OF_MONTH);
                                                      txtDate.setText(mDay + "/"
                                                              + (mMonth + 1) + "/" + mYear);
                                                      // Launch Date Picker Dialog
                                                     /*DatePickerDialog dpd = new DatePickerDialog(getActivity(),
                                                              new DatePickerDialog.OnDateSetListener() {

                                                                  @Override
                                                                  public void onDateSet(DatePicker view, int year,
                                                                                        int monthOfYear, int dayOfMonth) {
                                                                      // Display Selected date in textbox
                                                                        txtDate.setText(dayOfMonth + "-"
                                                                         + (monthOfYear + 1) + "-" + year);



                                                                  }
                                                              }, mYear, mMonth, mDay);
                                                      dpd.show();*/

                                                  }
                                              }
            );

            return rootView;
        }


    }

    public class DatePickerFragment extends DialogFragment {
        DatePickerDialog.OnDateSetListener ondateSet;

        public DatePickerFragment() {
        }

        public void setCallBack(DatePickerDialog.OnDateSetListener ondate) {
            ondateSet = ondate;
        }

        private int year, month, day;

        @Override
        public void setArguments(Bundle args) {
            super.setArguments(args);
            year = args.getInt("year");
            month = args.getInt("month");
            day = args.getInt("day");
        }

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            return new DatePickerDialog(getActivity(), ondateSet, year, month, day);
        }
    }


    public void goToRevenus() {
        setTitle(R.string.Revenus);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, new RevenusFragment())
                .commit();
    }

    public void onClickNom(Item item, int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Personne");

        builder.setMessage("Vous avez cliqué sur : " + item.nom);
        builder.setPositiveButton("Oui", null);
        builder.setNegativeButton("Non", null);
        builder.show();
    }

    public void popUp(String hist) {
        Context context;
        Toast msg;
        context = getApplicationContext();
        msg = Toast.makeText(context, hist, Toast.LENGTH_LONG);
        msg.setGravity(Gravity.BOTTOM, msg.getXOffset() / 2, msg.getYOffset() / 2);
        if (hist != null) {
            msg.show();
        }
    }



}