package com.jeongho.greendaostudy.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
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
//        viewHolder.circleImageView.setImageResource(R.mipmap.ic_launcher_round);

        Glide.with(viewHolder.itemView).load("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1495535143307&di=df247d326c3b553121cc16d3c1b9555c&imgtype=0&src=http%3A%2F%2Fv1.qzone.cc%2Favatar%2F201403%2F02%2F13%2F31%2F5312c22b0e7d4289.jpg%2521200x200.jpg")
                .into(viewHolder.circleImageView);
        viewHolder.nameTv.setText("" + mHeroes.get(i).getName());
        viewHolder.hpTv.setText("HP:" + mHeroes.get(i).getHp());
        viewHolder.mpTv.setText("MP:" + mHeroes.get(i).getMp());
        viewHolder.expTv.setText("EXP" + mHeroes.get(i).getExp());
        viewHolder.idTv.setText("" + mHeroes.get(i).getId());
    }

    @Override
    public int getItemCount() {
        return mHeroes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private CircleImageView circleImageView;

        private TextView idTv;

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
            idTv = (TextView) itemView.findViewById(R.id.tv_id);
        }

    }
    public void addHero(DotaHero hero){
        mHeroes.add(0, hero);
        notifyItemInserted(0);
    }

    public void removeHero(int position){
       // mHeroes.remove(position);
        notifyItemRemoved(position);
    }

    public void addHeros(List<DotaHero> heroes) {
        int startPosition = mHeroes.size();
        mHeroes.addAll(heroes);
        notifyItemRangeChanged(startPosition, heroes.size());
    }
}
