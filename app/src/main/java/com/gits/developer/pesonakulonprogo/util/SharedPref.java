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
}
