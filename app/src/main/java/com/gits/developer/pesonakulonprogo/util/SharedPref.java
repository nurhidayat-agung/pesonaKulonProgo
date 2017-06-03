package com.gits.developer.pesonakulonprogo.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by kazt on 31/05/17.
 */

public class SharedPref {
    private SharedPreferences sp;
    private SharedPreferences.Editor spe;
    private Context c;

    public SharedPref(Context context) {
        this.sp = context.getSharedPreferences(NameTag.TAG_APP_SHARED_PREF, Context.MODE_PRIVATE);
        this.spe = sp.edit();
        this.c = context;
    }

    public boolean isLogin(){
        return sp.getBoolean(NameTag.isLogin, true);
    }

    public void setLogin(boolean first){
        spe.putBoolean(NameTag.isLogin, first);
        spe.commit();
    }

    public void setUsername(String username){
        spe.putString(NameTag.username, username);
        spe.commit();
    }

    public String getUsername(){
        return sp.getString(NameTag.username,"");
    }

    public void setImgUrl(String imgUrl){
        spe.putString(NameTag.imgUrl,imgUrl);
        spe.commit();
    }

    public String getImgUrl(){
        return sp.getString(NameTag.imgUrl,"");
    }
}
