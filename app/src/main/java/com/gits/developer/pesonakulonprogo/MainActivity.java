package com.gits.developer.pesonakulonprogo;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.Profile;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.gits.developer.pesonakulonprogo.model.LoginFBData;
import com.gits.developer.pesonakulonprogo.ui.home.HomeFragment;
import com.gits.developer.pesonakulonprogo.ui.kritiksaran.KritikSaranFragment;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, FacebookCallback<LoginResult> {

    @BindView(R.id.container)FrameLayout layoutContainer;
    private FragmentManager fragmentManager;
    private KritikSaranFragment kritikSaranFragment;
    private CallbackManager callbackManager;
    private LoginButton btnFb;
    private String name = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
            this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        //fb config
        btnFb = (LoginButton) findViewById(R.id.login_button);
        callbackManager = CallbackManager.Factory.create();
        btnFb.setReadPermissions("email", "public_profile", "user_birthday");
        btnFb.registerCallback(callbackManager,this);
        Profile fbProfile = Profile.getCurrentProfile();
        AccessTokenTracker tracer = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {
                if (currentAccessToken == null){
                    Toast.makeText(MainActivity.this, "nah ini logout", Toast.LENGTH_SHORT).show();
                }
            }
        };


        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        kritikSaranFragment = new KritikSaranFragment();

        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.container, new HomeFragment()).commit();
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
        getMenuInflater().inflate(R.menu.main, menu);
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
        Fragment fragment = null;
        switch (item.getItemId()){
            case R.id.nav_kritik:
                fragment = kritikSaranFragment;
                break;
            case R.id.nav_beranda:
                fragment = new HomeFragment();
                break;
        }

        if(fragment != null)
        fragmentManager.beginTransaction().replace(R.id.container, fragment).commit();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode,resultCode,data);
    }

    @Override
    public void onSuccess(LoginResult loginResult) {
        GraphRequest graphRequest = GraphRequest.newMeRequest(loginResult.getAccessToken(),(object, response) -> {
            LoginFBData loginFBData = new LoginFBData();
            Log.d("cek", "email : "+getValueFromJson(object,"email"));
            Log.d("cek", "name : "+getValueFromJson(object,"name"));
            Log.d("cek","gender : "+getValueFromJson(object,"gender"));
            Log.d("cek","https://graph.facebook.com/" + getValueFromJson(object, "id") + "/picture?type=large");
            Log.d("cek", "birthday : " + getValueFromJson(object,"birthday"));
            name = getValueFromJson(object,"name");
            Toast.makeText(this, name, Toast.LENGTH_SHORT).show();
            //LoginManager.getInstance().logOut();
        });
        Bundle bundle = new Bundle();
        bundle.putString("field", "email,name,gender,birthday");
        graphRequest.setParameters(bundle);
        graphRequest.executeAsync();

    }

    @Override
    public void onCancel() {

    }

    @Override
    public void onError(FacebookException error) {

    }

    private String getValueFromJson(JSONObject object, String key){
        try {
            return object.getString(key);
        } catch (JSONException e) {
            return "";
        }
    }
}
