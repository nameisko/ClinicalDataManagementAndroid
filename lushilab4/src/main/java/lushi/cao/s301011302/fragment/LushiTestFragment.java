package lushi.cao.s301011302.fragment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import lushi.cao.s301011302.PatientViewModel;
import lushi.cao.s301011302.R;
import lushi.cao.s301011302.TestViewModel;
import lushi.cao.s301011302.adapter.CaoPatientAdapter;
import lushi.cao.s301011302.adapter.CaoTestAdapter;
import lushi.cao.s301011302.model.Patient;
import lushi.cao.s301011302.model.Test;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class LushiTestFragment extends AppCompatActivity {

    SharedPreferences sharedPref;
    PatientViewModel patientViewModel;
    TestViewModel testViewModel;
    CaoTestAdapter adapter;
    CaoPatientAdapter patientAdapter;
    RecyclerView recyclerView;
    TextView patientInfo;
    Integer patientID;
    Patient patient1;
    String info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_fragment);
        sharedPref = getApplicationContext().getSharedPreferences("healthInfo", Context.MODE_PRIVATE);
        patientID = sharedPref.getInt("patientId",0);
        recyclerView = findViewById(R.id.lushiTestRecyclerView);
        patientInfo = findViewById(R.id.lushiPatientInfoTV);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new CaoTestAdapter();
        recyclerView.setAdapter(adapter);
        patientViewModel = ViewModelProviders.of(this).get(PatientViewModel.class);
        testViewModel = ViewModelProviders.of(this).get(TestViewModel.class);

        testViewModel.getPatientTests(patientID).observe(this, new Observer<List<Test>>() {
            @Override
            public void onChanged(List<Test> tests) {
                //update recycler view
                adapter.setTests(tests);
            }
        });

        patientViewModel.getSpecificPatient(patientID).observe(this, new Observer<Patient>() {
            @Override
            public void onChanged(Patient patient) {
                info = patient.getFirstName() + " " + patient.getLastName();
                patientInfo.setText("patient name:" + info);
            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT){

            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                testViewModel.delete(adapter.getTestAt(viewHolder.getAdapterPosition()));
                Toast.makeText(getApplicationContext(),"test deleted",Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(recyclerView);
        //LiveData<Patient> patient= patientViewModel.getSpecificPatient(patientID);

        //patientInfo.setText((CharSequence) patient);

    }
}