package lushi.cao.s301011302;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

import lushi.cao.s301011302.adapter.CaoPatientAdapter;
import lushi.cao.s301011302.main.SectionsPagerAdapter;
import lushi.cao.s301011302.model.Patient;

public class CaoActivityViewInformation extends AppCompatActivity {
    FloatingActionButton addTestFab;
    FloatingActionButton addPatientFab;
    FloatingActionButton mainFab;
    boolean isFABOpen;
    CaoPatientAdapter adapter;
    Patient patient;
    Button searchBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cao_view_information);
        SectionsPagerAdapter tabAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(tabAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
        addPatientFab = findViewById(R.id.lushiAddPatientFab);
        addTestFab = findViewById(R.id.lushiAddTestFab);
        mainFab = findViewById(R.id.lushiMainFab);
        mainFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isFABOpen) {
                    showFABMenu();
                } else {
                    closeFABMenu();
                }
            }
        });

        addTestFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CaoAddTest.class);
                startActivity(intent);
            }
        });

        addPatientFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CaoAddPatient.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the tools bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.lushiMenuSearchBtn:
                Intent intent = new Intent(getApplicationContext(), CaoActivitySearch.class);
                startActivity(intent);
                //Do Whatever you want to do here.
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void showFABMenu() {
        isFABOpen = true;
        mainFab.setAlpha(0.50f);
        addTestFab.animate().translationY(-getResources().getDimension(R.dimen.standard_55));
        addPatientFab.animate().translationY(-getResources().getDimension(R.dimen.standard_105));
    }

    private void closeFABMenu() {
        isFABOpen = false;
        mainFab.setAlpha(1f);
        addTestFab.animate().translationY(0);
        addPatientFab.animate().translationY(0);
    }
}