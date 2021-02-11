package lushi.cao.s301011302.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import lushi.cao.s301011302.model.Patient;

@Dao
public interface PatientDao {

    @Query("SELECT * FROM patient")
    LiveData<List<Patient>> getAllPatients();

    @Query("SELECT * FROM patient WHERE doctor_id == 1")
    LiveData<List<Patient>> getMyPatients();

    @Query("SELECT * FROM patient WHERE patient_id =:id")
    LiveData<List<Patient>> getPatient(int id);

    @Query("SELECT * FROM patient WHERE department =:dept")
    LiveData<List<Patient>> getPatientsByDept(String dept);

    @Insert
    void insert(Patient patient);

    @Insert
    void insertAll(Patient... patients);

}
