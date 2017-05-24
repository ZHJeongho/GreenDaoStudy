package com.jeongho.greendaostudy;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.jeongho.greendaostudy.adapter.DotaAdapter;
import com.jeongho.greendaostudy.base.GreenApplication;
import com.jeongho.greendaostudy.dao.DaoSession;
import com.jeongho.greendaostudy.dao.DotaHero;
import com.jeongho.greendaostudy.dao.DotaHeroDao;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView mTextView;
    private Button mAddBtn;
    private Button mDeleteBtn;
    private Button mUpdateBtn;

    private DotaHeroDao mDotaHeroDao;

    private RecyclerView mRecyclerView;

    private DotaAdapter mAdapter;

    private List<DotaHero> mDotaHeroes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initData();
        initListener();


        List<DotaHero> heros = mDotaHeroDao.queryBuilder()
                .where(DotaHeroDao.Properties.Hp.eq(60))
                .list();

        Toast.makeText(this, "血量等于60的英雄有：" + heros.size() + "个", Toast.LENGTH_SHORT).show();
    }

    private void initData() {
        DaoSession daoSession = ((GreenApplication) getApplicationContext()).getDaoSession();
        mDotaHeroDao = daoSession.getDotaHeroDao();
//        mDotaHeroDao.deleteAll();

        mDotaHeroes = mDotaHeroDao.loadAll();
        mAdapter = new DotaAdapter(mDotaHeroes);
        mRecyclerView.setAdapter(mAdapter);
    }

    private void initView() {
        mTextView = (TextView) findViewById(R.id.tv);
        mAddBtn = (Button) findViewById(R.id.btn_add);
        mDeleteBtn = (Button) findViewById(R.id.btn_delete);
        mUpdateBtn = (Button) findViewById(R.id.btn_update);
        mRecyclerView = (RecyclerView) findViewById(R.id.rv);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    private void initListener() {
        mAddBtn.setOnClickListener(this);
        mDeleteBtn.setOnClickListener(this);
        mUpdateBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_add:
                addHero();
                break;
            case R.id.btn_delete:
                deleteHero(0);
                break;
            case R.id.btn_update:
                updateHero();
                break;
        }
    }

    private void updateHero() {

    }

    private void deleteHero(int position) {
        mDotaHeroDao.deleteByKey(mDotaHeroes.get(position).getId());
        mDotaHeroes.remove(position);
        Toast.makeText(this, "" + mDotaHeroDao.count(), Toast.LENGTH_SHORT).show();
        mAdapter.removeHero(position);
    }

    private void addHero() {
        List<DotaHero> heroes = new LinkedList<>();
        for (int i = 0; i < 20; i++){
            String name = "Jugg";
            int hp = (int) (Math.random() * 100);
            int mp = (int) (Math.random() * 100);
            int exp = (int) (Math.random() * 100) + 200;

            DotaHero hero = new DotaHero();
            hero.setId(i);
            hero.setName(name);
            hero.setHp(hp);
            hero.setMp(mp);
            hero.setExp(exp);

            heroes.add(hero);
        }
        mDotaHeroDao.insertInTx(heroes);

        mAdapter.addHeros(heroes);
    }

    private void orderAsc(){
        QueryBuilder<DotaHero> queryBuilder = mDotaHeroDao.queryBuilder().orderAsc(DotaHeroDao.Properties.Hp);
        mDotaHeroes = queryBuilder.list();
        mAdapter.notifyDataSetChanged();
    }

    private void orderDesc(){
        QueryBuilder<DotaHero> queryBuilder = mDotaHeroDao.queryBuilder().orderDesc(DotaHeroDao.Properties.Hp);
        mDotaHeroes = queryBuilder.list();
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.asc:
                orderAsc();
                break;
            case R.id.desc:
                orderDesc();
                break;
            case R.id.linear_manager:
                mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
                mAdapter.notifyDataSetChanged();
                break;
            case R.id.grid_manager:
                mRecyclerView.setLayoutManager(new GridLayoutManager(this, 3));
                mAdapter.notifyDataSetChanged();
                break;
        }
        return true;
    }
}
