package lushi.cao.s301011302;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import lushi.cao.s301011302.data.PatientRepository;
import lushi.cao.s301011302.model.Patient;

public class PatientViewModel extends AndroidViewModel {

    private PatientRepository repo;
    private LiveData<List<Patient>> allPatients;
    private LiveData<List<Patient>> myPatients;

    public PatientViewModel(@NonNull Application application) {
        super(application);
        repo = new PatientRepository(application);
        allPatients = repo.getAllPatients();
        myPatients = repo.getMyPatients();
    }

    public void insert(Patient patient){
        repo.insert(patient);
    }

    public LiveData<List<Patient>> getAllPatients(){
        return allPatients;
    }

    public LiveData<List<Patient>> getMyPatients(){
        return myPatients;
    }
}
