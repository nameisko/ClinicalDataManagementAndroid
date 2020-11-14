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

    }
}