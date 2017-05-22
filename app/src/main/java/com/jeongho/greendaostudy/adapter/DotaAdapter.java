package com.jeongho.greendaostudy.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jeongho.greendaostudy.R;
import com.jeongho.greendaostudy.dao.DotaHero;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Jeongho on 2017/5/22.
 */

public class DotaAdapter extends RecyclerView.Adapter<DotaAdapter.ViewHolder>{

    private List<DotaHero> mHeroes;
    //private Context mContext;

    public DotaAdapter(List<DotaHero> list) {
        mHeroes = list;
        //mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_dota, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        viewHolder.circleImageView.setImageResource(R.mipmap.ic_launcher_round);
        viewHolder.nameTv.setText("" + mHeroes.get(i).getName());
        viewHolder.hpTv.setText("" + mHeroes.get(i).getHp());
        viewHolder.mpTv.setText("" + mHeroes.get(i).getMp());
        viewHolder.expTv.setText("" + mHeroes.get(i).getExp());
    }

    @Override
    public int getItemCount() {
        return mHeroes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private CircleImageView circleImageView;

        private TextView nameTv;
        private TextView hpTv;
        private TextView mpTv;
        private TextView expTv;

        public ViewHolder(View itemView) {
            super(itemView);
            circleImageView = (CircleImageView) itemView.findViewById(R.id.profile_image);
            nameTv = (TextView) itemView.findViewById(R.id.tv_name);
            hpTv = (TextView) itemView.findViewById(R.id.tv_hp);
            mpTv = (TextView) itemView.findViewById(R.id.tv_mp);
            expTv = (TextView) itemView.findViewById(R.id.tv_exp);

        }
    }
}
