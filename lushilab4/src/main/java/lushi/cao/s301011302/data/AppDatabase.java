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

@Database(entities = {Patient.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase instance;
    private static final String DATABASE_NAME = "appdb";
    public abstract PatientDao patientDao();

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

        private PrepopulateAsyncTask(AppDatabase db){
            patientDao = db.patientDao();
        }

        @Override
        protected Void doInBackground(Void... voids){
            patientDao.insert(new Patient(2,"Billy", "Bill", "315A"));
            patientDao.insert(new Patient(2,"Donald", "Trump", "400B"));
            patientDao.insert(new Patient(3,"Cristiano", "Ronaldo", "315A"));
            patientDao.insert(new Patient(1,"Kayne", "West", "317A"));
            return null;
        }
    }
}
