package lushi.cao.s301011302.data;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SimpleSQLiteQuery;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import lushi.cao.s301011302.model.Patient;
import lushi.cao.s301011302.model.Test;

@Database(entities = {Patient.class, Test.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase instance;
    private static final String DATABASE_NAME = "appdb";
    public abstract PatientDao patientDao();
    public abstract TestDao testDao();

    public synchronized static AppDatabase getInstance(Context context) {
        if (instance == null) {
            instance = buildDatabase(context);
        }
        return instance;
    }

    private static AppDatabase buildDatabase(final Context context) {
        return Room.databaseBuilder(context,
                AppDatabase.class,
                DATABASE_NAME)
                .fallbackToDestructiveMigration()
                .addCallback(new Callback() {
                    @Override
                    public void onCreate(@NonNull SupportSQLiteDatabase db) {
                        super.onCreate(db);
                        new PrepopulateAsyncTask(instance).execute();
                    }
                })
                .build();
    }

    private static class PrepopulateAsyncTask extends AsyncTask<Void, Void, Void>{
        private PatientDao patientDao;
        private TestDao testDao;

        private PrepopulateAsyncTask(AppDatabase db){
            patientDao = db.patientDao();
            testDao = db.testDao();
        }

        @Override
        protected Void doInBackground(Void... voids){
            patientDao.insert(new Patient(2,"Donald", "Trump", "111A","Blood Lab","Male", "74"));
            patientDao.insert(new Patient(2,"Joe", "Biden", "210C","Allergy","Male", "77"));
            patientDao.insert(new Patient(3,"Cristiano", "Ronaldo", "222B","Nerosurgery","Male","35"));
            patientDao.insert(new Patient(4,"Justin", "Biber", "318A","Orthopedic","Male","26"));
            patientDao.insert(new Patient(4,"Hillary", "Clinton", "318A","Allergy","Female","73"));

            testDao.insert(new Test(1, "100","14","85%","104",true,"11/11/2020"));
            testDao.insert(new Test(1, "100","16","95%","90",false,"11/23/2020"));

            testDao.insert(new Test(2, "90","20","90%","120",false,"10/13/2020"));
            testDao.insert(new Test(2, "92","19","95%","110",false,"10/14/2020"));


            testDao.insert(new Test(3, "110","19","86%","112",true,"11/13/2020"));
            testDao.insert(new Test(3, "89","18","90%","100",false,"11/28/2020"));

            testDao.insert(new Test(4, "120","19","88%","90",true,"11/11/2020"));
            testDao.insert(new Test(4, "90","18","92%","100",false,"11/15/2020"));
            return null;
        }
    }
}
