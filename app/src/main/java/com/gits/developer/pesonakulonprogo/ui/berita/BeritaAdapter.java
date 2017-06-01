package com.gits.developer.pesonakulonprogo.ui.berita;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gits.developer.pesonakulonprogo.R;

import butterknife.ButterKnife;

/**
 * Created by annasblackhat on 11/16/16.
 */

public class BeritaAdapter extends RecyclerView.Adapter<BeritaAdapter.ViewHolderBerita> {
    @Override
    public ViewHolderBerita onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolderBerita(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_berita, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolderBerita holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 10;
    }

    class ViewHolderBerita extends RecyclerView.ViewHolder{

        public ViewHolderBerita(final View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    itemView.getContext().startActivity(new Intent(itemView.getContext(), BeritaDetailActivity.class));
                }
            });
        }
    }
}
