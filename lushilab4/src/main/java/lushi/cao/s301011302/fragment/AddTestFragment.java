package lushi.cao.s301011302.fragment;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import lushi.cao.s301011302.CaoAddTest;
import lushi.cao.s301011302.CaoTest;
import lushi.cao.s301011302.R;
import lushi.cao.s301011302.adapter.CaoTestAdapter;
import lushi.cao.s301011302.model.Patient;
import lushi.cao.s301011302.model.Test;
import lushi.cao.s301011302.viewmodel.PatientViewModel;
import lushi.cao.s301011302.viewmodel.TestViewModel;

public class AddTestFragment extends Fragment {

    NavController navController;
    FloatingActionButton addTestFab;
    FloatingActionButton addPatientFab;
    FloatingActionsMenu mainFab;
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
    boolean covid;
    RadioGroup radioGroup;
    RadioButton covidNegativeRadioBtn;
    ArrayAdapter<String> dataAdapter;
    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.activity_cao_add_test, container, false);
        //navController = Navigation.findNavController(root);
        Context context = getActivity().getApplicationContext();
        view = root;
        sharedPref = context.getSharedPreferences("healthInfo", Context.MODE_PRIVATE);
        sharedPrefEditor = sharedPref.edit();
        radioGroup = root.findViewById(R.id.lushiCovidRadioGp);
        covidNegativeRadioBtn = root.findViewById(R.id.lushiCovidNeBtn);
        patientViewModel = ViewModelProviders.of(this).get(PatientViewModel.class);
        testViewModel = ViewModelProviders.of(this).get(TestViewModel.class);
        spinner = root.findViewById(R.id.lushiPatientSpinner);
        temperatureET = root.findViewById(R.id.lushiTemperatureET);
        bpET = root.findViewById(R.id.lushiBloodPressureET);
        dateET = root.findViewById(R.id.lushiTestDateET);
        calenderET = root.findViewById(R.id.lushiTestDateET);
        temperature = temperatureET.getText().toString();
        bp = bpET.getText().toString();
        adapter = new CaoTestAdapter();

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == covidNegativeRadioBtn.getId()) {
                    covid = false;
                } else {
                    covid = true;
                }
            }
        });

        addTestBtn = root.findViewById(R.id.lushiAddTestBtn);
//        addTestBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                //patientID = specificPatient.getPatientID();
////                testViewModel.insert(new Test(Integer.parseInt(patientID), Integer.parseInt(bp),
////                        temperature, false, date));
//                date = dateET.getText().toString();
//                patientID = Integer.parseInt(patientName.split(" ")[0]);
//                testViewModel.insert(new Test(patientID, bp,
//                        temperature, covid, date));
////                sharedPrefEditor.putInt("patientId", patientID);
////                sharedPrefEditor.apply();
//            }
//        });

        Calendar today = Calendar.getInstance();
        year = today.get(Calendar.YEAR);
        month = today.get(Calendar.MONTH);
        day = today.get(Calendar.DAY_OF_MONTH);

        calenderET.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        calenderET.setText(dayOfMonth + "/" + month + "/" + year);
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });

        patientNameList = new ArrayList<String>();
        //patientNameList.add("Select a patient");
//        patientViewModel.getAllPatients().observe(this, new Observer<List<Patient>>() {
//            @Override
//            public void onChanged(List<Patient> patients) {
//                for(Patient p : patients){
//                    patientNameList.add(p.getPatientID() + " " + p.getFirstName() +" "+ p.getLastName());
//                    //allPatients.add(p);
//                }
//            }
//        });
        patientViewModel.getAllPatients().observe(getActivity(), results -> {
            for (Patient p : results) {
                patientNameList.add(p.getPatientID() + " " + p.getFirstName() + " " + p.getLastName());
            }
            dataAdapter = new ArrayAdapter<String>(
                    context, android.R.layout.simple_spinner_item, patientNameList);
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(dataAdapter);
        });
//        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(
//                this, android.R.layout.simple_spinner_item, patientNameList);


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                //String item = parent.getItemAtPosition(position).toString();
                patientName = parent.getItemAtPosition(position).toString();
                Toast.makeText(parent.getContext(), "Selected: " + patientName.split(" ")[0], Toast.LENGTH_LONG).show();
                spinner.setSelection(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(parent.getContext(), "Selected: none", Toast.LENGTH_LONG).show();
            }
        });
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        addTestBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validateTestInfo()){
                    date = dateET.getText().toString();
                    patientID = Integer.parseInt(patientName.split(" ")[0]);
                    testViewModel.insert(new Test(patientID, bp,
                            temperature, covid, date));
                    navController = Navigation.findNavController(view);
                    navController.navigateUp();
                }
            }
        });
    }

    public boolean validateTestInfo(){
        return true;
    }
}