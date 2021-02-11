package lushi.cao.s301011302.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import lushi.cao.s301011302.model.Patient;
import lushi.cao.s301011302.model.Test;

@Dao
public interface TestDao {

    @Query("SELECT * FROM test")
    LiveData<List<Test>> getAllTests();

    @Query("SELECT * FROM test WHERE patient_id =:id ORDER BY date DESC")
    LiveData<List<Test>> getPatientTests(int id);

    @Insert
    void insert(Test test);

    @Update
    void update(Test test);

    @Delete
    void delete(Test test);
}
