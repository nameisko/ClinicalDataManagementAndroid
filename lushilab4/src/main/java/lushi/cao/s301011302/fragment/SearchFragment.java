package lushi.cao.s301011302.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import lushi.cao.s301011302.R;
import lushi.cao.s301011302.viewmodel.TestViewModel;

public class SearchFragment extends Fragment {

    NavController navController;
    SharedPreferences sharedPref;
    SharedPreferences.Editor sharedPrefEditor;
    TestViewModel testViewModel;
    Button searchByIdBtn;
    Button searchByDeptBtn;
    EditText idET;
    Integer id;
    Spinner spinner;
    String departments[];
    String deptStr;
    FragmentTransaction fragmentTransaction;
    FragmentManager fragmentManager;
    Fragment fragment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root =inflater.inflate(R.layout.activity_cao_search, container, false);
        Context context = getActivity().getApplicationContext();
        sharedPref = context.getSharedPreferences("healthInfo", Context.MODE_PRIVATE);
        sharedPrefEditor = sharedPref.edit();
        testViewModel = ViewModelProviders.of(this).get(TestViewModel.class);
        departments = getResources().getStringArray(R.array.departments);
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        spinner = root.findViewById(R.id.lushiSearchDeptSpinner);
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(
                context, android.R.layout.simple_spinner_item, departments);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                deptStr = parent.getItemAtPosition(position).toString();
                Toast.makeText(context,deptStr,Toast.LENGTH_SHORT).show();
                spinner.setSelection(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(parent.getContext(), "Selected: none" , Toast.LENGTH_LONG).show();
            }
        });

        searchByIdBtn = root.findViewById(R.id.lushiSearchByIdBtn);
        searchByIdBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                idET = root.findViewById(R.id.lushiSearchByIdET);
                id = Integer.parseInt(idET.getText().toString());
                sharedPrefEditor.putInt("patientId", id);
                sharedPrefEditor.apply();
                fragment = new TestListFragment();
                fragmentManager.beginTransaction()
                        .addToBackStack(null)
                        .replace(android.R.id.content, fragment,"searchFrag").commit();
//                Intent intent = new Intent(context, CaoTest.class);
//                startActivity(intent);
            }
        });

        searchByDeptBtn = root.findViewById(R.id.searchByDeptBtn);
        searchByDeptBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedPrefEditor.putString("department", deptStr);
                sharedPrefEditor.apply();
                fragment = new MyPatientFragment();
                fragmentManager.beginTransaction()
                        .addToBackStack(null)
                        .replace(android.R.id.content, fragment,"searchFrag").commit();
            }
        });
        return root;
    }


}