package lushi.cao.s301011302;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import lushi.cao.s301011302.fragment.LushiPatientFragment;
import lushi.cao.s301011302.fragment.LushiTestFragment;
import lushi.cao.s301011302.model.Test;

public class CaoActivitySearch extends AppCompatActivity {

    SharedPreferences sharedPref;
    SharedPreferences.Editor sharedPrefEditor;
    TestViewModel testViewModel;
    Button searchByIdBtn;
    Button searchByDeptBtn;
    EditText idET;
    Integer id;
    String id2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cao_search);
        sharedPref = getApplicationContext().getSharedPreferences("healthInfo", Context.MODE_PRIVATE);
        sharedPrefEditor = sharedPref.edit();
        testViewModel = ViewModelProviders.of(this).get(TestViewModel.class);

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
    }
}