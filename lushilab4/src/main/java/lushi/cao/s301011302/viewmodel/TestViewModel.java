package lushi.cao.s301011302.viewmodel;
/**
 * Lushi Cao
 * 301011302
 * COMP304 SEC002
 */
import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import lushi.cao.s301011302.data.PatientRepository;
import lushi.cao.s301011302.data.TestRepository;
import lushi.cao.s301011302.model.Patient;
import lushi.cao.s301011302.model.Test;

public class TestViewModel extends AndroidViewModel {

    private TestRepository repo;
    private LiveData<List<Test>> patientTests;
    private LiveData<List<Test>> allTests;

    public TestViewModel(@NonNull Application application) {
        super(application);
        repo = new TestRepository(application);
        allTests = repo.getAllTests();
    }

    public void delete(Test test){
        repo.delete(test);
    }

    public void insert(Test test){
        repo.insert(test);
    }

    public LiveData<List<Test>> getPatientTests(int id){
        return repo.getPatientTests(id);
    }

    public LiveData<List<Test>> getAllTests(){
        return allTests;
    }
}
