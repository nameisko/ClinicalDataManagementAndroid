package lushi.cao.s301011302.data;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
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
            patientDao.insert(new Patient(2,"Donald", "Trump", "111A","Blood Lab"));
            patientDao.insert(new Patient(2,"Joe", "Biden", "210C","Allergy"));
            patientDao.insert(new Patient(3,"Cristiano", "Ronaldo", "222B","Nerosurgery"));
            patientDao.insert(new Patient(4,"Kayne", "West", "317A","Orthopedic"));
            patientDao.insert(new Patient(4,"Justin", "Biber", "318A","Orthopedic"));
            testDao.insert(new Test(5, "90","36c",false,"10/12/2020"));
            return null;
        }
    }
}
