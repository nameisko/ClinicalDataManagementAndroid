package lushi.cao.s301011302.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
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
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

import lushi.cao.s301011302.R;
import lushi.cao.s301011302.model.Patient;
import lushi.cao.s301011302.viewmodel.PatientViewModel;
import lushi.cao.s301011302.viewmodel.TestViewModel;

public class LushiFragmentSearch extends Fragment {

    SharedPreferences sharedPref;
    SharedPreferences.Editor sharedPrefEditor;
    TestViewModel testViewModel;
    PatientViewModel patientViewModel;
    Button searchByIdBtn;
    Button searchByDeptBtn;
    EditText idET;
    Integer id;
    String idStr;
    Spinner spinner;
    String departments[];
    String deptStr;
    FragmentTransaction fragmentTransaction;
    FragmentManager fragmentManager;
    Fragment fragment;
    LinearLayout layout;
    NavController navController;
    boolean exist;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_search, container, false);
        Context context = getActivity().getApplicationContext();
        sharedPref = context.getSharedPreferences("healthInfo", Context.MODE_PRIVATE);
        sharedPrefEditor = sharedPref.edit();
        layout = root.findViewById(R.id.lushiSearchLayout);
        testViewModel = ViewModelProviders.of(this).get(TestViewModel.class);
        patientViewModel = ViewModelProviders.of(this).get(PatientViewModel.class);
        departments = getResources().getStringArray(R.array.departments);
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        idET = root.findViewById(R.id.lushiSearchByIdET);
        spinner = root.findViewById(R.id.lushiSearchDeptSpinner);
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(
                context, android.R.layout.simple_spinner_item, departments);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                deptStr = parent.getItemAtPosition(position).toString();
//                Toast.makeText(context, deptStr, Toast.LENGTH_SHORT).show();
                spinner.setSelection(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
//                Toast.makeText(parent.getContext(), "Selected: none", Toast.LENGTH_LONG).show();
            }
        });

//        patientViewModel.getAllPatients().observe(getActivity(), results -> {
//            if(results.size()==0){
//                idET.requestFocus();
//                idET.setError("Patient does not exist, please re-enter");
//                patientExist = false;
//            }
//        });
//        searchByIdBtn = root.findViewById(R.id.lushiSearchByIdBtn);
//        searchByIdBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                id = Integer.parseInt(idET.getText().toString());
//                sharedPrefEditor.putInt("patientId", id);
//                sharedPrefEditor.apply();
//                fragment = new TestListFragment();
//                fragmentManager.beginTransaction()
//                        .addToBackStack(null)
//                        .replace(android.R.id.content, fragment,"searchFrag").commit();
//            }
//        });

//        searchByDeptBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                sharedPrefEditor.putString("department", deptStr);
//                sharedPrefEditor.apply();
//                fragment = new MyPatientFragment();
//                fragmentManager.beginTransaction()
//                        .addToBackStack(null)
//                        .replace(android.R.id.content, fragment,"searchFrag").commit();
//            }
//        });

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        navController = Navigation.findNavController(view);
        searchByIdBtn = view.findViewById(R.id.lushiSearchByIdBtn);
        searchByIdBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateInfo()) {
                    id = Integer.parseInt(idET.getText().toString());
                    patientViewModel.getSpecificPatient(id).observe(getActivity(), results -> {
                        if (results.size() != 0) {
                            sharedPrefEditor.putInt("patientId", id);
                            sharedPrefEditor.apply();
                            navController.navigate(R.id.testListFragment);
                        } else {
                            idET.requestFocus();
                            idET.setError(getString(R.string.patient_not_exist));
                        }
                    });
                }
            }
        });
        searchByDeptBtn = view.findViewById(R.id.lushiSearchByDeptBtn);
        searchByDeptBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedPrefEditor.putString("department", deptStr);
                sharedPrefEditor.apply();
                navController.navigate(R.id.allPatientFragment);
            }
        });
    }

    public boolean validateInfo() {
        boolean isValid = true;
        String numRegex = "^\\d*[1-9]\\d*$";
        idStr = idET.getText().toString();
        if (idStr.isEmpty() || !idStr.matches(numRegex)) {
            isValid = false;
            idET.requestFocus();
            idET.setError(getString(R.string.invalid_input));
        }
        return isValid;
    }
}