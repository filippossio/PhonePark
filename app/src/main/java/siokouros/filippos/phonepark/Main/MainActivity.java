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
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import siokouros.filippos.phonepark.R;
import siokouros.filippos.phonepark.StartUp.SignUp;
import siokouros.filippos.phonepark.StartUp.StartUpActivity;

public class MainActivity extends AppCompatActivity implements AboutFragment.OnFragmentInteractionListener {

    TextView mainName, mainSurname, mainEmail;
    NavigationView navigationViev;

    private DrawerLayout mDrawerLayout;


    FirebaseAuth auth;
    FirebaseDatabase database;
    DatabaseReference mRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        mRef = database.getReference("Users");

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp);

        loadUserInformation();

        mDrawerLayout = findViewById(R.id.drawer_layout);
        navigationViev = findViewById(R.id.nav_view);
        navigationViev.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                item.setChecked(true);

                mDrawerLayout.closeDrawers();

                switch (item.getItemId()){
                    case R.id.nav_about:
                        AboutFragment aboutFragment = new AboutFragment();
                        FragmentManager manager = getSupportFragmentManager();
                        manager.beginTransaction()
                                .replace(R.id.main_frame_fragment,aboutFragment).addToBackStack(null)
                                .commit();
                        break;
                    case R.id.nav_logout:
                        final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
                        alertDialog.setTitle("Logout?");
                        alertDialog.setButton(1, "Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                alertDialog.cancel();
                            }
                        });
                        alertDialog.setButton(0, "Logout", new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                    FirebaseAuth.getInstance().signOut();
                                    finish();
                            }
                        });

                }



                return true;
            }
        });


        mainName = findViewById(R.id.MainNameTextView);
        mainSurname = findViewById(R.id.MainSurnameTextView);
        mainEmail = findViewById(R.id.nav_email);


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

        FirebaseUser user = auth.getCurrentUser();
        String displayEmail = user.getEmail();
        mainEmail.setText(displayEmail);

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
