package lushi.cao.s301011302;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import lushi.cao.s301011302.viewmodel.PatientViewModel;
import lushi.cao.s301011302.viewmodel.TestViewModel;
import lushi.cao.s301011302.adapter.CaoPatientAdapter;
import lushi.cao.s301011302.adapter.CaoTestAdapter;
import lushi.cao.s301011302.model.Patient;
import lushi.cao.s301011302.model.Test;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class CaoTest extends AppCompatActivity {

    SharedPreferences sharedPref;
    PatientViewModel patientViewModel;
    TestViewModel testViewModel;
    CaoTestAdapter testAdapter;
    CaoPatientAdapter patientAdapter;
    RecyclerView recyclerView;
    RecyclerView recyclerView2;
    TextView patientInfo;
    Integer patientID;
    Patient patient;
    String info;
    Button doneBtn;
    LinearLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cao_test);
        layout = findViewById(R.id.testLayout);
        sharedPref = getApplicationContext().getSharedPreferences("healthInfo", Context.MODE_PRIVATE);
        patientID = sharedPref.getInt("patientId",0);
        recyclerView = findViewById(R.id.lushiTestRecyclerView);
        recyclerView2 = findViewById(R.id.lushiTestPatientInfoRecyclerView);
        doneBtn = findViewById(R.id.lushidTestDoneBtn);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView2.setLayoutManager(new LinearLayoutManager(this));
        testAdapter = new CaoTestAdapter();
        patientAdapter = new CaoPatientAdapter();
        patientAdapter.setContext(this);
        recyclerView.setAdapter(testAdapter);
        recyclerView2.setAdapter(patientAdapter);
        patientViewModel = ViewModelProviders.of(this).get(PatientViewModel.class);
        testViewModel = ViewModelProviders.of(this).get(TestViewModel.class);
        testViewModel.getPatientTests(patientID).observe(this, new Observer<List<Test>>() {
            @Override
            public void onChanged(List<Test> tests) {
                //update recycler view
                testAdapter.setTests(tests);
            }
        });

        patientViewModel.getSpecificPatient(patientID).observe(this, new Observer<List<Patient>>() {
            @Override
            public void onChanged(List<Patient> patient) {
                //update recycler view
                patientAdapter.setPatients(patient);
            }
        });

        doneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
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
                testViewModel.delete(testAdapter.getTestAt(viewHolder.getAdapterPosition()));
                //Toast.makeText(getApplicationContext(),"test deleted",Toast.LENGTH_SHORT).show();
                showSnackbar();
            }
        }).attachToRecyclerView(recyclerView);
    }
    public void showSnackbar(){
        Snackbar snackbar = Snackbar.make(layout, "Test record deleted",Snackbar.LENGTH_LONG)
                .setActionTextColor(Color.parseColor("#f5b461"));
        snackbar.show();
    }
}