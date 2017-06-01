package com.gits.developer.pesonakulonprogo.ui.berita;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import com.gits.developer.pesonakulonprogo.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BeritaActivity extends AppCompatActivity {

    @BindView(R.id.rec_view)RecyclerView recyclerView;

    private BeritaAdapter beritaAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_berita);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        beritaAdapter = new BeritaAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(beritaAdapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return super.onOptionsItemSelected(item);
    }
}
