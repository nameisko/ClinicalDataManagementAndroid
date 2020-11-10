package lushi.cao.s301011302;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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

import lushi.cao.s301011302.data.AppDatabase;
import lushi.cao.s301011302.model.Patient;

import static java.security.AccessController.getContext;

public class CaoAddTest extends AppCompatActivity {
    ArrayAdapter<String> adapter;
    PatientViewModel patientViewModel;
    Context context;
    ArrayList<String> patientNameList;
    Calendar calender;
    EditText calenderET;
    Spinner spinner;
    DatePickerDialog dialog;
    DatePicker datePicker;
    private Integer year, month, day;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cao_add_test);
        patientViewModel = ViewModelProviders.of(this).get(PatientViewModel.class);
        spinner = findViewById(R.id.lushiPatientSpinner);
        calenderET = findViewById(R.id.lushiTestDateET);

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


        //---get the current date---

        patientNameList = new ArrayList<>();
        patientNameList.add("Select a patient");
        patientViewModel.getAllPatients().observe(this, new Observer<List<Patient>>() {
            @Override
            public void onChanged(List<Patient> patients) {
                for(Patient p : patients){
                    patientNameList.add(p.getFirstName() +" "+ p.getLastName());
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

                String item = parent.getItemAtPosition(position).toString();
                Toast.makeText(parent.getContext(), "Selected: " + item , Toast.LENGTH_LONG).show();
                spinner.setSelection(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(parent.getContext(), "Selected: none" , Toast.LENGTH_LONG).show();
            }
        });
    }
}