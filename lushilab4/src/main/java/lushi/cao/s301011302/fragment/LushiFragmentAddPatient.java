package lushi.cao.s301011302.fragment;
/**
 * Lushi Cao
 * 301011302
 * COMP304 SEC002
 */

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

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

import com.google.android.material.snackbar.Snackbar;

import lushi.cao.s301011302.R;
import lushi.cao.s301011302.data.AppDatabase;
import lushi.cao.s301011302.model.Patient;
import lushi.cao.s301011302.viewmodel.PatientViewModel;

import static android.content.Context.MODE_PRIVATE;

public class LushiFragmentAddPatient extends Fragment {
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
    RadioButton maleRadioBtn;
    LinearLayout layout;
    FragmentTransaction fragmentTransaction;
    FragmentManager fragmentManager;
    NavController navController;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_add_patient, container, false);
        context = getActivity().getApplicationContext();
        sharedPref = context.getSharedPreferences("patientInfo", MODE_PRIVATE);
        firstNameET = root.findViewById(R.id.lushiPatientFirstET);
        lastNameET = root.findViewById(R.id.lushiPatientLastET);
        roomET = root.findViewById(R.id.lushiPatientRoomET);
        ageET = root.findViewById(R.id.lushiPatientAgeET);
        layout = root.findViewById(R.id.lushiAddPatientLayout);
        patientViewModel = ViewModelProviders.of(this).get(PatientViewModel.class);
        departments = getResources().getStringArray(R.array.departments);
        genderRdGp = root.findViewById(R.id.lushiPatientGenderRdGp);
        femaleRadioBtn = root.findViewById(R.id.lushiFemaleRdBtn);
        maleRadioBtn = root.findViewById(R.id.lushiMaleRdBtn);
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
//                Toast.makeText(parent.getContext(), "Selected: none", Toast.LENGTH_LONG).show();
            }
        });

        if (maleRadioBtn.isChecked()) {
            gender = getString(R.string.male);
        }
        genderRdGp.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == femaleRadioBtn.getId()) {
                    gender = getString(R.string.female);
                } else if (checkedId == maleRadioBtn.getId()) {
                    gender = getString(R.string.male);
                }
            }
        });

        addPatientBtn = root.findViewById(R.id.lushiAddPatientBtn);
//        addPatientBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //if(validatePatientInfo()) {
//                    //Toast.makeText(getApplicationContext(), "Selected: " + deptStr , Toast.LENGTH_LONG).show();
//                    Patient newPatient = new Patient(1, firstName, lastName, room, deptStr,gender,age);
//                    patientViewModel.insert(newPatient);
//                //}
//            }
//        });
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        addPatientBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validatePatientInfo()) {
                    Patient newPatient = new Patient(1, firstName, lastName, room, deptStr, gender, age);
                    patientViewModel.insert(newPatient);
                    String fName = newPatient.getFirstName() + " " + newPatient.getLastName();
                    showSnackbar(fName);
                    navController = Navigation.findNavController(view);
                    navController.navigateUp();
                }
            }
        });
    }


    public void showPatientListFrag() {
        Fragment fragment = new LushiFragmentViewInformation();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.lushiPatientLayout, fragment);
        fragmentTransaction.commit();
    }

    public boolean validatePatientInfo() {
        boolean isValid = true;

        firstName = firstNameET.getText().toString().trim();
        ;
        lastName = lastNameET.getText().toString().trim();
        ;
        room = roomET.getText().toString().trim();
        age = ageET.getText().toString().trim();
        String numRegex = "^\\d*[0-9]\\d*$";
        String nameRegex = "[a-zA-Z]{2,30}";

        if (!firstName.matches(nameRegex)) {
            firstNameET.requestFocus();
            firstNameET.setError(getString(R.string.enter_valid_fn));
            isValid = false;
        }
        if (!lastName.matches(nameRegex)) {
            lastNameET.requestFocus();
            lastNameET.setError(getString(R.string.enter_valid_ln));
            isValid = false;
        }
        if (!age.matches(numRegex)) {
            ageET.requestFocus();
            ageET.setError(getString(R.string.enter_valid_age));
            isValid = false;
        }
        if (room.isEmpty()) {
            roomET.requestFocus();
            roomET.setError(getString(R.string.nonempty_field));
            isValid = false;
        }
        if (deptStr.isEmpty()) {
            isValid = false;
        }
        return isValid;
    }

    public void showSnackbar(String patientName) {
        Snackbar snackbar = Snackbar.make(layout, getString(R.string.new_patient_added) + patientName, Snackbar.LENGTH_LONG);
        snackbar.show();
    }
}