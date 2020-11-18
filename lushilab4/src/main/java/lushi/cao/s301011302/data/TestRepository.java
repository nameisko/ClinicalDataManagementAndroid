package lushi.cao.s301011302.data;
/**
 * Lushi Cao
 * 301011302
 * COMP304 SEC002
 */

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

import lushi.cao.s301011302.model.Patient;
import lushi.cao.s301011302.model.Test;

public class TestRepository {
    private TestDao testDao;
    private LiveData<List<Test>> allTests;

    public TestRepository(Application application) {
        AppDatabase db = AppDatabase.getInstance(application);
        testDao = db.testDao();
        allTests = testDao.getAllTests();
    }

    public void insert(Test test) {

        new InsertTestAsyncTask(testDao).execute(test);
    }

    public void delete(Test test) {

        new DeleteTestAsyncTask(testDao).execute(test);
    }

    public LiveData<List<Test>> getPatientTests(int id) {
        return testDao.getPatientTests(id);
    }

    public LiveData<List<Test>> getAllTests() {
        return allTests;
    }

    private static class InsertTestAsyncTask extends AsyncTask<Test, Void, Void> {
        private TestDao testDao;

        private InsertTestAsyncTask(TestDao dao) {
            testDao = dao;
        }

        @Override
        protected Void doInBackground(Test... tests) {
            testDao.insert(tests[0]);
            return null;
        }
    }

    private static class DeleteTestAsyncTask extends AsyncTask<Test, Void, Void> {
        private TestDao testDao;

        private DeleteTestAsyncTask(TestDao dao) {
            testDao = dao;
        }

        @Override
        protected Void doInBackground(Test... tests) {
            testDao.delete(tests[0]);
            return null;
        }
    }
}
