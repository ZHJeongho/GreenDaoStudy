package com.jeongho.greendaostudy;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
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

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView mTextView;
    private Button mAddBtn;
    private Button mDeleteBtn;
    private Button mUpdateBtn;

    private DotaHeroDao mDotaHeroDao;

    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initListener();

        DaoSession daoSession = ((GreenApplication) getApplicationContext()).getDaoSession();
        mDotaHeroDao = daoSession.getDotaHeroDao();

        showAllHeros();

        List<DotaHero> heros = mDotaHeroDao.queryBuilder()
                .where(DotaHeroDao.Properties.Hp.eq(60))
                .list();

        Toast.makeText(this, "血量等于60的英雄有：" + heros.size() + "个", Toast.LENGTH_SHORT).show();
    }

    private void showAllHeros() {
        mTextView.setText("");

        List<DotaHero> allHero = mDotaHeroDao.loadAll();
        showHeros(allHero);

    }

    private void initView() {
        mTextView = (TextView) findViewById(R.id.tv);
        mAddBtn = (Button) findViewById(R.id.btn_add);
        mDeleteBtn = (Button) findViewById(R.id.btn_delete);
        mUpdateBtn = (Button) findViewById(R.id.btn_update);
        mRecyclerView = (RecyclerView) findViewById(R.id.rv);
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
                showAllHeros();
                break;
            case R.id.btn_delete:
                deleteHero();
                showAllHeros();
                break;
            case R.id.btn_update:
                updateHero();
                showAllHeros();
                break;
        }
    }

    private void updateHero() {

    }

    private void deleteHero() {
        mDotaHeroDao.deleteByKey(mDotaHeroDao.count());
    }

    private void addHero() {

        DotaHero hero = new DotaHero();
        hero.setId(mDotaHeroDao.count() + 1);
        hero.setName("Jugg");
        hero.setHp((int) (Math.random() * 100));
        hero.setMp((int) (Math.random() * 100));
        hero.setExp(200 + (int) (Math.random() * 100));

        mDotaHeroDao.insert(hero);
    }

    private void orderAsc(){
        QueryBuilder<DotaHero> queryBuilder = mDotaHeroDao.queryBuilder().orderAsc(DotaHeroDao.Properties.Hp);
        List<DotaHero> list = queryBuilder.list();
        showHeros(list);
    }

    private void orderDesc(){
        QueryBuilder<DotaHero> queryBuilder = mDotaHeroDao.queryBuilder().orderDesc(DotaHeroDao.Properties.Hp);
        List<DotaHero> list = queryBuilder.list();
        showHeros(list);
    }

    private void showHeros(List<DotaHero> list) {
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        DotaAdapter adapter = new DotaAdapter(list);
        mRecyclerView.setAdapter(adapter);

        mTextView.setText("");

        mTextView.append("当前数据库中有" + list.size()+ "个英雄\r\n");
        for (int i = 0; i < list.size(); i++){

            DotaHero hero = list.get(i);

            mTextView.append("ID: " + hero.getId() + "英雄名称：" + hero.getName() + "  HP:" + hero.getHp()
                    + "   MP:" + hero.getMp() + "   EXP:" + hero.getExp() + "\r\n");
        }
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
        }
        return true;
    }
}
