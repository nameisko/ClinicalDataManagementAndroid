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
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class CaoTest extends AppCompatActivity {

    SharedPreferences sharedPref;
    PatientViewModel patientViewModel;
    TestViewModel testViewModel;
    CaoTestAdapter adapter;
    CaoPatientAdapter patientAdapter;
    RecyclerView recyclerView;
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
        patientInfo = findViewById(R.id.lushiPatientInfoTV);
        doneBtn = findViewById(R.id.lushidTestDoneBtn);
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

        patientViewModel.getSpecificPatient(patientID).observe(this, specificPatient -> {
            patient = specificPatient;
            String info = "patient name: " + patient.getFirstName() + " " + patient.getLastName() + "\n" +
                    "room: " + patient.getRoom();

            patientInfo.setText(info);
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
                testViewModel.delete(adapter.getTestAt(viewHolder.getAdapterPosition()));
                //Toast.makeText(getApplicationContext(),"test deleted",Toast.LENGTH_SHORT).show();
                showSnackbar();
            }
        }).attachToRecyclerView(recyclerView);

        //LiveData<Patient> patient= patientViewModel.getSpecificPatient(patientID);

        //patientInfo.setText((CharSequence) patient);

    }
    public void showSnackbar(){
        Snackbar snackbar = Snackbar.make(layout, "Test record deleted",Snackbar.LENGTH_LONG)
                .setActionTextColor(Color.parseColor("#f5b461"));
        snackbar.show();
    }
}