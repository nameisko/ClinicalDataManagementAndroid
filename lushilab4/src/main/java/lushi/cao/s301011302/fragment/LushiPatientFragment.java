package lushi.cao.s301011302.fragment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import lushi.cao.s301011302.R;
import lushi.cao.s301011302.adapter.CaoPatientAdapter;
import lushi.cao.s301011302.model.Patient;
import lushi.cao.s301011302.viewmodel.PatientViewModel;

public class LushiPatientFragment extends AppCompatActivity {
    private static final String ARG_SECTION_NUMBER = "section_number";
    CaoPatientAdapter adapter;
    RecyclerView recylcerView;
    PatientViewModel patientViewModel;
    SharedPreferences sharedPref;
    String deptartment;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.patient_fragment);
        sharedPref = getApplicationContext().getSharedPreferences("healthInfo", Context.MODE_PRIVATE);
        deptartment = sharedPref.getString("department",null);
        recylcerView = findViewById(R.id.lushiRecyclerView);
        recylcerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new CaoPatientAdapter();
        recylcerView.setAdapter(adapter);
        adapter.setContext(this);
        Toast.makeText(this,"from patient: " +deptartment,Toast.LENGTH_SHORT).show();
        patientViewModel = ViewModelProviders.of(this).get(PatientViewModel.class);
        patientViewModel.getPatientsByDept(deptartment).observe(this, new Observer<List<Patient>>() {
            @Override
            public void onChanged(List<Patient> patients) {
                //update recycler view
                adapter.setPatients(patients);
            }
        });
    }
}