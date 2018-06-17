package siokouros.filippos.phonepark.StartUp;

import android.net.Uri;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;

import siokouros.filippos.phonepark.R;
import siokouros.filippos.phonepark.StartUp.LogIn;
import siokouros.filippos.phonepark.StartUp.SignUp;

public class StartUpActivity extends FragmentActivity implements LogIn.OnFragmentInteractionListener, SignUp.OnFragmentInteractionListener{

    private LogIn fragment = null;
    private android.support.v4.app.FragmentManager manager = null;
    private android.support.v4.app.FragmentTransaction ft;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_up);
        if (manager == null) manager = getSupportFragmentManager();

        if (manager.findFragmentById(R.id.fragmentArea) == null) {

            //If a fragment is not already loaded into your container, then add one...

            fragment = new LogIn();
            ft = manager.beginTransaction();
            ft.add(R.id.fragmentArea, fragment).commit();
        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {
        Log.i("Tag", "onFragmentInteraction called");
    }
}
