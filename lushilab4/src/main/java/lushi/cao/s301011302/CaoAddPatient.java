package lushi.cao.s301011302;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import lushi.cao.s301011302.data.AppDatabase;
import lushi.cao.s301011302.model.Patient;
import lushi.cao.s301011302.viewmodel.PatientViewModel;

public class CaoAddPatient extends AppCompatActivity {
    SharedPreferences sharedPref;
    SharedPreferences.Editor sharePrefEdit;
    public static final int ADD_PATIENT_REQUEST = 1;
    public static final String FIRST_NAME = "firstname";
    public static final String LAST_NAME = "lastname";
    public static final String ROOM = "room";
    String[] departments;
    PatientViewModel patientViewModel;
    AppDatabase db;
    Context context;
    EditText firstName;
    EditText lastName;
    EditText roomNum;
    Button button;
    Spinner spinner;
    String deptStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cao_add_patient);
        sharedPref = getSharedPreferences("patientInfo", MODE_PRIVATE);
        context = getApplicationContext();
        patientViewModel = ViewModelProviders.of(this).get(PatientViewModel.class);
        departments = getResources().getStringArray(R.array.departments);
        spinner = findViewById(R.id.lushiDeptSpinner);
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, departments);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                deptStr = parent.getItemAtPosition(position).toString();
                Toast.makeText(parent.getContext(), "Selected: " + deptStr , Toast.LENGTH_LONG).show();
                spinner.setSelection(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(parent.getContext(), "Selected: none" , Toast.LENGTH_LONG).show();
            }
        });

        button = findViewById(R.id.lushiAddPatientBtn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firstName = findViewById(R.id.lushiPatientFirstET);
                lastName = findViewById(R.id.lushiPatientLastET);
                roomNum = findViewById(R.id.lushiPatientRoomET);
                String first = firstName.getText().toString();
                String last = lastName.getText().toString();
                String room = roomNum.getText().toString();
                Intent data = new Intent();
                data.putExtra("first",first);
                data.putExtra("last",last);
                data.putExtra("room",room);
                setResult(RESULT_OK, data);
                patientViewModel.insert(new Patient(1, first, last, room, deptStr));
                Toast.makeText(getApplicationContext(),"inserted " + first,Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}