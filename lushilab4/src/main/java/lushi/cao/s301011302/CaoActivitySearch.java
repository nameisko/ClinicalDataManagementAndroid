package lushi.cao.s301011302;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
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

import java.util.List;

import lushi.cao.s301011302.fragment.LushiPatientFragment;
import lushi.cao.s301011302.fragment.LushiTestFragment;
import lushi.cao.s301011302.model.Test;
import lushi.cao.s301011302.viewmodel.TestViewModel;

public class CaoActivitySearch extends AppCompatActivity {

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cao_search);
        sharedPref = getApplicationContext().getSharedPreferences("healthInfo", Context.MODE_PRIVATE);
        sharedPrefEditor = sharedPref.edit();
        testViewModel = ViewModelProviders.of(this).get(TestViewModel.class);
        departments = getResources().getStringArray(R.array.departments);
        spinner = findViewById(R.id.lushiSearchDeptSpinner);
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, departments);
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

        searchByIdBtn = findViewById(R.id.lushiSearchByIdBtn);
        searchByIdBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                idET = findViewById(R.id.lushiSearchByIdET);
                id = Integer.parseInt(idET.getText().toString());
                sharedPrefEditor.putInt("patientId", id);
                sharedPrefEditor.apply();
                Intent intent = new Intent(getApplicationContext(), LushiTestFragment.class);
                startActivity(intent);
            }
        });

        searchByDeptBtn = findViewById(R.id.searchByDeptBtn);
        searchByDeptBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedPrefEditor.putString("department", deptStr);
                sharedPrefEditor.apply();
                Intent intent = new Intent(getApplicationContext(), LushiPatientFragment.class);
                startActivity(intent);
            }
        });
    }
}