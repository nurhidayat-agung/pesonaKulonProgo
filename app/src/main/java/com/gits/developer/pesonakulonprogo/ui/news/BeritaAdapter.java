package com.gits.developer.pesonakulonprogo.ui.news;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.gits.developer.pesonakulonprogo.R;
import com.gits.developer.pesonakulonprogo.model.news.NewsDataModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by annasblackhat on 11/16/16.
 */

public class BeritaAdapter extends RecyclerView.Adapter<BeritaAdapter.ViewHolderBerita> {
    private Context context;
    private List<NewsDataModel> newsDataModels = new ArrayList<>();

    public BeritaAdapter(Context context, List<NewsDataModel> newsDataModels) {
        this.context = context;
        this.newsDataModels = newsDataModels;
    }

    @Override
    public ViewHolderBerita onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolderBerita(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_berita, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolderBerita holder, int position) {
        NewsDataModel newsDataModel = newsDataModels.get(position);
        if (newsDataModel.getTitle().isEmpty()){
            newsDataModel.setTitle("Judul Event Daerah");
        }
        holder.tvBeritaHeader.setText(newsDataModel.getTitle());
        if (newsDataModel.getImage().size() > 0){
            Glide.with(context)
                    .load(newsDataModels.get(position).getImage().get(0).getUrl())
                    .fitCenter()
                    .placeholder(R.drawable.logo_pemerintah)
                    .error(R.drawable.logo_pemerintah)
                    .into(holder.ivBeritaTop);
        }else {
            Glide.with(context)
                    .load(R.drawable.logo_pemerintah)
                    .fitCenter()
                    .into(holder.ivBeritaTop);
        }
    }

    @Override
    public int getItemCount() {
        return newsDataModels.size();
    }

    class ViewHolderBerita extends RecyclerView.ViewHolder{
        @BindView(R.id.iv_berita_top)ImageView ivBeritaTop;
        @BindView(R.id.tv_berita_header)TextView tvBeritaHeader;

        public ViewHolderBerita(final View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    itemView.getContext().startActivity(new Intent(itemView.getContext(), BeritaDetailActivity.class));
//                }
//            });
        }
    }
}
