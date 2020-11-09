package lushi.cao.s301011302.data;

import android.app.Application;
import android.os.AsyncTask;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;

import java.util.List;

import lushi.cao.s301011302.model.Patient;

public class PatientRepository {
    private PatientDao patientDao;
    private LiveData<List<Patient>> allPatients;
    private LiveData<List<Patient>> myPatients;

    public PatientRepository(Application application){
        AppDatabase db = AppDatabase.getInstance(application);
        patientDao = db.patientDao();
        allPatients = patientDao.getAllPatients();
        myPatients = patientDao.getMyPatients();
    }

    public void insert(Patient patient){

        new InsertPatientAsyncTask(patientDao).execute(patient);
    }

    public LiveData<List<Patient>> getAllPatients(){
        return allPatients;
    }

    public LiveData<List<Patient>> getMyPatients(){
        return myPatients;
    }

    private static class InsertPatientAsyncTask extends AsyncTask<Patient, Void, Void>{
        private PatientDao patientDao;

        private InsertPatientAsyncTask(PatientDao dao){
            patientDao = dao;
        }

        @Override
        protected Void doInBackground(Patient... patients){
            patientDao.insert(patients[0]);
            return null;
        }
    }
}
