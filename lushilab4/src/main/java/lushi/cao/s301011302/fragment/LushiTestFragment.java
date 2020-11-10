package lushi.cao.s301011302.fragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
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

import java.util.List;

public class LushiTestFragment extends AppCompatActivity {

    SharedPreferences sharedPref;
    PatientViewModel patientViewModel;
    TestViewModel testViewModel;
    CaoTestAdapter adapter;
    RecyclerView recyclerView;
    TextView patientInfo;
    Integer patientID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_fragment);
        sharedPref = getApplicationContext().getSharedPreferences("healthInfo", Context.MODE_PRIVATE);
        patientID = sharedPref.getInt("patientId",0);
        recyclerView = findViewById(R.id.lushiTestRecyclerView);
        //patientInfo = findViewById(R.id.lushiPatientInfoTV);
        patientViewModel = ViewModelProviders.of(this).get(PatientViewModel.class);
        testViewModel = ViewModelProviders.of(this).get(TestViewModel.class);
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

    }
}