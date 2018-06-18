package siokouros.filippos.phonepark.Main;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Iterator;
import java.util.Objects;

import siokouros.filippos.phonepark.Model.Parking;
import siokouros.filippos.phonepark.Model.User;
import siokouros.filippos.phonepark.R;
import siokouros.filippos.phonepark.StartUp.LogIn;
import siokouros.filippos.phonepark.StartUp.SignUp;
import siokouros.filippos.phonepark.StartUp.StartUpActivity;

public class MainActivity extends AppCompatActivity implements AboutFragment.OnFragmentInteractionListener , ParkingFragment.OnFragmentInteractionListener {
    private static final String TAG = "MainActivity";


    TextView mainName, mainEmail;
    NavigationView navigationViev;

    private DrawerLayout mDrawerLayout;

    User user;

    //Firebase stuff
    FirebaseAuth auth;
    DatabaseReference databaseReference;
    FirebaseAuth.AuthStateListener authStateListener;
    FirebaseDatabase database;
    String userID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Firebase stuff
        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference();
        FirebaseUser user = auth.getCurrentUser();
        userID = FirebaseAuth.getInstance().getCurrentUser().getUid();


        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View v = navigationView.getHeaderView(0);


        mainEmail = v.findViewById(R.id.nav_email);
        mainName = v.findViewById(R.id.nav_name);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setDisplayShowCustomEnabled(false);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp);


        FragmentManager manager = getSupportFragmentManager();


        mDrawerLayout = findViewById(R.id.drawer_layout);
        navigationViev = findViewById(R.id.nav_view);
        navigationViev.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                item.setChecked(true);

                mDrawerLayout.closeDrawers();
                FragmentManager manager = getSupportFragmentManager();

                switch (item.getItemId()) {
                    case R.id.nav_myAccount:
                        ParkingFragment parkingFragment = new ParkingFragment();
                        manager.beginTransaction()
                                .replace(R.id.main_frame_fragment, parkingFragment).addToBackStack(null)
                                .commit();
                        item.setChecked(false);
                        break;

                    case R.id.nav_about:
                        AboutFragment aboutFragment = new AboutFragment();
                        manager.beginTransaction()
                                .replace(R.id.main_frame_fragment, aboutFragment).addToBackStack(null)
                                .commit();
                        item.setChecked(false);
                        break;
                    case R.id.nav_logout:
                        FirebaseAuth.getInstance().signOut();
                        logout();
                        item.setChecked(false);
                        break;
                }

                return true;
            }
        });
        String email = "example@something.com";
        email = FirebaseAuth.getInstance().getCurrentUser().getEmail();

        mainEmail.setText(email);

        ParkingFragment parkingFragment = new ParkingFragment();
        manager.beginTransaction()
                .replace(R.id.main_frame_fragment, parkingFragment).addToBackStack(null)
                .commit();

    }

    private void logout() {
        Intent myIntent = new Intent(this, StartUpActivity.class);
        startActivity(myIntent);
    }

    private void showData(DataSnapshot dataSnapshot) {
        for(DataSnapshot ds : dataSnapshot.getChildren()){
            User user = new User();
            user.setName(Objects.requireNonNull(ds.child(userID).getValue(User.class)).getName());
            user.setSurname(Objects.requireNonNull(ds.child(userID).getValue(User.class)).getSurname());
            user.setEmail(Objects.requireNonNull(ds.child(userID).getValue(User.class)).getEmail());

            mainName.setText(user.getName() + " " + user.getSurname());
        }


    }

    @Override
    public void onStart(){
        super.onStart();
        if(auth.getCurrentUser() == null){
            finish();
            Intent myIntent = new Intent(this, StartUpActivity.class);
            startActivity(myIntent);
        }



    }



    private void loadUserInformation() {



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }


        return true;
    }



    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
