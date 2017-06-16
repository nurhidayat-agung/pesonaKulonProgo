package com.gits.developer.pesonakulonprogo.util;

import android.app.ProgressDialog;
import android.content.Context;
import android.widget.Toast;

/**
 * Created by kazt on 04/06/17.
 */

public class PDialog {
    private ProgressDialog progressDialog;
    private Context context;

    public PDialog(Context context) {
        this.context = context;
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Mengambil Data Dari server...");
        progressDialog.setCancelable(false);
    }

    public void showPDialog(){
        if (!progressDialog.isShowing()){
            progressDialog.show();
        }
    }
    public void hidePDialog(){
        if (progressDialog.isShowing()){
            progressDialog.dismiss();
        }
    }

    public void fail(){
        Toast.makeText(context, "koneksi gagal", Toast.LENGTH_SHORT).show();
    }


}
