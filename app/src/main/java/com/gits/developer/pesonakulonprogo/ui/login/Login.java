package com.gits.developer.pesonakulonprogo.ui.login;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.gits.developer.pesonakulonprogo.MainActivity;
import com.gits.developer.pesonakulonprogo.R;
import com.gits.developer.pesonakulonprogo.databinding.ActivityLoginBinding;
import com.gits.developer.pesonakulonprogo.model.LoginFBData;

import org.json.JSONException;
import org.json.JSONObject;


public class Login extends AppCompatActivity implements FacebookCallback<LoginResult>{
    private ActivityLoginBinding binding;
    private CallbackManager callbackManager;
    private LoginManager loginManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_login);


        //fb configure
        callbackManager = CallbackManager.Factory.create();
        binding.loginButton.setReadPermissions("email", "public_profile", "user_birthday");
        binding.loginButton.registerCallback(callbackManager, this);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode,resultCode,data);
    }

    @Override
    public void onSuccess(LoginResult loginResult) {
//        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
//        startActivity(intent);
        GraphRequest graphRequest = GraphRequest.newMeRequest(loginResult.getAccessToken(),(object, response) -> {
            LoginFBData loginFBData = new LoginFBData();
            Log.d("cek", "email : "+getValueFromJson(object,"email"));
            Log.d("cek", "name : "+getValueFromJson(object,"name"));
            Log.d("cek","gender : "+getValueFromJson(object,"gender"));
            Log.d("cek","https://graph.facebook.com/" + getValueFromJson(object, "id") + "/picture?type=large");
            Log.d("cek", "birthday : " + getValueFromJson(object,"birthday"));
            LoginManager.getInstance().logOut();
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
