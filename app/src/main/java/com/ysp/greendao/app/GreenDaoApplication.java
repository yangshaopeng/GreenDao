package com.ysp.greendao.app;

import android.app.Application;

import com.ysp.greendao.entity.DaoMaster;
import com.ysp.greendao.entity.DaoMaster.DevOpenHelper;
import com.ysp.greendao.entity.DaoSession;

import org.greenrobot.greendao.database.Database;

/**
 * author : yangshaopeng.
 * Description:.....
 * 2017/10/6 18:50
 * email : yangshaopeng_it@163.com
 */

public class GreenDaoApplication extends Application {

    private DaoSession daoSession;
    public static final boolean ENCRYPTED = true;

    @Override
    public void onCreate() {
        super.onCreate();
        DevOpenHelper devOpenHelper = new DevOpenHelper(this, ENCRYPTED ? "notes-db-encrypted" : "notes-db");
        Database db = ENCRYPTED ? devOpenHelper.getEncryptedWritableDb("super-secret") : devOpenHelper.getWritableDb();
        daoSession = new DaoMaster(db).newSession();
    }

    public DaoSession getDaoSession() {
        return daoSession;
    }

}
