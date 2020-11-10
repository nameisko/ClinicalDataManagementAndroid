package lushi.cao.s301011302;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import lushi.cao.s301011302.adapter.CaoPatientAdapter;
import lushi.cao.s301011302.adapter.CaoTestAdapter;
import lushi.cao.s301011302.data.AppDatabase;
import lushi.cao.s301011302.fragment.LushiTestFragment;
import lushi.cao.s301011302.model.Patient;
import lushi.cao.s301011302.model.Test;

import static java.security.AccessController.getContext;

public class CaoAddTest extends AppCompatActivity {

    PatientViewModel patientViewModel;
    Context context;
    SharedPreferences sharedPref;
    SharedPreferences.Editor sharedPrefEditor;
    ArrayList<String> patientNameList;
    List<Patient> allPatients;
    EditText calenderET;
    Spinner spinner;
    private Integer year, month, day;
    Button addTestBtn;
    TestViewModel testViewModel;
    RecyclerView recyclerView;
    CaoTestAdapter adapter;
    EditText temperatureET;
    EditText bpET;
    EditText dateET;
    Integer patientID;
    String patientName;
    String date;
    String bp;
    String temperature;
    String covid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cao_add_test);
        sharedPref = getApplicationContext().getSharedPreferences("healthInfo", Context.MODE_PRIVATE);
        sharedPrefEditor = sharedPref.edit();
        patientViewModel = ViewModelProviders.of(this).get(PatientViewModel.class);
        testViewModel = ViewModelProviders.of(this).get(TestViewModel.class);
        spinner = findViewById(R.id.lushiPatientSpinner);
        temperatureET = findViewById(R.id.lushiTemperatureET);
        bpET = findViewById(R.id.lushiBloodPressureET);
        dateET = findViewById(R.id.lushiTestDateET);
        calenderET = findViewById(R.id.lushiTestDateET);
        temperature = temperatureET.getText().toString();
        bp = bpET.getText().toString();

        adapter = new CaoTestAdapter();
        String first, last;
//        String[] array = patientName.split(" ");
//        first = array[0];
//        last = array[1];
//        LiveData<Patient> patient = patientViewModel.getSpecificPatient("Donald", "Trump");

        addTestBtn = findViewById(R.id.lushiAddTestBtn);
        addTestBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //patientID = specificPatient.getPatientID();
//                testViewModel.insert(new Test(Integer.parseInt(patientID), Integer.parseInt(bp),
//                        temperature, false, date));
                date = dateET.getText().toString();
                patientID = Integer.parseInt(patientName.split(" ")[0]);
                testViewModel.insert(new Test(patientID, 80,
                        temperature, false, date));
                sharedPrefEditor.putInt("patientId", patientID);
                sharedPrefEditor.apply();
                Intent intent = new Intent(getApplicationContext(), LushiTestFragment.class);
                startActivity(intent);
            }
        });

        Calendar today = Calendar.getInstance();
        year = today.get(Calendar.YEAR);
        month = today.get(Calendar.MONTH);
        day = today.get(Calendar.DAY_OF_MONTH);

        calenderET.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog =new DatePickerDialog(CaoAddTest.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        calenderET.setText(dayOfMonth+"/"+month+"/"+year);
                    }
                },year,month,day);
                datePickerDialog.show();
            }
        });

        patientNameList = new ArrayList<>();
        patientNameList.add("Select a patient");
        patientViewModel.getAllPatients().observe(this, new Observer<List<Patient>>() {
            @Override
            public void onChanged(List<Patient> patients) {
                for(Patient p : patients){
                    patientNameList.add(p.getPatientID() + " " + p.getFirstName() +" "+ p.getLastName());
                    //allPatients.add(p);
                }
            }
        });
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, patientNameList);

        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(dataAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                //String item = parent.getItemAtPosition(position).toString();
                patientName = parent.getItemAtPosition(position).toString();
                Toast.makeText(parent.getContext(), "Selected: " + patientName.split(" ")[0] , Toast.LENGTH_LONG).show();
                spinner.setSelection(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(parent.getContext(), "Selected: none" , Toast.LENGTH_LONG).show();
            }
        });
    }
}