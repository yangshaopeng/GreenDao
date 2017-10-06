package com.ysp.greendao;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.ysp.greendao.app.GreenDaoApplication;
import com.ysp.greendao.entity.DaoSession;
import com.ysp.greendao.entity.Note;
import com.ysp.greendao.entity.NoteDao;
import com.ysp.greendao.entity.NoteType;

import org.greenrobot.greendao.query.Query;

import java.util.Date;
import java.util.List;
/*
*   轻量级的库，大小小于100K
*   DaoMaster -->  DaoSession --> NoteDao -->  NoteEntity
*
*
*
* */


public class MainActivity extends AppCompatActivity {

    private NoteDao noteDao;
    private DaoSession daoSession;
    private Query<Note> noteQuery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        daoSession = ((GreenDaoApplication) getApplication()).getDaoSession();
        noteDao = daoSession.getNoteDao();

        Note note = new Note();
        note.setText("yangshaopeng");
        note.setComment("shenzhen");
        note.setDate(new Date());
        note.setType(NoteType.TEXT);
        noteDao.insert(note);
        Log.i("yang:", "Inserted new note, ID: " + note.getId());

        noteQuery = noteDao.queryBuilder().orderAsc(NoteDao.Properties.Text).build();
        List<Note> noteList = noteQuery.list();
        Log.i("yang:", "List size:: " + noteList.size());
        for (int i = 0; i < noteList.size(); i++) {
            Log.i("yang:", noteList.get(i).getId() + ";" + noteList.get(i).getText() + ";" + noteList.get(i).getComment());
        }
    }
}
