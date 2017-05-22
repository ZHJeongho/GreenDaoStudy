package com.jeongho.greendaostudy.base;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import com.jeongho.greendaostudy.dao.DaoMaster;
import com.jeongho.greendaostudy.dao.DaoSession;

/**
 * Created by Jeongho on 2017/5/11.
 */

public class GreenApplication extends Application {



    public DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();


        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "dota-db");
        SQLiteDatabase database = helper.getWritableDatabase();

        DaoMaster daoMaster = new DaoMaster(database);
        daoSession = daoMaster.newSession();
    }

    public DaoSession getDaoSession() {
        return daoSession;
    }
}
