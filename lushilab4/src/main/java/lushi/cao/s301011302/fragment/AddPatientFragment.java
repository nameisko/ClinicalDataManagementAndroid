package lushi.cao.s301011302.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import lushi.cao.s301011302.R;
import lushi.cao.s301011302.data.AppDatabase;
import lushi.cao.s301011302.model.Patient;
import lushi.cao.s301011302.viewmodel.PatientViewModel;

import static android.content.Context.MODE_PRIVATE;

public class AddPatientFragment extends Fragment {
    SharedPreferences sharedPref;
    SharedPreferences.Editor sharePrefEdit;
    String[] departments;
    PatientViewModel patientViewModel;
    AppDatabase db;
    Context context;
    EditText firstNameET;
    EditText lastNameET;
    EditText roomET;
    EditText genderET;
    EditText ageET;
    String firstName;
    String lastName;
    String room;
    String gender;
    String age;
    Button addPatientBtn;
    Spinner spinner;
    String deptStr;
    RadioGroup genderRdGp;
    RadioButton femaleRadioBtn;
    LinearLayout layout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root= inflater.inflate(R.layout.activity_cao_add_patient, container, false);
        context = getActivity().getApplicationContext();
        sharedPref = context.getSharedPreferences("patientInfo", MODE_PRIVATE);
        firstNameET = root.findViewById(R.id.lushiPatientFirstET);
        lastNameET = root.findViewById(R.id.lushiPatientLastET);
        roomET = root.findViewById(R.id.lushiPatientRoomET);
        ageET = root.findViewById(R.id.lushiPatientAgeET);
        patientViewModel = ViewModelProviders.of(this).get(PatientViewModel.class);
        departments = getResources().getStringArray(R.array.departments);
        genderRdGp = root.findViewById(R.id.lushiPatientGenderRdGp);
        femaleRadioBtn = root.findViewById(R.id.lushiFemaleRdBtn);

        spinner = root.findViewById(R.id.lushiDeptSpinner);
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(
                context, android.R.layout.simple_spinner_item, departments);

        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                deptStr = parent.getItemAtPosition(position).toString();
                spinner.setSelection(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(parent.getContext(), "Selected: none" , Toast.LENGTH_LONG).show();
            }
        });

        genderRdGp.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == femaleRadioBtn.getId()) {
                    gender = "Female";
                } else {
                    gender = "Male";
                }
            }
        });

        addPatientBtn = root.findViewById(R.id.lushiAddPatientBtn);
        addPatientBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validatePatientInfo()) {
                    //Toast.makeText(getApplicationContext(), "Selected: " + deptStr , Toast.LENGTH_LONG).show();
                    Patient newPatient = new Patient(1, firstName, lastName, room, deptStr,gender,age);
                    patientViewModel.insert(newPatient);
                }
            }
        });
        return root;
    }
    public boolean validatePatientInfo(){
        boolean isValid = true;

        firstName = firstNameET.getText().toString().trim();;
        lastName = lastNameET.getText().toString().trim();;
        room = roomET.getText().toString().trim();
        age = ageET.getText().toString().trim();
        String numRegex = "[0-9]";

        if(firstName.isEmpty()){
            firstNameET.requestFocus();
            firstNameET.setError("Field cannot be empty");
            isValid = false;
        }
        if(lastName.isEmpty()){
            lastNameET.requestFocus();
            lastNameET.setError("Field cannot be empty");
            isValid = false;
        }
        if(age.isEmpty() || age.matches(numRegex)){
            ageET.requestFocus();
            ageET.setError("Enter valid age");
            isValid = false;
        }
        if(room.isEmpty()){
            roomET.requestFocus();
            roomET.setError("Field cannot be empty");
            isValid = false;
        }
        if(deptStr.isEmpty()){
            isValid = false;
        }
        return isValid;
    }
}