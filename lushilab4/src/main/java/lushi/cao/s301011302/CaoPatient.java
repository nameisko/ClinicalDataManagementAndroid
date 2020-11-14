package lushi.cao.s301011302;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;

import java.util.List;

import lushi.cao.s301011302.adapter.CaoPatientAdapter;
import lushi.cao.s301011302.main.SectionsPagerAdapter;
import lushi.cao.s301011302.model.Patient;
import lushi.cao.s301011302.viewmodel.PatientViewModel;

public class CaoPatient extends AppCompatActivity {
    private static final String ARG_SECTION_NUMBER = "section_number";
    CaoPatientAdapter adapter;
    RecyclerView recylcerView;
    PatientViewModel patientViewModel;
    SharedPreferences sharedPref;
    String deptartment;
    TabLayout tabs;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cao_patient);
        SectionsPagerAdapter tabAdapter = new SectionsPagerAdapter(getApplicationContext(), getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(tabAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
    }
}