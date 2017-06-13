package com.gits.developer.pesonakulonprogo.ui.news;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.gits.developer.pesonakulonprogo.R;
import com.gits.developer.pesonakulonprogo.model.news.NewsDataModel;
import com.gits.developer.pesonakulonprogo.model.news.NewsResponServer;
import com.gits.developer.pesonakulonprogo.network.config.MyConn;
import com.gits.developer.pesonakulonprogo.network.service.NewsService;
import com.gits.developer.pesonakulonprogo.util.NameTag;
import com.gits.developer.pesonakulonprogo.util.PDialog;
import com.gits.developer.pesonakulonprogo.util.RecyclerItemClickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BeritaActivity extends AppCompatActivity {

    @BindView(R.id.rec_view)RecyclerView recyclerView;

    private BeritaAdapter beritaAdapter;
    private MyConn myConn = new MyConn();
    private PDialog pDialog;
    private NewsService service;
    private List<NewsDataModel> newsDataModels = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_berita);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        pDialog = new PDialog(this);
        pDialog.showPDialog();
        service = myConn.createService(NewsService.class);
        Call<NewsResponServer> call = service.getNews();
        call.enqueue(new Callback<NewsResponServer>() {
            @Override
            public void onResponse(Call<NewsResponServer> call, Response<NewsResponServer> response) {
                pDialog.hidePDialog();
                newsDataModels = response.body().getData();
                Log.d("cek","size : "+ response.body().getData().size());
                initializeRecyclerView(newsDataModels);
            }

            @Override
            public void onFailure(Call<NewsResponServer> call, Throwable t) {
                pDialog.hidePDialog();
                Toast.makeText(BeritaActivity.this, "eror koneksi", Toast.LENGTH_SHORT).show();
            }
        });

        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(BeritaActivity.this,(view, position) -> {
            Intent intent = new Intent(BeritaActivity.this, BeritaDetailActivity.class);
            intent.putExtra(NameTag.detailNews, newsDataModels.get(position));
            startActivity(intent);
        }));

    }

    private void initializeRecyclerView(List<NewsDataModel> dataModels) {
        beritaAdapter = new BeritaAdapter(BeritaActivity.this,dataModels);
        recyclerView.setLayoutManager(new LinearLayoutManager(BeritaActivity.this));
        recyclerView.setAdapter(beritaAdapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return super.onOptionsItemSelected(item);
    }
}
